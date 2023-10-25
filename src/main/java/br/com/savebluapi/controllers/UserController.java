package br.com.savebluapi.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Usuário", description = "Endpoints relacionados ao Usuário") // customizando UI do Swagger
@RestController
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    UserService service;

    @Operation(description = "Lista todos os Usuários", method = "GET")// customizando UI do Swagger
    @GetMapping
    public List<UserDTO> listAll() {
        return service.listAll();
    }

    @Operation(description = "Procura um Usuário pel ID", method = "GET")// customizando UI do Swagger
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            UserDTO outputDTO = service.findById(id);
            return ResponseEntity.ok(outputDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Cria um novo Usuário", method = "POST")// customizando UI do Swagger
    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@Valid @RequestBody UserDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.createUser(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(description = "Exclui um Usuário", method = "DELETE")// customizando UI do Swagger
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.deleteUserById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Atualiza um Usuário", method = "PUT")// customizando UI do Swagger
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody UserDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.updateUser(id, newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
