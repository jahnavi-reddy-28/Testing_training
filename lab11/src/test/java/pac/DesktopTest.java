package pac;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DesktopTest extends BaseTest {

    @Test(dataProvider = "sortData")
    public void desktopFlow(String sortOption) {

        POMLocators pom =
                new POMLocators(driver);

        pom.goToMacPage();

        String heading =
                pom.getMacHeading();

        Assert.assertEquals(heading, "Mac");

        pom.sortAndAddToCart(sortOption);

        String actualMessage =
                pom.getSuccessMessage();

        Assert.assertTrue(
                actualMessage.contains(
                        "Success: You have added iMac to your shopping cart!"));
    }

    @DataProvider(name = "sortData")
    public Object[][] sortData() {

        return new Object[][] {
                {"Name (A - Z)"},
                {"Name (Z - A)"}
        };
    }
}