package com.sires.mp.service;

import com.sires.mp.service.dto.ConfigurationDTO;

import java.util.List;

public interface ConfDBService {
    List<ConfigurationDTO> getAll();
    ConfigurationDTO findByClave(String clave);
}
