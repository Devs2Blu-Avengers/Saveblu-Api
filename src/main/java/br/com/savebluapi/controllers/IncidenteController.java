package br.com.savebluapi.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.savebluapi.enums.Category;
import br.com.savebluapi.models.User;
import br.com.savebluapi.models.dtos.IncidenceDTO;
import br.com.savebluapi.models.dtos.UserDTO;
import br.com.savebluapi.services.IncidenceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/incidence")
public class IncidenteController {

    @Autowired
    IncidenceService incidenceService;

    @Operation(description = "Retornar uma lista de incidentes próximos a posição informada", method = "GET") // customizando
                                                                                                              // UI do
                                                                                                              // Swagger
    @GetMapping(value = "/byposition")
    public ResponseEntity<Object> getNearIncidentsByPositionRadius(@RequestBody @Nullable UserDTO user,
           @RequestParam @Nullable Integer[] categories, @RequestParam(required = false) Double latitude,
           @RequestParam(required = false) Double longitude, @RequestParam(required = false) Double radiusInMeters)
            throws Exception {
        /*
         * TODO: retornar uma lista de incidentes próximos a posição informada
         *
         * A regra de negócio que define quais incidentes são exibidos por tipo de
         * usuário não foi definida
         *
         * RECEBE:
         * {
         * user: ObjectJSON, (opcional) Se não for informado é um usuário
         * anônimo/cidadão
         * position: ObjectJSON, (obrigatória) Para limitar a um raio específico da
         * posição do usuário (110 ? 200 ? 330 ?)
         * categories: Array<int> (opcional) Se passar a categoria filtra por categoria
         * }
         *
         * RETORNA:
         * [
         * {ObjectJson<incidence1>},
         * {ObjectJson<incidence2>},
         * {ObjectJson<incidence3>}
         * ]
         *
         */
        if (latitude == null || longitude == null || radiusInMeters == null) {
            throw new Exception("Coordenadas(latitude e longitude) e raio não podem ser nulos!!");
        }

        try {
            return ResponseEntity.ok(incidenceService.getNearIncidentsByPositionRadius(user, categories, latitude,
                    longitude, radiusInMeters));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(description = "Retorna todos os incidentes de uma lista de categorias informadas", method = "GET") // customizando
                                                                                                                  // UI
                                                                                                                  // do
                                                                                                                  // Swagger
    @GetMapping(value = "/bycategory/{categories}")
    public ResponseEntity<Object> getIncidencesByCategory(@PathVariable Integer[] categories, @RequestBody UserDTO user)
            throws Exception {
        /**
         * TODO: retorna todos os incidentes de uma lista de categorias informadas
         *
         * A regra de negócio de quem poderá consumir esse endpoint não foi definida
         *
         * Não retornar todas as informações para usuário do tipo CIDADAO
         *
         * recebe um usuário e uma lista categorias
         * {
         * user: ObjectJSON,
         * categorylist: ArrayInteger
         * }
         * retorna uma lista de incidentes de acordo com a categoria informada
         * {
         * incidente: ObjectJson
         * }
         */
        try {
            return ResponseEntity.ok(incidenceService.getIncidencesByCategory(categories, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(description = "Cadastra uma Incidência", method = "POST") // customizando UI do Swagger
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewIncidence(@Valid @ModelAttribute IncidenceDTO incidenceDTO,
            @RequestParam("imageFile") MultipartFile imageFile) {
        /*
         * TODO: deve gravar um novo incidente
         * 
         * 
         * Deve receber um objeto com o usuário e o incidente
         * 
         * Recebe:
         * {
         * user: ObjectJSON,
         * incidente: ObjectJson
         * }
         * 
         * Regras para a service:
         * Se o campo Incidente.getUrgent() == true quer dizer que é um S.O.S.
         * caso contrário é apenas uma denúncia
         * 
         * User->Devices pode ser nulo caso o usuário for anônimo [Hazel]
         * 
         * Se o usuário não existir cria um novo usuário no banco, a chave única sserá o
         * endereço de email
         */

        try {
            return ResponseEntity.ok(incidenceService.createNewIncidence(incidenceDTO, imageFile));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Mensagens de Exceções personalizadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @Operation(description = "Retorna a imagem do incidência através do id", method = "GET")
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        byte[] imageBytes = incidenceService.getImageBytesById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/{incidence_id}/image")
    public ModelAndView showContent(@PathVariable Long incidence_id, Model model) throws Exception {
        IncidenceDTO incidenceDTO = new IncidenceDTO();
        try {
            incidenceDTO = incidenceService.findById(incidence_id);
        }catch(Exception e){
            //
        }

        if(incidenceDTO.getImage() == null){
            throw new Exception("Imagem inexistente");
        }

        ArrayList<IncidenceDTO> content = new ArrayList<>();
        model.addAttribute("post", content);


        byte[] encodeBase64 = Base64.getEncoder().encode(incidenceDTO.getImage());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("contentImage", base64Encoded );

        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("contentImage",base64Encoded );
        return modelAndView;
    }
}