package pac;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class POMPageFactory {

    WebDriver driver;

    // My Account
    @FindBy(xpath = "//a[@title='My Account']")
    WebElement myAccount;

    // Register
    @FindBy(xpath = "//a[text()='Register']")
    WebElement register;

    // First Name
    @FindBy(id = "input-firstname")
    WebElement firstname;

    // Last Name
    @FindBy(id = "input-lastname")
    WebElement lastname;

    // Email
    @FindBy(id = "input-email")
    WebElement email;

    // Telephone
    @FindBy(id = "input-telephone")
    WebElement telephone;

    // Password
    @FindBy(id = "input-password")
    WebElement password;

    // Confirm Password
    @FindBy(id = "input-confirm")
    WebElement confirmPassword;

    // Subscribe - No
    @FindBy(xpath = "//input[@name='newsletter' and @value='0']")
    WebElement subscribeButton;

    // Privacy Policy
    @FindBy(name = "agree")
    WebElement policy;

    // Continue
    @FindBy(xpath = "//input[@type='submit']")
    WebElement continueButton;

    // Registration success heading
    @FindBy(css = "#content h1")
    WebElement resultPageHeading;

    // Warning message
    @FindBy(css = ".alert.alert-danger")
    WebElement registrationWarning;

    public POMPageFactory(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    // Get Home Page Title
    public String getHomePageTitle() {

        return driver.getTitle();
    }

    // Open Register Page
    public void gotoRegisterPage() {

        myAccount.click();

        Reporter.log("Clicked My Account", true);

        register.click();

        Reporter.log("Clicked Register", true);
    }

    // Verify Register Page
    public String verifyRegisterPage() {

        return driver.getTitle();
    }

    // Fill Personal Details
    public void fillPersonalDetails(
            String fname,
            String lname,
            String emailid,
            String phno) {

        firstname.clear();
        firstname.sendKeys(fname);

        lastname.clear();
        lastname.sendKeys(lname);

        email.clear();
        email.sendKeys(emailid);

        telephone.clear();
        telephone.sendKeys(phno);

        Reporter.log("Personal details filled", true);
    }

    // Fill Password
    public void fillPasswords(
            String fpassword,
            String cPassword) {

        password.clear();
        password.sendKeys(fpassword);

        confirmPassword.clear();
        confirmPassword.sendKeys(cPassword);

        Reporter.log("Password details filled", true);
    }

    // Submit Registration
    public void submittingForm() {

        // Select No for newsletter
        if (!subscribeButton.isSelected()) {
            subscribeButton.click();
        }

        // Accept Privacy Policy
        if (!policy.isSelected()) {
            policy.click();
        }

        // Click Continue
        continueButton.click();

        Reporter.log("Registration form submitted", true);
    }

    // Get Registration Result
    public String getRegistrationResult() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(resultPageHeading),
                    ExpectedConditions.visibilityOf(registrationWarning)
            ));

            // Success
            if (resultPageHeading.isDisplayed()) {

                String heading = resultPageHeading.getText();

                if (heading.contains("Your Account Has Been Created!")) {

                    return heading;
                }
            }

            // Error / warning
            if (registrationWarning.isDisplayed()) {

                return registrationWarning.getText();
            }

        } catch (Exception e) {

            return "Registration result not found";
        }

        return "Registration result not found";
    }
}