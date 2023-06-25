package com.sires.mp.service.utils;

import com.sires.mp.service.dto.ConfigurationDTO;

import java.util.List;

public class ConfiguracionUtils {
    public static String getValueFromKey(List<ConfigurationDTO> configuraciones, String key){
       return configuraciones
                .stream()
                .filter(c->c.getKey().equalsIgnoreCase(key)).findFirst().get().getValue();
    }
}
