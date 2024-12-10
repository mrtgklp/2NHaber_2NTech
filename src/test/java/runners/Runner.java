package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumberReports.html",
                "junit:target/xml-report/cucumber.xml"},
        monochrome = true,
        features = "src/test/resources/features",
        glue = "stepDefitions",
        tags = "@2NHaber",
        dryRun = false
)
public class Runner {
}
