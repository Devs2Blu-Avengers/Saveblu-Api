package br.com.savebluapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/incidente")
public class IncidenteController {
    
    @GetMapping(value = "/position/{coordinates}")
    public ResponseEntity<Object> getNearbyIncidentsFromPosition(@PathVariable String coordinates){
        /*
         * TODO: retornar uma lista de incidentes próximos a coordenada informada
         */

        return null;
    }
}
