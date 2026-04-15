package com.example.cs712assignment2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Assignment6UiTest {

    private static final int TIMEOUT = 8000;
    private static final String PACKAGE_NAME = "com.example.cs712assignment2";

    private UiDevice device;

    @Before
    public void startFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        String launcherPackage = device.getLauncherPackageName();
        assertNotNull(launcherPackage);
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIMEOUT);
    }

    @Test
    public void launchApp_clickExplicitButton_verifySecondActivityText() {
        // Launch app from home screen using launcher intent
        openAppFromHomeScreen();

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), TIMEOUT);
        allowPermissionIfShown();

        UiObject2 explicitButton =
                device.wait(Until.findObject(By.text("Start Activity Explicitly")), TIMEOUT);
        assertNotNull("Could not find Start Activity Explicitly button", explicitButton);
        explicitButton.click();

        boolean foundChallenge =
                device.wait(Until.hasObject(By.textContains("Device Fragmentation")), TIMEOUT)
                        || device.wait(Until.hasObject(By.textContains("Battery Optimization")), TIMEOUT)
                        || device.wait(Until.hasObject(By.textContains("App Lifecycle Complexity")), TIMEOUT)
                        || device.wait(Until.hasObject(By.textContains("Security and Privacy")), TIMEOUT)
                        || device.wait(Until.hasObject(By.textContains("Rapid SDK Updates")), TIMEOUT);
        assertTrue("Second activity challenge text was not found", foundChallenge);
    }

    private void openAppFromHomeScreen() {
        final android.content.Context context =
                InstrumentationRegistry.getInstrumentation().getTargetContext();

        android.content.Intent intent =
                context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);

        assertNotNull("Launch intent not found for app package", intent);

        intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
    private void allowPermissionIfShown() {
        UiObject2 allowButton =
                device.wait(Until.findObject(By.text("Allow")), 3000);

        if (allowButton == null) {
            allowButton = device.wait(Until.findObject(By.textContains("Allow")), 3000);
        }

        if (allowButton == null) {
            allowButton = device.wait(Until.findObject(By.text("While using the app")), 3000);
        }

        if (allowButton == null) {
            allowButton = device.wait(Until.findObject(By.textContains("while using")), 3000);
        }

        if (allowButton != null) {
            allowButton.click();
            device.waitForIdle();
        }
    }
}