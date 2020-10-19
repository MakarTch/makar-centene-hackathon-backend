package com.centene.hackathon.application.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.centene.hackathon.application"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Health Care System API",
				"API for Centene Hackathon",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Makar Tchekalenkov", 
						"https://www.linkedin.com/in/makar-tchekalenkov/", "makar.tchekalenkov@gmail.com"),
				"API License",
				"https://www.linkedin.com/in/makar-tchekalenkov/",
				Collections.emptyList());
	}
}
