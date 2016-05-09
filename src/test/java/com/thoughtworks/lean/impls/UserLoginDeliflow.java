package com.thoughtworks.lean.impls;

import com.thoughtworks.lean.utils.SharedDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by qmxie on 5/5/16.
 */
public class UserLoginDeliflow {

    private WebDriver driver;

    @Given("^I enter deliflow url$")
    public void enter_deliflow_url() {
        driver = new SharedDriver();
        driver.navigate().to("http://deliflow-server:9900/login");
    }

    @When("^I login as admin$")
    public void login_as_admin() {
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin@localhost");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("div .login--field.right")).submit();
    }

    @Then("^I should in the Team view$")
    public void enter_deliflow_team_view() {
        assertTrue(driver.getTitle().contains("DeliFlow"));
    }

}
