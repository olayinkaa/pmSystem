package com.pmApplication.API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	   @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	        .apiInfo(getApiInfo())
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.pmApplication.API"))              
	          .paths(PathSelectors.ant("/api/**"))                          
	          .build();                                           
	    }

	   
//	   swagger Metadata: http://localhost:8080/v2/api-docs
//	   Swagger UI URL :http://localhost:8080/swagger-ui.html
	   
	   
	   private ApiInfo getApiInfo()
	   {
		   return new ApiInfoBuilder()
				   .title("Project Management System API")
				   .description("Lists of all API for PMSytem")
				   .version("2.1")
				   .contact(new Contact("Ibrahim Olayinka","http://ibrajames","ibrahimolayinkaa@gmail.com"))
				   .license("License 2.1")
				   .build();
   }
	   
   
	   
	   
	
}
