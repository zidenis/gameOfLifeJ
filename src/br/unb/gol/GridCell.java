/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.gol;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author zidenis
 */
public class GridCell extends JPanel {
    
    private final Color ONOVER_COLOR = Color.CYAN;
    private final Color ALIVE_COLOR = Color.RED;
    private final Color DEFAULT_BACKGROUND;
    
    private Color background;
    final int numLine;
    final int numCol;

    public GridCell(int line, int col, final GameController controller) {
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        background = getBackground();
        DEFAULT_BACKGROUND = background;
        numLine = line;
        numCol = col;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(ONOVER_COLOR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(background);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (controller.isCellAlive(numLine, numCol)) {
                    controller.killCell(numLine, numCol);
                } else {
                    controller.createCell(numLine, numCol);
                }
            }
        });
    }

    public void setAlive() {
        background =ALIVE_COLOR;
        setBackground(background);
    }

    void setDead() {
        background = DEFAULT_BACKGROUND;
        setBackground(background);
    }
    
}
