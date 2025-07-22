package com.fiap.produto_service.controller.json;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoJson {
    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal preco;
}
