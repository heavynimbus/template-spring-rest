package com.junia.jeeproject;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {
    "com.junia.jeeproject.stepDefs",
    "com.junia.jeeproject.config"
})
public class CucumberIntegrationTest {

}
