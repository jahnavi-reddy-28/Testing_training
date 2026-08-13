package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Features",
        glue = "StepDefinitions",

        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json"
        },

        monochrome = true,

        // Shows snippets for undefined steps
        snippets = CucumberOptions.SnippetType.CAMELCASE,

        // Publishes report information
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

}