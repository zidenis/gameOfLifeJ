/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * John Conway's "Game of Life" has fascinated and inspired many enthusiasts
 * due to the emergence of complex behavior from a very simple system.
 * There are 262144 (2^218) distinct Life-like rules.
 * List of interesting rules: {@link http://fano.ics.uci.edu/ca/rules/list.html}
 * @version 1.0
 * @author zidenis
 */
public class GameRuleList {

    private final String RULES_FILE = "Rules.json";
    
    List<GameRule> gameRuleList;
    GameRule activeRule;

    public GameRuleList() {
        gameRuleList = new ArrayList();
        JSONParser parser = new JSONParser();
        try {
            JSONArray rulesArray = (JSONArray) parser.parse(new FileReader(RULES_FILE));
            for (Object ruleObj : rulesArray) {
                
            }

        } catch (FileNotFoundException ex) {
            System.err.println("Rules file not found: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        gameRuleList.add(new GameRule("Conway Life", new int[]{2, 3}, new int[]{3}));
        gameRuleList.add(new GameRule("Amoeba", new int[]{1, 3, 5, 8}, new int[]{3, 5, 7}));
        
        activeRule = gameRuleList.get(0);
    }

    public GameRule getRuleAt(int index) {
        return gameRuleList.get(index);
    }
    
    protected GameRule getActiveRule() {
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
