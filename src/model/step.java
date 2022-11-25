package model;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class step {

    WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Nhập email...']")
    private WebElement username_input;

    @FindBy(xpath = "//input[@placeholder='Nhập password...']")
    private WebElement password_input;

    @FindBy(xpath = "//span[contains(text(),'Đăng nhập')]")
    private WebElement login_btn;

    @FindBy(css = "a[href='todolist']")
    public WebElement mnuTodolist;

    @FindBy(xpath = "//i[normalize-space()='chevron_right']")
    public WebElement chvDay;

    @FindBy(css = ".button.is-info")
    private WebElement btnAddWork;

    @FindBy(xpath = "/html/body/main/section/section/div[1]/table/tbody/tr/td/input")
    public WebElement txtNametodolist;

    @FindBy(xpath = "//span[contains(text(),'Cập nhật')]")
    private WebElement btnSave;

    @FindBy(css = "a[class='button is-link']")
    private WebElement btnCheckin;

    @FindBy(xpath = "(//select)[5]")
    private WebElement selectTimeStar;

    @FindBy(xpath = "(//select)[6]")
    private WebElement selectTimeEnd;

    @FindBy(css = "span[class='has-text-weight-semibold has-text-link']")
    private WebElement nametodolist;

    @FindBy(xpath = "//span[contains(text(),'Chỉnh sửa')]")
    public WebElement btnEdit;

    @FindBy(xpath = "/html/body/main/section/section/div[1]/table/tbody/tr[1]/td[4]/div")
    private WebElement getStarTime;

    @FindBy(xpath = "/html/body/main/section/section/div[1]/table/tbody/tr[1]/td[5]/div")
    private WebElement getEndTime;

    public step(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTime() {
        String time = getStarTime.getText().strip() + " " + getEndTime.getText().strip();
        return time;
    }

    public String verifyTodolist() {
        String name = nametodolist.getText().strip();
        return name;
    }

    public void choseTime(String value1, String value2) {
        try {
            btnEdit.click();
            Thread.sleep(1000);
            Select time1 = new Select(selectTimeStar);
            time1.selectByValue(value1);
            Select time2 = new Select(selectTimeEnd);
            time2.selectByValue(value2);
            Thread.sleep(1000);
            checkin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTodolist(String nameworks, String value1, String value2) {
        try {
            chvDay.click();
            Thread.sleep(1000);
            btnAddWork.click();
            Thread.sleep(1000);
            txtNametodolist.sendKeys(nameworks);
            Thread.sleep(1000);
            Select time1 = new Select(selectTimeStar);
            time1.selectByValue(value1);
            Select time2 = new Select(selectTimeEnd);
            time2.selectByValue(value2);
            btnSave.click();
            Thread.sleep(1000);
            checkin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkin() {
        try {
            btnCheckin.click();
            Thread.sleep(500);
            Alert alert = driver.switchTo().alert();
            Thread.sleep(500);
            alert.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login() {
        try {
            waitForPageLoaded();
            username_input.sendKeys("ndtruong.conando@gmail.com");
            Thread.sleep(1000);
            password_input.sendKeys("dantruong2410");
            Thread.sleep(1000);
            login_btn.click();
            waitForPageLoaded();
            chose_company();
            waitForPageLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void chose_company() {
        try {
            List<WebElement> companyList = driver.findElements(By.className("has-text-weight-bold"));
            for (WebElement row : companyList) {
                String company_name = row.getText().strip();
                if (company_name.equals("Công ty cổ phần Truyền thông và Công nghệ Conando")) {
                    row.click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Công ty cổ phần Truyền thông và Công nghệ Conando
    }

}
