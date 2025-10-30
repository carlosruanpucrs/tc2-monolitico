package com.carlosruanpucrs.tc2_monolitico.exception.handler;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleError {

    Integer status;
    String error;
    String message;
    LocalDateTime timestamp;
    List<String> fields;
}
