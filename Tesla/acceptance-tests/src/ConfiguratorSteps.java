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
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.Keys;

public class ConfiguratorSteps {

	public static WebDriver driver;

	private int priceOptionAutopilot = 0;

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


	@When("^j'appuie sur le bouton Commander") public void
	j_appuie_sur_le_bouton() throws Throwable { WebElement buttonDiv = driver.findElement(By.cssSelector(
			"div.hero-callouts--button.cmp-animate--to_reveal.cmp-animate--revealed"));
	WebElement button = buttonDiv.findElement(By.tagName("a"));
	//Thread.sleep(5000); // System.out.println("Paragraph text:" +button.getAttribute("href"));
	button.click();
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t"); }



	@Then("^le bouton nous renvoie vers \"([^\"]*)\"$")
	public void le_bouton_nous_renvoie_vers(String arg1) throws Throwable {
		Thread.sleep(5000);
		WebElement buttonDiv = driver
				.findElement(By.cssSelector("div.hero-callouts--button.cmp-animate--to_reveal.cmp-animate--revealed"));
		WebElement button = buttonDiv.findElement(By.tagName("a"));
		assertEquals(button.getAttribute("href").toLowerCase(), arg1.toLowerCase());
	}

	@Then("^le prix affiché est un \\\"([^\\\"]*)\\\" à \\\"([^\\\"]*)\\\"")
	public void le_prix_affiché_est_un_à(String arg1, String arg2) throws Throwable {
		WebElement divType = driver.findElement(By.cssSelector("div.financetype-selector--button"));
		assertEquals(divType.getText(), arg1);
		WebElement pPrice = driver
				.findElement(By.cssSelector("p.finance-item--price.finance-item--price-before-savings"));
		assertThat(pPrice.getText(), containsString(arg2));
	}

	@Then("^le prix devient un \"([^\"]*)\" à \"([^\"]*)\" et \"([^\"]*)\" d'économies de carburant et un total de \"([^\"]*)\"$")
	public void le_prix_devient_un_à_et_d_économies_de_carburant_et_un_total_de(String arg1, String arg2, String arg3,
			String arg4) throws Throwable {

		WebElement divType = driver.findElement(By.cssSelector("div.financetype-selector--button"));
		assertEquals(divType.getText(), arg1);

		WebElement seeMore = driver.findElement(By.cssSelector("a.finance-content--modal"));
		seeMore.click();

		List<WebElement> spansInfos = driver.findElements(By.cssSelector("span.line-item--value"));
		WebElement spanLoyer = spansInfos.get(0);
		assertThat(spanLoyer.getText(), containsString(arg2));
		WebElement spanEconomie = spansInfos.get(1);
		assertThat(spanEconomie.getText(), containsString(arg3));

		WebElement total = driver.findElement(By.cssSelector("input#totalLeaseAmount"));
		String totalStr = total.getAttribute("value");

		WebElement spanClose = driver.findElement(By.cssSelector("span.modal-content--close"));
		WebElement closeButton = spanClose.findElement(By.cssSelector("i.icon-close"));

		// driver.switchTo().defaultContent();
		closeButton.click();
		assertEquals(totalStr, arg4);
	}

	@When("^j'appuie sur \"([^\"]*)\"$")
	public void j_appuie_sur(String arg1) throws Throwable {
		WebElement divButtons = driver.findElement(By.cssSelector("div.child-group--option_details"));
		List<WebElement> buttons = divButtons.findElements(By.cssSelector("div.group--options_block--container"));
		for (WebElement element : buttons) {
			if (element.getText().contains(arg1)) {
				element.click();
			}
		}
	}

	@When("^j'appuie sur le logo$")
	public void j_appuie_sur_le_logo() throws Throwable {
		WebElement logo = driver.findElement(By.cssSelector("a.tsla-header-main--logo.tds-icon.tds-icon-wordmark"));
		logo.click();
		// Thread.sleep(1000);
	}

	@When("^je click sur le lien \"([^\"]*)\"$") // Scroll à faire soi même
	public void je_click_sur_le_lien(String arg1) throws Throwable {

		Thread.sleep(500);

		List<WebElement> listLocations = driver.findElements(By.cssSelector("a.region-link.notranslate"));
		for (WebElement element : listLocations) {
			// System.out.println("Paragraph text:" + element.getText());
			if (element.getText().contains("France")) {
				element.click();
				break;
			}
		}

		Thread.sleep(7000);
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver; WebElement footer =
		 * driver.findElement(By.tagName("footer"));
		 * js.executeScript("arguments[0].scrollIntoView(false);", footer);
		 * //driver.executeScript("arguments[0].scrollIntoView(false);", footer);
		 */
		List<WebElement> listLinks = driver.findElements(By.cssSelector("a.tds-footer-list_link.tds-link"));
		WebElement locationLink = listLinks.get(6);
		// WebElement lastElement = driver.findElement(By.tagName("footer"));
		int y = locationLink.getLocation().getY();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0," + y + ")");

		locationLink.click();
		Thread.sleep(500);
	}

