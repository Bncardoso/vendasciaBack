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

    @GetMapping()
    @Operation(description = "Lista de Vendas")
    public List<VendasListaDTO> listAll() {
        List<Vendas> vendas = vendasService.listarTodos();
        return vendasMapper.toDTO(vendas);
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar inclusão de vendas")
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
    @Operation(description = "Obter venda pelo ID informado!")
    public VendasDTO ObterPorId(@PathVariable(name = "id") Long id) {
        Vendas vendas = this.vendasService.obterVendasPeloId(id);
        return this.vendasMapper.toVendasDTO(vendas);
    }
    @GetMapping(path = "/cliente/{nomeCliente}")
    @Operation(description = "Busca vendas pelo nome do cliente informado")
    public List<VendasListaDTO> pesquisarPorNomeCliente(@PathVariable(name = "nomeCliente") String nomeCliente) {
        List<Vendas> vendasEncontradas = vendasService.localizarPorNomeCliente(nomeCliente);
        return vendasMapper.toDTO(vendasEncontradas);
    }

}