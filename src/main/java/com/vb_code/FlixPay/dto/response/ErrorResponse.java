package com.vb_code.FlixPay.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
    LocalDateTime timestamp,
    Integer status,
    String erro,
    List<String> mensagens
) {
}
