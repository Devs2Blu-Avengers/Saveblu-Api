package br.com.savebluapi.models.dtos;

import br.com.savebluapi.enums.UserType;
import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private static final long  serialVersionUID = 42L;

    Long id;

    private String email;

    private String name;

    private String telephone;

    private UserType type;

    private String deviceToken;

	private Double lastLatitude;

	private Double lastLongitude;
}
