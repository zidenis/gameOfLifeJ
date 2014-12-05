/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol;

import java.util.ArrayList;
import java.util.List;

/**
 * John Conway's "Game of Life" has fascinated and inspired many enthusiasts
 * due to the emergence of complex behavior from a very simple system.
 * There are 262144 (2^218) distinct Life-like rules.
 * List of interesting rules: {@link http://fano.ics.uci.edu/ca/rules/list.html}
 * @version 1.0
 * @author zidenis
 */
public class GameRuleList {

    List<GameRule> gameRuleList;
    GameRule activeRule;

    public GameRuleList() {
        gameRuleList = new ArrayList();
        gameRuleList.add(new GameRule("Conway Life", new int[]{2, 3}, new int[]{3}));
        gameRuleList.add(new GameRule("Amoeba", new int[]{1, 3, 5, 8}, new int[]{3, 5, 7}));
        gameRuleList.add(new GameRule("Assimilation", new int[]{4, 5, 6, 7}, new int[]{3, 4, 5}));
        gameRuleList.add(new GameRule("Backteria", new int[]{4, 5, 6}, new int[]{3, 4}));
        gameRuleList.add(new GameRule("Blinkers", new int[]{2}, new int[]{3, 4, 5}));
        gameRuleList.add(new GameRule("Bugs", new int[]{1, 5, 6, 7, 8}, new int[]{3, 5, 6, 7}));
        gameRuleList.add(new GameRule("Coagulations", new int[]{2, 3, 5, 6, 7, 8}, new int[]{3, 7, 8}));
        gameRuleList.add(new GameRule("Coral", new int[]{4, 5, 6, 7, 8}, new int[]{3}));
        gameRuleList.add(new GameRule("Day & Night", new int[]{3, 4, 6, 7, 8}, new int[]{3, 6, 7, 8}));
        gameRuleList.add(new GameRule("Diamoeba", new int[]{5, 6, 7, 8}, new int[]{3, 5, 6, 7, 8}));
        gameRuleList.add(new GameRule("Gnarl", new int[]{1}, new int[]{1}));
        gameRuleList.add(new GameRule("H-trees", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1}));
        gameRuleList.add(new GameRule("HighLife", new int[]{2, 3}, new int[]{3, 6}));
        gameRuleList.add(new GameRule("Holstein", new int[]{4, 6, 7, 8}, new int[]{3, 5, 6, 7, 8}));
        gameRuleList.add(new GameRule("Iceballs", new int[]{5, 6, 7, 8}, new int[]{2, 5, 6, 7, 8}));
        gameRuleList.add(new GameRule("Land Rush", new int[]{2, 3, 4, 5, 7, 8}, new int[]{3, 6}));
        gameRuleList.add(new GameRule("Life Without Death", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, new int[]{3}));
        gameRuleList.add(new GameRule("LongLife", new int[]{5}, new int[]{3, 4, 5}));
        gameRuleList.add(new GameRule("Majority", new int[]{5, 6, 7, 8}, new int[]{4, 5, 6, 7, 8}));
        gameRuleList.add(new GameRule("Maze", new int[]{1, 2, 3, 4, 5}, new int[]{3}));
        gameRuleList.add(new GameRule("Move", new int[]{2, 4, 5}, new int[]{3, 6, 8}));
        gameRuleList.add(new GameRule("Pseudo Life", new int[]{2, 3, 8}, new int[]{3, 5, 7}));
        gameRuleList.add(new GameRule("Replicator", new int[]{1, 3, 5, 7}, new int[]{1, 3, 5, 7}));
        gameRuleList.add(new GameRule("Seeds", new int[]{}, new int[]{2}));
        gameRuleList.add(new GameRule("Serviettes", new int[]{}, new int[]{2, 3, 4}));
        gameRuleList.add(new GameRule("Slow Blob", new int[]{1, 2, 5, 6, 7, 8}, new int[]{3, 6, 7}));
        gameRuleList.add(new GameRule("Stains", new int[]{2, 3, 5, 6, 7, 8}, new int[]{3, 6, 7, 8}));
        gameRuleList.add(new GameRule("2x2", new int[]{1, 2, 5}, new int[]{3, 6}));
        gameRuleList.add(new GameRule("3-4 Life", new int[]{3, 4}, new int[]{3}));
        activeRule = gameRuleList.get(0);
    }

    public GameRule getRuleAt(int index) {
        return gameRuleList.get(index);
    }
    
    public GameRule getActiveRule() {
        return activeRule;
    }

    public void setActiveRule(int index) {
        this.activeRule = gameRuleList.get(index);
    }

    public void addNewRule(GameRule gameRule) {
        gameRuleList.add(gameRule);
    }
    public List<GameRule> getGameRuleList() {
        return gameRuleList;
    }
}
