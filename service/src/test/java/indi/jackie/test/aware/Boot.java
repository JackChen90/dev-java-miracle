package indi.jackie.test.aware;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jackie chen
 * @create 2019/1/13
 * @description Boot
 */
public class Boot {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Boot.class);

    @Test
    public void test01() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("scripting/beans.xml");
        Hello hello = (Hello) ctx.getBean("hello");
        logger.info(hello.getBeanName());
        logger.info(hello.getBeanFactory().toString());
        logger.info(hello.getApplicationContext().toString());
    }
}
