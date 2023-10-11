package br.ueg.prog.webi.vendascia.controller;

import br.ueg.prog.webi.api.exception.MessageResponse;
 import br.ueg.prog.webi.vendascia.dto.ProdutosDTO;
import br.ueg.prog.webi.vendascia.dto.ProdutosDadosAlteravelDTO;
import br.ueg.prog.webi.vendascia.dto.ProdutosListaDTO;
import br.ueg.prog.webi.vendascia.mapper.ProdutosMapper;
import br.ueg.prog.webi.vendascia.model.Produtos;
import br.ueg.prog.webi.vendascia.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.version}/produtos")
public class ProdutosController {
    @Autowired
    private ProdutosMapper produtosMapper;

    @Autowired
    private ProdutosService produtosService;

    @GetMapping()
    @Operation(description = "Listagem geral de Produtos", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = ProdutosDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public List<ProdutosListaDTO> listAll(){
        List<Produtos> produtos = produtosService.listarTodos();
        return produtosMapper.toDTO(produtos);
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar inclusão de produtos", responses = {
            @ApiResponse(responseCode = "200", description = "Produtos inclusa",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = ProdutosDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
        })
    public ProdutosDTO incluir(@RequestBody ProdutosDadosAlteravelDTO produto) {
        //preparando entrada
        Produtos produtosIncluir = this.produtosMapper.toModelo(produto);

        // chamada de serviço
        System.out.println(produtosIncluir);
        produtosIncluir = this.produtosService.incluir(produtosIncluir);

        // prepando retorno
        ProdutosDTO retorno = this.produtosMapper.toProdutosDTO(produtosIncluir);
        return retorno;
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Metodo utilizado para alterar dados de uma produto", responses = {
            @ApiResponse(responseCode = "200", description = "Alteração concluída",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ProdutosDTO alterar(@RequestBody() ProdutosDadosAlteravelDTO produtos, @PathVariable(name = "id") Long id) {
        Produtos pProdutos = produtosMapper.toModelo(produtos);
        Produtos alterar = produtosService.alterar(pProdutos, id);
        return produtosMapper.toProdutosDTO(alterar);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Método utililzado para remover uma produto pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Produto Removida",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ProdutosDTO remover(@PathVariable(name = "id") Long id) {
        Produtos produtoExcluida = this.produtosService.excluir(id);
        return produtosMapper.toProdutosDTO(produtoExcluida);
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter produto pelo ID informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem pelo ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ProdutosDTO ObterPorId(@PathVariable(name = "id") Long id) {
        Produtos produtos = this.produtosService.obterProdutosPeloIdProduto(id);
        return this.produtosMapper.toProdutosDTO(produtos);
    }
    @GetMapping(path = "/cliente/{nomeCliente}")
    @Operation(description = "Busca produtos pelo nome do cliente informado", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem por nome",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public List<ProdutosListaDTO> localizarPorNomeCliente(@PathVariable(name = "nomeCliente") String nomeCliente) {
        List<Produtos> produtosEncontradas = produtosService.localizarPorNomeProduto(nomeCliente);
        return produtosMapper.toDTO(produtosEncontradas);
    }

}