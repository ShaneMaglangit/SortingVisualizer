package com.shanemaglangit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {
    private Canvas canvas;
    private JPanel pnlTop;
    private JButton btnRandomize;
    private JButton btnVisualize;
    private JComboBox cbxAlgorithm;

    public View() throws HeadlessException {
        super();
        String[] algorithms = {AlgorithmType.QUICK.getEquiv(), AlgorithmType.HEAP.getEquiv(), AlgorithmType.MERGE.getEquiv(),
                AlgorithmType.SELECTION.getEquiv(), AlgorithmType.INSERTION.getEquiv(), AlgorithmType.BUBBLE.getEquiv()};
        int minWidth = Toolkit.getDefaultToolkit().getScreenSize().width / 10 * 8;
        int minHeight = Toolkit.getDefaultToolkit().getScreenSize().height / 10 * 8;

        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);

        canvas = new Canvas();
        pnlTop = new JPanel();
        btnRandomize = new JButton("Randomize");
        btnVisualize = new JButton("Visualize");
        cbxAlgorithm = new JComboBox(algorithms);

        cbxAlgorithm.setSelectedIndex(0);

        pnlTop.setLayout(new FlowLayout());
        pnlTop.setMaximumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 200));
        pnlTop.add(cbxAlgorithm);
        pnlTop.add(btnRandomize);
        pnlTop.add(btnVisualize);

        add(pnlTop);
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

    public JComboBox getCbxAlgorithm() {
        return cbxAlgorithm;
    }

    public JButton getBtnVisualize() {
        return btnVisualize;
    }

    public void setButtonsEnabled(boolean isEnabled) {
        btnVisualize.setEnabled(isEnabled);
        btnRandomize.setEnabled(isEnabled);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void updateCanvas(ArrayList<Integer> values, Integer activeIndex) {
        canvas.setValues(values);
        canvas.setActiveIndex(activeIndex);
        canvas.repaint();
    }
}
