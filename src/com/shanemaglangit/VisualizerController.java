package com.shanemaglangit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VisualizerController implements ActionListener {
    VisualizerFrame view;

    VisualizerController(VisualizerFrame view) {
        this.view = view;

        view.getBtnRandomize().addActionListener(this);
        view.getBtnVisualize().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(view.getBtnRandomize())) randomize();
        else if(e.getSource().equals(view.getBtnVisualize())) visualize();
    }

    private void randomize() {
        ArrayList<Integer> values = new ArrayList();
        ArrayList<Integer> factors = Util.getFactors(view.getCanvas().getWidth());
        int valueHeight = view.getCanvas().getHeight();

        for(int i = 1; i <= factors.get(factors.size() - 2); i++) {
            if(valueHeight >= 10) values.add(valueHeight--);
            else values.add((int)(Math.random() * view.getCanvas().getHeight() + 10));
        }

        Collections.shuffle(values);

        view.updateCanvas(values, null);
    }

    private void visualize() {
        ArrayList<Integer> values = view.getCanvas().getValues();

        new Thread(() -> {
            try {
                for (int i = 0; i < values.size(); i++) {
                    for (int j = 0; j < values.size() - 1; j++) {
                        view.updateCanvas(values, j);
                        if (values.get(j) > values.get(j + 1)) {
                            int temp = values.get(j);
                            values.set(j, values.get(j + 1));
                            values.set(j + 1, temp);
                        }
                        Thread.sleep(0, 10);
                    }
                }
            } catch (InterruptedException interruptedException) {
                System.out.println("Thread interrupted");
            } finally {
                view.updateCanvas(values, null);
            }
        }).start();
    }
}
