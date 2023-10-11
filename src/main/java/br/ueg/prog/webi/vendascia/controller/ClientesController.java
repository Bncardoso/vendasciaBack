package br.ueg.prog.webi.vendascia.controller;

import br.ueg.prog.webi.api.exception.MessageResponse;
import br.ueg.prog.webi.vendascia.dto.ClientesDTO;
import br.ueg.prog.webi.vendascia.dto.ClientesDadosAlteravelDTO;
import br.ueg.prog.webi.vendascia.dto.ClientesListaDTO;
import br.ueg.prog.webi.vendascia.mapper.ClientesMapper;
import br.ueg.prog.webi.vendascia.model.Clientes;
import br.ueg.prog.webi.vendascia.service.ClientesService;
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
@RequestMapping(path = "${app.api.version}/clientes")
public class ClientesController {
    @Autowired
    private ClientesMapper clientesMapper;

    @Autowired
    private ClientesService clientesService;

    @GetMapping()
    @Operation(description = "Listagem geral de Clientes", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = ClientesDTO.class)))),
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
    public List<ClientesListaDTO> listAll(){
        List<Clientes> clientes = clientesService.listarTodos();
        return clientesMapper.toDTO(clientes);
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar inclusão de clientes", responses = {
            @ApiResponse(responseCode = "200", description = "Cliente incluso",
                    content = @Content( mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = ClientesDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
        })
    public ClientesDTO incluir(@RequestBody ClientesDadosAlteravelDTO cliente) {
        //preparando entrada
        Clientes clientesIncluir = this.clientesMapper.toModelo(cliente);

        // chamada de serviço
        System.out.println(clientesIncluir);
        clientesIncluir = this.clientesService.incluir(clientesIncluir);

        // prepando retorno
        ClientesDTO retorno = this.clientesMapper.toClientesDTO(clientesIncluir);
        return retorno;
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Metodo utilizado para alterar dados de uma cliente", responses = {
            @ApiResponse(responseCode = "200", description = "Alteração concluída",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ClientesDTO alterar(@RequestBody() ClientesDadosAlteravelDTO clientes, @PathVariable(name = "id") Long id) {
        Clientes pClientes = clientesMapper.toModelo(clientes);
        Clientes alterar = clientesService.alterar(pClientes, id);
        return clientesMapper.toClientesDTO(alterar);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Método utililzado para remover uma cliente pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cliente Removida",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ClientesDTO remover(@PathVariable(name = "id") Long id) {
        Clientes clienteExcluida = this.clientesService.excluir(id);
        return clientesMapper.toClientesDTO(clienteExcluida);
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Obter cliente pelo ID informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem pelo ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ClientesDTO ObterPorId(@PathVariable(name = "id") Long id) {
        Clientes clientes = this.clientesService.obterClientesPeloCpf(id);
        return this.clientesMapper.toClientesDTO(clientes);
    }
    @GetMapping(path = "/cliente/{nomeCliente}")
    @Operation(description = "Busca clientes pelo nome do cliente informado", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem por nome",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public List<ClientesListaDTO> localizarPorNomeCliente(@PathVariable(name = "nomeCliente") String nomeCliente) {
        List<Clientes> clientesEncontradas = clientesService.localizarPorNomeCliente(nomeCliente);
        return clientesMapper.toDTO(clientesEncontradas);
    }

}