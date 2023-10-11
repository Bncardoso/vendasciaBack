package br.ueg.prog.webi.vendascia.service.impl;


import br.ueg.prog.webi.vendascia.model.Produtos;
import br.ueg.prog.webi.vendascia.repository.ProdutosRepository;
import br.ueg.prog.webi.vendascia.service.ProdutosService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProdutosServiceImpl implements ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;
    @Override
    public Produtos incluir(Produtos produtos) {
        this.validarCamposObrigatorios(produtos);
        this.validarDados(produtos);
        Produtos produtoIncluida = this.gravarDados(produtos);
        return produtoIncluida;
    }
    private Produtos gravarDados(Produtos produtos) {
        return produtosRepository.save(produtos);
    }

    private void validarDados(Produtos produtos) {
        List<String> erros = new ArrayList<>();

        if(!erros.isEmpty()){
            throw  new IllegalArgumentException("Erro ao Validar os dados "+
                    String.join(",", erros)
                    );
        }
    }

    private void validarCamposObrigatorios(Produtos produtos) {
        //Precisa colocar validação para quantidade e valor do produto
        if(Objects.isNull(produtos)){
            throw  new IllegalArgumentException("Entidade Produtos deve ser preenchida");
        }
        List<String> camposVazios = new ArrayList<>();
        if(Strings.isEmpty(produtos.getNomeProduto())){
            camposVazios.add("Nome do Produto");
        }
        if(Strings.isEmpty(produtos.getNomeProduto())){
            camposVazios.add("Nome do Cliente");
        }
        if(produtos.getQtdEstoque() == 0){
            camposVazios.add("Quantidade vendida");
        }
        if(!camposVazios.isEmpty()) {
            throw  new IllegalArgumentException(
                    "Campos Obrigatórios não preenchidos ("+
                            String.join(",",camposVazios)+")"
            );
        }
    }

    @Override
    public Produtos alterar(Produtos produtos, Long id) {
        this.validarCamposObrigatorios(produtos);
        Produtos produtosBD = recuperarProdutosOuGeraErro(id);
        produtos.setIdProduto(id);
        this.validarDados(produtos);

        Produtos save = produtosRepository.save(produtos);
        return save;
    }
    private Produtos recuperarProdutosOuGeraErro(Long id) {
        Produtos produtosBD = produtosRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Erro ao Localizar o aluno para alteração")
                );
        return produtosBD;
    }


    @Override
    public Produtos excluir(Long id) {
        Produtos produtosDelete = this.recuperarProdutosOuGeraErro(id);
        this.produtosRepository.delete(produtosDelete);
        return produtosDelete;
    }

    @Override
    public Produtos obterProdutosPeloIdProduto(Long id) {
        return this.recuperarProdutosOuGeraErro(id);
    }

    @Override
    public List<Produtos> listarTodos() {
        return produtosRepository.findAll();
    }

    @Override
    public List<Produtos> localizarPorNomeProduto(String nomeCliente) {
        return listarTodos()
                .stream()
                .filter(produto -> produto.getNomeProduto().equals(nomeCliente))
                .collect(Collectors.toList());
    }
}
