package br.com.savebluapi.models.dtos;

import br.com.savebluapi.enums.UserType;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private static final long  serialVersionUID = 42L;

    private Long id;

    private String email;

    private String name;

    private String telephone;

    private UserType type;

    private String deviceToken;

	private Double lastLatitude;

	private Double lastLongitude;
}
