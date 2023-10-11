package br.ueg.prog.webi.vendascia.mapper;

import br.ueg.prog.webi.vendascia.dto.ClientesDTO;
import br.ueg.prog.webi.vendascia.dto.ClientesDadosAlteravelDTO;
import br.ueg.prog.webi.vendascia.dto.ClientesListaDTO;
import br.ueg.prog.webi.vendascia.model.Clientes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientesMapper {
    public ClientesListaDTO toDTO(Clientes clientes);

    public List<ClientesListaDTO> toDTO(List<Clientes> lclientes);

    public ClientesDadosAlteravelDTO toClientesIncluirDTO(Clientes clientes);

    public Clientes toModelo(ClientesDadosAlteravelDTO clientes);

    public ClientesDTO toClientesDTO (Clientes clientes);

    public Clientes toModelo (ClientesDTO clientes);


}
