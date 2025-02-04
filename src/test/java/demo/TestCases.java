package demo;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.logging.Level;

import demo.pages.GoogleFormPage;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {

    ChromeDriver driver;
    GoogleFormPage formPage;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
 /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(description = "Filling the Google form")
    public void testCase01() throws InterruptedException {
        SoftAssert sa = new SoftAssert();
        formPage = new GoogleFormPage(driver);

        // Navigate to Google Form URL
        System.out.println("Start Test Case 01");
        Wrappers.navigate(driver, "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        formPage.enterName("Crio Learner");
        formPage.enterReason("I want to be the best QA Engineer! ");
        formPage.selectExperience("3 - 5");
        formPage.selectTools(Arrays.asList("Java", "Selenium", "TestNG"));
        formPage.selectPronoun("Mr");
        formPage.enterDate(7);
        formPage.enterTime("19", "30");
        formPage.submitForm();

        // Check if the form submission is successfull
        sa.assertTrue(formPage.isFormSubmitted(), "Form submission failed");
        System.out.println(formPage.formSubmissionMsg.getText());
        System.out.println("End Test Case 01");
        sa.assertAll();
    }

    @AfterTest(enabled = true)
    public void endTest() {
        driver.close();
        driver.quit();
    }
}
