package com.vb_code.FlixPay.database.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.vb_code.FlixPay.database.entity.enums.PlanoStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_planos")
@Data
@EntityListeners(AuditingEntityListener.class)
public class PlanoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private PlanoStatusEnum ciclo;
    @CreatedDate
    private LocalDateTime criadoEm;
    private Boolean ativo = true;
}
