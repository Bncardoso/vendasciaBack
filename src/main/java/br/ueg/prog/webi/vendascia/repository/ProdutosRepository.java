package br.ueg.prog.webi.vendascia.repository;

import br.ueg.prog.webi.vendascia.model.Produtos;
import br.ueg.prog.webi.vendascia.model.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>, ProdutosRepositoryCustom {

    Optional<Produtos> findProdutosByNomeCliente(String nomeCliente);

    @Query(value = "select count(a) from Produtos a where a.nomeCliente=:paramnome_cliente")
    Integer countUtilizacaoNomeCliente(String paramnome_cliente);
}

