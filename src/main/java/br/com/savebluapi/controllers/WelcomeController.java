package br.com.savebluapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class WelcomeController {

    @Value("${app.url}")
    private String url;

    @GetMapping
    public ResponseEntity<Object> welcome(){
        String message = "Saveblu online!<br><br>Acesse a documentação da Api com o Swagger: <a href='" + url + "swagger-ui/index.html' target='_blank'>clicando aqui</a>";
        return ResponseEntity.ok(message);
    }
    
}
