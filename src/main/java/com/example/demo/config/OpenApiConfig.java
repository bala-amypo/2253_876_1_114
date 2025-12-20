package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI digitalQueueAPI() {

        Server httpsServer = new Server();
        httpsServer.setUrl("https://9023.pro604cr.amypo.ai");

        Server httpServer = new Server();
        httpServer.setUrl("http://9023.pro604cr.amypo.ai");

        return new OpenAPI()
                .servers(List.of(httpsServer, httpServer))
                .info(new Info()
                        .title("Digital Queue API")
                        .version("1.0")
                        .description("Digital Queue Management System API"));
    }
}
