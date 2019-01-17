package indi.jackie.miracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jackie chen
 * @create 2018/12/14
 * @description MiracleApplication
 */
@SpringBootApplication
public class MiracleApplication {
    /**
     * Main 方法 springboot 入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MiracleApplication.class);
        app.run(args);
    }
}
