package com.carlosruanpucrs.tc2_monolitico.service;

import com.carlosruanpucrs.tc2_monolitico.enums.SituacaoPagamentoInssEnum;
import com.carlosruanpucrs.tc2_monolitico.model.dto.PagamentoInssDto;
import com.carlosruanpucrs.tc2_monolitico.model.entity.PagamentoInssEntity;
import com.carlosruanpucrs.tc2_monolitico.repository.PagamentoInssRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoInssService {

    private final TransacaoService transacaoService;
    private final PagamentoInssRepository pagamentoInssRepository;

    public void processarLote(MultipartFile file) throws Exception {
        var pagamentos = lerExcel(file);
        for (PagamentoInssDto pagamentoInss : pagamentos) {
            registrarPagamentoInss(pagamentoInss);
        }

        pagarInss();
    }

    private void pagarInss() {
        pagamentoInssRepository.findAllBySituacaoIs(SituacaoPagamentoInssEnum.PENDENTE)
                .forEach(transacaoService::pagarInss);
    }

    private void registrarPagamentoInss(PagamentoInssDto pagamentoInss) {
        var inss = PagamentoInssEntity.builder()
                .numeroBeneficio(pagamentoInss.getNumeroBeneficio())
                .numeroConta(pagamentoInss.getNumeroConta())
                .situacao(SituacaoPagamentoInssEnum.PENDENTE)
                .valorPagamento(pagamentoInss.getValor())
                .dataPagamento(pagamentoInss.getDataPagamento())
                .build();

        pagamentoInssRepository.save(inss);
    }

    private List<PagamentoInssDto> lerExcel(MultipartFile file) throws IOException {
        List<PagamentoInssDto> creditos = new ArrayList<>();
        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();

                if (linhaEstaVazia(row)) continue;

                Integer numeroConta = getInteger(row.getCell(0));
                Integer numeroBeneficio = getInteger(row.getCell(1));
                BigDecimal valor = getBigDecimal(row.getCell(2));
                LocalDate dataPagamento = getLocalDate(row.getCell(3));

                creditos.add(new PagamentoInssDto(numeroConta, numeroBeneficio, valor, dataPagamento));
            }
        }

        return creditos;
    }

    private Integer getInteger(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case NUMERIC -> (int) cell.getNumericCellValue();
            case STRING -> {
                try {
                    yield Integer.parseInt(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    yield null;
                }
            }
            default -> null;
        };
    }

    private BigDecimal getBigDecimal(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case NUMERIC -> BigDecimal.valueOf(cell.getNumericCellValue());
            case STRING -> {
                try {
                    yield new BigDecimal(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    yield null;
                }
            }
            default -> null;
        };
    }

    private LocalDate getLocalDate(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        if (cell.getCellType() == CellType.STRING) {
            try {
                return LocalDate.parse(cell.getStringCellValue().trim()); // yyyy-MM-dd
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private boolean linhaEstaVazia(Row row) {
        if (row == null) return true;

        for (int i = 0; i < 4; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                String cellValue = cell.toString().trim();
                if (!cellValue.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
