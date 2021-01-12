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

public class EnemyTest {

    Enemy enemy;

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
        enemy = new Enemy("Tyreen", 69);
        System.out.println("Avant un test");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Après un test");
    }

	/*
	@Test
	public void testSetLevel() throws Exception {
		Enemy.setLevel(7);
		assertThat(Enemy, hasProperty("level", is(7)));
	}
	 */

    @Test
    public void testTakeDamage()throws Exception {
        int oldHp = enemy.getHp();
        enemy.takeDamage(10);
        assertThat(enemy, hasProperty("hp", is(oldHp - 10)));
    }

    @Test
    public void testAttack()throws Exception {
        Hero hero = new Hero("FL4K");
        int oldHp = hero.getHp();
        enemy.attack(hero);
        int newHp = hero.getHp();
        assertThat(oldHp, is(lessThan(newHp)));
    }

    @Test
    public void testEnemyProperties() throws Exception {
        //assertThat(Enemy, hasProperty("name"));
        assertThat(enemy, hasProperty("name", is("Tyreen")));
        assertThat(enemy, hasProperty("level", is(69)));
        assertThat(enemy, hasProperty("hp", is(enemy.getHp()*15)));
        assertThat(enemy, hasProperty("atk", is(enemy.getHp())));
    }

}
