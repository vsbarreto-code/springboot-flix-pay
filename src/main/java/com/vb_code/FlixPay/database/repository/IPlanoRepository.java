package com.vb_code.FlixPay.database.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vb_code.FlixPay.database.entity.PlanoModel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface IPlanoRepository extends JpaRepository<PlanoModel, UUID> {
    boolean existsByNomeIgnoreCaseAndAtivoTrue(@NotNull(message = "O campo NOME deve ser informado!") @Size(min = 3, max = 100, message = "O campo NOME deve ter 3 a 100 caracteres") String nome);

    List<PlanoModel> findAllByAtivoTrue();
}
