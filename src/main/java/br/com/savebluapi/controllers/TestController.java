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
        String message = "Hello, world!";
        System.out.println(message);
        return ResponseEntity.ok(message);
    }
    
}
