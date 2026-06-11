package com.vb_code.FlixPay.dto.resquest;

import java.math.BigDecimal;

import com.vb_code.FlixPay.database.entity.enums.PlanoStatusEnum;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PlanoRequestDTO(
    @NotNull(message = "O campo NOME deve ser informado!")
    @Size(min = 3, max = 100, message = "O campo NOME deve ter 3 a 100 caracteres")
    String nome,
    @NotNull(message = "O campo VALOR deve ser Informado")
    @Positive(message = "O campo VALOR deve ser maior que zero")
    @Digits(integer = 6, fraction = 2, message = "O preço deve ter no máximo 6 dígitos na parte inteira e 2 na parte decimal.")
    BigDecimal valor,
    @NotNull(message = "O campo CICLO deve ser informado!")
    PlanoStatusEnum ciclo
) {
}
