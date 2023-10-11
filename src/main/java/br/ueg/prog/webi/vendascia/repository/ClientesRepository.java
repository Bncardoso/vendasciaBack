package br.ueg.prog.webi.vendascia.repository;

import br.ueg.prog.webi.vendascia.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long>, ClientesRepositoryCustom {

    Optional<Clientes> findClientesByNomeCliente(String nomeCliente);

    @Query(value = "select count(a) from Clientes a where a.nomeCliente=:paramnome_cliente")
    Integer countUtilizacaoNomeCliente(String paramnome_cliente);
}

