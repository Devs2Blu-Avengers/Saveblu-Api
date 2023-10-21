package br.com.savebluapi.models;

import java.util.List;

import br.com.savebluapi.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String telephone;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserType type;
	
	@Column(nullable = false)
	@OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
	private List<Device> devices;
	
}
