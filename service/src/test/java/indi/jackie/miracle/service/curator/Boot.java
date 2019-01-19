package indi.jackie.miracle.service.curator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author jackie chen
 * @create 2019/1/18
 * @description Boot
 */
public class Boot {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Boot.class);

    @Test
    public void test01() throws Exception {
        String connStr = "47.110.34.237:2181";
        DefaultConfigurationCenter configurationCenter = new DefaultConfigurationCenter(connStr);

        Map<String, String> all = configurationCenter.getConfigurations();

        all.forEach((key, value) ->
                logger.info("key: [{}] value: [{}]", key, value));

        configurationCenter.addConfiguration("miracle", "bangbangbang");
        configurationCenter.addConfiguration("class1", "class1");
        configurationCenter.addConfiguration("class2", "class2");
        configurationCenter.addConfiguration("testnode", "yeahyeahyeah");

        logger.info("-----------------------------------------------------");

        Thread.sleep(3000);
        all = configurationCenter.getConfigurations();
        all.forEach((key, value) ->
                logger.info("key: [{}] value: [{}]", key, value));

        logger.info("-----------------------------------------------------");

        configurationCenter.updateConfiguration("miracle", "lueluelue");

        Thread.sleep(3000);
        all = configurationCenter.getConfigurations();
        all.forEach((key, value) ->
                logger.info("key: [{}] value: [{}]", key, value));

        logger.info(configurationCenter.getConfiguration("miracle"));


        configurationCenter.deleteConfiguration("miracle");
        configurationCenter.deleteConfiguration("class1");
        configurationCenter.deleteConfiguration("class2");
        configurationCenter.deleteConfiguration("testnode");
    }
}
