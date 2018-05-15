package es.codeurjc.ais.tictactoe;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty"},
        features = { "classpath:es/codeurjc/test"},
        glue = {"es.codeurjc.ais.tictactoe"}
)
public class TicTacToeGameCucumberTest {
    @BeforeClass
    public static void beforeAll() {
        WebApp.start();
    }

    @AfterClass
    public static void afterAll() {
        WebApp.stop();
    }

    @Rule
    public static BrowserWebDriverContainer chromeOne = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("target"));
    @Rule
    public static BrowserWebDriverContainer chromeTwo= new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("target"));


}
