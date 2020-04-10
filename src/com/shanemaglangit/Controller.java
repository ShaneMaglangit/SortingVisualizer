package com.shanemaglangit;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Controller extends ComponentAdapter implements ActionListener {
    View view;

    Controller(View view) {
        this.view = view;

        view.getBtnRandomize().addActionListener(this);
        view.getBtnVisualize().addActionListener(this);
        view.addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        randomize();
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
        int decrement = valueHeight / factors.get(factors.size() - 2) + 1;

        for(int i = 1; i <= factors.get(factors.size() - 2); i++) {
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
                String selectedAlgorithm = view.getCbxAlgorithm().getSelectedItem().toString();

                view.setResizable(false);
                view.setButtonsEnabled(false);

                if(selectedAlgorithm.equals(AlgorithmType.QUICK.getEquiv())) {
                    Algorithm.quickSort(values, view, 0, values.size() - 1);
                } else if(selectedAlgorithm.equals(AlgorithmType.MERGE.getEquiv())) {
                    Algorithm.mergeSort(values, view, 0, values.size() - 1);
                } else if(selectedAlgorithm.equals(AlgorithmType.HEAP.getEquiv())) {
                    Algorithm.heapSort(values, view);
                } else if(selectedAlgorithm.equals(AlgorithmType.SELECTION.getEquiv())) {
                    Algorithm.selectionSort(values, view);
                } else if(selectedAlgorithm.equals(AlgorithmType.INSERTION.getEquiv())) {
                    Algorithm.insertionSort(values, view);
                } else {
                    Algorithm.bubbleSort(values, view);
                }
            } catch (InterruptedException interruptedException) {
                System.out.println("Thread interrupted");
            } finally {
                view.setButtonsEnabled(true);
                view.setResizable(true);
                view.updateCanvas(values, null);
            }
        }).start();
    }
}
