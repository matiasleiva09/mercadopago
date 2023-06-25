package com.sires.mp.repository;

import com.sires.mp.domain.Configuracion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationJPA extends CrudRepository<Configuracion,Integer> {
    Configuracion findByClave(String clave);
}
