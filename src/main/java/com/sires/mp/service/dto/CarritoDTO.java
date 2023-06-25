package com.sires.mp.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarritoDTO {
    private String id;
    private List<ItemCompraDTO> items;
    private UsuarioDTO usuario;

}
