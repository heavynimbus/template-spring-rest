package heavynimbus.templatespringrest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"classpath:features/author", "classpath:features/book"},
    glue = {"heavynimbus.templatespringrest.stepDefs", "heavynimbus.templatespringrest.config"},
    plugin = {"pretty", "html:target/cucumber-reports.html"})
public class CucumberIntegrationTest {}
