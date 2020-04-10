package com.shanemaglangit;

import java.util.ArrayList;
import java.util.List;

public abstract class Algorithm {
    public static void bubbleSort(ArrayList<Integer> values, VisualizerFrame view, long millis, int nano) throws InterruptedException {
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size() - 1; j++) {
                view.updateCanvas(values, j);
                if (values.get(j) > values.get(j + 1)) swap(values, j, j + 1);
                Thread.sleep(millis, nano);
            }
        }
    }

    public static void selectionSort(ArrayList<Integer> values, VisualizerFrame view, long millis, int nano) throws InterruptedException {
        for(int i = 0; i < values.size() - 1; i++) {
            int smallest = i;
            for(int j = i + 1; j < values.size(); j++) {
                if(values.get(j) < values.get(smallest)) smallest = j;
                view.updateCanvas(values, j);
                Thread.sleep(millis, nano);
            }
            swap(values, i, smallest);
        }
    }

    public static void insertionSort(ArrayList<Integer> values, VisualizerFrame view, long millis, int nano) throws InterruptedException {
        for(int i = 1; i < values.size(); i++) {
            if(values.get(i) < values.get(i - 1)) {
                int temp = values.get(i);

                view.updateCanvas(values, i);
                values.remove(i);
                Thread.sleep(millis, nano);

                for(int j = 0; j < i; j++) {
                    if(values.get(j) > temp) {
                        values.add(j, temp);
                        break;
                    }
                    view.updateCanvas(values, j);
                    Thread.sleep(millis, nano);
                }
            }
        }
    }

    public static void heapSort(ArrayList<Integer> values, VisualizerFrame view, long millis, int nano) throws InterruptedException {
        int n = values.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(values, view, millis, nano, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>0; i--)
        {
            // Move current root to end
            swap(values, 0, i);

            // call max heapify on the reduced heap
            heapify(values, view, millis, nano, i, 0);
        }
    }

    private static void heapify(ArrayList<Integer> values, VisualizerFrame view, long millis, int nano, int n, int i) throws InterruptedException {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        view.updateCanvas(values, largest);
        Thread.sleep(millis, nano);

        // If left child is larger than root
        if (l < n && values.get(l) > values.get(largest))
            largest = l;

        view.updateCanvas(values, l);
        Thread.sleep(millis, nano);

        // If right child is larger than largest so far
        if (r < n && values.get(r) > values.get(largest))
            largest = r;

        view.updateCanvas(values, r);
        Thread.sleep(millis, nano);

        // If largest is not root
        if (largest != i)
        {
            swap(values, i, largest);

            // Recursively heapify the affected sub-tree
            heapify(values, view, millis, nano, n, largest);
        }
    }

    public static void mergeSort(ArrayList<Integer> values, VisualizerFrame view, int start, int end, long millis, int nano) throws InterruptedException {
        if(start < end) {
            int center = (start + end) / 2;

            mergeSort(values, view, start, center, millis, nano);
            mergeSort(values, view, center + 1, end, millis, nano);

            merge(values, view, start, center, end, millis, nano);
        }
    }

    private static void merge(ArrayList<Integer> values, VisualizerFrame view, int start, int center, int end, long millis, int nano) throws InterruptedException {
        ArrayList<Integer> leftArr = new ArrayList<>();
        ArrayList<Integer> rightArr = new ArrayList<>();
        int leftPointer = 0;
        int rightPointer = 0;
        int arrayPointer = start;

        leftArr.addAll(values.subList(start, center + 1));
        rightArr.addAll(values.subList(center + 1, end + 1));

        while(leftPointer < leftArr.size() || rightPointer < rightArr.size()) {
            if(rightPointer == rightArr.size()) {
                values.set(arrayPointer, leftArr.get(leftPointer++));
            } else if(leftPointer == leftArr.size()) {
                values.set(arrayPointer, rightArr.get(rightPointer++));
            } else if(leftArr.get(leftPointer) < rightArr.get(rightPointer)) {
                values.set(arrayPointer, leftArr.get(leftPointer++));
            } else {
                values.set(arrayPointer, rightArr.get(rightPointer++));
            }

            arrayPointer++;

            view.updateCanvas(values, arrayPointer);
            Thread.sleep(millis, nano);
        }
    }

    public static void quickSort(ArrayList<Integer> values, VisualizerFrame view, int low, int high, int millis, int nano) throws InterruptedException {
        if(low < high) {
            int partitionIndex = partition(values, view, low, high, millis, nano);

            quickSort(values, view, low, partitionIndex - 1, millis, nano);
            quickSort(values, view, partitionIndex + 1, high, millis, nano);
        }
    }

    private static int partition(ArrayList<Integer> values, VisualizerFrame view, int low, int high, int millis, int nano) throws InterruptedException{
        int pivot = values.get(high);
        int pointer = low;

        for(int i = low; i < high; i++) {

            view.updateCanvas(values, i);
            Thread.sleep(millis, nano);

            if(values.get(i) < pivot) {
                view.updateCanvas(values, pointer);
                Thread.sleep(millis, nano);

                swap(values, pointer++, i);
            }
        }

        view.updateCanvas(values, high);
        Thread.sleep(millis, nano);

        swap(values, pointer, high);

        view.updateCanvas(values, pointer);
        Thread.sleep(millis, nano);

        return pointer;
    }

    private static void swap(ArrayList<Integer> values, int x, int y) {
        int temp = values.get(x);
        values.set(x, values.get(y));
        values.set(y, temp);
    }
}
