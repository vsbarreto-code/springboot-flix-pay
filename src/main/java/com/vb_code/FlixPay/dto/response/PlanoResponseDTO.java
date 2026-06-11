package com.vb_code.FlixPay.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.vb_code.FlixPay.database.entity.enums.PlanoStatusEnum;

public record PlanoResponseDTO(
    UUID id,
    String nome,
    BigDecimal valor,
    PlanoStatusEnum ciclo,
    LocalDateTime criadoEm,
    Boolean ativo
) {

}
