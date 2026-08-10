package pac;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POMLocators {

    private WebDriver driver;

    private By searchBox;

    private By searchButton;

    private By resultPageHeading;

    public POMLocators(WebDriver driver) {

        this.driver = driver;

        String searchbox =
                XmlObjectRepository.getlocator("searchbox");

        String searchbutton =
                XmlObjectRepository.getlocator("searchbutton");

        String resultpageheading =
                XmlObjectRepository.getlocator(
                        "resultpageheading"
                );

        searchBox = getBy(searchbox);

        searchButton = getBy(searchbutton);

        resultPageHeading =
                getBy(resultpageheading);
    }

    private By getBy(String locator) {

        String[] parts = locator.split(":", 2);

        if (parts.length != 2) {

            throw new RuntimeException(
                    "Invalid locator format: "
                            + locator
            );
        }

        String type = parts[0].trim();

        String value = parts[1].trim();

        switch (type.toLowerCase()) {

            case "id":

                return By.id(value);

            case "name":

                return By.name(value);

            case "xpath":

                return By.xpath(value);

            case "css":

                return By.cssSelector(value);

            case "class":

                return By.className(value);

            case "tag":

                return By.tagName(value);

            case "linktext":

                return By.linkText(value);

            case "partiallinktext":

                return By.partialLinkText(value);

            default:

                throw new RuntimeException(
                        "Invalid locator type: "
                                + type
                );
        }
    }

    public void searchText(
            String firstSearch,
            String secondSearch) {

        WebElement searchBoxElement =
                driver.findElement(searchBox);

        searchBoxElement.sendKeys(firstSearch);

        searchBoxElement.clear();

        searchBoxElement.sendKeys(secondSearch);

        driver.findElement(searchButton).click();
    }

    public String resultPageHeading() {

        return driver
                .findElement(resultPageHeading)
                .getText();
    }
}