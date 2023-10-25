package br.com.savebluapi.models.dtos;

import br.com.savebluapi.enums.Category;
import br.com.savebluapi.models.User;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class IncidenceDTO implements Serializable {

    private static final long  serialVersionUID = 42L;

    private Long id;

    private Date date;

    private Double latitude;

    private Double longitude;

    private Category category;

    private String description;

    private User user;
    
    private byte[] image;
    
    private Boolean urgent;

    private Boolean status;

    private Boolean valid;

    private String ticket;
}
