// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .components(new Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new SecurityScheme()
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                         )
//                 )
//                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                 .servers(List.of(
//                         new Server().url("https://9143.32procr.amypo.ai/")
//                 ));
//     }
// }

// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("Token Management API")
//                         .version("1.0")
//                         .description("API for managing tokens and queues"));
//     }
// }
// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//         return http.build();
//     }
// }
// package com.example.demo.config;
// package com.example.demo.config;

// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class OpenApiConfig {
// }
// package com.example.demo.config;

// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class OpenApiConfig {
// }


// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class OpenApiConfig {
    
//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("Token Management API")
//                         .version("1.0")
//                         .description("API for managing tokens and queues"));
//     }
// }

// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("Supplier Diversity Tracker")
//                         .version("1.0")
//                         .description("API documentation for AmyPO test cases")
//                 )
//                 .servers(List.of(
//                         new Server().url("https://9126.32procr.amypo.ai/")
//                 ));
//     }
// }


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
            public OpenAPI customOpenAPI() {
                    return new OpenAPI()
                                    .info(new Info()
                                                            .title("Demo API")
                                                                                    .version("1.0")
                                                                                    .description("Demo Spring Boot API"))
                                                                                                                            .servers(List.of(
                                                                                                                                                    new Server().url("https://9067.408procr.amypo.ai/")
                                                                                                                                                                    ));
                                                                                                                                                                        }
                                                                                                                                                                        }
