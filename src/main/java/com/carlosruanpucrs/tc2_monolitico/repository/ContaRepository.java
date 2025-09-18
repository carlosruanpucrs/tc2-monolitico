package com.carlosruanpucrs.tc2_monolitico.repository;

import com.carlosruanpucrs.tc2_monolitico.model.entity.ContaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends MongoRepository<ContaEntity, Integer> {
    ContaEntity findContaEntityByNumeroConta(Integer numeroConta);
    ContaEntity findContaEntityByDocumentoCliente(String numeroDocumento);
}
