package br.com.savebluapi.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeviceDTO {
    
    private static final long serialVersionUID = 1L;

    @Schema(hidden = true)
    private Long id;

}
