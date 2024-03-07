package com.spring.restApp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",glue = {"com.spring.restApp.cucumberglue"})
public class CucumberIntegrationTest {
}
