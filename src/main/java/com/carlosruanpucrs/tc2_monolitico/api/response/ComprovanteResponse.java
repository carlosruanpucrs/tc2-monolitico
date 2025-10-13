package com.carlosruanpucrs.tc2_monolitico.api.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComprovanteResponse {

    String comprovante;
}
