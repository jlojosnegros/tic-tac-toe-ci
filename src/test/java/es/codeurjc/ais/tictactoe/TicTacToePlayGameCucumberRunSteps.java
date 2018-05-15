package es.codeurjc.ais.tictactoe;


import cucumber.api.Transform;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;

import static es.codeurjc.ais.tictactoe.SystemAndAcceptanceTestUtilities.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

public class TicTacToePlayGameCucumberRunSteps {

    @Rule
    public static BrowserWebDriverContainer chromeOne = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("target"));
    @Rule
    public static BrowserWebDriverContainer chromeTwo= new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("target"));

    static String ip = "192.168.16.1"; // ifconfig docker0
    static String URL_SUT = "http://" + ip +":12345";
    static String cellIdString = "cell-";

    private RemoteWebDriver driverPlayerOne;
    private RemoteWebDriver driverPlayerTwo;
    private WebDriver lastMove;

    @Before
    public void beforeEach() {
        driverPlayerOne = chromeOne.getWebDriver();
        driverPlayerTwo = chromeTwo.getWebDriver();
    }

    @After
    public void afterEach() {
        releaseWebDriver(driverPlayerOne);
        releaseWebDriver(driverPlayerTwo);
    }


    @Given("^I have a tictactoe game at (-?.*)$")
    public void i_have_a_tictactoe_game_at(String url) throws Throwable {
        if (null == driverPlayerOne)
        {
            System.out.println("TENEMOS UN NULL EN driverPlayerOne!!!");
        }
        if (null == driverPlayerTwo)
        {
            System.out.println("TENEMOS UN NULL EN driverPlayerTwo!!!");
        }

        goToHost(driverPlayerOne, url);
        goToHost(driverPlayerTwo, url);

    }

    @And("^player_one is (-?[^ ]+)$")
    public void player_one_is(String nicknamePlayerOne) throws Throwable {
        registerUser(nicknamePlayerOne, driverPlayerOne);
    }

    @And("^player_two is (-?[^ ]+)$")
    public void player_two_is_player_two(String nicknamePlayerTwo) throws Throwable {
        registerUser(nicknamePlayerTwo, driverPlayerTwo);
    }

    @When("^this moves are played \\[(-?\\d+(?:[ \\t]*,[ \\t]*\\d+)+)\\]$")
    public void this_moves_are_played(
            @Transform(IntegerListTransformer.class) int[] moves) throws Throwable {
        WebDriverWait waitToMove = new WebDriverWait(driverPlayerOne, 30);
        WebElement cell_0 = driverPlayerOne.findElement(By.id("cell-0"));
        waitToMove.until(ExpectedConditions.elementToBeClickable(cell_0));

        WebDriver[] drivers = {driverPlayerOne, driverPlayerTwo};
        int index = 0;
        for (int cell : moves) {
            move(drivers[index], cell);
            index = (index+1)%drivers.length;
        }

        index = (index+1)%drivers.length;
        lastMove = drivers[index];
    }


    @Then("^the result is (-?[^ ]+ wins|draw)!$")
    public void the_result_is(String result) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebDriverWait wait = new WebDriverWait(lastMove, 30);
        wait.until(ExpectedConditions.alertIsPresent());

        String alert_text = lastMove.switchTo().alert().getText();
        assertThat(alert_text.toLowerCase()).startsWith(result.toLowerCase());
    }

}
