package com.fiap.produto_service.usecase;

import com.fiap.produto_service.domain.Produto;
import com.fiap.produto_service.gateway.ProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscaProdutoPorSkuUsecase {
    @Autowired
    private ProdutoGateway produtoGateway;
    public Produto buscarProdutoPorSku(String sdk){
        return produtoGateway.buscaPorSku(sdk);
    }

}
