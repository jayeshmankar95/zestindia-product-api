package com.zestindia.productapi.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI productAPI() {

		final String securitySchemeName = "BearerAuth";

		return new OpenAPI()
				.info(new Info().title("Product API").version("1.0")
						.description("Product Management API with JWT Security"))

				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

				.components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
						.name("Authorization").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
	
}