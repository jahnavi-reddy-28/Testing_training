package Pages;

import org.openqa.selenium.WebDriver;

/**
 * Handles the top navigation bar: Timesheets / Attendance / Reports / Project Info,
 * and their dropdown submenus. Locators confirmed against the provided screenshots.
 */
public class NavigationPage extends BasePage {

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    // ---------- Timesheets ----------
    public void openMyTimesheets() {
        click("nav_timesheets");
        click("sub_my_timesheets");
    }

    public void openEmployeeTimesheets() {
        click("nav_timesheets");
        click("sub_employee_timesheets");
    }

    // ---------- Attendance ----------
    public void openMyRecords() {
        click("nav_attendance");
        click("sub_my_records");
    }

    public void openPunchInOut() {
        click("nav_attendance");
        click("sub_punch_in_out");
    }

    public void openEmployeeRecords() {
        click("nav_attendance");
        click("sub_employee_records");
    }

    public void openAttendanceConfiguration() {
        click("nav_attendance");
        click("sub_configuration");
    }

    // ---------- Reports ----------
    public void openProjectReports() {
        click("nav_reports");
        click("sub_project_reports");
    }

    public void openEmployeeReports() {
        click("nav_reports");
        click("sub_employee_reports");
    }

    public void openAttendanceSummary() {
        click("nav_reports");
        click("sub_attendance_summary");
    }

    // ---------- Project Info ----------
    public void openCustomers() {
        click("nav_projectinfo");
        click("sub_customers");
    }

    public void openProjects() {
        click("nav_projectinfo");
        click("sub_projects");
    }
}
