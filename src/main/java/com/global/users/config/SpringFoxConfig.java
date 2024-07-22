package com.global.users.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.global.users"))
	      .paths(PathSelectors.any())
	      .build()
	      .apiInfo(apiInfo())
	      .securitySchemes(Collections.singletonList(apiKey()))
          .securityContexts(Collections.singletonList(securityContext()));
	}

	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "USER API GL", 
	      "Create User GL", 
	      "v1.0.0", 
	      "Terms of service", 
	      new Contact("Gustavo Figuera", "www.example.com", "figueragustavo@gmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
	
	private SecurityScheme apiKey() {
        return new springfox.documentation.service.ApiKey("Bearer", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
    }


	
}
