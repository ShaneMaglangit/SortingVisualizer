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
        ArrayList<Integer> factors = getFactors(view.getCanvas().getWidth());

        for(int i = 0; i < factors.get(factors.size() - 3); i++) {
            values.add((int)(Math.random() * 95 + 5));
        }

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
                        Thread.sleep(0, 500);
                    }
                }
            } catch (InterruptedException interruptedException) {
                System.out.println("Thread interrupted");
            } finally {
                view.updateCanvas(values, null);
            }
        }).start();
    }

    private ArrayList<Integer> getFactors(int num) {
        ArrayList<Integer> factors = new ArrayList();

        for(int i = 1; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                factors.add(i);
                if(i != Math.sqrt(num)) {
                    factors.add(num / i);
                }
            }
        }

        Collections.sort(factors);

        return factors;
    }
}
