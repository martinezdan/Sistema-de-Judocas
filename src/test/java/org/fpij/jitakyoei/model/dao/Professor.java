package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProfessorDaoTest {

    private static DAO<Professor> professorDao;
    private static Professor professor;
    private static Filiado filiado;
    private static Endereco endereco;

    @BeforeClass
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);

        endereco = new Endereco();
        endereco.setRua("Avenida Atlântica");
        endereco.setBairro("Copacabana");
        endereco.setCidade("Rio de Janeiro");
        endereco.setEstado("RJ");
        endereco.setCep("22070-000");

        filiado = new Filiado();
        filiado.setNome("Marcos Oliveira");
        filiado.setCpf("987.654.321-00");
        filiado.setDataNascimento(new Date(75, 5, 15));
        filiado.setDataCadastro(new Date(122, 0, 1));
        filiado.setEndereco(endereco);
        filiado.setId(2L);

        professor = new Professor();
        professor.setFiliado(filiado);

        professorDao = new DAOImpl<>(Professor.class);
    }

    public static void clearDatabase() {
        List<Professor> all = professorDao.list();
        for (Professor each : all) {
            professorDao.delete(each);
        }
        assertEquals(0, professorDao.list().size());
    }

    @Test
    public void testSalvarProfessor() throws Exception {
        clearDatabase();

        professorDao.save(professor);
        Professor savedProfessor = professorDao.get(professor);

        assertEquals("Marcos Oliveira", savedProfessor.getFiliado().getNome());
        assertEquals("987.654.321-00", savedProfessor.getFiliado().getCpf());
        assertEquals("Avenida Atlântica", savedProfessor.getFiliado().getEndereco().getRua());
    }

    @Test
    public void testAtualizarProfessor() throws Exception {
        clearDatabase();

        professorDao.save(professor);
        Professor p = professorDao.get(professor);
        p.getFiliado().setNome("Renato Pereira");
        professorDao.save(p);

        Professor updatedProfessor = professorDao.get(p);
        assertEquals("Renato Pereira", updatedProfessor.getFiliado().getNome());
    }

    @Test
    public void testListarProfessores() {
        clearDatabase();

        int initialCount = professorDao.list().size();
        professorDao.save(new Professor());
        professorDao.save(new Professor());
        professorDao.save(new Professor());

        assertEquals(initialCount + 3, professorDao.list().size());
    }

    @Test
    public void testPesquisarProfessor() throws Exception {
        clearDatabase();

        professorDao.save(professor);

        Filiado searchCriteria = new Filiado();
        searchCriteria.setNome("Marcos Oliveira");
        Professor searchProfessor = new Professor();
        searchProfessor.setFiliado(searchCriteria);

        List<Professor> result = professorDao.search(searchProfessor);
        assertEquals(1, result.size());
        assertEquals("987.654.321-00", result.get(0).getFiliado().getCpf());
    }

    @AfterClass
    public static void closeDatabase() {
        clearDatabase();
        DatabaseManager.close();
    }
}
