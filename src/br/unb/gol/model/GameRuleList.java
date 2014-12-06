/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

    private final String EXTRA_RULES_FILE = "ExtraRules.json";
    
    List<GameRule> gameRuleList;
    GameRule activeRule;

    public GameRuleList() {
        gameRuleList = new ArrayList<GameRule>();
        //Adding Conway Game of Life Rule as Default rule
        gameRuleList.add(new GameRule("Conway Life", new int[]{2, 3}, new int[]{3}));
        activeRule = gameRuleList.get(0);
        //Loading Extra Rules from file
        JSONParser parser = new JSONParser();
        try {
            JSONArray rulesArray = (JSONArray) parser.parse(new FileReader(EXTRA_RULES_FILE));
            for (Object obj : rulesArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String rulestring = (String) jsonObj.get("rulestring");
                JSONArray survivalSet = (JSONArray) jsonObj.get("survivalSet");
                JSONArray birthSet = (JSONArray) jsonObj.get("birthSet");
                int[] survivalArray = new int[survivalSet.size()];
                int[] birthArray = new int[birthSet.size()];
                for (int i = 0; i < survivalSet.size(); i++) {
                    survivalArray[i] = Long.valueOf((long)survivalSet.get(i)).intValue();
                }
                for (int i = 0; i < birthSet.size(); i++) {
                    birthArray[i] = Long.valueOf((long)birthSet.get(i)).intValue();
                }
                gameRuleList.add(new GameRule(rulestring, survivalArray, birthArray));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Extra rules file not found: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
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
