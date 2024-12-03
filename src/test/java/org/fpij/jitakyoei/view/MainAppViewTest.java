package org.fpij.jitakyoei.view;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MainAppViewTest {

    private MainAppView mainAppView;

    @Before
    public void setUp() {
        mainAppView = new MainAppView();
    }

    @Test
    public void testComponentInitialization() {
        assertNotNull("Menu should be initialized", mainAppView.getMenu());
        assertNotNull("Toolbar should be initialized", mainAppView.getToolbar());
    }

    @Test
    public void testMenuAction() {

        mainAppView.getMenu().getItem(0).doClick();

    }


}
