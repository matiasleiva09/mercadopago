package com.sires.mp.service;

import com.sires.mp.service.dto.ConfigurationDTO;

import java.util.List;

public interface ConfiguracionService {
    List<ConfigurationDTO> getAllConfiguration();
    ConfigurationDTO findByKey(String key);
}
