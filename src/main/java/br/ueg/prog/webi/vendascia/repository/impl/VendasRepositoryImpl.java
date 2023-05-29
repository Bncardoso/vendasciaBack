package br.ueg.prog.webi.vendascia.repository.impl;


import br.ueg.prog.webi.vendascia.model.Vendas;
import br.ueg.prog.webi.vendascia.repository.VendasRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendasRepositoryImpl implements VendasRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Vendas> localizarPorVendas(@NotNull Vendas vendas) {
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT vendas FROM Vendas vendas");

        jpql.append(" WHERE 1=1 ");

        if (Strings.isNotEmpty(vendas.getNomeCliente())) {
            jpql.append(" AND UPPER(vendas.nomeCliente) LIKE UPPER('%' || :nomeCliente || '%')  ");
            parametros.put("nomeCliente", vendas.getNomeCliente());
        }
        if (Strings.isNotEmpty(vendas.getNomeProduto())) {
            jpql.append(" AND UPPER(vendas.nomeProduto) LIKE UPPER('%' || :nomeProduto || '%')  ");
            parametros.put("nomeProduto", vendas.getNomeProduto());
        }

        TypedQuery<Vendas> query = entityManager.createQuery(jpql.toString(), Vendas.class);
        parametros.entrySet().forEach(parametro -> query.setParameter(parametro.getKey(), parametro.getValue()));
        return query.getResultList();
    }
}