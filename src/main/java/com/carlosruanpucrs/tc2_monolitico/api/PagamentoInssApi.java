package com.carlosruanpucrs.tc2_monolitico.api;

import com.carlosruanpucrs.tc2_monolitico.service.PagamentoInssService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/pagamentos-inss")
public class PagamentoInssApi {

    private final PagamentoInssService pagamentoInssService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> processarLoteInss(@RequestParam("file") MultipartFile file) throws Exception {
        pagamentoInssService.processarLote(file);
        return ResponseEntity.noContent().build();
    }
}
