package br.com.savebluapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/incidence")
public class IncidenteController {
    
    @GetMapping(value = "/position/{coordinates}")
    public ResponseEntity<Object> getNearbyIncidentsFromPosition(@PathVariable String coordinates){
        /*
         * TODO: retornar uma lista de incidentes próximos a coordenada informada
         * A regra de negócio que define quais incidentes são exibidos por tipo de usuário não foi definida
         */

        return null;
    }

    @GetMapping(value = "/create")
    public void createNewIncidence(){
        /*
         * TODO: deve gravar um novo incidente
         * Se o campo Incidente.getUrgent() == true quer dizer que é um S.O.S.
         * caso contrário é apenas uma denúncia 
         */

    }

    @GetMapping(value = "/incidence/bycategory")
    public void getIncidencesByCategory(){
        /**
         * TODO: retorna todos os incidentes da categoria informada
         * A regra de negócio de quem poderá rconsumir esse endpoint não foi definida
         */
    }

    @GetMapping(value = "/incidence/byusertype")
    public void getIncidencesByUserType(){
        /**
         * TODO: retorna todos os incidentes que um usuário pode visualizar independente de posição
         * A regra de negócio de quem poderá rconsumir esse endpoint não foi definida
         */
    }
}
