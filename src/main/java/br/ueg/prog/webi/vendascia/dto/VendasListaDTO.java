package br.ueg.prog.webi.vendascia.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data class VendasListaDTO {
    private Long id;
    private LocalDate dataEncomenda;
    private String nomeProduto;
    private String qtdVenda;
    private Double valorUnidade;
    private Double valorTotal;
    private String nomeCliente;
    private Long contatoCliente;
}
