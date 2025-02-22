package demo.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import demo.utils.WaitUtils;
import demo.wrappers.Wrappers;

public class GoogleFormPage {

    WebDriver driver;

    public GoogleFormPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement nameTextBox;

    @FindBy(xpath = "//textarea[@class='KHxj8b tL9Q4c']")
    public WebElement reasonTextArea;

    @FindBy(xpath = "//span[@class='aDTYNe snByac OvPDhc OIC90c']") ////span[contains(@class,'OvPDhc')]
    public List<WebElement> expRadioBtnList;

    @FindBy(xpath = "//div[@class = 'eBFwI']")
    public List<WebElement> toolChkBoxSelection;

    @FindBy(xpath = "//div[@role='listbox']//span[text()='Choose']")
    public WebElement dropDownbtn;

    @FindBy(xpath = "//div[@class='OA0qNb ncFHed QXL7Te']//span[@class = 'vRMGwf oJeWuf']")
    public List<WebElement> pronaunList;

    @FindBy(xpath = "//input[@type='date']")
    public WebElement dateInput;

    @FindBy(xpath = "(//div[@class='ocBCTb']//input)[1]")
    public WebElement hourTextBox;

    @FindBy(xpath = "(//div[@class='ocBCTb']//input)[2]")
    public WebElement minTextBox;

    @FindBy(xpath = "//span[contains(text(),'Submit')]")
    public WebElement submitBtn;

    @FindBy(xpath = "//span[contains(text(),'Clear form')]")
    public WebElement clearFormBtn;

    @FindBy(xpath = "//div[@class='vHW8K']")
    public WebElement formSubmissionMsg;

    //Enter the name to the text box located by locator (//input[@type='text'])[1]
    public void enterName(String name) {
        Wrappers.sendKeys(driver, nameTextBox, name);
    }

    //Enter the reason to the text area located by locator //textarea[@class='KHxj8b tL9Q4c']
    public void enterReason(String reason) {
        long epoch = System.currentTimeMillis() / 1000;
        Wrappers.sendKeys(driver, reasonTextArea, reason + epoch);
    }

    //Select the Experience radio button located by locator //span[@class='aDTYNe snByac OvPDhc OIC90c']
    public void selectExperience(String exp) {
        Wrappers.selectFromList(driver, expRadioBtnList, exp);
    }

    //Select the tools checkbox located by locator //div[@class = 'eBFwI']
    public void selectTools(List<String> tools) {
        for (String tool : tools) {
            Wrappers.selectFromList(driver, toolChkBoxSelection, tool);
        }
    }

    /*
     * Select the pronoun from the list located by locator 
     * //div[@role='listbox']//span[text()='Choose']
     * and //div[@class='OA0qNb ncFHed QXL7Te']//span[@class = 'vRMGwf oJeWuf']
     */
    
    public void selectPronoun(String pronoun) {
        Wrappers.clickOnElement(driver, dropDownbtn);
        WaitUtils.sleep(1000);
        Wrappers.selectFromList(driver, pronaunList, pronoun);

    }

    //Enter the date to dateInput located by locator //input[@type='date']
    public void enterDate(int daysAgo) {
        // Get date minus `days` in "MM-dd-yyyy" format
        LocalDate date = LocalDate.now().minusDays(daysAgo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        Wrappers.sendKeys(driver, dateInput, date.format(formatter));
    }

    //Enter the time to hour and min field located by locator (//div[@class='ocBCTb']//input)[1] and (//div[@class='ocBCTb']//input)[2]
    public void enterTime(String hour, String minute) {
        Wrappers.sendKeys(driver, hourTextBox, hour);
        Wrappers.sendKeys(driver, minTextBox, minute);
    }

    //Click on the submit button located by locator //span[contains(text(),'Submit')]
    public void submitForm() {
        Wrappers.clickOnElement(driver, submitBtn);
    }

    //Check if the form is submitted and message is displayed located by locator //div[@class='vHW8K']
    public boolean isFormSubmitted() {
        WaitUtils.waitForVisibility(driver, formSubmissionMsg);
        formSubmissionMsg.isDisplayed();
        return true;
    }

}
