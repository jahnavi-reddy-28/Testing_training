package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ReportsPage extends BasePage {

    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    // ---------- TS_27: Project Reports Viewing ----------
    public void filterByProject(String projectName) {
        Select select = new Select(waitVisible("rep_project_filter"));
        select.selectByVisibleText(projectName);
    }

    public void filterByDateRange(String fromDate, String toDate) {
        type("rep_date_from", fromDate);
        type("rep_date_to", toDate);
    }

    public void generateReport() {
        click("rep_generate_button");
    }

    public int getReportRowCount() {
        return driver.findElements(by("rep_result_table")).size();
    }
}
