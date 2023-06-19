package br.ueg.prog.webi.vendascia.controller;

import br.ueg.prog.webi.api.exception.MessageResponse;
import br.ueg.prog.webi.vendascia.dto.VendasDTO;
import br.ueg.prog.webi.vendascia.dto.VendasDadosAlteravelDTO;
import br.ueg.prog.webi.vendascia.dto.VendasListaDTO;
import br.ueg.prog.webi.vendascia.mapper.VendasMapper;
import br.ueg.prog.webi.vendascia.model.Vendas;
import br.ueg.prog.webi.vendascia.service.VendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.Id;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.version}/vendas")
public class VendasController {
    @Autowired
    private VendasMapper vendasMapper;

    @Autowired
    private VendasService vendasService;

    @GetMapping()
    @Operation(description = "Listagem geral de Vendas", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = VendasDTO.class)))),
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
    public List<VendasListaDTO> listAll(){
        List<Vendas> vendas = vendasService.listarTodos();
        return vendasMapper.toDTO(vendas);
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar inclusão de vendas", responses = {
            @ApiResponse(responseCode = "200", description = "Venda inclusa",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = VendasDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
        })
    public VendasDTO incluir(@RequestBody VendasDadosAlteravelDTO venda) {
        //preparando entrada
        Vendas vendasIncluir = this.vendasMapper.toModelo(venda);

        // chamada de serviço
        System.out.println(vendasIncluir);
        vendasIncluir = this.vendasService.incluir(vendasIncluir);

        // prepando retorno
        VendasDTO retorno = this.vendasMapper.toVendasDTO(vendasIncluir);
        return retorno;
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Metodo utilizado para alterar dados de uma venda", responses = {
            @ApiResponse(responseCode = "200", description = "Alteração concluída",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public VendasDTO alterar(@RequestBody() VendasDadosAlteravelDTO vendas, @PathVariable(name = "id") Long id) {
        Vendas pVendas = vendasMapper.toModelo(vendas);
        Vendas alterar = vendasService.alterar(pVendas, id);
        return vendasMapper.toVendasDTO(alterar);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Método utililzado para remover uma venda pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Venda Removida",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public VendasDTO remover(@PathVariable(name = "id") Long id) {
        Vendas vendaExcluida = this.vendasService.excluir(id);
        return vendasMapper.toVendasDTO(vendaExcluida);
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter venda pelo ID informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem pelo ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public VendasDTO ObterPorId(@PathVariable(name = "id") Long id) {
        Vendas vendas = this.vendasService.obterVendasPeloId(id);
        return this.vendasMapper.toVendasDTO(vendas);
    }
    @GetMapping(path = "/cliente/{nomeCliente}")
    @Operation(description = "Busca vendas pelo nome do cliente informado", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem por nome",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public List<VendasListaDTO> localizarPorNomeCliente(@PathVariable(name = "nomeCliente") String nomeCliente) {
        List<Vendas> vendasEncontradas = vendasService.localizarPorNomeCliente(nomeCliente);
        return vendasMapper.toDTO(vendasEncontradas);
    }

}