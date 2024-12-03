package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntidadeDaoTest {

    private static DAO<Entidade> entidadeDao;
    private static Entidade entidade;

    @BeforeClass
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);

        Endereco endereco = new Endereco();
        endereco.setRua("Estrada do Campo Grande");
        endereco.setNumero("450");
        endereco.setBairro("Jardim das Flores");
        endereco.setCidade("Rio de Janeiro");
        endereco.setEstado("RJ");
        endereco.setCep("23045-678");

        entidade = new Entidade();
        entidade.setNome("Clube Esportivo do Rio");
        entidade.setCnpj("45.987.123/0001-55");
        entidade.setTelefone1("(021)99876-5432");
        entidade.setTelefone2("(021)88765-4321");
        entidade.setEndereco(endereco);

        entidadeDao = new DAOImpl<>(Entidade.class);
    }

    public static void clearDatabase() {
        List<Entidade> all = entidadeDao.list();
        for (Entidade each : all) {
            entidadeDao.delete(each);
        }
        assertEquals(0, entidadeDao.list().size());
    }

    @Test
    public void testSalvarEntidade() throws Exception {
        clearDatabase();

        entidadeDao.save(entidade);
        Entidade savedEntidade = entidadeDao.get(entidade);

        assertEquals("Clube Esportivo do Rio", savedEntidade.getNome());
        assertEquals("45.987.123/0001-55", savedEntidade.getCnpj());
        assertEquals("Estrada do Campo Grande", savedEntidade.getEndereco().getRua());
    }

    @Test
    public void testAtualizarEntidade() throws Exception {
        clearDatabase();

        entidadeDao.save(entidade);
        Entidade e = entidadeDao.get(entidade);
        e.setNome("Nova Associação Esportiva");
        entidadeDao.save(e);

        Entidade updatedEntidade = entidadeDao.get(e);
        assertEquals("Nova Associação Esportiva", updatedEntidade.getNome());
    }

    @Test
    public void testListarEntidades() {
        clearDatabase();

        int initialCount = entidadeDao.list().size();
        entidadeDao.save(new Entidade());
        entidadeDao.save(new Entidade());
        entidadeDao.save(new Entidade());

        assertEquals(initialCount + 3, entidadeDao.list().size());
    }

    @Test
    public void testPesquisarEntidade() throws Exception {
        clearDatabase();

        entidadeDao.save(entidade);

        Entidade searchCriteria = new Entidade();
        searchCriteria.setNome("Clube Esportivo do Rio");

        List<Entidade> result = entidadeDao.search(searchCriteria);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("45.987.123/0001-55", result.get(0).getCnpj());
    }

    @AfterClass
    public static void closeDatabase() {
        clearDatabase();
        DatabaseManager.close();
    }
}
