package br.ueg.prog.webi.vendascia.repository.impl;


import br.ueg.prog.webi.vendascia.model.Produtos;
import br.ueg.prog.webi.vendascia.model.Vendas;
import br.ueg.prog.webi.vendascia.repository.ProdutosRepositoryCustom;
import br.ueg.prog.webi.vendascia.repository.VendasRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutosRepositoryImpl implements ProdutosRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Produtos> localizarPorProdutos(@NotNull Produtos produtos) {
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT produtos FROM Produtos produtos");

        jpql.append(" WHERE 1=1 ");

        if (Strings.isNotEmpty(produtos.getNomeProduto())) {
            jpql.append(" AND UPPER(produtos.nomeProduto) LIKE UPPER('%' || :nomeProduto || '%')  ");
            parametros.put("nomeProduto", produtos.getNomeProduto());
        }
        if (Strings.isNotEmpty(produtos.getNomeProduto())) {
            jpql.append(" AND UPPER(produtos.nomeProduto) LIKE UPPER('%' || :nomeProduto || '%')  ");
            parametros.put("nomeProduto", produtos.getNomeProduto());
        }

        TypedQuery<Produtos> query = entityManager.createQuery(jpql.toString(), Produtos.class);
        parametros.entrySet().forEach(parametro -> query.setParameter(parametro.getKey(), parametro.getValue()));
        return query.getResultList();
    }
}