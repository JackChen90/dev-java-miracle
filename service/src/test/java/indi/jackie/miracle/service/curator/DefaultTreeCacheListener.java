package indi.jackie.miracle.service.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type.INITIALIZED;

public class DefaultTreeCacheListener implements TreeCacheListener {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultTreeCacheListener.class);

    private volatile Map<String, String> configurations;

    public DefaultTreeCacheListener(Map<String, String> configurations) {
        this.configurations = configurations;
    }

    @Override
    public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
        if (INITIALIZED.equals(event.getType())) {
            return;
        }
        String eventType = event.getType().name();
        String path = event.getData().getPath();
        String data = event.getData() == null ? "" : new String(event.getData().getData(), "UTF-8");
        logger.debug(String.format("eventType:%s,path:%s,data:%s", eventType, path, data));
        String key = path.substring(path.lastIndexOf("/") + 1);
        switch (event.getType()) {
            case NODE_ADDED:
            case NODE_UPDATED:
                reloadSingleConfigurationMapEntity(key, data);
                break;
            case NODE_REMOVED:
                removeSingleConfigurationMapEntity(key);
        }
    }

    private void reloadSingleConfigurationMapEntity(String key, String data) {
        logger.info("reload key:{} value:{}", key, data);
        configurations.put(key, data);
    }

    private void removeSingleConfigurationMapEntity(String key) {
        configurations.remove(key);
    }
}