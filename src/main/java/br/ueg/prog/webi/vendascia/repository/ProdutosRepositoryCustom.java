package br.ueg.prog.webi.vendascia.repository;

import br.ueg.prog.webi.vendascia.model.Produtos;
import br.ueg.prog.webi.vendascia.model.Vendas;

import java.util.List;

public interface ProdutosRepositoryCustom {
    List<Produtos> localizarPorProdutos(Produtos produtos);
}
