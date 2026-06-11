package com.vb_code.FlixPay.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vb_code.FlixPay.dto.response.PlanoResponseDTO;
import com.vb_code.FlixPay.dto.resquest.PlanoRequestDTO;
import com.vb_code.FlixPay.service.PlanoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1/api/planos")
@RequiredArgsConstructor
public class PlanoController {
    private final PlanoService service;

    //   http://localhost:8080/v1/api/planos
    @PostMapping
    public ResponseEntity<PlanoResponseDTO> cadastrar(@Valid @RequestBody PlanoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(service.cadastrar(dto));
    }

    //    http://localhost:8080/v1/api/planos
    @GetMapping
    public ResponseEntity<List<PlanoResponseDTO>> listar() {
        return ResponseEntity.ok()
                             .body(service.listar());
    }

    //    http://localhost:8080/v1/api/planos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PlanoResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok()
                             .body(service.buscarPorId(id));
    }

    //    http://localhost:8080/v1/api/planos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> desativar(@PathVariable UUID id) {
        service.desativar(id);

        return ResponseEntity.ok()
                             .body("Plano desativado com sucesso!");
    }

}
