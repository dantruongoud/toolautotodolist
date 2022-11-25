package setupbase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class baseSetup {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    // Khởi tạo cấu hình của Browser
    public WebDriver initChromeDriver() {

        driver = new ChromeDriver();
        System.out.println("Launching Chrome browser...");
        driver.manage().window().maximize();
        driver.get("https://work.conando.vn/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }
}