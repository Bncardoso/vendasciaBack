/*package br.ueg.prog.webi.vendascia.model.enums.converter;

import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
 @Converter(autoApply = true)
    public class StatusEncomendaConverter implements AttributeConverter<StatusEncomenda, String> {

        @Override
        public String convertToDatabaseColumn(StatusEncomenda status) {
            return status != null ? status.getId() : null;
        }

        @Override
        public StatusEncomenda convertToEntityAttribute(String id) {
            return StatusEncomenda.getById(id);
        }

 }*/
