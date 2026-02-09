package com.ems.apigateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

   /* @Bean
    public OpenAPI gatewayOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("EMS API Gateway")
                        .description("All APIs are accessed via API Gateway")
                        .version("1.0"));
        // ❌ REMOVE hardcoded server URL
    }*/
}
