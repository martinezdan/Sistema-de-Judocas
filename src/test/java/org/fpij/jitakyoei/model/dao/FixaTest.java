import static org.junit.Assert.*;
import org.fpij.jitakyoei.model.beans.*;
import org.fpij.jitakyoei.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class FaixaTest {
    private static Faixa faixa;
    private static Filiado filiado;
    private static Aluno aluno;
    private static List<Faixa> faixas;
    private static CorFaixa cor;

    @BeforeClass
    public static void setUp() {
        faixa = new Faixa();
        filiado = new Filiado();
        aluno = new Aluno();
        faixas = new ArrayList<>();
        
    }

     @Test
    public void testCor() {        
        cor = CorFaixa.AZUL;
        faixa.setCor(cor);
        assertEquals(cor, faixa.getCor());
    }

    @Test
    public void testFaixaAluno() {
        cor = CorFaixa.AZUL;
        
        filiado.setNome("aaaaaaaaaaaaaa");
        
        
        faixa.setCor(cor);
        faixas.add(faixa);
        filiado.setFaixas(faixas);

        aluno.setFiliado(filiado);
        
        assertEquals(faixas, aluno.getFiliado().getFaixas());
    }
}
