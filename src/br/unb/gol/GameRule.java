package br.unb.gol;

import java.util.HashSet;
import java.util.Set;

/**
 * John Conway's "Game of Life" has fascinated and inspired many enthusiasts
 * due to the emergence of complex behavior from a very simple system.
 * There are 262144 (2^218) distinct Life-like rules.
 * List of interesting rules: {@link http://fano.ics.uci.edu/ca/rules/list.html}
 * @author zidenis
 */
public class GameRule {

    private final String rulestring;
    private final Set<Integer> survivalSet;
    private final Set<Integer> birthSet;

    /**
     * Build a new GameRule.
     * @param ruleName name of the rule (e.g.: HighLife, Conway's Life, etc)
     * @param survivalList  a list of all the numbers of ON cells that cause an ON cell to remain ON
     * @param birthList a list of all the numbers of ON cells that cause an OFF cell to turn ON.
     */
    
    public GameRule(String ruleName, int[] survivalList, int[] birthList) {
        StringBuilder sb = new StringBuilder(ruleName).append(" (");
        survivalSet = new HashSet<Integer>();
        birthSet = new HashSet<Integer>();
        for (int s: survivalList) {
            survivalSet.add(s);
            sb.append(s);
        }
        sb.append('/');
        for (int b: birthList) {
            birthSet.add(b);
            sb.append(b);
        }
        rulestring = sb.append(')').toString();
    }

    /**
     * Checks if the cell should keep alive.
     * @param numOfNeighborCells number of neighbor cells
     * @return true if the cell should keel alive
     */
    protected boolean shouldKeepAlive(int numOfNeighborCells) {
        return survivalSet.contains(numOfNeighborCells);
    }

    /**
     * Checks if the cell should revive.
     * @param numOfNeighborCells number of neighbor cells
     * @return true if the cell should revive
     */
    protected boolean shouldRevive(int numOfNeighborCells) { 
        return birthSet.contains(numOfNeighborCells);
    }

    /**
     * A common notation used to describe life-like cellular automata "S/B".
     * which is known as its rule (or rulestring).
     * S (for survival) is a list of all the numbers of ON cells that cause an ON cell to remain ON. 
     * B (for birth) is a list of all the numbers of ON cells that cause an OFF cell to turn ON.
     * {@link http://www.conwaylife.com/wiki/Cellular_automaton}
     * @return "S/B" rulestring
     */
    @Override
    public String toString() {
        return rulestring;
    }
}
