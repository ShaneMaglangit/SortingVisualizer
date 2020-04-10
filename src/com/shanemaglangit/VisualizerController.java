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
        ArrayList<Integer> factors = Util.getFactors(view.getCanvas().getWidthWithBorder());
        int valueHeight = view.getCanvas().getHeight();
        int decrement = valueHeight / factors.get(factors.size() - 3) + 1;

        for(int i = 1; i <= factors.get(factors.size() - 3); i++) {
            if(valueHeight >= 10) values.add(valueHeight -= decrement);
            else values.add((int)(Math.random() * view.getCanvas().getHeight() + 10));
        }

        Collections.shuffle(values);

        view.updateCanvas(values, null);
    }

    private void visualize() {
        ArrayList<Integer> values = view.getCanvas().getValues();

        new Thread(() -> {
            try {
//                Algorithm.bubbleSort(values, view, 0, 2);
//                Algorithm.selectionSort(values, view, 0, 2);
//                Algorithm.insertionSort(values, view, 0, 2);
//                Algorithm.heapSort(values, view, 0, 10);
//                Algorithm.mergeSort(values, view, 0, values.size() - 1, 5, 0);
                Algorithm.quickSort(values, view, 0, values.size() - 1, 5, 0);
            } catch (InterruptedException interruptedException) {
                System.out.println("Thread interrupted");
            } finally {
                view.updateCanvas(values, null);
            }
        }).start();
    }
}
