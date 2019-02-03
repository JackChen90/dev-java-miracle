package indi.jackie.miracle.service.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jackie chen
 * @create 2019/1/18
 * @description DefaultConfigurationCenter
 */
public class DefaultConfigurationCenter implements IConfigurationCenter {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultConfigurationCenter.class);

    private static final String CONFIGURATION_ROOT_PATH = "/config";

    private Map<String, String> configurations = new HashMap<>(16);

    private CuratorFramework curatorClient;

    private ExecutorService pool = Executors.newFixedThreadPool(2);

    public DefaultConfigurationCenter(String connStr) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.curatorClient = CuratorFrameworkFactory.builder().connectString(connStr)
                .sessionTimeoutMs(30000)
                .connectionTimeoutMs(8000)
                .retryPolicy(retryPolicy)
                .build();
        //初始化配置中心
        initCuratorClient();
    }

    /**
     * 初始化配置 map;设置监听器
     */
    private void initCuratorClient() {
        try {
            curatorClient.start();
            //若 base 路径不存在,新建 base 路径
            Stat stat = curatorClient.checkExists().forPath(CONFIGURATION_ROOT_PATH);
            if (stat == null) {
                curatorClient.create().withMode(CreateMode.PERSISTENT).forPath(CONFIGURATION_ROOT_PATH);
            }
            //设置监听器
//            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorClient, CONFIGURATION_ROOT_PATH, true);
//            pathChildrenCache.getListenable().addListener(new DefaultListener(this.configurations), pool);
//            pathChildrenCache.start();

            TreeCache treeCache = new TreeCache(curatorClient, CONFIGURATION_ROOT_PATH);
            treeCache.getListenable().addListener(new DefaultTreeCacheListener(this.configurations), pool);
            treeCache.start();
            //初始化属性 map
            initConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化属性map
     */
    private void initConfiguration() throws Exception {
        Map<String, String> configs = getConfigurationFromRemote();
        configs.forEach((key, value) -> {
            this.configurations.put(key, value);
        });
        logger.info("init configurations value: {}", configurations);
    }

    /**
     * 获取配置
     *
     * @param key key标识
     * @return
     * @throws Exception
     */
    public String getConfiguration(String key) throws Exception {
        if (configurations.containsKey(key)) {
            return configurations.get(key);
        } else {
            logger.info("get configuration fail, item {} not be found", key);
            return null;
        }
    }

    /**
     * 路径拼接方法
     *
     * @param key 子路径
     * @return
     */
    private String concatKey(String key) {
        return CONFIGURATION_ROOT_PATH.concat("/").concat(key);
    }


    @Override
    public void addConfiguration(String key, String value) throws Exception {
        if (!checkChildPathExists(key)) {
            curatorClient.create().withMode(CreateMode.PERSISTENT).forPath(concatKey(key), value.getBytes("UTF-8"));
        }
    }

    @Override
    public void deleteConfiguration(String key) throws Exception {
        if (checkChildPathExists(key)) {
            curatorClient.delete().forPath(concatKey(key));
        } else {
            logger.warn("child path {} not be found", key);
        }
    }

    @Override
    public void updateConfiguration(String key, String value) throws Exception {
        if (checkChildPathExists(key)) {
            curatorClient.setData().forPath(concatKey(key), value.getBytes("UTF-8"));
        }
    }

    @Override
    public Map<String, String> getConfigurationFromRemote() throws Exception {
        Map<String, String> configs = new HashMap<>();
        try {
            List<String> childPaths = curatorClient.getChildren().forPath(CONFIGURATION_ROOT_PATH);
            if (!CollectionUtils.isEmpty(childPaths)) {
                String key;
                String data;
                for (String childPath : childPaths) {
                    key = childPath.substring(childPath.indexOf("/") + 1);
                    data = new String(curatorClient.getData().forPath(concatKey(key)), "UTF-8");
                    configs.put(key, data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configs;
    }

    /**
     * zk 中路径是否存在
     *
     * @param key 子路径
     * @return
     */
    private boolean checkChildPathExists(String key) {
        boolean flag = false;
        try {
            flag = curatorClient.checkExists().forPath(concatKey(key)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Map<String, String> getConfigurations() {
        return configurations;
    }
}
