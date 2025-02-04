package demo.wrappers;

import java.sql.Driver;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import javax.swing.text.DateFormatter;

import com.sun.jdi.event.AccessWatchpointEvent;

import demo.utils.WaitUtils;

public class Wrappers {

    /*
         * Write your selenium wrappers here
     */
    // Navigate to a URL
    public static boolean navigate(WebDriver driver, String url) {
        try {
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(url)) {
                driver.get(url);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error while navigating: " + e.getMessage());
        }
        return false;
    }

    // Send keys to an input field
    public static boolean sendKeys(WebDriver driver, WebElement element, String keysToSend) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WaitUtils.waitForVisibility(driver, element);
                if (element.isDisplayed() && element.isEnabled()) {
                    element.clear();
                    element.sendKeys(keysToSend);
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error in sendKeys: " + e.getMessage());
            }
            attempts++;
            WaitUtils.sleep(1000);
        }
        return false;
    }

    // Click on an element
    public static boolean clickOnElement(WebDriver driver, WebElement element) {
        try {
            WaitUtils.waitForClickable(driver, element);
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error clicking element: " + e.getMessage());
        }

        return false;
    }

    public static boolean selectFromList(WebDriver driver, List<WebElement> elements, String str) {
        try {
            for (WebElement elem : elements) {
                if (elem.getText().equals(str)) {
                    clickOnElement(driver, elem);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error while clicking on the element" + e.getMessage());
        }
        return false;
    }

}
