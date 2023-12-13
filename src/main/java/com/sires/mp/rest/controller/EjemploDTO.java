package com.sires.mp.rest.controller;

import com.sires.mp.validations.interfaces.Ci;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EjemploDTO {
    @Ci(message = "La cédula no es correcta")
    private String cedula;
}
