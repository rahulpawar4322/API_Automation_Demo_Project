package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import config.ConfigReader;
import io.cucumber.java.*;
import io.restassured.RestAssured;
import utils.ExtentManager;
import utils.ExtentTestManager;

public class Hooks {

    static ExtentReports extent;

    @BeforeAll
    public static void beforeAll() {
        extent = ExtentManager.getExtent();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        RestAssured.baseURI = ConfigReader.get("baseUrl");

        ExtentTest test = extent.createTest(scenario.getName());
        ExtentTestManager.setTest(test);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentTestManager.getTest().fail("Scenario Failed ❌");
        } else {
            ExtentTestManager.getTest().pass("Scenario Passed ✅");
        }
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }
}
