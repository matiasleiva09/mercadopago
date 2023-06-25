package com.sires.mp.repository;

import com.sires.mp.domain.MercadoPagoPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface PagoRespository extends CrudRepository<MercadoPagoPayment,Long> {
    @Query(value = "select * from mp_pago where registrado between :fechaDesde and :fechaHasta order by registrado desc",nativeQuery = true)
    List<MercadoPagoPayment> getPagos(@Param("fechaDesde") Date xFechaDesde,@Param("fechaHasta") Date xFechaHasta);
}
