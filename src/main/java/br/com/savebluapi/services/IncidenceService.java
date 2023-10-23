package br.com.savebluapi.services;

import br.com.savebluapi.enums.Category;
import br.com.savebluapi.enums.UserType;
import br.com.savebluapi.models.Device;
import br.com.savebluapi.models.Incidence;
import br.com.savebluapi.models.User;
import br.com.savebluapi.models.dtos.IncidenceDTO;
import br.com.savebluapi.models.dtos.UserDTO;
import br.com.savebluapi.repositories.DeviceRepository;
import br.com.savebluapi.repositories.IncidenceRepository;
import br.com.savebluapi.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidenceService {

    @Autowired
    IncidenceRepository incidenceRepository;

    @Autowired
    UserService userService;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ModelMapper mapper;

    public Long createNewIncidence(IncidenceDTO incidenceDTO) throws Exception {
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
         * Se o usuário não existir cria um novo usuário no banco, a chave única será o endereço de email
         */
        Incidence incidenceCreated = null;

        if (incidenceDTO.getUrgent()) {
            // SOS

        } else {
            // Denúncia
        }

        try {

            if (incidenceDTO.getUser() == null) {
                // Se o usuário for nulo
                incidenceCreated = incidenceRepository.save(mapper.map(incidenceDTO, Incidence.class));

            } else {
                User userSearched = userService.findUserByEmail(incidenceDTO.getUser().getEmail());
                if (userSearched != null) {
                    incidenceCreated = incidenceRepository.save(mapper.map(incidenceDTO, Incidence.class));
                }


            }
        } catch (EntityNotFoundException e) {
            // Cria um usuário se ele não existir
            incidenceCreated = incidenceRepository.save(mapper.map(incidenceDTO, Incidence.class));
        }
        catch (Exception e) {
            throw new Exception("Erro ao criar um Incidente");
        }

        return incidenceCreated.getId();
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

    public List<IncidenceDTO> getNearIncidentsByPositionRadius(User user,Category category,Double latitude, Double longitude, Double radiusInMeters){
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
        List<IncidenceDTO> incidenceDTOList = new ArrayList<>();

        if (user.getType() != UserType.CIDADAO) {
            incidenceDTOList = incidenceRepository
                    .findAll().stream()
                    .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
        }

        // Lista para armazenar os incidentes dentro do raio
        List<IncidenceDTO> incidenceDTOListiInRadius = findIncidentsInRadius(latitude, longitude, incidenceDTOList, radiusInMeters);

        return incidenceDTOListiInRadius;
    }

    public List<IncidenceDTO> getUserNearIncidents(User user, IncidenceDTO incidenceDTO, Category category){
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
        // Número de incidentes a serem recuperados
        int numberOfIncidentsToRetrieve = 10;
        Pageable pageable = PageRequest.of(0, numberOfIncidentsToRetrieve);


        List<IncidenceDTO> nearIncidenceDTOList = new ArrayList<>();

        if (user.getType() != UserType.CIDADAO) {
            nearIncidenceDTOList = incidenceRepository
            .findIncidencesNearUser(incidenceDTO.getLatitude(), incidenceDTO.getLongitude(), pageable).stream()
                    .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();
        }


        return nearIncidenceDTOList;
    }

    // Função para calcular a distância entre dois pontos usando a fórmula de Haversine
    public static double haversine(double userLat, double userLon, double incidentLat, double incidentLon) {
        final int R = 6371; // Raio médio da Terra em quilômetros
        double lat1 = Math.toRadians(userLat);
        double lon1 = Math.toRadians(userLon);
        double lat2 = Math.toRadians(incidentLat);
        double lon2 = Math.toRadians(incidentLon);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c * 1000; // Distância em metros
    }

    // Função encontra incidentes num dado raio
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
    
}
