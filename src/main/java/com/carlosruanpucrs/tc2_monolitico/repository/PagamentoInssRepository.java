package com.carlosruanpucrs.tc2_monolitico.repository;

import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoPagamentoInssEnum;
import com.carlosruanpucrs.tc2_monolitico.model.entity.PagamentoInssEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoInssRepository extends MongoRepository<PagamentoInssEntity, String> {

    List<PagamentoInssEntity> findAllBySituacaoIs(SituacaoPagamentoInssEnum situacao);
}
