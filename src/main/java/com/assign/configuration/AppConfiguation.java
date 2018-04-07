package com.assign.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Default WebMvcAutoConfiguration applying
 * @author kimilb
 *
 */
@Configuration
public class AppConfiguation extends WebMvcConfigurerAdapter {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {  
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/" 
          };
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        }
    }
}
