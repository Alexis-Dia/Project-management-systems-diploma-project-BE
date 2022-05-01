package com.projectManagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.TypeResolver;
import com.projectManagement.dto.EmptyDto;

/* Swagger URL: http://localhost:8080/swagger-ui/#/project/ */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

  public static final String BASE_CONTROLLER_PACKAGE = "com.projectManagement.rest.controller";
  public static final String ADDRESS_APPROVAL_SERVICE_ROUTE_REST_API_TITLE = "Project management REST API";
  public static final String ADDRESS_APPROVAL_SERVICE_GENERATOR_REST_API_DESCRIPTION =
    "\"Project management REST API\"";

  @Autowired
  private TypeResolver typeResolver;

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage(BASE_CONTROLLER_PACKAGE))
      .paths(PathSelectors.any())
      .build()
      .additionalModels(typeResolver.resolve(ResponseEntity.class))
      .additionalModels(typeResolver.resolve(EmptyDto.class))
      .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
      .title(ADDRESS_APPROVAL_SERVICE_ROUTE_REST_API_TITLE)
      .description(ADDRESS_APPROVAL_SERVICE_GENERATOR_REST_API_DESCRIPTION)
      .build();
  }
}
