package Pages;

import org.openqa.selenium.WebDriver;

public class AttendancePage extends BasePage {

    public AttendancePage(WebDriver driver) {
        super(driver);
    }

    // ---------- TS_25: Daily Attendance Entry ----------
    public void markAttendance(String date) {
        type("att_date_input", date);
        click("att_mark_button");
        click("att_save_button");
    }

    // ---------- TS_26: Attendance Records Viewing ----------
    public int getAttendanceRecordCount() {
        return driver.findElements(by("att_records_table")).size();
    }

    public void filterByDateRange(String fromDate, String toDate) {
        type("att_filter_date_from", fromDate);
        type("att_filter_date_to", toDate);
        click("flt_search_button");
    }

    public boolean isNoRecordsMessageDisplayed() {
        return isDisplayed("flt_no_records_message");
    }
}
