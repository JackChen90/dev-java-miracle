package indi.jackie.miracle.spring.bpp;

/**
 * @author jackie chen
 * @create 2019/1/6
 * @description TestMessage
 */
public class TestMessage {

    public TestMessage(){
        System.out.println("testMessage construct method");
    }

    private String name;

    private Integer age;

    public void init(){
        System.out.println("testMessage init");
    }

    @Override
    public String toString() {
        return "TestMessage{" +
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
