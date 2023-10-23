package br.com.savebluapi.models.dtos;

import br.com.savebluapi.enums.UserType;
import br.com.savebluapi.models.Device;
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

    private List<Device> devices;
}
