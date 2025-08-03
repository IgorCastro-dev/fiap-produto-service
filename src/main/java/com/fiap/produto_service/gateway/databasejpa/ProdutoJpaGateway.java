package com.fiap.produto_service.gateway.databasejpa;

import com.fiap.produto_service.domain.Produto;
import com.fiap.produto_service.gateway.ProdutoGateway;
import com.fiap.produto_service.gateway.databasejpa.entity.ProdutoEntity;
import com.fiap.produto_service.gateway.databasejpa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class ProdutoJpaGateway implements ProdutoGateway {
    @Autowired
    ProdutoRepository produtoRepository;
    @Override
    public Produto criar(Produto produto) {
        ProdutoEntity produtoEntity = produtoRepository.save(mapToEntity(produto));
        return mapToDto(produtoEntity);
    }

    @Override
    public Produto buscaPorSku(String sku) {
        ProdutoEntity produtoEntity = produtoRepository.findBySku(sku)
                .orElseThrow(
                        () -> new RuntimeException("Produto não ENCONTRADO POR ESSE SKU")
                );
        return mapToDto(produtoEntity);
    }

    @Override
    public String deletaPorSku(String sku) {
        buscaPorSku(sku);
        produtoRepository.deleteBySku(sku);
        return "Produto deletado com sucesso";
    }

    @Override
    public Page<Produto> listarProdutos(Pageable pageable) {
        return mapToDTOS(produtoRepository.findAll(pageable));
    }

    private ProdutoEntity mapToEntity(Produto produto){
        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .sku(UUID.randomUUID().toString())
                .preco(produto.getPreco())
                .build();
    }


    private Produto mapToDto(ProdutoEntity produtoEntity){
        return Produto.builder()
                .sku(produtoEntity.getSku())
                .nome(produtoEntity.getNome())
                .preco(produtoEntity.getPreco())
                .build();
    }

    private Page<Produto> mapToDTOS(Page<ProdutoEntity> produtoEntities){
        return produtoEntities.map(this::mapToDto);
    }

}

