package br.ueg.prog.webi.vendascia.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/vendas")
public class VendasController {
    @Autowired
    private VendasMapper vendasMapper;

    @Autowired
    private VendasService vendasService;

    @GetMapping(path = "/listar")
    @Operation(description = "Lista de Vendas", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listagem de vendas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = VendasListaDTO.class)))
            )
    })
    public ResponseEntity<List<VendasListaDTO>> listALL() {
        List<Vendas> vendas = vendasService.listarTodos();
        return ResponseEntity.ok(vendasMapper.toDTO(vendas));
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar inclusão de vendas", responses = {@ApiResponse(responseCode = "200", description = "Venda Incluída",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = VendasDTO.class)))})

    public VendasDTO incluir(@RequestBody VendasDadosAlteravelDTO vendas) {
        //preparação para entrada.
        Vendas vendasIncluir = this.vendasMapper.toModelo(vendas);

        //chamada do serviço
        System.out.println(vendasIncluir);
        vendasIncluir = this.vendasService.incluir(vendasIncluir);

        //preparação par ao retorno
        VendasDTO retorno = this.vendasMapper.toVendasDTO(vendasIncluir);
        return retorno;
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Metodo utilizado para alterar dados de uma venda")
    public VendasDTO alterar(@RequestBody() VendasDadosAlteravelDTO vendas, @PathVariable(name = "id") Long id) {
        Vendas pVendas = vendasMapper.toModelo(vendas);
        Vendas alterar = vendasService.alterar(pVendas, id);
        return vendasMapper.toVendasDTO(alterar);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Método utililzado para remover uma venda pelo ID")
    public VendasDTO remover(@PathVariable(name = "id") Long id) {
        Vendas vendaExcluida = this.vendasService.excluir(id);
        return vendasMapper.toVendasDTO(vendaExcluida);
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter os dados completos de uma venda pelo ID informado!")
    public VendasDTO ObterPorId(@PathVariable(name = "id") Long id) {
        Vendas vendas = this.vendasService.obterVendasPeloId(id);
        return this.vendasMapper.toVendasDTO(vendas);
    }

    @PostMapping(path = "/pesquisar")
    @Operation(description = "Busca uma venda pelos dados informados")
    public List<VendasListaDTO> pesquisar(
            @RequestBody VendasDTO vendas
    ) {
        Vendas vendasBusca = this.vendasMapper.toModelo(vendas);
        List<Vendas> localizar = vendasService.localizar(vendasBusca);
        return this.vendasMapper.toDTO(localizar);
    }
}

