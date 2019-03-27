package indi.jackie.miracle.core;

import org.springframework.util.StringValueResolver;

/**
 * @author jackie chen
 * @create 2019/3/27
 * @description CustomStringValueResolver
 */
public class CustomStringValueResolver implements StringValueResolver {

    private CustomPropertySourcesPropertyResolver propertySourcesPropertyResolver;

    public CustomStringValueResolver(CustomPropertySourcesPropertyResolver propertySourcesPropertyResolver) {
        this.propertySourcesPropertyResolver = propertySourcesPropertyResolver;
    }

    @Override
    public String resolveStringValue(String strVal) {
        return null;
    }
}
