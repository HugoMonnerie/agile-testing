package test.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class FunctionalTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Library/Java/JUNIT/chromedriver");
        driver = new ChromeDriver();
        // Seems no more working in last Chrome versions
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // Test de la Story #1-homepage (https://trello.com/c/WKTneu9o/1-homepage)
    /*@Test
    public void testHomepage() throws Exception {
        driver.get("https://www.meetup.com/fr-FR/");
        assertEquals(driver.getTitle(), "Partagez vos passions | Meetup");

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals(h1.getText(), "Le monde vous tend les bras");

        WebElement psoutitre = driver.findElement(By.className("exploreHome-hero-subTitle"));
        WebElement spansoutitre = psoutitre.findElement(By.tagName("span"));
        assertEquals(spansoutitre.getText(), "Rejoignez un groupe local pour rencontrer du monde, tester une nouvelle activité ou partager vos passions.");

        WebElement buttonRed = driver.findElement(By.className("button--primary"));
        assertEquals(buttonRed.getText(), "Rejoindre Meetup");
        assertEquals(buttonRed.getAttribute("href"), "https://www.meetup.com/fr-FR/register/");
    }
     */

    @Test
    public void testRecherche() throws Exception {
        driver.get("https://www.meetup.com/fr-FR/find/outdoors-adventure/");
        assertThat(driver.getTitle().toLowerCase() ,containsString("Nature et Aventure".toLowerCase()));
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertThat(h1.getText().toLowerCase() ,containsString("Nature et Aventure".toLowerCase()));

        WebElement seachform = driver.findElement(By.id("searchForm"));
        

    }



    // Test de la Story n ...
    // TODO
    // To Be Completed By Coders From Coding Factory

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
