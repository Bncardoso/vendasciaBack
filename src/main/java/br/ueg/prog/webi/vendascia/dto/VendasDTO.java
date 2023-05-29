package br.ueg.prog.webi.vendascia.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

public @Data class VendasDTO {
    private Long id;
    private LocalDate dataEncomenda;
    private String nomeProduto;
    private Integer qtdVenda;
    private Double valorUnidade;
    private Double valorTotal;
    private String nomeCliente;
    private Long contatoCliente;
}
