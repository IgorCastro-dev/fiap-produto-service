package com.fiap.produto_service.usecase;

import com.fiap.produto_service.domain.Produto;
import com.fiap.produto_service.gateway.ProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaTodosProdutosUsecase {
    @Autowired
    private ProdutoGateway produtoGateway;

    public Page<Produto> listarProdutos(Pageable pageable){
        return produtoGateway.listarProdutos(pageable);
    }

}
