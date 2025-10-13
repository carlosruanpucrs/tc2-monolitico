package com.carlosruanpucrs.tc2_monolitico.api;

import com.carlosruanpucrs.tc2_monolitico.api.request.TransferenciaRequest;
import com.carlosruanpucrs.tc2_monolitico.api.response.ComprovanteResponse;
import com.carlosruanpucrs.tc2_monolitico.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/transferencias")
public class TransacaoApi {

    private final TransacaoService transacaoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComprovanteResponse> transferir(@RequestBody TransferenciaRequest request) {
        return ResponseEntity.ok(transacaoService.transferir(request));
    }
}
