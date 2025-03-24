import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sms {

    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setPlatformVersion("16")
                .setDeviceName("Pixel 9")
                .setAppPackage("com.google.android.apps.messaging")
                .setAppActivity("com.google.android.apps.messaging.ui.ConversationListActivity");

        driver = new AndroidDriver(new URL("http://localhost:4723"), options);
    }

    @Test
    public void sendSms(){
        driver.sendSMS("555-123-456", "hello world");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
