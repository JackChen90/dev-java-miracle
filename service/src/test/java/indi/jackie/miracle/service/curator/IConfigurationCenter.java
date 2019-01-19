package indi.jackie.miracle.service.curator;

import java.util.Map;

public interface IConfigurationCenter {

    /**
     * 添加配置
     *
     * @param key
     * @param value
     * @throws Exception e
     */
    void addConfiguration(String key, String value) throws Exception;

    /**
     * 移除配置
     *
     * @param key
     * @throws Exception e
     */
    void deleteConfiguration(String key) throws Exception;

    /**
     * 更新配置
     *
     * @param key
     * @throws Exception e
     */
    void updateConfiguration(String key, String value) throws Exception;

    /**
     * 获取所有配置
     *
     * @throws Exception e
     */
    Map<String, String> getConfigurationFromRemote() throws Exception;
}