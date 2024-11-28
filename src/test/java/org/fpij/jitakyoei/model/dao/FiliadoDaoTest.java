package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FiliadoDaoTest {

    private static DAO<Filiado> filiadoDao;
    private static Filiado filiado;

    @BeforeClass
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);
        filiado = new Filiado();
        filiado.setNome("Carlos Silva");
        filiado.setCpf("123.456.789-00");
        filiado.setDataNascimento(new Date());
        filiado.setDataCadastro(new Date());
        filiado.setId(1L);
        filiado.setIdade(30); // Valor válido para teste
        filiadoDao = new DAOImpl<>(Filiado.class);
    }

    public static void clearDatabase() {
        List<Filiado> all = filiadoDao.list();
        for (Filiado each : all) {
            filiadoDao.delete(each);
        }
        assertEquals(0, filiadoDao.list().size());
    }

    @Test
    public void testSalvarFiliadoComDadosValidos() throws Exception {
        clearDatabase();
        filiadoDao.save(filiado);
        Filiado resultado = filiadoDao.get(filiado);

        assertEquals("Carlos Silva", resultado.getNome());
        assertEquals("123.456.789-00", resultado.getCpf());
        assertEquals(30, resultado.getIdade());
    }

    @Test
    public void testSalvarFiliadoComIdadeInvalidaBaixa() {
        clearDatabase();

        filiado.setIdade(10); // Idade inválida
        try {
            filiadoDao.save(filiado);
            fail("Deveria lançar IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            assertEquals("Idade fora do intervalo permitido!", e.getMessage());
        }
    }

    @Test
    public void testSalvarFiliadoComIdadeInvalidaAlta() {
        clearDatabase();

        filiado.setIdade(150); // Idade inválida
        try {
            filiadoDao.save(filiado);
            fail("Deveria lançar IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            assertEquals("Idade fora do intervalo permitido!", e.getMessage());
        }
    }

    @AfterClass
    public static void closeDatabase() {
        clearDatabase();
        DatabaseManager.close();
    }
}
