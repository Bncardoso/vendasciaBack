package br.ueg.prog.webi.vendascia.dto;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import lombok.Data;

public @Data class ProdutosDadosAlteravelDTO {
    private String nomeProduto;
    private String descricaoProduto;
    private Integer qtdEstoque;
    private Double valorUnidade;
 }
