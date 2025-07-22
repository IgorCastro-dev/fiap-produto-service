package com.fiap.produto_service.usecase;

import com.fiap.produto_service.gateway.ProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletaProdutoUsecase {
    @Autowired
    private ProdutoGateway produtoGateway;
    public String deletaProdutoPorSku(UUID sku){
        return produtoGateway.deletaPorSku(sku);
    }
}
