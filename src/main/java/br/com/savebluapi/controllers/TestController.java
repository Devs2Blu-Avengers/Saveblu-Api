package br.com.savebluapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Teste", description = "Endpoint de Teste e acesso a documentação do Swagger") // customizando UI do Swagger
@RestController
@RequestMapping(value = "/")
public class TestController {

    @Operation(description = "Teste e acesso a documentação do Swagger", method = "GET")
    @GetMapping
    public ResponseEntity<Object> helloWorld(){
        String message = "Saveblu online!<br><br>Acesse a documentação da Api com o Swagger: <a href='http://localhost:8080/swagger-ui/index.html' target='_blank'>clicando aqui</a>";
        return ResponseEntity.ok(message);
    }
    
}
