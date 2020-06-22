package com.yswuyu.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //声明这是一个注解类
@EnableSwagger2

public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("swaggerGroup")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.zto.toolbest"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Hankin",
                "https://www.Hankin.com/Hankin-admin/",
                "807741830@qq.com");
        return new ApiInfoBuilder()
                .title("项目API接口")
                .description("接口描述")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}