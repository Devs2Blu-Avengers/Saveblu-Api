package br.com.savebluapi.controllers;

import br.com.savebluapi.enums.Category;
import br.com.savebluapi.models.User;
import br.com.savebluapi.models.dtos.IncidenceDTO;
import br.com.savebluapi.services.IncidenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Incidência", description = "Endpoints relacionados a Incidência") // customizando UI do Swagger
@Server(url = "http://localhost:8080", description = "Servidor local de desenvolvimento")
@Server(url = "http://52.23.70.87:8080/", description = "Servidor de produção")
@RestController
@RequestMapping(value = "/incidence")
public class IncidenteController {

    @Autowired
    IncidenceService incidenceService;

    @Operation(description = "Retornar uma lista de incidentes próximos a posição do Usuário", method = "GET")// customizando UI do Swagger
    @GetMapping
    public ResponseEntity<Object> getUserNearIncidents(@RequestBody User user, @RequestParam(required = false) Category category) throws Exception {
        /*
         * TODO: retornar uma lista de incidentes próximos as coordenadas do usuário informado
         *
         *
         * RECEBE:
         * {
         *      user: ObjectJSON,           (obrigatória) Pois irá pegar as coordenadas do device do usuário
         *      categories: Array<int>      (opcional) Se passar a categoria filtra por categoria
         * }
         *
         * RETORNA:
         * [
         *      {ObjectJson1<incidence>},
         *      {ObjectJson2<incidence>},
         *      {ObjectJson3<incidence>}
         * ]
         *
         * A regra de negócio que define quais incidentes são exibidos por tipo de usuário não foi definida
         *
         * Não retornar todas as informações para usuário do tipo CIDADAO
         */

        // Verificar se o Usuário tem um device cadastrado
        if (user.getDevices() == null) {
            throw new Exception("Usuário sem um device cadastrado!");
        }

        try {
            return ResponseEntity.ok(incidenceService.getUserNearIncidents(user, category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(description = "Retornar uma lista de incidentes próximos a posição informada", method = "GET")// customizando UI do Swagger
    @GetMapping(value = "/byposition")
    public ResponseEntity<Object> getNearIncidentsByPositionRadius(@RequestBody @Nullable User user, @RequestParam @Nullable Category category
            , @RequestParam(required = false) Double latitude, @RequestParam(required = false) Double longitude, @RequestParam(required = false) Double radiusInMeters) throws  Exception {
        /*
         * TODO: retornar uma lista de incidentes próximos a posição informada
         *
         * A regra de negócio que define quais incidentes são exibidos por tipo de usuário não foi definida
         *
         * RECEBE:
         * {
         *      user: ObjectJSON,           (opcional) Se não for informado é um usuário anônimo/cidadão
         *      position: ObjectJSON,       (obrigatória) Para limitar a um raio específico da posição do usuário (110 ? 200 ? 330 ?)
         *      categories: Array<int>      (opcional) Se passar a categoria filtra por categoria
         * }
         *
         * RETORNA:
         * [
         *      {ObjectJson<incidence1>},
         *      {ObjectJson<incidence2>},
         *      {ObjectJson<incidence3>}
         * ]
         *
         */
        if (latitude == null || longitude == null || radiusInMeters == null) {
            throw  new Exception("Coordenadas(latitude e longitude) e raio não podem ser nulos!!");
        }


        try {
            return ResponseEntity.ok(incidenceService.getNearIncidentsByPositionRadius(user, category, latitude,
                    longitude, radiusInMeters));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(description = "Retorna todos os incidentes de uma lista de categorias informadas", method = "GET")// customizando UI do Swagger
    @GetMapping(value = "/bycategory/{category}")
    public ResponseEntity<Object> getIncidencesByCategory(@PathVariable Category category, @RequestBody User user) throws  Exception {
        /**
         * TODO: retorna todos os incidentes de uma lista de categorias informadas
         *
         * A regra de negócio de quem poderá consumir esse endpoint não foi definida
         *
         * Não retornar todas as informações para usuário do tipo CIDADAO
         *
         * recebe um usuário e uma lista categorias
         * {
         *  user: ObjectJSON,
         *  categorylist: ArrayInteger
         * }
         * retorna uma lista de incidentes de acordo com a categoria informada
         * {
         *  incidente: ObjectJson
         * }
         */
        try {
            return ResponseEntity.ok(incidenceService.getIncidencesByCategory(category, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(description = "Cadastra uma Incidência", method = "POST")// customizando UI do Swagger
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewIncidence(@RequestBody IncidenceDTO incidenceDTO){
        /*
         * TODO: deve gravar um novo incidente
         * 
         * 
         * Deve receber um objeto com o usuário e o incidente
         * 
         * Recebe:
         * {
         *  user: ObjectJSON,
         *  incidente: ObjectJson
         * }
         * 
         * Regras para a service:
         * Se o campo Incidente.getUrgent() == true quer dizer que é um S.O.S.
         * caso contrário é apenas uma denúncia 
         * 
         * User->Devices pode ser nulo caso o usuário for anônimo [Hazel]
         * 
         * Se o usuário não existir cria um novo usuário no banco, a chave única sserá o endereço de email
         */

        try {
            return ResponseEntity.ok(incidenceService.createNewIncidence(incidenceDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Mensagens de Exceções personalizadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
