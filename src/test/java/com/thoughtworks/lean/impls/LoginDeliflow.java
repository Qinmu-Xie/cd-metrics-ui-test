package com.thoughtworks.lean.impls;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by qmxie on 5/5/16.
 */
public class LoginDeliflow {
    WebDriver driver = null;

    @Given("^I enter deliflow url$")
    public void enter_deliflow_url(){
        driver = new ChromeDriver();
        driver.navigate().to("http://121.42.193.129:9900/login");
    }

    @When("^I login as admin$")
    public void login_as_admin(){
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin@localhost");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("div .login--field.right")).submit();
    }

    @Then("^I should in the Team view$")
    public void enter_deliflow_team_view(){
        assertTrue(driver.getTitle().contains("DeliFlow"));
    }

    @And("^close the browser$")
    public void close_the_browser()  {
        driver.close();
    }

}
