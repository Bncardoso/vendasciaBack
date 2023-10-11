package br.ueg.prog.webi.vendascia.mapper;

import br.ueg.prog.webi.vendascia.dto.*;
import br.ueg.prog.webi.vendascia.model.Produtos;
import br.ueg.prog.webi.vendascia.model.Vendas;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosMapper {
    public ProdutosListaDTO toDTO(Produtos produtos);

    public List<ProdutosListaDTO> toDTO(List<Produtos> lprodutos);

    public ProdutosDadosAlteravelDTO toProdutosIncluirDTO(Produtos produtos);

    public Produtos toModelo(ProdutosDadosAlteravelDTO produtos);

    public ProdutosDTO toProdutosDTO (Produtos produtos);

    public Produtos toModelo (ProdutosDTO produtos);


}
