package indi.jackie.miracle.service.curator;

import org.junit.Test;

/**
 * @author jackie chen
 * @create 2019/1/18
 * @description Boot
 */
public class Boot {

    @Test
    public void test01() {
        String connStr = "47.110.34.237:2181";
        DefaultConfigurationCenter configurationCenter = new DefaultConfigurationCenter(connStr);
    }
}
