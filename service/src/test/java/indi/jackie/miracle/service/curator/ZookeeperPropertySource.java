package indi.jackie.miracle.service.curator;

import org.springframework.core.env.EnumerablePropertySource;

/**
 * @author jackie chen
 * @create 2019/1/18
 * @description ZookeeperPropertySource
 */
public class ZookeeperPropertySource extends EnumerablePropertySource {
    public ZookeeperPropertySource(String name, Object source) {
        super(name, source);
    }

    protected ZookeeperPropertySource(String name) {
        super(name);
    }

    @Override
    public String[] getPropertyNames() {
        return new String[0];
    }

    @Override
    public Object getProperty(String s) {
        return null;
    }
}
