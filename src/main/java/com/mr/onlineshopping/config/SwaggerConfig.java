package com.mr.onlineshopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	private String schemeName = "bearerAuth";
	private String bearerFormat = "jwt";
	private String scheme = "bearer";
	
	@Bean
	public OpenAPI config() { // Da' warning su public perchè mettendo bean già di base lo rende pubblico, lo si lascia per leggibilità
		
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(schemeName))
				.components(new Components().addSecuritySchemes(schemeName, new SecurityScheme()
																			 .name(schemeName)
																			 .type(SecurityScheme.Type.HTTP)
																			 .bearerFormat(bearerFormat)
																			 .in(SecurityScheme.In.HEADER)
																			 .scheme(scheme)))
				.info(new Info()
						.title("ISCS OnlineShopping Swagger")
						.description("Mostra le API di onlineshopping")
						.version("1.0.0"));
	}
		
}
