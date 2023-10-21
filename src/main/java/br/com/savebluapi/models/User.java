package br.com.savebluapi.models;

import java.util.List;

import br.com.savebluapi.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
	private String email;
	private String name;
	private String telephone;
	private UserType type;
	private List<Device> devices;
	
}
