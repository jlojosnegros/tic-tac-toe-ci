package es.codeurjc.ais.tictactoe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class SystemAndAcceptanceTestUtilities {

    static void releaseWebDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    static void registerUser(String playerNickName, WebDriver driver) {
        String play_button = "startBtn";
        String name_text = "nickname";

        driver.findElement(By.id(name_text)).sendKeys(playerNickName);
        driver.findElement(By.id(play_button)).click();
    }

    static void goToHost(WebDriver driver, String url) {
        driver.get(url);
    }

    static void move(WebDriver driver, int cell) {
        String cellIdString = "cell-";
        StringBuilder sb = new StringBuilder(cellIdString);
        sb.append(cell);

        driver.findElement(By.id(sb.toString())).click();
    }
}
