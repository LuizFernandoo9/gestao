package com.fernando.gestao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi(){
        
        return new OpenAPI()
        .info(new Info().title("gestao de vagas")
        .description("API para gestão de vagas")
        .version("1"))
        .schemaRequirement("jwt_auth", creteSecurityScheme());
    }

    private SecurityScheme creteSecurityScheme(){
        return new SecurityScheme().name("jwt_auth").scheme("bearer").bearerFormat("JWT").type(SecurityScheme.Type.HTTP).in(SecurityScheme.In.HEADER);    
    }
    
}
