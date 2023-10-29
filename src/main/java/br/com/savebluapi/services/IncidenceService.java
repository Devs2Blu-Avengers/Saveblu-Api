package br.com.savebluapi.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.savebluapi.models.dtos.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import br.com.savebluapi.enums.Category;
import br.com.savebluapi.enums.UserType;
import br.com.savebluapi.models.Incidence;
import br.com.savebluapi.models.User;
import br.com.savebluapi.models.dtos.IncidenceDTO;
import br.com.savebluapi.repositories.IncidenceRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class IncidenceService {

	@Autowired
	IncidenceRepository incidenceRepository;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper mapper;

	public IncidenceDTO findById(Long incidenceID) throws Exception{
		Optional<Incidence> optional = incidenceRepository.findById(incidenceID);
		if (optional.isPresent()) {
            return mapper.map(optional.get(), IncidenceDTO.class);
        } else {
            throw new Exception("Não encontrado");
        }
	}

	public Incidence createIncidenceFromDTO(IncidenceDTO incidenceDTO, MultipartFile imageFile) throws Exception {
	    byte[] imageBytes = imageFile.getBytes();
	    incidenceDTO.setImage(imageBytes);
	    return mapper.map(incidenceDTO, Incidence.class);
	}

	public Long createNewIncidence(IncidenceDTO incidenceDTO, MultipartFile imageFile) throws Exception {

		IncidenceDTO newIncidenceDTO = null;
        // Validar dados
        if (incidenceDTO.getUser() == null) {
                throw new Exception("Informe o id do usuário existente");
        }

        // Define o próximo ticket 
        incidenceDTO.setTicket(getNextTicket());
        System.out.println(incidenceDTO.toString());

        try {
			// Cria a imagem
			byte[] imageBytes = imageFile.getBytes();
	    	incidenceDTO.setImage(imageBytes);

            Incidence newIncidence = incidenceRepository.save(mapper.map(incidenceDTO, Incidence.class));

			newIncidenceDTO = mapper.map(newIncidence, IncidenceDTO.class);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Erro ao criar um Incidente");
        }

        return newIncidenceDTO.getId();
    }

	public String getNextTicket(){
        String nextTicket = null;

        String maxTicket = incidenceRepository.findMaxTicket();

        // Obtém o ano atual, mês e dia
        String anoAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String mesAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String diaAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));

        if (maxTicket != null && maxTicket.startsWith(anoAtual)) {
            // Se o ano atual é o mesmo do último ticket, incrementa o número
            int numeroAtual = Integer.parseInt(maxTicket.substring(6)); // Removendo os primeiros 6 caracteres
            int proximoNumero = numeroAtual + 1;
            String proximoNumeroStr = String.valueOf(proximoNumero).length() > 5 ? String.valueOf(proximoNumero) : String.format("%04d", proximoNumero); // Garante que o próximo número tenha 4 dígitos ou mais digitos
            nextTicket = anoAtual + mesAtual + diaAtual + proximoNumeroStr;
        } else {
            // Caso contrário, começa com o primeiro número do ano atual
            nextTicket = anoAtual + mesAtual + diaAtual + "0001";
        }

        System.out.println("Novo ticker : " + nextTicket);
        return nextTicket;
    }

    public List<IncidenceDTO> getIncidencesByCategory(Integer[] categories, UserDTO user) throws Exception {
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
        // Carregar do banco as incidências
        List<IncidenceDTO> incidenceDTOList = incidenceRepository.findAll().stream()
                .map(incidence -> mapper.map(incidence, IncidenceDTO.class)).toList();

        // Lista concatenada de Incidências
        List<IncidenceDTO> concatenatedList = new ArrayList<>();

        // Lista temporária que será concatenada em concatenatedList
        List<IncidenceDTO> tempList = incidenceDTOList;

        // Array de index de categorias
        Category[] categoriesList = Category.values();

        if (user.getType() != UserType.CIDADAO) {
            for (Integer i: categories) {
                concatenatedList.addAll(tempList.stream().filter(incidence ->
                                incidence.getCategory() == categoriesList[i])
                                .toList());

                tempList = incidenceDTOList;
            }
        } else {
            for (Integer i: categories) {
                if (categoriesList[i] == Category.INCENDIO ||
                        categoriesList[i] == Category.ENCHENTE ||
                        categoriesList[i] == Category.ALAGAMENTO ||
                        categoriesList[i] == Category.RISCO_ELETRICO ||
                        categoriesList[i] == Category.DESLIZAMENTO
                ) {
                    concatenatedList.addAll(tempList.stream().filter(incidence ->
                                    incidence.getCategory() == categoriesList[i])
                                    .toList());
                    tempList = incidenceDTOList;
                }
            }
        }

        return concatenatedList;
    }

    public List<IncidenceDTO> getNearIncidentsByPositionRadius(UserDTO user, Integer[] categories, Double latitude,
                                                               Double longitude, Double radiusInMeters) {
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

        // Lista concatenada de Incidências
        List<IncidenceDTO> incidenceDTOListiInRadius = null;

        // Lista temporária que será concatenada em concatenatedList
        List<IncidenceDTO> tempList = incidenceDTOList;

        // Lista concatenada de Incidências
        List<IncidenceDTO> concatenatedList = new ArrayList<>();

        // Array de index de categorias
        Category[] categoriesList = Category.values();

        // Se for um usuário especial
        if (user != null && user.getType() != UserType.CIDADAO) {
            // Lista por categoria
            if (categories != null) {
                for (Integer i: categories) {
                    concatenatedList.addAll(tempList.stream()
                            .filter(incidence -> incidence.getCategory() == categoriesList[i])
                            .toList());
                    tempList = incidenceDTOList;
                }
            } else {
                // Lista todos
                concatenatedList = incidenceDTOList;
            }
        } else {
            // Se for um cidadão
            if (categories != null ) {
                for (Integer i: categories) {
                    // categorias permitidas para o cidadão
                    if (categoriesList[i] == Category.INCENDIO ||
                            categoriesList[i] == Category.ENCHENTE ||
                            categoriesList[i] == Category.ALAGAMENTO ||
                            categoriesList[i] == Category.RISCO_ELETRICO ||
                            categoriesList[i] == Category.DESLIZAMENTO
                    )
                        concatenatedList.addAll(tempList.stream()
                                .filter(incidence -> incidence.getCategory() == categoriesList[i])
                                .toList());
                    tempList = incidenceDTOList;
                }
            } else {
                // lista todas as categorias permitidas para o cidadão
                concatenatedList = tempList.stream()
                        .filter(
                                incidence -> incidence.getCategory() == Category.INCENDIO ||
                                        incidence.getCategory() == Category.ENCHENTE ||
                                        incidence.getCategory() == Category.ALAGAMENTO ||
                                        incidence.getCategory() == Category.RISCO_ELETRICO ||
                                        incidence.getCategory() == Category.DESLIZAMENTO
                        )
                        .toList();
            }
        }

        // Lista para armazenar os incidentes dentro do raio
        incidenceDTOListiInRadius = findIncidentsInRadius(latitude, longitude, concatenatedList, radiusInMeters);

        for (IncidenceDTO incidenteDTO : incidenceDTOListiInRadius) {
            System.out.print(incidenteDTO.getLatitude().toString()+ ",");
            System.out.print(incidenteDTO.getLongitude().toString()+ ",");
            System.out.print(incidenteDTO.getCategory().toString()+ ",");
            System.out.println("#FF5733");
        }

        return incidenceDTOListiInRadius;
    }

	public List<IncidenceDTO> getUserNearIncidents(User user, IncidenceDTO incidenceDTO, Category category) {
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

    public long updateIncidence(IncidenceDTO incidenceDTO) throws Exception {
        try {
            return incidenceRepository.save(mapper.map(incidenceDTO, Incidence.class)).getId();
        } catch (Exception e) {
            throw new Exception("Erro ao conectar ao banco de dados");
        }
    }

    public void deleteIncidence(Long incidenceId) throws Exception {
        try {
            incidenceRepository.deleteById(incidenceId);
        } catch (Exception e) {
            throw new Exception("Erro ao conectar ao banco de dados");
        }
    }

	public byte[] getImageBytesById(Long id) {
		try {
			Incidence incidence = incidenceRepository.findById(id).orElseThrow(
					() -> new IllegalArgumentException("Imagem da incidência não encontrada com o ID: " + id));
			return incidence.getImage();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	// Mensagens de Exceções personalizadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}