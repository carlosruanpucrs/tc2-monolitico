package com.carlosruanpucrs.tc2_monolitico.api.request;

import com.carlosruanpucrs.tc2_monolitico.enums.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratacaoContaRequest {

    @NonNull
    private String numeroDocumento;
    @NonNull
    private String nomeCliente;
    @NonNull
    private LocalDate dataNascimentoCliente;
    @NonNull
    private String cep;
    @NonNull
    private String cidade;
    @NonNull
    private EstadoEnum estado;
    @NonNull
    private String endereco;
}
