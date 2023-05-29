package br.ueg.prog.webi.vendascia.dto;

import lombok.Data;

public @Data class VendasDadosAlteravelDTO {
    private String nomeProduto;
    private Integer qtdVenda;
    private Double valorUnidade;
    private String nomeCliente;
    private Long contatoCliente;
}