	@Then("^je me retrouve sur la page \"([^\"]*)\"$")
	public void je_me_retrouve_sur_la_page(String arg1) throws Throwable {
		assertEquals(driver.getCurrentUrl(), arg1);
	}

	@When("^je vais dans l'onglet \"([^\"]*)\"$")
	public void je_vais_dans_l_onglet(String arg1) throws Throwable {
		// WebElement h2 = driver.findElement(By.tagName("h2"));
		// assertThat(h2.getText().toLowerCase(), containsString(arg1.toLowerCase()));

		WebElement divButtons = driver.findElement(By.cssSelector("div.package-options--nav"));
		List<WebElement> buttons = divButtons.findElements(By.cssSelector("li.packages-options--nav-item"));
		for (WebElement element : buttons) {
			if (element.getText().toLowerCase().contains(arg1.toLowerCase())) {
				element.click();
				break;
			}
		}
	}

	@When("^je click sur \"([^\"]*)\"$")
	public void je_click_sur(String arg1) throws Throwable {
		WebElement buttonDiv = driver
				.findElement(By.cssSelector("i.icon-checkbox.option-checkbox--icon.icon-checkbox--blue"));
		WebElement pPrice = driver
				.findElement(By.cssSelector("p.finance-item--price.finance-item--price-before-savings"));
		String justPrice = pPrice.getText().split(" ")[3];
		this.priceOptionAutopilot = Integer.parseInt(justPrice);
		buttonDiv.click();
	}

	@Then("^Le prix augente de \"([^\"]*)\" € /mois$")
	public void le_prix_augente_de_€_mois(String arg1) throws Throwable {
		WebElement pPrice = driver
				.findElement(By.cssSelector("p.finance-item--price.finance-item--price-before-savings"));
		String justPrice = pPrice.getText().split(" ")[3];
		int pricePlus = Integer.parseInt(justPrice);
		assertEquals(pricePlus - this.priceOptionAutopilot, Integer.parseInt(arg1));
	}

	//Events Page

	@When("^je choisi un lieu dans le monde$")
	public void je_choisi_un_lieu_dans_le_monde() throws Throwable {
		WebElement submitLocation = driver.findElement(By.cssSelector("input#edit-loupe"));
		submitLocation.click();
		Thread.sleep(5000);
	}

	@Then("^La page contient les \"([^\"]*)\" prochains evenements autour de chez moi$")
	public void la_page_contient_les_prochains_evenements_autour_de_chez_moi(String arg1) throws Throwable {
		List<WebElement> listEvents = driver.findElements(By.cssSelector("div.node.node-event.node-teaser.clearfix"));
		int eventsCounts = listEvents.size();
		assertEquals(listEvents.size(), Integer.parseInt(arg1));
	}

	@Then("^il ya un lien pour voit tout les évenements$")
	public void il_ya_un_lien_pour_voit_tout_les_évenements() throws Throwable {
		WebElement seeAll = driver.findElement(By.cssSelector("a.view-all"));
		assertEquals(seeAll.getText(), "Voir tous les événements");
	}

	@Then("^il ya un lien pour voir plus de (\\d+) évenements$")
	public void il_ya_un_lien_pour_voir_plus_de_évenements(int arg1) throws Throwable {
		WebElement seeAll = driver.findElement(By.cssSelector("li.pager-next.first.last"));
		assertEquals(seeAll.getText().toLowerCase(), "Afficher plus".toLowerCase());
	}

	@Then("^je peut remplir un formulaire pour rester informé$")
	public void je_peut_remplir_un_formulaire_pour_rester_informer() throws Throwable {
		WebElement form = driver.findElement(By.cssSelector("#test-drive-form"));
		assertEquals(form.getTagName(),"form");
	}

	@Then("^il contient un champ \"([^\"]*)\" \"([^\"]*)\"$")
	public void il_contient_un_champ(String arg1, String arg2) throws Throwable {
		WebElement form = driver.findElement(By.cssSelector("#test-drive-form"));
		List<WebElement> listInputs = form.findElements(By.cssSelector("div.form-item.tds-form-item"));
		for (WebElement element : listInputs) {
			WebElement label = element.findElement(By.tagName("label"));
			System.out.println(label.getText());
		}
		WebElement label = listInputs.get(Integer.parseInt(arg1)-1).findElement(By.tagName("label"));
		assertThat(label.getText().toLowerCase(), containsString(arg2.toLowerCase()));
	}

	@Then("^il contient en bouton d'envoie \"([^\"]*)\"$")
	public void il_contient_en_bouton_d_envoie(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		WebElement submitButton = driver.findElement(By.cssSelector("#edit-submit-td-ajax"));
		assertEquals(submitButton.getAttribute("value"), arg1);
	}

