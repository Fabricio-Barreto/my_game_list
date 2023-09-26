package com.plancto.mygamelist.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API My game list")
                        .version("v1")
                        .description("API to my personal project.")
                        .termsOfService("https://www.linkedin.com/in/fabricio-barreto-nogueira-876776208/")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.linkedin.com/in/fabricio-barreto-nogueira-876776208/")));
    }
}
