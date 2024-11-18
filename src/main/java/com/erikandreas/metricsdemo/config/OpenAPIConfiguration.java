package com.erikandreas.metricsdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("My SERVER description");

        Contact myContact = new Contact();
        myContact.setName("Erik Andreas");
        myContact.setEmail("userx@email.com");

        Info information = new Info()
                .title("My API title")
                .version("1.0")
                .description("My API description")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
