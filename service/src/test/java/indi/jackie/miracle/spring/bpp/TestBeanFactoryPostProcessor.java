package indi.jackie.miracle.spring.bpp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author jackie chen
 * @create 2019/1/17
 * @description TestBeanFactoryPostProcessor
 */
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TestBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        logger.info("TestBeanFactoryPostPorcessor->postProcessBeanFactory");
    }
}
