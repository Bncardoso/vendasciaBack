package br.ueg.prog.webi.vendascia.model;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_VENDAS")

public class Vendas {
    @SequenceGenerator(name = "v_gerador_sequence", sequenceName = "vendas_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "v_gerador_sequence")
    @Id
    @Column(name = "codigo")
    private Long id;

    @Column(name = "data_encomenda")
    private LocalDate dataEncomenda;

    @Column(name = "produto", length = 200, nullable = false)
    private String nomeProduto;

    @Column(name = "qtd_venda", nullable = false)
    private Integer qtdVenda;

    @Column(name = "valor_unidade" , nullable = false)
    private Double valorUnidade;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "nome_cliente", length = 200, nullable = false)
    private String nomeCliente;

    @Column(name = "contato_cliente")
    private Long contatoCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_encomenda", length = 10)
    private StatusEncomenda statusEncomenda;
    @Column
    public void setTotal(Integer qtdVenda, Double valorUnidade) {
        this.qtdVenda = qtdVenda;
        this.valorUnidade = valorUnidade;
        this.valorTotal = qtdVenda * valorUnidade;
    }
    /*

    ****Implementar para atualização de melhoria ******

    @Column(name = "status")
    private Boolean status;*/

}
