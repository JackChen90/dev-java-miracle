package indi.jackie.test.bpp;

/**
 * @author jackie chen
 * @create 2019/1/13
 * @description TestMessage2
 */
public class TestMessage2 {

    private String name;

    private Integer age;

    public void init(){
        System.out.println("testMessage2 init");
    }

    @Override
    public String toString() {
        return "TestMessage2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
