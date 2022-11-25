package checkin;

import org.openqa.selenium.WebDriver;

import model.step;
import setupbase.baseSetup;

public class createTodolist {
    int day;
    String nameworks;

    public createTodolist(int day, String nameworks) {
        this.day = day;
        this.nameworks = nameworks;
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
                    new createTodolist(2, "Chào cờ và sinh hoạt đầu tuần"),
                    new createTodolist(3, "Dự án: "),
                    new createTodolist(4, "Happy hour"),
                    new createTodolist(5, "Checkin Nháp"),
                    new createTodolist(6, "Checkin 1:1"),
                    new createTodolist(7, "Checkin Plan Tuần")
            };

            for (int i = 0; i < data.length; i++) {
                index.createTodolist(data[i].nameworks);
                index.waitForPageLoaded();

                String name = index.verifyTodolist();
                switch (name) {
                    case "Chào cờ và sinh hoạt đầu tuần":
                        index.btnEdit.click();
                        index.choseTimeStar("08:30");
                        index.choseTimeEnd("09:00");
                        Thread.sleep(1000);
                        index.checkin();
                        break;
                    case "Dự án:":
                        index.btnEdit.click();
                        index.choseTimeStar("08:30");
                        index.choseTimeEnd("09:30");
                        Thread.sleep(1000);

                        index.checkin();
                        break;
                    case "Happy hour":
                        index.btnEdit.click();
                        index.choseTimeStar("16:30");
                        index.choseTimeEnd("17:30");
                        Thread.sleep(1000);
                        index.checkin();
                        break;
                    case "Checkin Nháp":
                        index.btnEdit.click();
                        index.choseTimeStar("17:00");
                        index.choseTimeEnd("17:30");
                        Thread.sleep(1000);
                        index.checkin();
                        break;
                    case "Checkin 1:1":
                        index.btnEdit.click();
                        index.choseTimeStar("11:00");
                        index.choseTimeEnd("12:00");
                        Thread.sleep(1000);
                        index.checkin();
                        break;
                    case "Checkin Plan Tuần":
                        index.btnEdit.click();
                        index.choseTimeStar("10:30");
                        index.choseTimeEnd("11:30");
                        Thread.sleep(1000);
                        index.checkin();
                        break;
                }
                Thread.sleep(1200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
