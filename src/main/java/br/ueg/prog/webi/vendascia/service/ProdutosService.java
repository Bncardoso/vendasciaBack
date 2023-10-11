package br.ueg.prog.webi.vendascia.service;

import br.ueg.prog.webi.vendascia.model.Produtos;

import java.util.List;

public interface ProdutosService {

    public Produtos incluir(Produtos produtos);

    public Produtos alterar (Produtos produtos, Long id);

    public Produtos excluir (Long id);

    public Produtos obterProdutosPeloIdProduto(Long id);

    public List<Produtos> listarTodos();
    public List<Produtos> localizarPorNomeProduto(String nomeProduto); // melhorar isso
}
