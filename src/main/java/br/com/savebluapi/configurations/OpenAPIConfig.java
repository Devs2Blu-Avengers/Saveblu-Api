package br.com.savebluapi.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Value("http://localhost:8080")
  private String devUrl;

  @Value("http://52.23.70.87:8080")
  private String prodUrl;
  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Servidor local de Desenvolvimento");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Servidor de Produção");

    // Contact contact = new Contact();
    // contact.setEmail("endereco@gmail.com");
    // contact.setName("Nome do Contato");
    // contact.setUrl("#");

    // License mitLicense = new License().name("MIT License").url("#");

    Info info = new Info()
        .title("Saveblu API")
        .version("1.0")
        // .contact(contact)
        .description("Os endpoints e requisições abaixo são atendidos por essa API.");
        // .termsOfService("#")
        // .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}