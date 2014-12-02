package br.unb.cic.lp.gol;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class RulesListModel implements ListModel {
    
    GameController controller;

    public RulesListModel(GameController controller) {
        this.controller = controller;
    }
    
    @Override
    public int getSize() {
        return controller.getRules().getGameRuleList().size();
    }

    @Override
    public Object getElementAt(int index) {
        return controller.getRules().getRuleAt(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        //DEBUG System.out.println("Rule added");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        //DEBUG System.out.println("Rule remover");
    }
}
