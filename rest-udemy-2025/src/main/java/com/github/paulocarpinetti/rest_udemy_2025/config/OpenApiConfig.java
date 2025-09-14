package com.github.paulocarpinetti.rest_udemy_2025.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful api's with java")
                        .version("V1")
                        .description("RESTful api's with java")
                        .termsOfService("https://github.com/paulocarpinetti")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/paulocarpinetti"))
        );
    }
}
