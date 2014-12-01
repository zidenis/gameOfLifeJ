package br.unb.cic.lp.gol;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests Conway's Rule
 * @author zidenis
 */
public class RuleConwayTest {
    
    GameRule rule;
    
    @Before
    public void setUp() {
        rule = new GameRule("Conway's Life", new int[] {2, 3}, new int[] {3});
    }
    
    @Test
    public void testShouldKeepAlive() {
        assertFalse(rule.shouldKeepAlive(1));
        assertTrue(rule.shouldKeepAlive(2));
        assertTrue(rule.shouldKeepAlive(3));
        assertFalse(rule.shouldKeepAlive(4));
        assertFalse(rule.shouldKeepAlive(5));
        assertFalse(rule.shouldKeepAlive(6));
        assertFalse(rule.shouldKeepAlive(7));
        assertFalse(rule.shouldKeepAlive(8));
    }
    
    @Test
    public void testShouldRevive() {
        assertFalse(rule.shouldRevive(1));
        assertFalse(rule.shouldRevive(2));
        assertTrue(rule.shouldRevive(3));
        assertFalse(rule.shouldRevive(4));
        assertFalse(rule.shouldRevive(5));
        assertFalse(rule.shouldRevive(6));
        assertFalse(rule.shouldRevive(7));
        assertFalse(rule.shouldRevive(8));
    }
}
