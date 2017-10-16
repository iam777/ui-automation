package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomation3;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests for AutomationTextBox.
 *
 * @author Mark Humphreys
 * Date 11/12/2016.
 */
public class AutomationTextBoxTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        Value value = Mockito.mock(Value.class);
        when(value.value()).thenReturn("VALUE");

        IUIAutomation3 mocked_automation = Mockito.mock(IUIAutomation3.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTextBox ctrl = new AutomationTextBox(element, value, instance);

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetValue_Gets_Value_From_Element_Value() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        Value value = Mockito.mock(Value.class);
        when(value.value()).thenReturn("NAME");

        IUIAutomation3 mocked_automation = Mockito.mock(IUIAutomation3.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTextBox ctrl = new AutomationTextBox(element, value, instance);

        String name = ctrl.getValue();

        assertTrue(name.equals("NAME"));
    }
}
