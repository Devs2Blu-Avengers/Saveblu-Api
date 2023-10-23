package br.com.savebluapi.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.savebluapi.models.User;
import lombok.Data;

@Data
public class DeviceDTO {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private String token;
	
	private User user;

	private double last_latitude;
	
	private double last_longitude;
    
	private String number; 

}
