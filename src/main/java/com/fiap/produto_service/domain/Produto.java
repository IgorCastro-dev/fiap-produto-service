package com.fiap.produto_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class Produto {
    private UUID sku;
    private String nome;
    private BigDecimal preco;
}