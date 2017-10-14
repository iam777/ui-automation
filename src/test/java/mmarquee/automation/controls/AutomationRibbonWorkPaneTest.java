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

import mmarquee.automation.AutomationElement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationRibbonWorkPane;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomation3;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Mark Humphreys
 * Date 01/12/2016.
 *
 * Tests for RibbonWorkPane controls
 */
public class AutomationRibbonWorkPaneTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        when(element.getClassName()).thenReturn(AutomationRibbonWorkPane.CLASS_NAME);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation3 mocked_automation = Mockito.mock(IUIAutomation3.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationRibbonWorkPane pane = new AutomationRibbonWorkPane(element, container, instance);

        String name = pane.getName();

        assertTrue(name.equals("NAME"));
    }
}
