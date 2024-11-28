package org.fpij.jitakyoei.model.validation;

import org.fpij.jitakyoei.model.beans.Filiado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FiliadoValidationTest {

    @Test
    public void testValidIdade() {
        Filiado f = new Filiado();
        f.setIdade(75); // Valor válido
        assertEquals(75, f.getIdade());
    }

    @Test
    public void testIdadeInvalidaBaixa() {
        Filiado f = new Filiado();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.setIdade(49); // Valor inválido
        });
        assertEquals("Idade fora do intervalo permitido!", exception.getMessage());
    }

    @Test
    public void testIdadeInvalidaAlta() {
        Filiado f = new Filiado();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.setIdade(102); // Valor inválido
        });
        assertEquals("Idade fora do intervalo permitido!", exception.getMessage());
    }
}
