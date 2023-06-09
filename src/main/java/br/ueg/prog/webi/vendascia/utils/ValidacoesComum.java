package br.ueg.prog.webi.vendascia.utils;

import br.ueg.prog.webi.vendascia.dto.VendasDTO;
import br.ueg.prog.webi.vendascia.model.enums.StatusEncomenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacoesComum {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Função para validar e-mail com expressão regular
     * @param email - email a ser validado
     * @return true se o texto passado é um e-mail válido, false caso contrário
     */
    public static boolean isEmailValido(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
