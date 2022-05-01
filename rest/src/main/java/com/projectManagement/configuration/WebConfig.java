package com.projectManagement.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private static final String CLASSPATH_META_INF_RESOURCES = "classpath:/META-INF/resources/";
  private static final String CLASSPATH_META_INF_RESOURCES_WEBJARS = "classpath:/META-INF/resources/webjars/";
  private static final String SWAGGER_UI_HTML = "swagger-ui.html";
  private static final String WEBJARS = "/webjars/**";

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(SWAGGER_UI_HTML).addResourceLocations(CLASSPATH_META_INF_RESOURCES);
    registry.addResourceHandler(WEBJARS).addResourceLocations(CLASSPATH_META_INF_RESOURCES_WEBJARS);
  }
}
