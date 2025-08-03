package com.fiap.produto_service.controller;

import com.fiap.produto_service.controller.json.ProdutoJson;
import com.fiap.produto_service.domain.Produto;
import com.fiap.produto_service.usecase.BuscaProdutoPorSkuUsecase;
import com.fiap.produto_service.usecase.BuscaTodosProdutosUsecase;
import com.fiap.produto_service.usecase.CriarProdutoUsecase;
import com.fiap.produto_service.usecase.DeletaProdutoUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private CriarProdutoUsecase criarProdutoUsecase;

    @Mock
    private BuscaProdutoPorSkuUsecase buscaProdutoPorSkuUsecase;

    @Mock
    private DeletaProdutoUsecase deletaProdutoUsecase;

    @Mock
    private BuscaTodosProdutosUsecase buscaTodosProdutosUsecase;

    private Produto produtoMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        produtoMock = Produto.builder()
                .sku("sku123")
                .nome("Produto Teste")
                .preco(BigDecimal.valueOf(50))
                .build();
    }

    @Test
    void deveCriarProdutoERetornar201() {
        ProdutoJson json = new ProdutoJson();
        json.setNome("Produto Teste");
        json.setPreco(BigDecimal.valueOf(50));

        when(criarProdutoUsecase.salvaProduto(any())).thenReturn(produtoMock);

        ResponseEntity<Produto> response = produtoController.criarProduto(json);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Produto Teste", response.getBody().getNome());
    }

    @Test
    void deveBuscarProdutoPorSku() {
        when(buscaProdutoPorSkuUsecase.buscarProdutoPorSku("sku123")).thenReturn(produtoMock);

        ResponseEntity<Produto> response = produtoController.buscarPorSku("sku123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto Teste", response.getBody().getNome());
    }

    @Test
    void deveDeletarProdutoPorSku() {
        when(deletaProdutoUsecase.deletaProdutoPorSku("sku123")).thenReturn("Produto deletado com sucesso");

        ResponseEntity<String> response = produtoController.deletarPorSku("sku123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto deletado com sucesso", response.getBody());
    }

    @Test
    void deveListarProdutosComPaginacao() {
        Page<Produto> page = new PageImpl<>(List.of(produtoMock));

        when(buscaTodosProdutosUsecase.listarProdutos(any())).thenReturn(page);

        ResponseEntity<Page<Produto>> response = produtoController.buscaProdutos(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getContent().size());
        assertEquals("Produto Teste", response.getBody().getContent().get(0).getNome());
    }
}

