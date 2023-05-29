package br.ueg.prog.webi.vendascia.repository;

import br.ueg.prog.webi.vendascia.model.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VendasRepository  extends JpaRepository<Vendas, Long>, VendasRepositoryCustom {

    Optional<Vendas> findVendasByNomeCliente(String nomeCliente);

    @Query(value = "select count(a) from Vendas a where a.nomeCliente=:paramnome_cliente")
    Integer countUtilizacaoNomeCliente(String paramnome_cliente);
}

