package br.ueg.prog.webi.vendascia.dto;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import lombok.Data;

import java.time.LocalDate;

public @Data class ProdutosDTO {
    private Long idProduto;
     private String nomeProduto;
     private String descricaoProduto;
    private Integer qtdEstoque;
    private Double valorUnidade;
 }
