package peaksoft.restcrudlms.swaggerConfig;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKeySecurityScheme()))
                .info(new Info().title("CRUD LMS FROM ALMAZ").description("Written by: Almaz Farhatovich"))
                .security(List.of(new SecurityRequirement().addList("Bearer Token")));
    }

    private SecurityScheme apiKeySecurityScheme() {
        return new SecurityScheme()
                .name("Authorization")
                .description("put your jwt token here!")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }
}
