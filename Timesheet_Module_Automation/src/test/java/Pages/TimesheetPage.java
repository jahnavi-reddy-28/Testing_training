package Pages;

import org.openqa.selenium.WebDriver;

public class TimesheetPage extends BasePage {

    public TimesheetPage(WebDriver driver) {
        super(driver);
    }

    // ---------- TS_21: Create ----------
    public void clickCreate() {
        click("ts_create_button");
    }

    public void enterHours(String hours) {
        type("ts_hours_input", hours);
    }

    public void enterDate(String date) {
        type("ts_date_input", date);
    }

    public void save() {
        click("ts_save_button");
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed("ts_success_message");
    }

    // ---------- TS_22: Update ----------
    public void clickEdit() {
        click("ts_edit_button");
    }

    // ---------- TS_23: Delete ----------
    public void clickDelete() {
        click("ts_delete_button");
    }

    public void confirmDelete() {
        click("ts_delete_confirm_button");
    }

    public void cancelDelete() {
        click("ts_delete_cancel_button");
    }

    // ---------- TS_24: Submit ----------
    public void submitTimesheet() {
        click("ts_submit_button");
    }

    public String getStatus() {
        return waitVisible("ts_status_field").getText().trim();
    }

    // ---------- TS_28-30: Admin review / approve / reject ----------
    public void approve() {
        click("ts_approve_button");
    }

    public void reject(String comment) {
        click("ts_reject_button");
        type("ts_reject_comment_box", comment);
    }

    public boolean isNotificationDisplayed() {
        return isDisplayed("ts_notification_banner");
    }

    // ---------- TS_31: View all employee timesheets ----------
    public int getListedRecordCount() {
        return driver.findElements(by("ts_list_rows")).size();
    }

    // ---------- TS_32: Search / filter ----------
    public void selectStatusFilter(String status) {
        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(waitVisible("flt_status_dropdown"));
        select.selectByVisibleText(status);
    }

    public void search() {
        click("flt_search_button");
    }

    public void resetFilters() {
        click("flt_reset_button");
    }

    public boolean isNoRecordsMessageDisplayed() {
        return isDisplayed("flt_no_records_message");
    }
}
