package indi.jackie.miracle.service.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author jackie chen
 * @create 2019/1/19
 * @description DefaultListener
 */
public class DefaultListener implements PathChildrenCacheListener {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultListener.class);

    private volatile Map<String, String> configurations;

    public DefaultListener(Map<String, String> configurations) {
        this.configurations = configurations;
    }

    @Override
    public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        String eventType = event.getType().name();
        String path = event.getData().getPath();
        String key = path.substring(path.lastIndexOf("/") + 1);
        String data = null != event.getData() ? new String(event.getData().getData(), "UTF-8") : "";
        logger.debug(String.format("eventType:%s,path:%s,data:%s", eventType, path, data));
        switch (event.getType()) {
            case CHILD_ADDED:
            case CHILD_UPDATED:
                reloadSingleConfigurationMapEntity(key, data);
                break;
            case CHILD_REMOVED:
                removeSingleConfigurationMapEntity(key);
                break;
        }
    }


    private void removeSingleConfigurationMapEntity(String key) {
        configurations.remove(key);
    }

    private void reloadSingleConfigurationMapEntity(String key, String data) {
        logger.info("reload key:{} value:{}", key, data);
        configurations.put(key, data);
    }
}
