package br.ueg.prog.webi.vendascia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table ( name = "TBL_VENDAS")

public class Vendas {
    @SequenceGenerator(
            name = "v_gerador_sequence",
            sequenceName = "vendas_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "v_gerador_sequence"
    )
    @Id
    @Column(name =  "codigo")
    private Long id;

    @Column(name =  "data_encomenda")
    private LocalDate dataEncomenda;

    @Column(name =  "produto", length = 200, nullable = false)
    private String nomeProduto;

    @Column(name =  "qtd_venda", nullable = false)
    private Integer qtdVenda;

    @Column(name =  "valor_unidade")
    private Double valorUnidade;

    @Column(name =  "valor_total")
    private Double valorTotal;

    @Column(name =  "nome_cliente", length = 200, nullable = false)
    private String nomeCliente;

    @Column(name =  "contato_cliente")
    private Long contatoCliente;


    /*

    ****Implementar para atualização de melhoria ******

    @Column(name = "status")
    private Boolean status;*/

}
