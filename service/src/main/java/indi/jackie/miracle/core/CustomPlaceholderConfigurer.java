package indi.jackie.miracle.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.StringValueResolver;

/**
 * @author jackie chen
 * @create 2019/3/27
 * @description CustomPlaceholderConfigurer
 */
public class CustomPlaceholderConfigurer implements BeanFactoryPostProcessor, PriorityOrdered {

    private MutablePropertySources propertySources;

    /**
     * 处理属性
     *
     * @param beanFactoryToProcess
     * @param propertySourcesPropertyResolver
     * @throws BeansException
     */
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     CustomPropertySourcesPropertyResolver propertySourcesPropertyResolver) throws BeansException {
        //自定义 StringValueResolver
        StringValueResolver valueResolver = new CustomStringValueResolver(propertySourcesPropertyResolver);
        doProcessProperties(beanFactoryToProcess, valueResolver);
    }

    private void doProcessProperties(ConfigurableListableBeanFactory beanFactoryToProcess, StringValueResolver valueResolver) {
        //todo 具体处理属性
    }

    /**
     * 后置处理器
     * <p>
     * 1.初始化properties
     * </p>
     *
     * @param beanFactory bean工厂(spring容器)
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //todo 初始化properties

        //自定义 PropertyResolver
        CustomPropertySourcesPropertyResolver propertySourcesPropertyResolver = new CustomPropertySourcesPropertyResolver(propertySources);
        processProperties(beanFactory, propertySourcesPropertyResolver);
    }

    @Override
    public int getOrder() {
        //定义最低优先级
        return Ordered.LOWEST_PRECEDENCE;
    }
}