	@When("^je rempli tout les champs du formulaire sauf l'email$")
	public void je_rempli_tout_les_champs_du_formulaire_sauf_l_email() throws Throwable {

		Thread.sleep(2000);
		WebElement form = driver.findElement(By.cssSelector("#test-drive-form"));
		List<WebElement> listInputs = form.findElements(By.cssSelector("div.form-item.tds-form-item")); //.form-type-textfield
		for (WebElement element : listInputs) {
			WebElement label = element.findElement(By.tagName("label"));
			System.out.println(label.getText());
			if(!label.getText().equals("E-mail *") && !label.getText().equals("Region*") && !label.getText().equals("Recevoir les News Tesla") ) {
				System.out.println(label.getText());
				WebElement input = element.findElement(By.tagName("input"));
				input.sendKeys("66666");
			}
			if(label.getText().equals("Recevoir les News Tesla")) {
				break;
			}
		}
		Thread.sleep(500);
	}

	@When("^j'appuie sur Suivant$")
	public void j_appuie_sur_Suivant() throws Throwable {
		WebElement submitButton = driver.findElement(By.cssSelector("#edit-submit-td-ajax"));
		submitButton.click();
		Thread.sleep(500);
	}

	@Then("^un message textuel en rouge apparait sous le champ email indiquant \"([^\"]*)\"$")
	public void un_message_textuel_en_rouge_apparait_sous_le_champ_email_indiquant(String arg1) throws Throwable {
		WebElement mailDiv = driver.findElement(By.cssSelector("div.form-item-usermail-td"));
		WebElement mailRequired = mailDiv.findElement(By.cssSelector("li.parsley-required"));
		assertEquals(mailRequired.getText(), arg1);
	}

	@Then("^je vais dans les caractéristiques$")
	public void je_vais_dans_les_caractéristiques() throws Throwable {
		List<WebElement> listMenu = driver.findElements(By.cssSelector("li.side_nav-item"));
		WebElement caracButton = listMenu.get(7);
		caracButton.click();
		Thread.sleep(1000);
	}

	@When("^je choisis le modèle \"([^\"]*)\"$")
	public void je_choisis_le_modèle(String arg1) throws Throwable {

		List<WebElement> listButtons = driver.findElements(By.cssSelector("a.tds-tab.tcl-tab.tcl-tab--pill"));

		for (WebElement element : listButtons) {
			System.out.println(element.getText());
			if (element.getText().toLowerCase().contains(arg1.toLowerCase())) {
				element.click();
				break;
			}
			Thread.sleep(2000);
		}
	}

	@Then("^le \"([^\"]*)\" est de \"([^\"]*)\"$")
	public void le_est_de(String arg1, String arg2) throws Throwable {
		List<WebElement> listPage = driver.findElements(By.cssSelector("div.tds-flex-item.tds-flex--col_1of2.tcl-specifications__item"));
		WebElement caracteristicsPart = listPage.get(1);
		List<WebElement> listsCaracteristics = caracteristicsPart.findElements(By.cssSelector("li.tds-o-list-item.tds-list-item"));
		for (WebElement element : listsCaracteristics) {
			WebElement label = element.findElement(By.cssSelector("span.tds-list-item_title"));

			if (label.getText().contains(arg1)) {
				System.out.println(arg1);
				if(arg1.equals("Accélération")) {
					assertThat(element.getText(), containsString(arg2));
				}
				else {
					WebElement value = element.findElement(By.cssSelector("span.tds-list-item_value"));
					assertEquals(value.getText(), arg2);
				}

				break;
			}
			//Thread.sleep(2000);
		}
	}

	@When("^je fais une recherche pour le lieu \"([^\"]*)\"$")
	public void je_fais_une_recherche_pour_le_lieu(String arg1) throws Throwable {
		WebElement inputPlace = driver.findElement(By.cssSelector("input#edit-geoautocomplete"));
		Thread.sleep(1000);
		//inputPlace.sendKeys(Keys.CONTROL + "a");
		inputPlace.clear();
		inputPlace.sendKeys(arg1);
		Thread.sleep(500);
		inputPlace.sendKeys(Keys.ENTER);
		Thread.sleep(500);
		WebElement submitButton = driver.findElement(By.cssSelector("input#edit-loupe"));
		submitButton.click();
		Thread.sleep(7000);
	}


	@When("^Je click sur le lien de l'inscription de cet évenement$")
	public void je_click_sur_le_lien_de_l_inscription_de_cet_évenement() throws Throwable {
		List<WebElement> listEvents = driver.findElements(By.cssSelector("div.node.node-event.node-teaser.clearfix"));
		WebElement eventDiv = listEvents.get(1);
		WebElement eventLink = eventDiv.findElement(By.tagName("a"));
		eventLink.click();
		Thread.sleep(3000);
	}

	@Then("^je me retrouve sur l'url' \"([^\"]*)\"$")
	public void je_me_retrouve_sur_l_url(String arg1) throws Throwable {
		assertThat(driver.getCurrentUrl(), containsString(arg1));
	}
	
	@After
	public void afterScenario() {
		driver.quit();
	}
}


//Actions action = new Actions(driver);
//action.click(seeAll); action.build().perform();