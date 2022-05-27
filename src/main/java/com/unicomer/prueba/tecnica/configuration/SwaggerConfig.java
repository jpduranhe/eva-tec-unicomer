package com.unicomer.prueba.tecnica.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi()
public class SwaggerConfig {
    @Bean
    public Docket api() {
        
        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.unicomer.prueba.tecnica"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api Salesman")
                .description("Api ")
                .version("1.0.0")
                .contact(new Contact("Juan Pablo Duran", "https://www.linkedin.com/in/jpduranhe", "jpduranhe@gmail.cl"))
                .build();
    }



}
