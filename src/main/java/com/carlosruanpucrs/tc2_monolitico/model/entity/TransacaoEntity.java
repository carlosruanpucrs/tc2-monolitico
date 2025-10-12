package com.carlosruanpucrs.tc2_monolitico.model.entity;

import com.carlosruanpucrs.tc2_monolitico.enums.TipoMovimentacaoEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "transacoes")
public class TransacaoEntity {

    @Id
    String comprovante;

    Integer contaOrigem;
    Integer contaDestino;
    BigDecimal valor;
    LocalDateTime dataHora;
    TipoMovimentacaoEnum tipoMovimentacao;
}
