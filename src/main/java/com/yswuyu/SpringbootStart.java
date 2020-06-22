package com.yswuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * 项目启动方法
 * @author Hankin
 *
 */
@EnableConfigurationProperties
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStart.class, args);
    }
}
