package org.profit.avia.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 *  конфигурация сваггера
 *  localhost:8080/swagger-ui/index.html
 */
@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Authorization",
        scheme = "Bearer",
        in= SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(@Value("${application-description}")String appDescription,
                                 @Value("${application-version}")String appVersion) {
        return new OpenAPI().info(new Info().title("Application API")
                        .version(appVersion)
                        .description(appDescription)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("bna").email("n_bryklia@bk.ru")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(
                        new Components()
                                .addSecuritySchemes("Bearer Authentication",
                                        new io.swagger.v3.oas.models.security.SecurityScheme()
                                                .name("Bearer Authentication")
                                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .servers(List.of(new Server().url("http://localhost:8080")
                        .description("Dev service")));

    }
}