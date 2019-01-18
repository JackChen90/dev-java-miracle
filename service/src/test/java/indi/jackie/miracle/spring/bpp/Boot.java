package indi.jackie.miracle.spring.bpp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Boot {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Boot.class);


    @Test
    public void test01() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("scripting/beans.xml");
        logger.info("start get bean from container");
        TestMessage2 messenger = (TestMessage2) ctx.getBean("testMessage2");
        System.out.println(messenger);
    }

}