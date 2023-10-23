package br.com.savebluapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.savebluapi.models.dtos.UserDTO;
import br.com.savebluapi.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    UserService service;

    @GetMapping
    public List<UserDTO> listAll() {
        return service.listAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            UserDTO outputDTO = service.findById(id);
            return ResponseEntity.ok(outputDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
