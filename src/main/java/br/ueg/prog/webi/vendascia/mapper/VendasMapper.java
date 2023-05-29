package br.ueg.prog.webi.vendascia.mapper;

import br.ueg.prog.webi.vendascia.dto.VendasDTO;
import br.ueg.prog.webi.vendascia.dto.VendasDadosAlteravelDTO;
import br.ueg.prog.webi.vendascia.dto.VendasListaDTO;
import br.ueg.prog.webi.vendascia.model.Vendas;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendasMapper {
    public VendasListaDTO toDTO(Vendas vendas);

    public List<VendasListaDTO> toDTO(List<Vendas> lvendas);

    public VendasDadosAlteravelDTO toVendasIncluirDTO(Vendas vendas);

    public Vendas toModelo(VendasDadosAlteravelDTO vendas);

    public VendasDTO toVendasDTO (Vendas vendas);

    public Vendas toModelo (VendasDTO vendas);


}
