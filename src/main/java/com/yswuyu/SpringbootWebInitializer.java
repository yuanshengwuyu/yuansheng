package com.yswuyu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
* @ClassName: SpringbootWebInitializer
* @Description: web容器中进行部署
* @author Hankin
* @date 2020年8月18日
*
*/
public class SpringbootWebInitializer  extends SpringBootServletInitializer{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(SpringbootStart.class);
    }
}
