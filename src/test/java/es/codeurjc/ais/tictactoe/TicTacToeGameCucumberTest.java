package es.codeurjc.ais.tictactoe;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty"},
        features = { "classpath:es/codeurjc/test"},
        glue = {"es.codeurjc.ais.tictactoe"}
)
public class TicTacToeGameCucumberTest {
    @BeforeClass
    public static void beforeAll() {
        ChromeDriverManager.getInstance().setup();
        WebApp.start();
    }

    @AfterClass
    public static void afterAll() {
        WebApp.stop();
    }

}
