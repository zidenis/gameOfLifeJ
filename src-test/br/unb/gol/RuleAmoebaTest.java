package br.unb.gol;

import br.unb.gol.GameRule;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests Conway's Rule
 * @author zidenis
 */
public class RuleAmoebaTest {
    
    GameRule rule;
    
    @Before
    public void setUp() {
        rule = new GameRule("Amoeba", new int[] {1,3,5,8}, new int[] {3,5,7});
    }
    
    @Test
    public void testShouldKeepAlive() {
        assertTrue(rule.shouldKeepAlive(1));
        assertFalse(rule.shouldKeepAlive(2));
        assertTrue(rule.shouldKeepAlive(3));
        assertFalse(rule.shouldKeepAlive(4));
        assertTrue(rule.shouldKeepAlive(5));
        assertFalse(rule.shouldKeepAlive(6));
        assertFalse(rule.shouldKeepAlive(7));
        assertTrue(rule.shouldKeepAlive(8));
    }
    
    @Test
    public void testShouldRevive() {
        assertFalse(rule.shouldRevive(1));
        assertFalse(rule.shouldRevive(2));
        assertTrue(rule.shouldRevive(3));
        assertFalse(rule.shouldRevive(4));
        assertTrue(rule.shouldRevive(5));
        assertFalse(rule.shouldRevive(6));
        assertTrue(rule.shouldRevive(7));
        assertFalse(rule.shouldRevive(8));
    }
}
