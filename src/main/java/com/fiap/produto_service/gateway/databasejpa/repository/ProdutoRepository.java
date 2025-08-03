package com.fiap.produto_service.gateway.databasejpa.repository;

import com.fiap.produto_service.gateway.databasejpa.entity.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    Optional<ProdutoEntity> findBySku(String sku);
    void deleteBySku(String sku);

    Page<ProdutoEntity> findAll(Pageable pageable);
}

