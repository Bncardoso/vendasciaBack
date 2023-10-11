package br.ueg.prog.webi.vendascia.service;

import br.ueg.prog.webi.vendascia.model.Clientes;

import java.util.List;

public interface ClientesService {

    public Clientes incluir(Clientes clientes);

    public Clientes alterar (Clientes clientes, Long id);

    public Clientes excluir (Long id);

    public Clientes obterClientesPeloCpf(Long id);

    public List<Clientes> listarTodos();
    public List<Clientes> localizarPorNomeCliente(String nomeCliente); // melhorar isso
}
