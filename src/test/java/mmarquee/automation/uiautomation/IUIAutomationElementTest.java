/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
package mmarquee.automation.uiautomation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import junit.framework.TestCase;
import mmarquee.automation.ControlType;
import org.apache.log4j.Logger;

/**
 * Created by inpwt on 18/10/2016.
 */
public class IUIAutomationElementTest extends TestCase {

    protected Logger logger = Logger.getLogger(IUIAutomationTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(IUIAutomationTest.class);
    }

    private IUIAutomation automation;

    protected IUIAutomationElement getRootElement() throws Exception {
        PointerByReference root = new PointerByReference();
        automation.GetRootElement(root);

        Unknown uRoot = new Unknown(root.getValue());

        WinNT.HRESULT result = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), root);
        if (COMUtils.SUCCEEDED(result)) {
            return IUIAutomationElement.Converter.PointerToInterface(root);
        } else {
            throw new Exception("Failed to get root element");
        }
    }

    protected void setUp() throws Exception {
        // Initialise COM
        Ole32.INSTANCE.CoInitializeEx(Pointer.NULL, Ole32.COINIT_MULTITHREADED);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT hr = Ole32.INSTANCE.CoCreateInstance(
                IUIAutomation.CLSID,
                null,
                WTypes.CLSCTX_SERVER,
                IUIAutomation.IID,
                pbr);

        COMUtils.checkRC(hr);

        Unknown unk = new Unknown(pbr.getValue());

        PointerByReference pbr1 = new PointerByReference();

        WinNT.HRESULT result = unk.QueryInterface(new Guid.REFIID(IUIAutomation.IID), pbr1);
        if (COMUtils.SUCCEEDED(result)) {
            this.automation = IUIAutomation.Converter.PointerToInterface(pbr1);
        }
    }

    public void testClassNameForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            PointerByReference sr = new PointerByReference();

            if (root.get_CurrentClassName(sr) != 0) {
                assertTrue("Failed to get_CurrentClassName", false);
            }

            String name = sr.getValue().getWideString(0);

            assertTrue(name.equals("#32769"));

        } catch (Throwable error) {
            assertTrue("Exception", false);
        }
    }

    public void testNameForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            PointerByReference sr = new PointerByReference();

            if (root.get_CurrentName(sr) != 0) {
                assertTrue("Failed to get_CurrentName", false);
            }

            String name = sr.getValue().getWideString(0);

            assertTrue("CurrentName", name.equals("Desktop"));

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    public void testIsPasswordForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            IntByReference ir = new IntByReference();

            if (root.get_CurrentIsPassword(ir) != 0) {
                assertTrue("Failed to get_CurrentIsPassword", false);
            }

            int isPassword = ir.getValue();

            assertTrue("CurrentIsPassword", isPassword == 0);

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    public void testGetControlTypeForRootElement() {
        try {
            IUIAutomationElement root = this.getRootElement();

            IntByReference ir = new IntByReference();

            if (root.get_CurrentControlType(ir) != 0) {
                assertTrue("Failed to get_CurrentControlType", false);
            }

            int controlType = ir.getValue();

            assertTrue("get_CurrentControlType", controlType == ControlType.Pane.getValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    public void testIsOffScreenForRootElement() {

        try {
            IUIAutomationElement root = this.getRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsOffscreen(br) != 0) {
                assertTrue("Failed to get_CurrentIsOffscreen", false);
            }

            WinDef.BOOL isOffScreen = br.getValue();

            assertFalse("CurrentIsPassword", isOffScreen.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }

    public void testIsEnabledForRootElement() {

        try {
            IUIAutomationElement root = this.getRootElement();

            WinDef.BOOLByReference br = new WinDef.BOOLByReference();

            if (root.get_CurrentIsEnabled(br) != 0) {
                assertTrue("Failed to get_CurrentIsEnabled", false);
            }

            WinDef.BOOL isEnabled = br.getValue();

            assertTrue("CurrentIsEnabled", isEnabled.booleanValue());

        } catch (Exception error) {
            assertTrue("Exception", false);
        }
    }
}