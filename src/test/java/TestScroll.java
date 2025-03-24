import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class TestScroll {

    private AndroidDriver driver;
    private Dimension size;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setPlatformVersion("16")
                .setDeviceName("Pixel 9")
                .setAppPackage("io.appium.android.apis")
                .setAppActivity("io.appium.android.apis.ApiDemos");

        driver = new AndroidDriver(new URL("http://localhost:4723"), options);
        size = driver.manage().window().getSize();
    }

    @Test
    public void scrollTest() throws InterruptedException {
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]\n")).click();
        scrollVertical(ScrollDirection.DOWN);
        Thread.sleep(1000); // Apenas para visualização
        scrollVertical(ScrollDirection.UP);
    }

    private void scrollVertical(ScrollDirection direction) {

        int startX = size.width / 2;
        int startY, endY;

        if (direction == ScrollDirection.DOWN) {
            startY = (int) (size.height * 0.8);
            endY = (int) (size.height * 0.2);
        } else {
            startY = (int) (size.height * 0.2);
            endY = (int) (size.height * 0.8);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO,
                        PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(500),
                        PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(scroll));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private enum ScrollDirection {
        UP, DOWN
    }
}