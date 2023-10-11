package br.ueg.prog.webi.vendascia.service.impl;


import br.ueg.prog.webi.vendascia.model.Clientes;
import br.ueg.prog.webi.vendascia.repository.ClientesRepository;
import br.ueg.prog.webi.vendascia.service.ClientesService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;
    @Override
    public Clientes incluir(Clientes clientes) {
        this.validarCamposObrigatorios(clientes);
        this.validarDados(clientes);
        Clientes clienteIncluida = this.gravarDados(clientes);
        return clienteIncluida;
    }
    private Clientes gravarDados(Clientes clientes) {
        return clientesRepository.save(clientes);
    }

    private void validarDados(Clientes clientes) {
        List<String> erros = new ArrayList<>();

        if(!erros.isEmpty()){
            throw  new IllegalArgumentException("Erro ao Validar os dados "+
                    String.join(",", erros)
                    );
        }
    }

    private void validarCamposObrigatorios(Clientes clientes) {
        //Precisa colocar validação para quantidade e valor do cliente
        if(Objects.isNull(clientes)){
            throw  new IllegalArgumentException("Entidade Clientes deve ser preenchida");
        }
        List<String> camposVazios = new ArrayList<>();
        if(Strings.isEmpty(clientes.getNomeCliente())){
            camposVazios.add("Nome do Cliente");
        }
        if(clientes.getCpfCliente() == 0){
            camposVazios.add("Quantidade vendida");
        }
        if(!camposVazios.isEmpty()) {
            throw  new IllegalArgumentException(
                    "Campos Obrigatórios não preenchidos ("+
                            String.join(",",camposVazios)+")"
            );
        }
    }

    @Override
    public Clientes alterar(Clientes clientes, Long id) {
        this.validarCamposObrigatorios(clientes);
        Clientes clientesBD = recuperarClientesOuGeraErro(id);
        clientes.setCpfCliente(id);
        this.validarDados(clientes);

        Clientes save = clientesRepository.save(clientes);
        return save;
    }
    private Clientes recuperarClientesOuGeraErro(Long id) {
        Clientes clientesBD = clientesRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Erro ao Localizar o aluno para alteração")
                );
        return clientesBD;
    }


    @Override
    public Clientes excluir(Long id) {
        Clientes clientesDelete = this.recuperarClientesOuGeraErro(id);
        this.clientesRepository.delete(clientesDelete);
        return clientesDelete;
    }

    @Override
    public Clientes obterClientesPeloCpf(Long id) {
        return this.recuperarClientesOuGeraErro(id);
    }

    @Override
    public List<Clientes> listarTodos() {
        return clientesRepository.findAll();
    }

    @Override
    public List<Clientes> localizarPorNomeCliente(String nomeCliente) {
        return listarTodos()
                .stream()
                .filter(cliente -> cliente.getNomeCliente().equals(nomeCliente))
                .collect(Collectors.toList());
    }
}
