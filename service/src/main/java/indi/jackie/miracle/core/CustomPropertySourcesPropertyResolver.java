package indi.jackie.miracle.core;

import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

/**
 * @author jackie chen
 * @create 2019/3/27
 * @description CustomPropertySourcesPropertyResolver
 */
public class CustomPropertySourcesPropertyResolver extends PropertySourcesPropertyResolver {


    public CustomPropertySourcesPropertyResolver(PropertySources propertySources) {
        super(propertySources);
    }
}
