package br.ueg.prog.webi.vendascia.dto;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import lombok.Data;

import java.time.LocalDate;

public @Data class ClientesDTO {
    private Long cpfCliente;
    private String nomeCliente;
    private Long contatoCliente;
 }
