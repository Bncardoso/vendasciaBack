package br.ueg.prog.webi.vendascia.repository.impl;


import br.ueg.prog.webi.vendascia.model.Clientes;
import br.ueg.prog.webi.vendascia.repository.ClientesRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientesRepositoryImpl implements ClientesRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Clientes> localizarPorClientes(@NotNull Clientes clientes) {
        Map<String, Object> parametros = new HashMap<>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT clientes FROM Clientes clientes");

        jpql.append(" WHERE 1=1 ");

        if (Strings.isNotEmpty(clientes.getNomeCliente())) {
            jpql.append(" AND UPPER(clientes.nomeCliente) LIKE UPPER('%' || :nomeCliente || '%')  ");
            parametros.put("nomeCliente", clientes.getNomeCliente());
        }
        if (Strings.isNotEmpty(clientes.getNomeCliente())) {
            jpql.append(" AND UPPER(clientes.nomeCliente) LIKE UPPER('%' || :nomeCliente || '%')  ");
            parametros.put("nomeCliente", clientes.getNomeCliente());
        }

        TypedQuery<Clientes> query = entityManager.createQuery(jpql.toString(), Clientes.class);
        parametros.entrySet().forEach(parametro -> query.setParameter(parametro.getKey(), parametro.getValue()));
        return query.getResultList();
    }
}