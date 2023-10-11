package br.ueg.prog.webi.vendascia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_CLIENTES")

public class Clientes {
    @Id
    @Column(name = "cpf")
    private Long cpfCliente;

    @Column(name = "cliente", length = 200, nullable = false)
    private String nomeCliente;

    @Column(name = "contato_cliente")
    private Long contato_cliente;
    /*

    ****Implementar para atualização de melhoria ******

    @Column(name = "status")
    private Boolean status;*/

}
