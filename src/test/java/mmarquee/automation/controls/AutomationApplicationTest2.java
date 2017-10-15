package mmarquee.automation.controls;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinNT;
import mmarquee.automation.*;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Mark Humphreys
 * Date 13/01/2017.
 *
 * Mocked tests for AutomationApplication
 *
 * Currently these need to be run in a Windows environment.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( { Ole32Wrapper.class })
public class AutomationApplicationTest2 {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    AutomationElement element;

    @Mock
    Window window;

    @Mock
    ItemContainer container;

    @Mock
    WinNT.HANDLE handle;

    @InjectMocks
    UIAutomation automation;

    @Test
    @Ignore("Need to mock the elem getting a name")
    public void testGetWindow_Calls_FindAll_From_Element()
            throws AutomationException, PatternNotFoundException {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationApplication app = new AutomationApplication(element, handle, false);

        AutomationWindow window = app.getWindow("Untitled - Notepad");

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testClose_Calls_FindWindow() throws Exception {
        User32 user32 = Mockito.mock(User32.class);

        AutomationApplication app = new AutomationApplication(element, handle, true, user32);

        app.close("Untitled - Notepad");

        verify(user32, atLeastOnce()).FindWindow(any(), any());
    }

    @Test
    public void testQuit_Calls_FindWindow() throws Exception {
        User32 user32 = Mockito.mock(User32.class);

        AutomationApplication app = new AutomationApplication(element, handle, true, user32);

        app.quit("Untitled - Notepad");

        verify(user32, atLeastOnce()).FindWindow(any(), any());
    }

}
