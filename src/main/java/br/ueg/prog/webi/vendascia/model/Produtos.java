package br.ueg.prog.webi.vendascia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_PRODUTOS")

public class Produtos {
    @Id
    @Column(name = "codigo")
    private Long idProduto;

    @Column(name = "produto", length = 200, nullable = false)
    private String nomeProduto;

    @Column(name = "descricao", length = 200, nullable = false)
    private String descricaoProduto;

    @Column(name = "valor_unidade" , nullable = false)
    private Double valorUnidade;

    @Column(name = "estoque" , nullable = false)
    private Integer qtdEstoque;


    /*

    ****Implementar para atualização de melhoria ******

    @Column(name = "status")
    private Boolean status;*/

}
