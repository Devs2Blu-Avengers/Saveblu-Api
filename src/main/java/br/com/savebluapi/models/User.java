package br.com.savebluapi.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.savebluapi.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users") 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String telephone;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserType type;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("user")
	private List<Device> devices;
}
