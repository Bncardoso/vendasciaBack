package br.ueg.prog.webi.vendascia.service.impl;


import br.ueg.prog.webi.vendascia.model.Vendas;
import br.ueg.prog.webi.vendascia.repository.VendasRepository;
import br.ueg.prog.webi.vendascia.service.VendasService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VendasServiceImpl implements VendasService {

    @Autowired
    private VendasRepository vendasRepository;
    @Override
    public Vendas incluir(Vendas vendas) {
        this.validarCamposObrigatorios(vendas);
        this.validarDados(vendas);
        Vendas vendaIncluida = this.gravarDados(vendas);
        return vendaIncluida;
    }
    private Vendas gravarDados(Vendas vendas) {
        return vendasRepository.save(vendas);
    }

    private void validarDados(Vendas vendas) {
        List<String> erros = new ArrayList<>();

        if(!erros.isEmpty()){
            throw  new IllegalArgumentException("Erro ao Validar os dados "+
                    String.join(",", erros)
                    );
        }
    }

    private void validarCamposObrigatorios(Vendas vendas) {
        //Precisa colocar validação para quantidade e valor do produto
        if(Objects.isNull(vendas)){
            throw  new IllegalArgumentException("Entidade Vendas deve ser preenchida");
        }
        List<String> camposVazios = new ArrayList<>();
        if(Strings.isEmpty(vendas.getNomeProduto())){
            camposVazios.add("Nome do Produto");
        }
        if(Strings.isEmpty(vendas.getNomeCliente())){
            camposVazios.add("Nome do Cliente");
        }
        if(vendas.getQtdVenda() == 0){
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
    public Vendas alterar(Vendas vendas, Long id) {
        this.validarCamposObrigatorios(vendas);
        Vendas vendasBD = recuperarVendasOuGeraErro(id);
        vendas.setId(id);
        this.validarDados(vendas);
        // vendas.setDataEncomenda(LocalDate.now());
        Vendas save = vendasRepository.save(vendas);
        return save;
    }
    private Vendas recuperarVendasOuGeraErro(Long id) {
        Vendas vendasBD = vendasRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Erro ao Localizar o aluno para alteração")
                );
        return vendasBD;
    }


    @Override
    public Vendas excluir(Long id) {
        Vendas vendasDelete = this.recuperarVendasOuGeraErro(id);
        this.vendasRepository.delete(vendasDelete);
        return vendasDelete;
    }

    @Override
    public Vendas obterVendasPeloId(Long id) {
        return this.recuperarVendasOuGeraErro(id);
    }

    @Override
    public List<Vendas> listarTodos() {
        return vendasRepository.findAll();
    }

    @Override
    public List<Vendas> localizar(Vendas vendas) {
        return this.vendasRepository.localizarPorVendas(vendas);
    }
}
