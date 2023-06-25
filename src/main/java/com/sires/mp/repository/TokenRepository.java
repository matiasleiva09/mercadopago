package com.sires.mp.repository;


import com.sires.mp.domain.TokenMP;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<TokenMP,Long> {
    @Query(value = "select * from MP_TOKEN ORDER BY date_time desc limit 1",nativeQuery = true)
    TokenMP obtenerUltimoToken();

}
