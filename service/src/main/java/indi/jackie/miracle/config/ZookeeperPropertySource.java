package indi.jackie.miracle.config;

import com.google.common.collect.Maps;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;

import java.util.Map;

/**
 * @author jackie chen
 * @create 2019/3/27
 * @description ZookeeperPropertySource
 */
public class ZookeeperPropertySource extends PropertySource {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperPropertySource.class);

    /**
     * 存储 zk 中配置的属性
     */
    private Map<String, String> properties = Maps.newHashMap();

    public ZookeeperPropertySource(String contextPath, CuratorFramework zkClient) {
        super(contextPath, zkClient);

        findProperties(contextPath);
    }

    private void findProperties(String contextPath) {

    }

    @Override
    public Object getProperty(String name) {
        return this.properties.get(name);
    }
}
