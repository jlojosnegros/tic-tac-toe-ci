package es.codeurjc.ais.tictactoe;


import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;

import static es.codeurjc.ais.tictactoe.SystemAndAcceptanceTestUtilities.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

@RunWith(Parameterized.class)
public class SeleniumSytemTest {

    @Rule
    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("target"));

    private static String namePlayerOne = "player One";
    private static String namePlayerTwo = "player Two";

    @Parameters
    public static Collection<Object[]> data () {
        Object[][] values = {
                { new int[] { 4,0,7,1,2,6,3,5,8}, "draw"},
                { new int[] { 0,3,1,4,7,5}, namePlayerTwo},
                { new int[] { 0,3,1,4,2} , namePlayerOne},
        };

        return Arrays.asList(values);
    }

    @Parameter(0) public int[] moves;
    @Parameter(1) public String result;


    private RemoteWebDriver driverPlayerOne;
    private RemoteWebDriver driverPlayerTwo;

    @BeforeClass
    public static void beforeAll() {
        WebApp.start();
    }

    @AfterClass
    public static void afterAll() {
        WebApp.stop();
    }

    @Before
    public void beforeEach() {
        driverPlayerOne = chrome.getWebDriver();
        driverPlayerTwo = chrome.getWebDriver();
    }

    @After
    public void afterEach() {
        releaseWebDriver(driverPlayerOne);
        releaseWebDriver(driverPlayerTwo);
    }

    @Test
    public void test() {
        // Exercise and verify
//        String interfaceName = "docker0";
        String ip = "192.168.16.1"; // ifconfig docker0
//        try {
//            NetworkInterface networkInterface = NetworkInterface.getByName(interfaceName);
//            Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
//            InetAddress currentAddress;
//            currentAddress = inetAddress.nextElement();
//
//            while(inetAddress.hasMoreElements())
//            {
//                currentAddress = inetAddress.nextElement();
//                if(currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress())
//                {
//                    ip = currentAddress.toString();
//                    break;
//                }
//            }
//        }
//        catch (java.net.SocketException e)
//        {
//            assertThat(false);
//        }

        System.out.println("-++++ " + ip);

        String URL_SUT = "http://"+ip+":12345"; // -p 12345:8080 ( because gerrit is already using port 8080 )
        goToHost(driverPlayerOne, URL_SUT);
        registerUser(namePlayerOne, driverPlayerOne);

        goToHost(driverPlayerTwo, URL_SUT);
        registerUser(namePlayerTwo, driverPlayerTwo);

        WebDriver[] drivers = {driverPlayerOne, driverPlayerTwo};
        int index = 0;

        for(int cell : moves) {
            move(drivers[index], cell);
            index = (index+1)%drivers.length;
        }

        index = (index+1)%drivers.length;
        WebDriverWait wait = new WebDriverWait(drivers[index], 30);

        wait.until(ExpectedConditions.alertIsPresent());

        String alert_result = drivers[index].switchTo().alert().getText();
        assertThat(alert_result.toLowerCase()).startsWith(result.toLowerCase());
    }

}
