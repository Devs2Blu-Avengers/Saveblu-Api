package br.com.savebluapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TestController {

    @GetMapping
    public ResponseEntity<Object> helloWorld(){
        String message = "Saveblu online!<br><br>Acesse a documentação da Api com o Swagger: <a href='http://localhost:8080/swagger-ui/index.html' target='_blank'>clicando aqui</a>";
        return ResponseEntity.ok(message);
    }
    
}
