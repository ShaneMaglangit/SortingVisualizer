package com.shanemaglangit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisualizerFrame extends JFrame {
    private VisualizerCanvas canvas;
    private JButton btnRandomize;
    private JButton btnVisualize;

    public VisualizerFrame() throws HeadlessException {
        super();
        int minWidth = Toolkit.getDefaultToolkit().getScreenSize().width / 10 * 8;
        int minHeight = Toolkit.getDefaultToolkit().getScreenSize().height / 10 * 8;

        canvas = new VisualizerCanvas();
        btnRandomize = new JButton("Randomize");
        btnVisualize = new JButton("Visualize");

        canvas.setBackground(Color.BLACK);

        add(btnRandomize);
        add(btnVisualize);
        add(canvas);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(minWidth, minHeight));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getBtnRandomize() {
        return btnRandomize;
    }

    public JButton getBtnVisualize() {
        return btnVisualize;
    }

    public VisualizerCanvas getCanvas() {
        return canvas;
    }

    public void updateCanvas(ArrayList<Integer> values, Integer activeIndex) {
        canvas.setValues(values);
        canvas.setActiveIndex(activeIndex);
        canvas.repaint();
    }
}
