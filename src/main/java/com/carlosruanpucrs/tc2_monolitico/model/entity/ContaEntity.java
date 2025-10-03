package com.carlosruanpucrs.tc2_monolitico.model.entity;

import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoContaEnum;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoContaEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "contas")
public class ContaEntity {

    @Id
    Integer numeroConta;

    @Indexed(unique = true)
    String documentoCliente;
    String nomeCliente;
    SituacaoContaEnum situacao;
    LocalDate dataCriacao;
    BigDecimal saldo;
    BigDecimal saldoBloqueado;
    TipoContaEnum tipoConta;
    Integer numeroBeneficio;

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void bloquearSaldo(BigDecimal saldo) {
        this.saldo = this.saldo.subtract(saldo);
        this.saldoBloqueado = this.saldoBloqueado.add(saldo);
    }
}