package com.sires.mp.service.impl;

import com.sires.mp.repository.ConfigurationJPA;
import com.sires.mp.service.ConfDBService;
import com.sires.mp.service.dto.ConfigurationDTO;
import com.sires.mp.service.mapper.MapperApp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfDBServiceImpl implements ConfDBService {
    private final ConfigurationJPA repository;

    @Override
    public List<ConfigurationDTO> getAll() {
        return MapperApp.INSTANCE.fromEntityToDTO(repository.findAll());
    }

    @Override
    public ConfigurationDTO findByClave(String clave) {
        return MapperApp.INSTANCE.fromEntityToDTO(repository.findByClave(clave));
    }
}
