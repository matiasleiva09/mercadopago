package com.sires.mp.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    @JsonProperty(value = "name")
    private String nombre;
    @JsonProperty(value="surname")
    private String apellido;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value="area_code")
    private String areaTelefono;
    @JsonProperty(value="phone")
    private String telefono;
    @JsonProperty(value="street")
    private String direccion;
    @JsonProperty(value="street_number")
    private String altura;
    @JsonProperty(value="zip_code")
    private String cp;
}
