package com.vb_code.FlixPay.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.vb_code.FlixPay.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<ErrorResponse> nomeDuplicado(ConflitoException ex) {
        ErrorResponse response =
            new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Conflito de Dados",
                              List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(response);
    }

    @ExceptionHandler(IdNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> idNaoEncontrado(IdNaoEncontradoException ex) {
        ErrorResponse response =
            new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Recurso Não Encontrado",
                              List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(response);
    }

    // 1. Tratamento para a exceção manual (Regras de negócio)
    @ExceptionHandler(CampoInvalidoException.class)
    public ResponseEntity<ErrorResponse> campoInvalido(CampoInvalidoException ex) {
        ErrorResponse response =
            new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Campo Inválido",
                              List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(response);
    }

    // 2. Tratamento para a exceção do SPRING (Erros do @Valid do DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> erroDeValidacao(MethodArgumentNotValidException ex) {
        List<String> errosDeValidacao = ex.getBindingResult()
                                          .getFieldErrors()
                                          .stream()
                                          .map(FieldError::getDefaultMessage)
                                          .collect(Collectors.toList());

        ErrorResponse response =
            new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Erro de Validação",
                              errosDeValidacao);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(response);
    }
}
