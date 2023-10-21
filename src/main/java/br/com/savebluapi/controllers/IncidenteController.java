package br.com.savebluapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/incidence")
public class IncidenteController {
    
    @PostMapping
    public ResponseEntity<Object> getIncidents(){
        /*
         * TODO: retornar uma lista de incidentes próximos as coordenadas do usuário informado
         * 
         * 
         * RECEBE:
         * {
         *      user: ObjectJSON,           (opcional) Se não for informado é um usuário anônimo/cidadão
         *      position: ObjectJSON,       (obrigatória) Para restringir a uma localização específica
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

        return null;
    }


    @PostMapping(value = "/byposition")
    public ResponseEntity<Object> getNearbyIncidentsFromPosition(){
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

        return null;
    }

    @GetMapping(value = "/incidence/bycategory/{category}")
    public void getIncidencesByCategory(){
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
    }

    @PostMapping(value = "/create")
    public void createNewIncidence(){
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

    }
}
