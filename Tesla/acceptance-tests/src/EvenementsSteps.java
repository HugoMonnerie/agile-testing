package test.acceptance;

import java.util.concurrent.TimeUnit;
import java.util.List;

import java.lang.*;

import org.junit.Test;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Keys;

public class EvenementsSteps {

    public static WebDriver driver;

    @Before
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.driver", "/Library/Java/JUNIT/chromedriver");
        driver = new ChromeDriver();
        // Seems no more working in last Chrome versions
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Given("^je suis sur \"([^\"]*)\"$")
    public void je_suis_sur(String arg1) throws Throwable {
        driver.get(arg1);
        driver.manage().window().fullscreen();
    }

    @When("^j'ouvre le burger menu$")
    public void j_ouvre_le_burger_menu() throws Throwable {
        WebElement logo = driver.findElement(By.cssSelector("span.tds-menu-header-main--trigger_icon"));
        logo.click();
    }

    // @When("^je click sur le bouton \"([^\"]*)\"$")
    // public void je_click_sur_le_bouton(String arg1) throws Throwable {
    //     WebElement button = driver.findElement();
    //     buttonDiv.click();
    // }

    @Then("^je me retrouve sur \"([^\"]*)\"$")
    public void je_me_retrouve_sur(String arg1) throws Throwable {
        assertEquals(driver.getCurrentUrl(), arg1);
    }

    @After
    public void afterScenario() {
        driver.quit();
    }
}
