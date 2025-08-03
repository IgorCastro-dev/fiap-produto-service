package com.fiap.produto_service.gateway;

import com.fiap.produto_service.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProdutoGateway {
    Produto criar(Produto produto);
    Produto buscaPorSku(String sku);
    String deletaPorSku(String sku);
    Page<Produto> listarProdutos(Pageable pageable);
}
