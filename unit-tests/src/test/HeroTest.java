package test;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import static org.hamcrest.Matchers.*;

import codingfactory.rpgconsole.hero.Hero;
import codingfactory.rpgconsole.enemy.Enemy;

public class HeroTest {

	Hero hero;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Avant le démarrage");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Après tous les tests");
	}

	@Before
	public void setUp() throws Exception {
		hero = new Hero("Revali & Daruk");
		System.out.println("Avant un test");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Après un test");
	}

	/*
	@Test
	public void testSetLevel() throws Exception {
		hero.setLevel(7);
		assertThat(hero, hasProperty("level", is(7)));
	}
	 */

	@Test
	public void testHeroLevelUp() throws Exception {
		System.out.println("Avant le démarrage");
		int oldvalue = hero.getLevel();
		hero.levelUp();
		assertThat(hero, hasProperty("level", is(oldvalue + 1)));
	}

	@Test
	public void testTakeDamage() throws Exception {
		int oldHp = hero.getHp();
		hero.takeDamage(10);
		assertThat(hero, hasProperty("hp", is(oldHp - 10)));
	}

	@Test
	public void testHeroProperties() throws Exception {
		System.out.println("Avant le démarrage");
		//assertThat(hero, hasProperty("name"));
        assertThat(hero, hasProperty("name", is("Revali & Daruk")));
		assertThat(hero, hasProperty("level", is(1)));
		assertThat(hero, hasProperty("hp", is(20)));
		assertThat(hero, hasProperty("atk", is(2)));
	}

	@Test
	public void testAttack() throws Exception {
		Enemy enemy = new Enemy("Lynel Doré", 15);
		int oldHp = enemy.getHp();
		hero.attack(enemy);
		int newHp = enemy.getHp();
		assertThat(newHp, is(lessThan(oldHp)));
	}

	/*public static void main(String []args) {
		HeroTest hero = new	HeroTest();

		hero.testAttack();

	}*/

}

