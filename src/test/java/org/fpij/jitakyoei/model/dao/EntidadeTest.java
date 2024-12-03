import static org.junit.Assert.*;
import org.fpij.jitakyoei.model.beans.*;
import org.fpij.jitakyoei.util.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class EntidadeTest {
    private static Entidade entidade;
    private static Endereco endereco;

    @BeforeClass
    public static void setUp() {
        entidade = new Entidade();
        endereco = new Endereco();
    }

    @Test
    public void testEntidade() {
        entidade.setNome("aaaaaaaaaaa");        
        entidade.setCnpj("111111");
        assertEquals("111111", entidade.getCnpj());
        assertEquals("aaaaaaaaaaa", entidade.getNome());
    }
    
    @Test
    public void testEndereco() {
        endereco.setRua("bbbbbbbbbb");
        endereco.setNumero("1");
        endereco.setCidade("xx");
        endereco.setEstado("xx");
        entidade.setEndereco(endereco);
        assertEquals(endereco, entidade.getEndereco());
    }
}
