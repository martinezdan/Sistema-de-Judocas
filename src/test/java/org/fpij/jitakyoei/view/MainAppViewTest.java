package org.fpij.jitakyoei.view;

import static org.junit.Assert.*;

import org.fpij.jitakyoei.facade.AppFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MainAppViewTest {

    private MainAppView mainAppView;
    private AppFacade facadeMock;

    @Before
    public void setUp() {
        facadeMock = Mockito.mock(AppFacade.class); 
        mainAppView = new MainAppView();
        mainAppView.registerFacade(facadeMock); 
    }

    @Test
    public void testCadastrarAlunoMenuItem() {
        try {
            mainAppView.cadastrarAlunoMenuItem();

            assertEquals(
                "Deve haver 1 aba aberta",
                1,
                mainAppView.frame.getTabbedPane().getTabCount()
            );
            assertEquals(
                "O título da aba deve ser 'Cadastrar Aluno'",
                "Cadastrar Aluno",
                mainAppView.frame.getTabbedPane().getTitleAt(0).trim()
            );
        } catch (Exception e) {
            fail("Não deve lançar exceção ao cadastrar aluno: " + e.getMessage());
        }
    }

    @Test
    public void testSobreMenuItem() {
        mainAppView.sobreMenuItem();
        assertEquals(
            "Deve abrir uma aba com informações sobre os desenvolvedores",
            "Desenvolvedores",
            mainAppView.frame.getTabbedPane().getTitleAt(0).trim()
        );
    }
}
