package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

@Suite
@SelectClasspathResource("features")  // the path to your feature files
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps, steps.api") // the path to step definitions
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report.html") // tells cucumber where to put reports
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@getPatientsWithQueryParam")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@regression or @smoke")

//end 1:02

public class CucumberRunner {
}
