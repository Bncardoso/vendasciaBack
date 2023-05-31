package br.ueg.prog.webi.vendascia.dto;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import lombok.Data;

import java.time.LocalDate;

public @Data class VendasDTO {
    private Long id;
    private LocalDate dataEncomenda;
    private Double valorTotal;
    private String nomeProduto;
    private Integer qtdVenda;
    private Double valorUnidade;
    private String nomeCliente;
    private Long contatoCliente;
    private StatusEncomenda statusEncomenda;
}
