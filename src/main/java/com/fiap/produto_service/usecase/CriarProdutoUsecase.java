package com.fiap.produto_service.usecase;

import com.fiap.produto_service.domain.Produto;
import com.fiap.produto_service.gateway.ProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarProdutoUsecase {
    @Autowired
    private ProdutoGateway produtoGateway;
    public Produto salvaProduto(Produto produto){
        return produtoGateway.criar(produto);
    }
}
