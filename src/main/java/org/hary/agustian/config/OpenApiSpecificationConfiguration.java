package org.hary.agustian.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Hary Agustian",
                        email = "tech.hary.agustian@gmail.com",
                        url = "https://www.haryagustian.github.io/"
                ),
                description = "OpenApi documentation for Spring Security",
                title = "OpenApi specification - Hary Agustian",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://www.haryagustian.github.io/"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
)
@SecurityScheme(
        name = "Bearer Auth",
        description = "JWT Auth Description",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Json Web Token",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiSpecificationConfiguration {
}
