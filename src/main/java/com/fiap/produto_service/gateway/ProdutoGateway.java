package com.fiap.produto_service.gateway;

import com.fiap.produto_service.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProdutoGateway {
    Produto criar(Produto produto);
    Produto buscaPorSku(UUID sku);
    String deletaPorSku(UUID sku);
    Page<Produto> listarProdutos(Pageable pageable);
}
