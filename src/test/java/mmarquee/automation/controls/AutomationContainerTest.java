/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation.controls;

import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.controls.ribbon.AutomationRibbonBar;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationContainerTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationContainerTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetTab() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTab tab = window.getTab(0);

            String m = tab.name();

            assertTrue(m.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetEditBox_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationEditBox tab = window.getEditBox(0);

            String m = tab.getValue();

            assertTrue(m.equals("Edit1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            String name = grid.name();
            assertTrue(name.equals("AutomationStringGrid1"));
        } finally {
            closeApplication();
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetDataGrid_By_Index_Fails_When_No_Grid() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(1);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetToolBar_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationToolBar toolbar = window.getToolBar(0);

            String name = toolbar.name();
            assertTrue(name.equals("ToolBar1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRibbonBar() throws Exception {
        loadApplication("explorer", "File Explorer");

        try {
            AutomationRibbonBar ribbon = window.getRibbonBar();

            String name = ribbon.name();

            assertTrue(name.equals("UIRibbonDockTop"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCombobox_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationComboBox cb1 = window.getCombobox(0);

            String name = cb1.name();

            logger.info(name);

            assertTrue(name.equals("AutomatedCombobox2"));
        } finally {
            closeApplication();
        }
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCombobox_By_Index_Errors_When_Too_Big() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationComboBox cb1 = window.getCombobox(99);

            String name = cb1.name();

            logger.info(name);

            assertTrue(name.equals("AutomatedCombobox2"));
        } finally {
            closeApplication();
        }
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCheckBox_By_Index_Fails_When_Index_No_Present() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationCheckbox cb1 = window.getCheckbox(99);

            String name = cb1.name();

            logger.info(name);

            assertTrue(name.equals("AutomatedCombobox1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCheckBox_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationCheckbox cb1 = window.getCheckbox(0);

            String name = cb1.name();

            logger.info(name);

            assertTrue(name.equals("Second"));
        } finally {
            closeApplication();
        }
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetRadioButton_By_Index_Fails_When_Index_No_Present() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationRadioButton rb1 = window.getRadioButton(99);

            String name = rb1.name();

            logger.info(name);

            assertTrue(name.equals("AutomatedCombobox1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRadioButton_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationRadioButton rb1 = window.getRadioButton(0);

            String name = rb1.name();

            logger.info(name);

            assertTrue(name.equals("Radio 2"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetListItem_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationList li1 = window.getListItem(0);

            AutomationListItem item = li1.getItem("First (List)");

            String name = item.name();

            logger.info(name);

            assertTrue(name.equals("First (List)"));
        } finally {
            closeApplication();
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    @Ignore // failed to find window
    public void testgetCalendar_By_Index_Throws_Exception_Whwn_Not_Found() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationCalendar cal = window.getCalendar(0);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCalendar_By_Index() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationTab tab = window.getTab(0);

            tab.selectTabPage("Calendar");

            AutomationCalendar calendar = window.getCalendar(0);

            String name = calendar.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetTab_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTab tab = window.getTab(0);

            String name = tab.name();

            logger.info(name);

            assertTrue(name.equals(""));

        } finally {
            closeApplication();
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTab_By_Index_When_Not_Present() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTab tab = window.getTab(99);

            String name = tab.name();

            logger.info(name);

            assertFalse(name.equals(""));

        } finally {
            closeApplication();
        }
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetHyperlink_By_Index_Fails_When_Index_No_Present() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationHyperlink hl1 = window.getHyperlink(99);

            String name = hl1.name();

            logger.info(name);

            assertTrue(name.equals("AutomatedCombobox1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetHyperlink_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationHyperlink hl1 = window.getHyperlink(0);

            String name = hl1.name();

            logger.info(name);

            assertTrue(name.equals("This is a link"));
        } finally {
            closeApplication();
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testList_By_Index_Throws_Exception_When_Not_found() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationList hl1 = window.getListItem(99);

            String name = hl1.name();

            logger.info(name);

            assertTrue(name.equals("This is a link"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testListItem_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTreeView li1 = window.getTreeView(0);

            String name = li1.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testTreeView_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTreeView tv1 = window.getTreeView(0);

            String name = tv1.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }


    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetToolbar_By_Index_Fails_When_Not_Found() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationToolBar toolbar = window.getToolBar(99);

            String name = toolbar.name();

            logger.info(name);

            assertTrue(name.equals("This is a link"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetToolbar_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationToolBar toolbar = window.getToolBar(1);

            String name = toolbar.name();

            logger.info(name);

            assertTrue(name.equals("ToolBar1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore("Still fails to find the window")
    public void testGetSlider_By_Index() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationSlider slider = window.getSlider(0);

            String name = slider.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore("Fails for some reason")
    public void testGetProgress_By_Index() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationProgressBar progressBar = window.getProgressBar(0);

            String name = progressBar.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetDocument_By_Index() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationTab tab = window.getTab(0);

            tab.selectTabPage("Document");

            AutomationDocument doc = window.getDocument(0);

            String name = doc.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Not_Found() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationProgressBar pbar = window.getProgressBar(99);

            String name = pbar.name();

            logger.info(name);

            assertTrue(name.equals("ToolBar1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore("Fails for unexplained reasons")
    public void testGetMaskedEdit_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationMaskedEdit me0 = window.getMaskedEdit(0);
            String value = me0.name();
            logger.info(value);
            assertTrue(value.equals("12/12/99"));
        } finally {
            this.closeApplication();
        }
    }

    @Test
    public void testGetPasswordEditBox() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationEditBox passwd = window.getPasswordEditBox(0);
            //passwd.setValue("Hello there everyone");
            String name = passwd.name();
            logger.info(name);
            assertTrue(name.equals(""));
        } finally {
            this.closeApplication();
        }
    }

    @Test
    public void testGetPanel_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationPanel panel = window.getPanel(0);

            String name = panel.name();
            logger.info(name);
            assertTrue(name.equals(""));
        } finally {
            this.closeApplication();
        }
    }
}
