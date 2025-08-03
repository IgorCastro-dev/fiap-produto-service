package com.fiap.produto_service.usecase;

import com.fiap.produto_service.gateway.ProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletaProdutoUsecase {
    @Autowired
    private ProdutoGateway produtoGateway;
    public String deletaProdutoPorSku(String sku){
        return produtoGateway.deletaPorSku(sku);
    }
}
