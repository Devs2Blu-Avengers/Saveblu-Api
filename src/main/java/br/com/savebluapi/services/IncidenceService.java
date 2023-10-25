package br.com.savebluapi.services;

import br.com.savebluapi.enums.Category;
import br.com.savebluapi.enums.UserType;
import br.com.savebluapi.models.Incidence;
import br.com.savebluapi.models.User;
import br.com.savebluapi.models.dtos.IncidenceDTO;
import br.com.savebluapi.models.dtos.UserDTO;
import br.com.savebluapi.repositories.IncidenceRepository;
import br.com.savebluapi.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncidenceService {

    @Autowired
    IncidenceRepository incidenceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper mapper;

    public Long createNewIncidence(IncidenceDTO incidenceDTO) throws Exception {

        Incidence incidenceCreated = null;

        // Validar dados
        if (incidenceDTO.getUser() == null) {
                throw new Exception("Informe o id do usuário existente");
        }

        // Define o próximo ticket number
        incidenceDTO.setTicketNumber(getNextTicketNumber());
        System.out.println(incidenceDTO.toString());

        try {
            incidenceCreated = incidenceRepository.save(mapper.map(incidenceDTO, Incidence.class));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Erro ao criar um Incidente");
        }

        return incidenceCreated.getId();
    }

    public String getNextTicketNumber(){
        String nextTicketNumber = null;

        String maxTicketNumber = incidenceRepository.findMaxTicketNumber();

        // Obtém o ano atual, mês e dia
        String anoAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String mesAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String diaAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));

        if (maxTicketNumber != null && maxTicketNumber.startsWith(anoAtual)) {
            // Se o ano atual é o mesmo do último ticket_number, incrementa o número
            int numeroAtual = Integer.parseInt(maxTicketNumber.substring(6)); // Removendo os primeiros 6 caracteres
            int proximoNumero = numeroAtual + 1;
            String proximoNumeroStr = String.valueOf(proximoNumero).length() > 5 ? String.valueOf(proximoNumero) : String.format("%04d", proximoNumero); // Garante que o próximo número tenha 4 dígitos ou mais digitos
            nextTicketNumber = anoAtual + mesAtual + diaAtual + proximoNumeroStr;
        } else {
            // Caso contrário, começa com o primeiro número do ano atual
            nextTicketNumber = anoAtual + mesAtual + diaAtual + "0001";
        }

        System.out.println("Novo ticker number: " + nextTicketNumber);
        return nextTicketNumber;
    }

    public List<IncidenceDTO> getIncidencesByCategory(Category category, User user) throws  Exception {
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
        List<IncidenceDTO> incidenceDTOList = new ArrayList<>();

        if (user.getType() != UserType.CIDADAO) {
            incidenceDTOList = incidenceRepository.findAll().stream().filter(incidence ->
                            incidence.getCategory() == category).
                    map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
        } else {
            if (category == Category.ALAGAMENTO || category == Category.DESLIZAMENTO || category == Category.ENCHENTE || category == Category.INCENDIO) {
                incidenceDTOList = incidenceRepository.findAll().stream().filter(incidence ->
                                incidence.getCategory() == category).
                        map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
            }
        }


        return incidenceDTOList;
    }

    public List<IncidenceDTO> getNearIncidentsByPositionRadius(User user,Category category,Double latitude, Double longitude, Double radiusInMeters) throws Exception {
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

        // Carregar do banco as incidências
        List<IncidenceDTO> incidenceDTOList = incidenceRepository.findAll().stream()
                .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
        // Incidentes que serão filtrados
        List<IncidenceDTO> incidenceDTOListiInRadius= null;

            // Se for um usuário especial
            if (user != null && user.getType() != UserType.CIDADAO) {
                // Lista por categoria
                if (category != null) {
                    incidenceDTOList = incidenceDTOList.stream()
                            .filter(incidence -> incidence.getCategory() == category)
                            .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
                } else {
                    // Lista todos
                    incidenceDTOList = incidenceDTOList.stream()
                            .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
                }
            } else {
                // Se for um cidadão
                if (category != null) {
                    // categorias permitidas para o cidadão
                    if (category == Category.ENCHENTE ||
                            category == Category.ALAGAMENTO ||
                            category == Category.DESLIZAMENTO
                    )
                        incidenceDTOList = incidenceDTOList.stream()
                                .filter(incidence -> incidence.getCategory() == category)
                                .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
                } else {
                    // lista todas as categorias permitidas para o cidadão
                    incidenceDTOList = incidenceDTOList.stream()
                            .filter(
                                    incidence -> incidence.getCategory() == Category.ENCHENTE ||
                                            incidence.getCategory() == Category.ALAGAMENTO ||
                                            incidence.getCategory() == Category.DESLIZAMENTO
                            )
                            .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
                }
            }

            // Lista para armazenar os incidentes dentro do raio
            incidenceDTOListiInRadius = findIncidentsInRadius(latitude, longitude, incidenceDTOList, radiusInMeters);

        for (IncidenceDTO incidenteDTO : incidenceDTOListiInRadius) {
            System.out.print(incidenteDTO.getLatitude().toString()+ ",");
            System.out.print(incidenteDTO.getLongitude().toString()+ ",");
            System.out.print(incidenteDTO.getCategory().toString()+ ",");
            System.out.println("#FF5733");
        }

        return incidenceDTOListiInRadius;
    }

    public List<IncidenceDTO> getUserNearIncidents(User user, Category category) throws Exception {
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


        // Carregar do banco as incidências
        List<IncidenceDTO> incidenceDTOList = incidenceRepository.findAll().stream()
                .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
        // Incidentes que serão filtrados
        List<IncidenceDTO> incidenceDTOListNearUser= null;

        // Se for um usuário especial
        if (user.getType() != UserType.CIDADAO) {
            // Lista por categoria
            if (category != null) {
                incidenceDTOList = incidenceDTOList.stream()
                        .filter(incidence -> incidence.getCategory() == category)
                        .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
            } else {
                // Lista todos
                incidenceDTOList = incidenceDTOList.stream()
                        .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
            }
        } else {
            // Se for um cidadão
            if (category != null) {
                // categorias permitidas para o cidadão
                if (category == Category.ENCHENTE ||
                        category == Category.ALAGAMENTO ||
                        category == Category.DESLIZAMENTO
                )
                    incidenceDTOList = incidenceDTOList.stream()
                            .filter(incidence -> incidence.getCategory() == category)
                            .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
            } else {
                // lista todas as categorias permitidas para o cidadão
                incidenceDTOList = incidenceDTOList.stream()
                        .filter(
                                incidence -> incidence.getCategory() == Category.ENCHENTE ||
                                        incidence.getCategory() == Category.ALAGAMENTO ||
                                        incidence.getCategory() == Category.DESLIZAMENTO
                        )
                        .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
            }
        }

        // Lista para armazenar os incidentes perto do Usuário
        // (defini que aparecerão as incidências num raio de 1000m do usuário)
        incidenceDTOListNearUser = findIncidentsInRadius(user.getLastLatitude(),
                user.getLastLongitude(), incidenceDTOList, 1000);

        return incidenceDTOListNearUser;
    }


    // Função encontra incidentes num dado raio coordenadas
    public static List<IncidenceDTO> findIncidentsInRadius(double userLatitude, double userLongitude, List<IncidenceDTO> incidenceDTOList, double radiusInMeters) {
        List<IncidenceDTO> nearbyIncidencesDTO = new ArrayList<>();

        final double EARTH_RADIUS = 6371000; // Raio da Terra em metros

        for (IncidenceDTO incidence : incidenceDTOList) {
            double lat1 = Math.toRadians(userLatitude);
            double lon1 = Math.toRadians(userLongitude);
            double lat2 = Math.toRadians(incidence.getLatitude());
            double lon2 = Math.toRadians(incidence.getLongitude());

            // Calcula a diferença de latitude e longitude
            double dLat = lat2 - lat1;
            double dLon = lon2 - lon1;

            // Fórmula de Haversine para calcular a distância
            double a = Math.pow(Math.sin(dLat / 2), 2) +
                    Math.cos(lat1) * Math.cos(lat2) *
                            Math.pow(Math.sin(dLon / 2), 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = EARTH_RADIUS * c;

            if (distance <= radiusInMeters) {
                nearbyIncidencesDTO.add(incidence);
            }
        }

        return nearbyIncidencesDTO;
    }

    // Mensagens de Exceções personalizadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
