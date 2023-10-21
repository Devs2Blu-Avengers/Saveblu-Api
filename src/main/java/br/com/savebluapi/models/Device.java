package br.com.savebluapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Device {
    // TODO: escrever a model Device
	
	private String token;
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private User user;
	
	private double last_latitude;
	private double last_longitude;
	private String number;
	
    
}
