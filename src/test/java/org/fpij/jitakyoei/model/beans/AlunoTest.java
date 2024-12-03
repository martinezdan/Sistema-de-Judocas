package org.fpij.jitakyoei;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;

public class AlunoTest {
    private static Entidade ent;
    private static Professor prof;
    private static Filiado fil;
    private static Aluno alu;

    @BeforeClass
    public static void setEnvironment(){
        fil = new Filiado();
        fil.setNome("FiliadoTeste");
        fil.setId(101L);

        ent = new Entidade();
        ent.setNome("EntidadeTeste");
        ent.setCnpj("987654321");

        prof = new Professor();
        Filiado profFiliado = new Filiado();
        profFiliado.setNome("ProfessorTeste");
        profFiliado.setId(202L);
        prof.setFiliado(profFiliado);

        alu = new Aluno();
        alu.setEntidade(ent);
        alu.setFiliado(fil);
        alu.setProfessor(prof);
    }

    @Test
    public void GetESetFiliado(){
        Aluno a = new Aluno();
        Filiado novoFiliado = new Filiado();
        novoFiliado.setNome("NovoFiliado");
        novoFiliado.setId(102L);

        a.setFiliado(novoFiliado);
        assertEquals(novoFiliado, a.getFiliado());
    }

    @Test
    public void GetESetProfessor(){
        Aluno a = new Aluno();
        Professor novoProf = new Professor();
        Filiado profFiliado = new Filiado();
        profFiliado.setNome("NovoProfessor");
        profFiliado.setId(203L);
        novoProf.setFiliado(profFiliado);

        a.setProfessor(novoProf);
        assertEquals(novoProf, a.getProfessor());
    }

    @Test
    public void GetESetEntidade(){
        Aluno a = new Aluno();
        Entidade novaEntidade = new Entidade();
        novaEntidade.setNome("NovaEntidade");
        novaEntidade.setCnpj("123456789");

        a.setEntidade(novaEntidade);
        assertEquals(novaEntidade, a.getEntidade());
    }

    @Test
    public void VerificarRetornoDoToString(){
        Aluno a = new Aluno();
        a.setFiliado(fil);

        assertNotEquals("", a.toString());
    }

    @Test
    public void TestaIgualdadeDeAlunoPorId(){
        Aluno a = new Aluno();
        a.setFiliado(fil);
        a.setProfessor(prof);
        a.setEntidade(ent);

        assertEquals(true, a.equals(alu));
    }

    @Test
    public void TestaDesigualdadeDeAlunoPorId(){
        Aluno a = new Aluno();
        Filiado outroFiliado = new Filiado();
        outroFiliado.setNome("OutroFiliado");
        outroFiliado.setId(999L);

        a.setFiliado(outroFiliado);
        a.setProfessor(prof);
        a.setEntidade(ent);

        assertEquals(false, a.equals(alu));
    }

    @Test
    public void TestaDesigualdadeDeAluno(){
        assertEquals(false, alu.equals(prof));
    }

    @Test
    public void VerificaHashCode(){
        alu.getFiliado().setId(1500L);
        
        int valHash = 31 * 7 + (int)(long) alu.getFiliado().getId();
        valHash = valHash * 31 + (alu.getEntidade() != null ? alu.getEntidade().hashCode() : 0);
        assertEquals(valHash, alu.hashCode());
    }

    @Test
    public void VerificaHashCodeNull(){
        alu.getFiliado().setId(null);
        alu.setEntidade(null);
        
        int valHash = 31 * 7;
        assertEquals(valHash, alu.hashCode());
    }

    @Test
    public void CopiaAlunoParaOutro(){
        Aluno a = new Aluno();
        Entidade novaEntidade = new Entidade();
        novaEntidade.setNome("CopiaEntidade");
        a.setEntidade(novaEntidade);

        Filiado f = new Filiado();
        f.setNome("CopiaFiliado");
        a.setFiliado(f);

        a.setProfessor(prof);

        alu.copyProperties(a);

        assertEquals(true, alu.equals(a));
    }

    @Test
    public void TestaNuloNoToString(){
        Aluno a = new Aluno();
        a.setFiliado(null);

        assertNotEquals("", a.toString());
    }
}
