package com.thoughtworks.lean.impls;

import com.thoughtworks.lean.utils.SharedDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

/**
 * Created by qmxie on 5/5/16.
 */
public class UserTeamViewTest {
    private String teamName;
    private WebDriver driver;
    private WebElement getElement(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    @Given("^我以admin登陆$")
    public void login_as_admin() {
        driver = new SharedDriver();
        driver.navigate().to("http://deliflow-server:9900/login");
        this.getElement("[name=username]").sendKeys("admin@localhost");
        this.getElement("[name=password]").sendKeys("admin");
        this.getElement("div .login--field.right").submit();
        assertTrue(driver.getTitle().contains("DeliFlow"));
    }


    @Given("^如果我在某团队里面$")
    public void there_should_be_more_than_one_team() throws Throwable {
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("ul.card--flow li"),2));
        int teams = this.getElement("ul.card--flow").findElements(By.cssSelector("li")).size() - 1;
        assertTrue(teams > 0);
    }

    @When("^我点击某团队时$")
    public void click_the_first_team() throws Throwable {
        teamName = this.getElement("ul.card--flow li a").getText();
        this.getElement("ul.card--flow li a").click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.top_nav__list-item a")));
    }

    @Then("^我会进到该团队的页面$")
    public void enter_team_view() throws Throwable {
        assertEquals(teamName, this.getElement("h3.header__title").getText());
    }

    @When("^我点击了 新的团队 按钮$")
    public void creat_new_team() throws Throwable {
        this.getElement("#add-team").click();
    }

    @Then("^我能看到新建团队的页面$")
    public void see_create_team_view() throws Throwable {
        assertNotNull(this.getElement("#add-team div input"));
    }
}
