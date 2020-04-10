package com.shanemaglangit;

public enum AlgorithmType {
    QUICK("Quick Sort"), HEAP("Heap Sort"), MERGE("Merge Sort"),
    SELECTION("Selection Sort"), INSERTION("Insertion Sort"), BUBBLE("Bubble Sort");

    private String equiv;

    public String getEquiv() {
        return equiv;
    }

    private AlgorithmType(String equiv) {
        this.equiv = equiv;
    }
}
