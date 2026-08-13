Feature: Timesheet, Attendance, Reports and Project Info Module

  Background:
    Given the user is logged in and on the dashboard

  # TS_21 / TR_Time_01
  Scenario: Create Timesheet with valid data
    Given the user opens "My Timesheets"
    When the user selects employee "Admin"
    And creates a timesheet with date "2026-08-10" and hours "8"
    Then a success message should be displayed
    And the new timesheet should appear in the list

  # TS_22 / TR_Time_02
  Scenario: Update an existing Timesheet
    Given the user opens "My Timesheets"
    When the user selects employee "Admin"
    And edits the timesheet hours to "6"
    Then a success message should be displayed
    And the timesheet should reflect the updated hours "6"

  # TS_23 / TR_Time_03
  Scenario Outline: Delete Timesheet
    Given the user opens "My Timesheets"
    When the user selects employee "Admin"
    And deletes the timesheet and chooses "<confirmation>"
    Then the timesheet deletion result should be "<result>"

    Examples:
      | confirmation | result           |
      | confirm      | deleted          |
      | cancel       | not deleted      |

  # TS_24 / TR_Time_04
  Scenario: Submit Timesheet
    Given the user opens "My Timesheets"
    When the user selects employee "Admin"
    And submits the completed timesheet
    Then a success message should be displayed
    And the timesheet status should be "Submitted"

  # TS_25 / TR_Time_05
  Scenario: Mark Daily Attendance
    Given the user opens "My Records" under Attendance
    When the user marks attendance for date "2026-08-13"
    Then the attendance record should appear in the attendance list

  # TS_26 / TR_Time_06
  Scenario: View Attendance Records
    Given the user opens "Employee Records" under Attendance
    When the user selects employee "Admin"
    And filters attendance from "2026-08-01" to "2026-08-13"
    Then the attendance records displayed should be accurate

  # TS_27 / TR_Time_07
  Scenario: View Project Reports
    Given the user opens "Project Reports" under Reports
    When the user filters the report by project "Client Portal"
    And filters the report from "2026-08-01" to "2026-08-13"
    And generates the report
    Then the report should be generated successfully

  # TS_28 / TR_Time_08
  Scenario: Review Submitted Timesheets
    Given the user opens "Employee Timesheets"
    When the admin views submitted timesheets
    Then all submitted timesheets should be listed with details visible

  # TS_29 / TR_Time_09
  Scenario: Approve Timesheet
    Given the user opens "Employee Timesheets"
    When the admin approves a submitted timesheet
    Then the timesheet status should be "Approved"
    And a notification should be sent to the user

  # TS_30 / TR_Time_10
  Scenario: Reject Timesheet
    Given the user opens "Employee Timesheets"
    When the admin rejects a submitted timesheet with comment "Incorrect hours logged"
    Then the timesheet status should be "Rejected"
    And a notification should be sent to the user

  # TS_31 / TR_Time_11
  Scenario: View All Employee Timesheets
    Given the user opens "Employee Timesheets"
    When the admin views all employee timesheets
    Then employee details and timesheet status should be displayed for each row

  # TS_32 / TR_Time_12
  Scenario: Timesheet Search and Filter
    Given the user opens "Employee Timesheets"
    When the user filters timesheets by status "Approved"
    And searches
    Then only matching timesheets should be displayed

  Scenario: Timesheet Search returns no matches
    Given the user opens "Employee Timesheets"
    When the user filters timesheets by status "Rejected"
    And searches
    Then a "No Records Found" message should be displayed if there are no matches

  # TS_33 / TR_Time_13 - Boundary Value Analysis
  Scenario Outline: Timesheet Date Field Boundaries
    Given the user opens "My Timesheets"
    When the user selects employee "Admin"
    And creates a timesheet with date "<date>" and hours "8"
    Then the date boundary result should be "<result>"

    Examples:
      | date       | result  |
      | 2026-01-01 | valid   |
      | 2026-12-31 | valid   |
      | 2020-01-01 | invalid |
      | 2099-12-31 | invalid |

  # TS_34 / TR_Time_14 - Equivalence Partitioning
  Scenario Outline: Timesheet Input Validations
    Given the user opens "My Timesheets"
    When the user selects employee "Admin"
    And creates a timesheet with date "2026-08-10" and hours "<hours>"
    Then the input validation result should be "<result>"

    Examples:
      | hours | result  |
      | 8     | valid   |
      | -1    | invalid |
      | ab    | invalid |
      |       | invalid |
      | 25    | invalid |
