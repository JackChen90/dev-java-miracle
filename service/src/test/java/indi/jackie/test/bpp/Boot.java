package indi.jackie.test.bpp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Boot {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Boot.class);

    public static void main(final String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("scripting/beans.xml");
        logger.info("start get bean from container");
        TestMessage2 messenger = (TestMessage2) ctx.getBean("testMessage2");
        System.out.println(messenger);
    }

}