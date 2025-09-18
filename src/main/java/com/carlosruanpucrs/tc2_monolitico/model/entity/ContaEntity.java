package com.carlosruanpucrs.tc2_monolitico.model.entity;

import com.carlosruanpucrs.tc2_monolitico.model.dto.MovimentacaoFinanceiraDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@Document(collection = "contas")
public class ContaEntity {

    @Id
    private Integer numeroConta;

    //    @Indexed(unique = true)
    private String documentoCliente;

    private String nomeCliente;
    private String situacao;
    private LocalDate dataCriacao;
    private BigDecimal saldo;
    private List<MovimentacaoFinanceiraDto> movimentacoesFinanceiras;
}