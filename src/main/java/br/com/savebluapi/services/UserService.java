
package br.com.savebluapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savebluapi.models.Device;
import br.com.savebluapi.models.User;
import br.com.savebluapi.models.dtos.UserDTO;
import br.com.savebluapi.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    ModelMapper mapper;

	public UserDTO findById(Long userId) throws Exception{
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
            return mapper.map(optional.get(), UserDTO.class);
        } else {
            throw new Exception("Não encontrado");
        }
	}

	public List<UserDTO> listAll() {
		return userRepository.findAll().stream()
            .map(user -> mapper.map(user, UserDTO.class))
            .collect(Collectors.toList());
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<Device> findAllDevicesForUserId(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		return user.getDevices();
	}
	
	public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new EntityNotFoundException("Usuário não encontrado para o email fornecido");
        }
    }
	
	public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
	
	
	public User findUserByTelephone(String telephone) {
		User user = userRepository.findByTelephone(telephone);
        if (user != null) {
            return user;
        } else {
            throw new EntityNotFoundException("Usuário não encontrado para o telefone fornecido");
        }
    }

	public User updateUser(Long userId, User updatedUserDate) {
		if (updatedUserDate == null) {
			throw new IllegalArgumentException("Dados de usuário atualizados inválidos");
		}
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

		existingUser.setEmail(updatedUserDate.getEmail());
		existingUser.setName(updatedUserDate.getName());
		existingUser.setTelephone(updatedUserDate.getTelephone());
		existingUser.setType(updatedUserDate.getType());
		existingUser.setDevices(updatedUserDate.getDevices());

		User updatedUser = userRepository.save(existingUser);

		return updatedUser;
	}

	public User createUser(User newUser) {
		validateUserData(newUser);
		if (userRepository.existsByEmail(newUser.getEmail())
				|| userRepository.existsByTelephone(newUser.getTelephone())) {
			throw new IllegalArgumentException("Email ou telefone já em uso");
		}

		return userRepository.save(newUser);
	}

	private void validateUserData(User user) {
		if (user == null) {
			throw new IllegalArgumentException("Dados de usuário inválidos");
		}
		if (!isValidEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email inválido");
		}
		if (user.getName() == null || user.getName().isEmpty()) {
			throw new IllegalArgumentException("Nome é obrigatório");
		}
		if (!isValidPhoneNumber(user.getTelephone())) {
			throw new IllegalArgumentException("Telefone inválido");
		}
		if (user.getType() == null) {
			throw new IllegalArgumentException("Tipo de usuário é obrigatório");
		}
	}

	// validar email
	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"; // verificar se o email esta em um formato
														// valido(email@exemplo.com)
		return email.matches(emailRegex);
	}

	// validar telefone
	private boolean isValidPhoneNumber(String phoneNumber) {
		String phoneRegex = "^[0-9]{9}$";// validação se tem 9 digitos o telefone informado
		return phoneNumber.matches(phoneRegex);
	}

	public void deleteUserById(Long userId) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		userRepository.delete(existingUser);
	}

	public void notifyDevices() {
		/**
		 * TODO: notifica todos os dispositivos do usuário Esse método irá notificar os
		 * dispositivos do usuário utilizando a api do Firebase
		 */
	}

}
