import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class PrimeiroTeste {

    AppiumDriver drive;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "16");
        caps.setCapability("deviceName", "Pixel 9");
        caps.setCapability("appPackage", "com.google.android.calculator");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        drive = new AndroidDriver(new URL("http://localhost:4723"), caps);

    }

    @Test
    public void testSomar(){
        drive.findElement(By.id("com.google.android.calculator:id/digit_1")).click();
        drive.findElement(By.id("com.google.android.calculator:id/op_add")).click();
        drive.findElement(By.id("com.google.android.calculator:id/digit_9")).click();

        Assert.assertEquals(drive.findElement(By.id("com.google.android.calculator:id/result_preview")).getText(), "10");
    }

    @AfterTest
    public void tearDown() {
        if (null != drive) {
            drive.quit();
        }
    }
}
