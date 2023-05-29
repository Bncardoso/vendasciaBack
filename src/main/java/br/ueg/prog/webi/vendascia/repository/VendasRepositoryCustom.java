package br.ueg.prog.webi.vendascia.repository;

import br.ueg.prog.webi.vendascia.model.Vendas;

import java.util.List;

public interface VendasRepositoryCustom {
    List<Vendas> localizarPorVendas(Vendas vendas);
}
