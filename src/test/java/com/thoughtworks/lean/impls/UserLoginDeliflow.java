package com.thoughtworks.lean.impls;

import com.thoughtworks.lean.utils.SharedDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UserLoginDeliflow {

    private WebDriver driver;

    private void WaitForPresence(int seconds, String cssselector) {
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.presenceOfElementLocated(this.CssSelect(cssselector)));
    }

    private By CssSelect(String cssselector) {
        return By.cssSelector(cssselector);
    }

    @Given("^I enter deliflow url$")
    public void enter_deliflow_url() {
        driver = new SharedDriver();
        String deliflowServer = System.getenv("DELIFLOW_SERVER");
        driver.navigate().to(deliflowServer);
        this.WaitForPresence(5, "input[name=username]");
        this.WaitForPresence(5, "input[name=password]");

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
