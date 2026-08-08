package pac;



import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(dataProvider = "searchData")
    public void searchFlow(
            String firstSearch,
            String secondSearch) {

        POMLocators pom =
                new POMLocators(driver);

        pom.searchProduct(
                firstSearch,
                secondSearch);

        String actualHeading =
                pom.getResultHeading();

        Assert.assertEquals(
                actualHeading,
                "Search - " + secondSearch);
    }

    @DataProvider(name = "searchData")
    public Object[][] searchData() {

        return new Object[][] {

                {"Mobile", "Monitors"},
                {"Laptop", "Phones"},
                {"Camera", "Cameras"}
        };
    }
}
