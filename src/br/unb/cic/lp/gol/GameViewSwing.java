package br.unb.cic.lp.gol;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 * 
 * @author zidenis
 */
public class GameViewSwing extends JFrame {

    private final Color ONOVER_BACKGROUND = Color.CYAN;

    private final GameEngine engine;
    private final GameController controller;

    public GameViewSwing(GameController controller, GameEngine engine) {
        this.engine = engine;
        this.controller = controller;
        initComponents();
    }

    public class GridCell extends JPanel {

        private Color background;
        private final int x;
        private final int y;

        public GridCell(int col, int line) {
            setBorder(new LineBorder(Color.LIGHT_GRAY));
            background = getBackground();
            x = col;
            y = line;

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(ONOVER_BACKGROUND);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(background);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                System.out.println("Cell: " + x + "," + y);
                    controller.makeCellAlive(x, y);
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gridPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        strategiesPane = new javax.swing.JScrollPane();
        strategiesList = new javax.swing.JList();
        statisticsPanel = new javax.swing.JPanel();
        statsRevivedLabel = new javax.swing.JLabel();
        statsRevivedValue = new javax.swing.JLabel();
        statsKilledLabel = new javax.swing.JLabel();
        statsKilledValue = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        prevButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gridPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        gridPanel.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(gridPanel, java.awt.BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < Main.GRID_HEIGHT; row++) {
            for (int col = 0; col < Main.GRID_WIDTH; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                gridPanel.add(new GridCell(col, row), gbc);
            }
        }

        rightPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 0, 15, 15));
        rightPanel.setLayout(new javax.swing.BoxLayout(rightPanel, javax.swing.BoxLayout.Y_AXIS));

        strategiesPane.setBackground(new java.awt.Color(238, 238, 238));
        strategiesPane.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true), "Strategy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), java.awt.Color.lightGray)); // NOI18N

        strategiesList.setBackground(new java.awt.Color(238, 238, 238));
        strategiesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        strategiesPane.setViewportView(strategiesList);

        rightPanel.add(strategiesPane);

        statisticsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true), "Statistics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), java.awt.Color.lightGray)); // NOI18N
        statisticsPanel.setLayout(new java.awt.GridLayout(2, 2));

        statsRevivedLabel.setText("Revived Cells:");
        statisticsPanel.add(statsRevivedLabel);

        statsRevivedValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statsRevivedValue.setText("0");
        statisticsPanel.add(statsRevivedValue);

        statsKilledLabel.setText("Killed Cells:");
        statisticsPanel.add(statsKilledLabel);

        statsKilledValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statsKilledValue.setText("0");
        statisticsPanel.add(statsKilledValue);

        rightPanel.add(statisticsPanel);

        getContentPane().add(rightPanel, java.awt.BorderLayout.EAST);

        bottomPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 15, 15));
        bottomPanel.setLayout(new javax.swing.BoxLayout(bottomPanel, javax.swing.BoxLayout.X_AXIS));

        prevButton.setText("<< Prev.");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(prevButton);

        playButton.setText("Play");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(playButton);

        nextButton.setText("Next >>");
        bottomPanel.add(nextButton);

        clearButton.setText("Clear");
        bottomPanel.add(clearButton);
        bottomPanel.add(speedSlider);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prevButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel gridPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JPanel statisticsPanel;
    private javax.swing.JLabel statsKilledLabel;
    private javax.swing.JLabel statsKilledValue;
    private javax.swing.JLabel statsRevivedLabel;
    private javax.swing.JLabel statsRevivedValue;
    private javax.swing.JList strategiesList;
    private javax.swing.JScrollPane strategiesPane;
    // End of variables declaration//GEN-END:variables
}
