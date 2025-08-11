package br.com.Isabela01vSilva.schedulo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder()
                .group("Schedule")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(
                new Info()
                        .title("API - Projeto Schedule - Isabela")
                        .description("Serviço responsável pelo controle e gestão de agendamentos. Inclui endpoints para criação, consulta e cancelamento")
                        .version("v0.0.1")
        );
    }
}
