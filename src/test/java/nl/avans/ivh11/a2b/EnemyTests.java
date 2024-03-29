package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnemyTests {
    private Enemy enemy;

    @Before
    public void setup() {
        System.out.println("SETUP CALLED");
        EnemyBuilder enemyBuilder = new EnemyBuilder();
        EnemyBuilderDirector enemyDirector = new EnemyBuilderDirector(enemyBuilder);
        Stats stats = new Stats();
        stats.setHitpoints(100);
        stats.setArchery(999);
        enemy = enemyDirector.createEnemy("Bram", new Media("test.png", "test"), "End boss", new SpecialAttack());
        enemy.setStats(stats);
    }

    @Test
    public void enemyBuilderTest() {
        assertEquals("Bram", enemy.getName());
        assertEquals("End boss", enemy.getDescription());
        assertEquals(100, enemy.getStats().getHitpoints());
        assertEquals(999, enemy.getStats().getArchery());
        assertTrue(enemy.getActionBehavior() instanceof  SpecialAttack);
    }

    @Test
    public void enemyIsAliveTest() {
        assertTrue(enemy.isAlive());
    }

    @Test
    public void enemyIsNotAliveTest() {
        enemy.getStats().setCurrentHitpoints(0);
        assertFalse(enemy.isAlive());
    }

    @Test
    public void enemyIsNotAliveWithNegativeAmountTest() {
        enemy.getStats().setCurrentHitpoints(-10);
        assertFalse(enemy.isAlive());
    }

    @Test
    public void enemyTakeDamageSuccessful() {
        // Act
        enemy.takeDamage(5);
        assertEquals(5, enemy.getStats().getCurrentHitpoints());
    }

    @Test
    public void enemyTakeDamageUnsuccessful() {
        // Act
        enemy.takeDamage(0);
        assertEquals(100, enemy.getStats().getHitpoints());
    }

    @Test
    public void enemyRandomDropTest() {
        System.out.println("NOT IMPLEMENTED YET"); // TODO: will be added later
    }

    @Test
    public void enemyReceiveXpTest() {
        assertEquals(100, enemy.getStats().getHitpoints());
    }
}
