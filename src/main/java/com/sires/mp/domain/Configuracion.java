package com.sires.mp.domain;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "MP_CONFIGURACION")
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clave;
    private String valor;
}
