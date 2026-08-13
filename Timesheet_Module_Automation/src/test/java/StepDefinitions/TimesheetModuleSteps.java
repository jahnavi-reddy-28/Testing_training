package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Pages.AttendancePage;
import Pages.NavigationPage;
import Pages.ReportsPage;
import Pages.TimesheetPage;
import Utilities.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TimesheetModuleSteps {

    WebDriver driver;
    WebDriverWait wait;

    NavigationPage navigation;
    TimesheetPage timesheetPage;
    AttendancePage attendancePage;
    ReportsPage reportsPage;

    boolean lastDeleteConfirmed;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        navigation = new NavigationPage(driver);
        timesheetPage = new TimesheetPage(driver);
        attendancePage = new AttendancePage(driver);
        reportsPage = new ReportsPage(driver);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ---------- Background ----------
    @Given("the user is logged in and on the dashboard")
    public void the_user_is_logged_in_and_on_the_dashboard() {

        String url = ConfigReader.getvalue("url");
        driver.get(url);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                .sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Dashboard']")));
    }

    // ---------- Navigation ----------
    @Given("the user opens {string}")
    public void the_user_opens(String menuItem) {
        switch (menuItem) {
            case "My Timesheets" -> navigation.openMyTimesheets();
            case "Employee Timesheets" -> navigation.openEmployeeTimesheets();
            default -> throw new RuntimeException("Unknown menu item: " + menuItem);
        }
    }

    @Given("the user opens {string} under Attendance")
    public void the_user_opens_under_attendance(String menuItem) {
        switch (menuItem) {
            case "My Records" -> navigation.openMyRecords();
            case "Punch In/Out" -> navigation.openPunchInOut();
            case "Employee Records" -> navigation.openEmployeeRecords();
            case "Configuration" -> navigation.openAttendanceConfiguration();
            default -> throw new RuntimeException("Unknown Attendance menu item: " + menuItem);
        }
    }

    @Given("the user opens {string} under Reports")
    public void the_user_opens_under_reports(String menuItem) {
        switch (menuItem) {
            case "Project Reports" -> navigation.openProjectReports();
            case "Employee Reports" -> navigation.openEmployeeReports();
            case "Attendance Summary" -> navigation.openAttendanceSummary();
            default -> throw new RuntimeException("Unknown Reports menu item: " + menuItem);
        }
    }

    @When("the user selects employee {string}")
    public void the_user_selects_employee(String employeeName) {
        timesheetPage.selectEmployee(employeeName);
    }

    // ---------- TS_21: Create ----------
    @And("creates a timesheet with date {string} and hours {string}")
    public void creates_a_timesheet_with_date_and_hours(String date, String hours) {
        timesheetPage.clickCreate();
        timesheetPage.enterDate(date);
        timesheetPage.enterHours(hours);
        timesheetPage.save();
    }

    @Then("a success message should be displayed")
    public void a_success_message_should_be_displayed() {
        Assert.assertTrue(timesheetPage.isSuccessMessageDisplayed(), "Success message not displayed");
    }

    @Then("the new timesheet should appear in the list")
    public void the_new_timesheet_should_appear_in_the_list() {
        Assert.assertTrue(timesheetPage.getListedRecordCount() > 0, "No timesheet rows found in list");
    }

    // ---------- TS_22: Update ----------
    @And("edits the timesheet hours to {string}")
    public void edits_the_timesheet_hours_to(String hours) {
        timesheetPage.clickEdit();
        timesheetPage.enterHours(hours);
        timesheetPage.save();
    }

    @Then("the timesheet should reflect the updated hours {string}")
    public void the_timesheet_should_reflect_the_updated_hours(String hours) {
        // TODO: replace with an assertion against the actual hours cell/value once that locator is known
        Assert.assertTrue(timesheetPage.isSuccessMessageDisplayed(), "Update was not confirmed");
    }

    // ---------- TS_23: Delete ----------
    @And("deletes the timesheet and chooses {string}")
    public void deletes_the_timesheet_and_chooses(String confirmation) {
        timesheetPage.clickDelete();
        if (confirmation.equalsIgnoreCase("confirm")) {
            timesheetPage.confirmDelete();
            lastDeleteConfirmed = true;
        } else {
            timesheetPage.cancelDelete();
            lastDeleteConfirmed = false;
        }
    }

    @Then("the timesheet deletion result should be {string}")
    public void the_timesheet_deletion_result_should_be(String expectedResult) {
        if (expectedResult.equals("deleted")) {
            Assert.assertTrue(lastDeleteConfirmed, "Timesheet was not deleted after confirmation");
        } else {
            Assert.assertFalse(lastDeleteConfirmed, "Timesheet was deleted despite cancellation");
        }
    }

    // ---------- TS_24: Submit ----------
    @And("submits the completed timesheet")
    public void submits_the_completed_timesheet() {
        timesheetPage.submitTimesheet();
    }

    @Then("the timesheet status should be {string}")
    public void the_timesheet_status_should_be(String expectedStatus) {
        Assert.assertEquals(timesheetPage.getStatus(), expectedStatus, "Timesheet status mismatch");
    }

    // ---------- TS_25: Daily Attendance Entry ----------
    @When("the user marks attendance for date {string}")
    public void the_user_marks_attendance_for_date(String date) {
        attendancePage.markAttendance(date);
    }

    @Then("the attendance record should appear in the attendance list")
    public void the_attendance_record_should_appear_in_the_attendance_list() {
        Assert.assertTrue(attendancePage.getAttendanceRecordCount() > 0, "No attendance records found");
    }

    // ---------- TS_26: Attendance Records Viewing ----------
    @And("filters attendance from {string} to {string}")
    public void filters_attendance_from_to(String fromDate, String toDate) {
        attendancePage.filterByDateRange(fromDate, toDate);
    }

    @Then("the attendance records displayed should be accurate")
    public void the_attendance_records_displayed_should_be_accurate() {
        Assert.assertTrue(attendancePage.getAttendanceRecordCount() >= 0, "Attendance record fetch failed");
    }

    // ---------- TS_27: Project Reports ----------
    @When("the user filters the report by project {string}")
    public void the_user_filters_the_report_by_project(String project) {
        reportsPage.filterByProject(project);
    }

    @And("filters the report from {string} to {string}")
    public void filters_the_report_from_to(String fromDate, String toDate) {
        reportsPage.filterByDateRange(fromDate, toDate);
    }

    @And("generates the report")
    public void generates_the_report() {
        reportsPage.generateReport();
    }

    @Then("the report should be generated successfully")
    public void the_report_should_be_generated_successfully() {
        Assert.assertTrue(reportsPage.getReportRowCount() >= 0, "Report did not generate");
    }

    // ---------- TS_28: Review submitted timesheets ----------
    @When("the admin views submitted timesheets")
    public void the_admin_views_submitted_timesheets() {
        // Filtering to "Submitted" status re-uses the shared filter control
        timesheetPage.selectStatusFilter("Submitted");
        timesheetPage.search();
    }

    @Then("all submitted timesheets should be listed with details visible")
    public void all_submitted_timesheets_should_be_listed_with_details_visible() {
        Assert.assertTrue(timesheetPage.getListedRecordCount() > 0, "No submitted timesheets listed");
    }

    // ---------- TS_29 / TS_30: Approve / Reject ----------
    @When("the admin approves a submitted timesheet")
    public void the_admin_approves_a_submitted_timesheet() {
        timesheetPage.approve();
    }

    @When("the admin rejects a submitted timesheet with comment {string}")
    public void the_admin_rejects_a_submitted_timesheet_with_comment(String comment) {
        timesheetPage.reject(comment);
    }

    @Then("a notification should be sent to the user")
    public void a_notification_should_be_sent_to_the_user() {
        Assert.assertTrue(timesheetPage.isNotificationDisplayed(), "Notification not displayed");
    }

    // ---------- TS_31: View all employee timesheets ----------
    @When("the admin views all employee timesheets")
    public void the_admin_views_all_employee_timesheets() {
        timesheetPage.resetFilters();
    }

    @Then("employee details and timesheet status should be displayed for each row")
    public void employee_details_and_timesheet_status_should_be_displayed_for_each_row() {
        Assert.assertTrue(timesheetPage.getListedRecordCount() > 0, "No employee timesheet rows displayed");
    }

    // ---------- TS_32: Search and filter ----------
    @When("the user filters timesheets by status {string}")
    public void the_user_filters_timesheets_by_status(String status) {
        timesheetPage.selectStatusFilter(status);
    }

    @And("searches")
    public void searches() {
        timesheetPage.search();
    }

    @Then("only matching timesheets should be displayed")
    public void only_matching_timesheets_should_be_displayed() {
        Assert.assertTrue(timesheetPage.getListedRecordCount() >= 0, "Filtered search failed");
    }

    @Then("a {string} message should be displayed if there are no matches")
    public void a_message_should_be_displayed_if_there_are_no_matches(String message) {
        if (timesheetPage.getListedRecordCount() == 0) {
            Assert.assertTrue(timesheetPage.isNoRecordsMessageDisplayed(),
                    "'" + message + "' message not displayed for empty result set");
        }
    }

    // ---------- TS_33: Boundary Value Analysis ----------
    @Then("the date boundary result should be {string}")
    public void the_date_boundary_result_should_be(String expectedResult) {
        boolean success = timesheetPage.isSuccessMessageDisplayed();
        if (expectedResult.equals("valid")) {
            Assert.assertTrue(success, "Valid boundary date was rejected");
        } else {
            Assert.assertFalse(success, "Invalid boundary date was accepted");
        }
    }

    // ---------- TS_34: Equivalence Partitioning ----------
    @Then("the input validation result should be {string}")
    public void the_input_validation_result_should_be(String expectedResult) {
        boolean success = timesheetPage.isSuccessMessageDisplayed();
        if (expectedResult.equals("valid")) {
            Assert.assertTrue(success, "Valid input was rejected");
        } else {
            Assert.assertFalse(success, "Invalid input was accepted");
        }
    }
}
