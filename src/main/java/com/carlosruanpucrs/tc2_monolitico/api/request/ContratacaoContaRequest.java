package com.carlosruanpucrs.tc2_monolitico.api.request;

import com.carlosruanpucrs.tc2_monolitico.enums.EstadoEnum;
import com.carlosruanpucrs.tc2_monolitico.enums.TipoContaEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContratacaoContaRequest {

    @NonNull
    TipoContaEnum tipoConta;
    @NonNull
    String numeroDocumento;
    @NonNull
    String nomeCliente;
    @NonNull
    LocalDate dataNascimentoCliente;
    @NonNull
    String cep;
    @NonNull
    String cidade;
    @NonNull
    EstadoEnum estado;
    @NonNull
    String endereco;
}
