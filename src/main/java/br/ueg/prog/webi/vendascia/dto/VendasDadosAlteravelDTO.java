package br.ueg.prog.webi.vendascia.dto;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import lombok.Data;

public @Data class VendasDadosAlteravelDTO {
    private String nomeProduto;
    private Integer qtdVenda;
    private Double valorUnidade;
    private String nomeCliente;
    private Long contatoCliente;
    private StatusEncomenda statusEncomenda;
}
