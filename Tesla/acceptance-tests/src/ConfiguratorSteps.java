package test.acceptance;

import java.util.concurrent.TimeUnit;

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

public class ConfiguratorSteps {

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
	}

	@When("^j'appuie sur le bouton Commander")
	public void j_appuie_sur_le_bouton() throws Throwable {
		WebElement buttonDiv = driver.findElement(By.cssSelector("div.hero-callouts--button.cmp-animate--to_reveal.cmp-animate--revealed"));
		WebElement button = buttonDiv.findElement(By.tagName("a"));
		System.out.println("Paragraph text:" + button.getAttribute("href"));
		button.click();
	}

	@Then("^le bouton nous renvoie vers \"([^\"]*)\"$")
	public void le_bouton_nous_renvoie_vers(String arg1) throws Throwable {
		assertEquals(driver.getCurrentUrl(), arg1);
	}

	@Then("^le prix affiché est un LOA à (\\d+)$")
	public void le_prix_affiché_est_un_LOA_à(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@After
	public void afterScenario() {
		driver.quit();
	}
}
