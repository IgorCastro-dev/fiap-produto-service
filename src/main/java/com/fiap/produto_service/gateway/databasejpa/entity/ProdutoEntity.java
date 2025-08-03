package com.fiap.produto_service.gateway.databasejpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoEntity {
    @Id
    @Column(name = "SKU", columnDefinition = "CHAR(36)")
    private String sku;
    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;
    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;
}
