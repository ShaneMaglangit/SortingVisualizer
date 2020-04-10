package com.shanemaglangit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class VisualizerCanvas extends JPanel {
    private static final int BORDER_WIDTH = 2;

    ArrayList<Integer> values;
    Integer activeIndex = null;

    public VisualizerCanvas() {
        this.values = new ArrayList<>();

        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_WIDTH));
    }

    public int getWidthWithBorder() {
        return getWidth() - BORDER_WIDTH;
    }

    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for(int i = 0; i < values.size(); i++) {
            if(activeIndex != null && activeIndex == i) g2.setColor(Color.RED);
            else g2.setColor(Color.WHITE);

            int width = getWidthWithBorder() / values.size();
            int x = (width * i) + (BORDER_WIDTH / 2);
            g2.fillRect(x, 0, width, values.get(i));
        }
    }
}
