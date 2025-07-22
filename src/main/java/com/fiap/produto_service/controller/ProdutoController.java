package com.fiap.produto_service.controller;

import com.fiap.produto_service.controller.json.ProdutoJson;
import com.fiap.produto_service.domain.Produto;
import com.fiap.produto_service.usecase.BuscaProdutoPorSkuUsecase;
import com.fiap.produto_service.usecase.BuscaTodosProdutosUsecase;
import com.fiap.produto_service.usecase.CriarProdutoUsecase;
import com.fiap.produto_service.usecase.DeletaProdutoUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/produto")
@Tag(name = "Cadastro de Produto", description = "Um endpoint de cadastro de produto")
public class ProdutoController {
    @Autowired
    private CriarProdutoUsecase criarProdutoUsecase;
    @Autowired
    private BuscaProdutoPorSkuUsecase buscaProdutoPorSkuUsecase;
    @Autowired
    private DeletaProdutoUsecase deletaProdutoUsecase;
    @Autowired
    private BuscaTodosProdutosUsecase buscaTodosProdutosUsecase;

    @Operation(description = "Cria um produto novo")
    @ApiResponse(responseCode = "201",description = "Produto cadastrado com sucesso")
    @ApiResponse(responseCode = "500",description = "Erro no servidor ao cria o produto")
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody ProdutoJson produtoJson){
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProdutoUsecase.salvaProduto(mapToEntity(produtoJson)));
    }

    @Operation(description = "Buscar produto por cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "400", description = "Não existe produto com esse cpf"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao buscar o produto")
    })
    @GetMapping("/{sku}")
    public ResponseEntity<Produto> buscarPorSku(@Valid @NotNull @PathVariable UUID sku){
        return ResponseEntity.status(HttpStatus.OK).body(buscaProdutoPorSkuUsecase.buscarProdutoPorSku(sku));
    }

    @Operation(description = "Deleta produto por sku")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado"),
            @ApiResponse(responseCode = "400", description = "Não existe produto com esse cpf"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao deletar o produto")
    })
    @DeleteMapping("/{sku}")
    public ResponseEntity<String> deletarPorSku(@Valid @NotNull @PathVariable UUID sku){
        return ResponseEntity.status(HttpStatus.OK).body(deletaProdutoUsecase.deletaProdutoPorSku(sku));
    }

    @Operation(description = "Buscar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor ao buscar os produtos")
    })
    @GetMapping
    public ResponseEntity<Page<Produto>> buscaProdutos(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(buscaTodosProdutosUsecase.listarProdutos(pageable));
    }

    private Produto mapToEntity(ProdutoJson produtoJson){
        return Produto.builder()
                .nome(produtoJson.getNome())
                .preco(produtoJson.getPreco())
                .build();
    }

}