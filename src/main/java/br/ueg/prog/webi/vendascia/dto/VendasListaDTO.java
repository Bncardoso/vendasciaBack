package br.ueg.prog.webi.vendascia.dto;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import lombok.Data;

import java.time.LocalDate;

public @Data class VendasListaDTO {
    private Long id;
    private LocalDate dataEncomenda;
    private String nomeProduto;
    private Integer qtdVenda;
    private Double valorUnidade;
    private Double valorTotal;
    private String nomeCliente;
    private Long contatoCliente;
    private StatusEncomenda statusEncomenda;
}
