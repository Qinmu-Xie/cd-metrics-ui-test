package com.thoughtworks.lean;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/feature"},plugin = {"html:target/cucumber","json:target/cucumber.json"})
public class TestRunner {
}