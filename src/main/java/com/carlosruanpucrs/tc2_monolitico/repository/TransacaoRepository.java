package com.carlosruanpucrs.tc2_monolitico.repository;

import com.carlosruanpucrs.tc2_monolitico.model.entity.TransacaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends MongoRepository<TransacaoEntity, String> {

    List<TransacaoEntity> findByContaOrigemOrContaDestino(String contaOrigem, String contaDestino);
}
