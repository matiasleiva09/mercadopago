package com.sires.mp.service.impl;

import com.sires.mp.service.ConfDBService;
import com.sires.mp.service.ConfiguracionService;
import com.sires.mp.service.dto.ConfigurationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfiguracionServiceImpl implements ConfiguracionService {
    private final ConfDBService repository;
    @Override
    public List<ConfigurationDTO> getAllConfiguration() {
        return repository.getAll();
    }

    @Override
    public ConfigurationDTO findByKey(String key) {
       return repository.findByClave(key);
    }
}
