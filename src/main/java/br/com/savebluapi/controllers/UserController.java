package br.com.savebluapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.savebluapi.models.dtos.UserDTO;
import br.com.savebluapi.services.UserService;
import jakarta.validation.Valid;

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

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@Valid @RequestBody UserDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.createUser(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.deleteUserById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody UserDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.updateUser(id, newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
