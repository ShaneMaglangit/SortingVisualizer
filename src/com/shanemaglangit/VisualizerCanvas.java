package com.shanemaglangit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisualizerCanvas extends JPanel {
    ArrayList<Integer> values;
    Integer activeIndex = null;

    public VisualizerCanvas() {
        this.values = new ArrayList<>();
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
            if(activeIndex != null && activeIndex == i) g2.setColor(Color.WHITE);
            else g2.setColor(Color.GRAY);

            int width = getWidth() / values.size();
            int height = getHeight() / 100 * values.get(i);
            int x = width * i;
            g2.fillRect(x, 0, width, height);
        }
    }
}
