package br.com.savebluapi.models;


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

	@Column(nullable = false)
	private String deviceToken;

	@Column(nullable = false)
	private Double lastLatitude;

	@Column(nullable = false)
	private Double lastLongitude;
	
}
