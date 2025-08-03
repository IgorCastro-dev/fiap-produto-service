package com.fiap.produto_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class Produto {
    private String sku;
    private String nome;
    private BigDecimal preco;
}