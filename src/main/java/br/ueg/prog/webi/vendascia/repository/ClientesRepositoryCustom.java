package br.ueg.prog.webi.vendascia.repository;

import br.ueg.prog.webi.vendascia.model.Clientes;

import java.util.List;

public interface ClientesRepositoryCustom {
    List<Clientes> localizarPorClientes(Clientes clientes);
}
