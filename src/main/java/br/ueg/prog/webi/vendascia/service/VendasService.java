package br.ueg.prog.webi.vendascia.service;

import br.ueg.prog.webi.vendascia.model.Vendas;

import java.util.List;

public interface VendasService {

    public Vendas incluir(Vendas vendas);

    public Vendas alterar (Vendas vendas, Long id);

    public Vendas excluir (Long id);

    public Vendas obterVendasPeloId(Long id);

    public List<Vendas> listarTodos();
    public List<Vendas> localizarPorNomeCliente(String nomeCliente); // melhorar isso
}
