package checkin;

import org.openqa.selenium.WebDriver;

import model.step;
import setupbase.baseSetup;

public class createTodolist {
    int day;
    String nameworks, value1, value2;

    public createTodolist(int day, String nameworks, String value1, String value2) {
        this.day = day;
        this.nameworks = nameworks;
        this.value1 = value1;
        this.value2 = value2;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            step index = new step(driver);

            index.login();

            index.mnuTodolist.click();
            index.waitForPageLoaded();

            // Chỗ này là click next day nhé anhem, nếu hôm nay là thứ 6 thì mình sẽ click 2
            // nhát cho đến chủ nhật
            // Có một cái click ở hàm createTodolist nên chỉ cần cho click từ ngày hiện tại
            // đến chủ nhật là đúng rồi nhé
            // index.chvDay.click();
            // index.waitForPageLoaded();
            index.chvDay.click();
            index.waitForPageLoaded();

            createTodolist[] data = {
                    new createTodolist(2, "Chào cờ và sinh hoạt đầu tuần", "08:30", "09:30"),
                    new createTodolist(3, "Dự án: ", "08:30", "10:30"),
                    new createTodolist(4, "Happy hour", "16:30", "17:30"),
                    new createTodolist(5, "Checkin Nháp", "17:00", "17:30"),
                    new createTodolist(6, "Checkin 1:1", "11:00", "12:00"),
                    new createTodolist(7, "Checkin Plan Tuần", "10:00", "11:00")
            };

            for (int i = 0; i < data.length; i++) {
                index.createTodolist(data[i].nameworks, data[i].value1, data[i].value2);
                index.waitForPageLoaded();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
