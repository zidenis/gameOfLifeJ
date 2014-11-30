package br.unb.cic.lp.gol;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres Java Swing GUI.
 * 
 * @version 1.0
 * @author zidenis
 */
public class GameViewSwing extends JFrame implements GameView {

    private final Color ONOVER_COLOR = Color.CYAN;
    private final Color ALIVE_COLOR = Color.RED;

    private final GameController controller;
    private Statistics statistics;

    public GameViewSwing(GameController controller) {
        this.controller = controller;
        initComponents();
        setVisible(true);
    }

    @Override
    public void update() {
        statistics = controller.getStatistics();
        statsAlivedValue.setText(String.valueOf(statistics.getRevivedCells()));
        statsKilledValue.setText(String.valueOf(statistics.getKilledCells()));
        statsGenerationsValue.setText(String.valueOf(statistics.getNumOfGenerations()));
        for (Component c :gridPanel.getComponents()) {
            GridCell cell = (GridCell) c;
            if (controller.isCellAlive(cell.numLine, cell.numCol)) {
                cell.setAlive();
            } else {
                cell.setDead();
            }
        }
    }

    public class GridCell extends JPanel {

        private final Color DEFAULT_BACKGROUND;
        private Color background;
        private final int numLine;
        private final int numCol;

        public GridCell(int line, int col) {
            setBorder(new LineBorder(Color.LIGHT_GRAY));
            background = getBackground();
            DEFAULT_BACKGROUND = background;
            numLine = line;
            numCol = col;
            addMouseListener(new MouseAdapter() {
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
                        controller.makeCellAlive(numLine, numCol);
                    }
                }
            });
        }   

        public void setAlive() {
            background = ALIVE_COLOR;
            setBackground(background);
        }

        private void setDead() {
            background = DEFAULT_BACKGROUND;
            setBackground(background);
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
        statsAlivedLabel = new javax.swing.JLabel();
        statsAlivedValue = new javax.swing.JLabel();
        statsKilledLabel = new javax.swing.JLabel();
        statsKilledValue = new javax.swing.JLabel();
        statsGenerationsLabel = new javax.swing.JLabel();
        statsGenerationsValue = new javax.swing.JLabel();
        speedSlider = new javax.swing.JSlider();
        bottomPanel = new javax.swing.JPanel();
        prevButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gridPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        gridPanel.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(gridPanel, java.awt.BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < Main.GRID_HEIGHT; row++) {
            for (int col = 0; col < Main.GRID_WIDTH; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                gridPanel.add(new GridCell(row, col), gbc);
            }
        }

        rightPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 0, 15, 15));
        rightPanel.setLayout(new javax.swing.BoxLayout(rightPanel, javax.swing.BoxLayout.Y_AXIS));

        strategiesPane.setBackground(new java.awt.Color(238, 238, 238));
        strategiesPane.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true), "Strategy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 12), java.awt.Color.lightGray)); // NOI18N

        strategiesList.setBackground(new java.awt.Color(238, 238, 238));
        strategiesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        strategiesPane.setViewportView(strategiesList);

        rightPanel.add(strategiesPane);

        statisticsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true), "Statistics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 12), java.awt.Color.lightGray)); // NOI18N
        statisticsPanel.setLayout(new java.awt.GridLayout(0, 2));

        statsAlivedLabel.setText("   Alived Cells:");
        statisticsPanel.add(statsAlivedLabel);

        statsAlivedValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statsAlivedValue.setText("0");
        statisticsPanel.add(statsAlivedValue);

        statsKilledLabel.setText("   Killed Cells:");
        statisticsPanel.add(statsKilledLabel);

        statsKilledValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statsKilledValue.setText("0");
        statisticsPanel.add(statsKilledValue);

        statsGenerationsLabel.setText("   Generations:");
        statisticsPanel.add(statsGenerationsLabel);

        statsGenerationsValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statsGenerationsValue.setText("0");
        statisticsPanel.add(statsGenerationsValue);

        rightPanel.add(statisticsPanel);

        speedSlider.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        speedSlider.setForeground(java.awt.Color.lightGray);
        speedSlider.setMajorTickSpacing(500);
        speedSlider.setMaximum(2250);
        speedSlider.setMinimum(250);
        speedSlider.setMinorTickSpacing(250);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setValue(1250);
        speedSlider.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true), "Next Gen. Building Time (ms)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 12), java.awt.Color.lightGray)); // NOI18N
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });
        rightPanel.add(speedSlider);

        getContentPane().add(rightPanel, java.awt.BorderLayout.EAST);

        bottomPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 15, 15));
        bottomPanel.setAlignmentX(1.0F);
        bottomPanel.setAlignmentY(0.5F);
        bottomPanel.setLayout(new javax.swing.BoxLayout(bottomPanel, javax.swing.BoxLayout.X_AXIS));

        prevButton.setText("<< Prev.");
        prevButton.setEnabled(false);
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(prevButton);

        playButton.setText("Play >");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(playButton);

        nextButton.setText("Next >>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(nextButton);

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(clearButton);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(resetButton);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        String label = playButton.getText();
        if (label.equals("Play >")) {
            playButton.setText("Pause ");
            controller.start();
        }
        else {
            playButton.setText("Play >");
             controller.halt();
        }
    }//GEN-LAST:event_playButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        controller.nextGeneration();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        playButton.setText("Play >");
        controller.killAllCells();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        controller.setDelayTime(speedSlider.getValue());
    }//GEN-LAST:event_speedSliderStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        clearButtonActionPerformed(evt);
        controller.reset();
    }//GEN-LAST:event_resetButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel gridPanel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JPanel statisticsPanel;
    private javax.swing.JLabel statsAlivedLabel;
    private javax.swing.JLabel statsAlivedValue;
    private javax.swing.JLabel statsGenerationsLabel;
    private javax.swing.JLabel statsGenerationsValue;
    private javax.swing.JLabel statsKilledLabel;
    private javax.swing.JLabel statsKilledValue;
    private javax.swing.JList strategiesList;
    private javax.swing.JScrollPane strategiesPane;
    // End of variables declaration//GEN-END:variables
}
