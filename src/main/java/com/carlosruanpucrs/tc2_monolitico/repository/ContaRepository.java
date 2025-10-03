package com.carlosruanpucrs.tc2_monolitico.repository;

import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends MongoRepository<ContaEntity, Integer> {
    Optional<ContaEntity> findContaEntityByNumeroConta(Integer numeroConta);
    Optional<ContaEntity> findContaEntityByDocumentoCliente(String numeroDocumento);
}
