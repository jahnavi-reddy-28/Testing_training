package pac;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POMPageFactory {

    WebDriver driver;

    public POMPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//a[@title='My Account']")
    WebElement myAccount;

    @FindBy(linkText="Register")
    WebElement register;

    @FindBy(id="input-firstname")
    WebElement firstName;

    @FindBy(id="input-lastname")
    WebElement lastName;

    @FindBy(id="input-email")
    WebElement email;

    @FindBy(id="input-telephone")
    WebElement telephone;

    @FindBy(id="input-password")
    WebElement password;

    @FindBy(id="input-confirm")
    WebElement confirmPassword;

    @FindBy(name="agree")
    WebElement privacyPolicy;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement continueBtn;

    @FindBy(xpath="//div[@id='content']//h1")
    WebElement successMessage;

    // Home Page Title
    public String getHomePageTitle() {
        return driver.getTitle();
    }

    // Click Register
    public void clickRegister() {
        myAccount.click();
        register.click();
    }

    // Register Page Title
    public String getRegisterPageTitle() {
        return driver.getTitle();
    }

    // Enter Registration Details
    public void enterDetails(String fname,
                             String lname,
                             String mail,
                             String phone,
                             String pwd,
                             String cpwd) {

        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        email.sendKeys(mail);
        telephone.sendKeys(phone);
        password.sendKeys(pwd);
        confirmPassword.sendKeys(cpwd);
    }

    // Submit Form
    public void submitForm() {
        privacyPolicy.click();
        continueBtn.click();
    }

    // Success Message
    public String getSuccessMessage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOf(successMessage));

        return successMessage.getText();
    }
}