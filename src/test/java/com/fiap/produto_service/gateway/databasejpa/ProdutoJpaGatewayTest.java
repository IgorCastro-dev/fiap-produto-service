package com.fiap.produto_service.gateway.databasejpa;

import com.fiap.produto_service.domain.Produto;
import com.fiap.produto_service.gateway.databasejpa.entity.ProdutoEntity;
import com.fiap.produto_service.gateway.databasejpa.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoJpaGatewayTest {

    @InjectMocks
    private ProdutoJpaGateway produtoJpaGateway;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarProduto() {
        Produto produto = Produto.builder()
                .nome("Camiseta")
                .preco(BigDecimal.valueOf(99.90))
                .build();

        ProdutoEntity savedEntity = ProdutoEntity.builder()
                .sku(UUID.randomUUID().toString())
                .nome("Camiseta")
                .preco(BigDecimal.valueOf(99.90))
                .build();

        when(produtoRepository.save(any())).thenReturn(savedEntity);

        Produto result = produtoJpaGateway.criar(produto);

        assertNotNull(result);
        assertEquals("Camiseta", result.getNome());
        assertEquals(savedEntity.getPreco(), result.getPreco());
    }

    @Test
    void deveBuscarProdutoPorSku() {
        String sku = "abc-123";
        ProdutoEntity produtoEntity = ProdutoEntity.builder()
                .sku(sku)
                .nome("Tênis")
                .preco(BigDecimal.valueOf(150))
                .build();

        when(produtoRepository.findBySku(sku)).thenReturn(Optional.of(produtoEntity));

        Produto result = produtoJpaGateway.buscaPorSku(sku);

        assertEquals("Tênis", result.getNome());
        assertEquals(BigDecimal.valueOf(150), result.getPreco());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarProduto() {
        when(produtoRepository.findBySku("sku-invalido")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                produtoJpaGateway.buscaPorSku("sku-invalido")
        );

        assertEquals("Produto não ENCONTRADO POR ESSE SKU", exception.getMessage());
    }

    @Test
    void deveDeletarProdutoPorSku() {
        String sku = "sku123";
        ProdutoEntity produtoEntity = ProdutoEntity.builder()
                .sku(sku)
                .nome("Notebook")
                .preco(BigDecimal.valueOf(3500))
                .build();

        when(produtoRepository.findBySku(sku)).thenReturn(Optional.of(produtoEntity));
        doNothing().when(produtoRepository).deleteBySku(sku);

        String result = produtoJpaGateway.deletaPorSku(sku);

        assertEquals("Produto deletado com sucesso", result);
        verify(produtoRepository).deleteBySku(sku);
    }

    @Test
    void deveListarProdutosComPaginacao() {
        ProdutoEntity produto1 = ProdutoEntity.builder().sku("1").nome("P1").preco(BigDecimal.TEN).build();
        ProdutoEntity produto2 = ProdutoEntity.builder().sku("2").nome("P2").preco(BigDecimal.ONE).build();
        Page<ProdutoEntity> page = new PageImpl<>(List.of(produto1, produto2));

        when(produtoRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Produto> result = produtoJpaGateway.listarProdutos(PageRequest.of(0, 10));

        assertEquals(2, result.getContent().size());
        assertEquals("P1", result.getContent().get(0).getNome());
    }
}
