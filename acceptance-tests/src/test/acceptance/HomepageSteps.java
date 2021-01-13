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

public class HomepageSteps {

	public static WebDriver driver;

	@Before
	public void beforeScenario() {
		System.setProperty("webdriver.chrome.driver", "/Library/Java/JUNIT/chromedriver");
		driver = new ChromeDriver();
		// Seems no more working in last Chrome versions
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Given("^je suis sur la homepage$")
	public void je_suis_sur_la_homepage() throws Throwable {
		driver.get("https://www.meetup.com/fr-FR/");
	}

	@Then("^le titre doit être \"([^\"]*)\"$")
	public void le_titre_doit_être(String arg1) throws Throwable {
		assertEquals(driver.getTitle(), arg1);
	}

	@Then("^la description contient \"([^\"]*)\"$")
	public void la_description_doit_être(String arg1) throws Throwable {
		// By CSS Selector
		assertTrue(
				driver.findElement(By.cssSelector("meta[name='description']")).getAttribute("content").contains(arg1));
		// By XPATH, si vous préférez...
		// assertEquals(driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content"),
		// arg1);
	}

	@Then("^la punchline doit être \"([^\"]*)\"$")
	public void la_punchline_doit_être(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		WebElement h1 = driver.findElement(By.tagName("h1"));
		assertThat(h1.getText().toLowerCase(), containsString(arg1.toLowerCase()));

	}

	@Then("^la sous-punchline contient \"([^\"]*)\"$")
	public void la_sous_punchline_contient(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		WebElement psoutitre = driver.findElement(By.className("exploreHome-hero-subTitle"));
		WebElement spansoutitre = psoutitre.findElement(By.tagName("span"));
		assertEquals(spansoutitre.getText(), arg1);
	}

	@Then("^le bouton rouge doit avoir pour texte \"([^\"]*)\"$")
	public void le_bouton_rouge_doit_avoir_pour_texte(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		WebElement buttonRed = driver.findElement(By.cssSelector("#joinMeetupButton"));
		assertEquals(buttonRed.getText(), arg1);

	}

	@When("^le click sur le bouton rouge$")
	public void le_click_sur_le_bouton_rouge() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		WebElement buttonRed = driver.findElement(By.cssSelector("#joinMeetupButton"));
		buttonRed.click();

	}

	@Then("^le bouton nous renvoie vers \"([^\"]*)\"$")
	public void le_bouton_nous_renvoie_vers(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(driver.getCurrentUrl(), arg1);

	}

	@After
	public void afterScenario() {
		driver.quit();
	}
}
