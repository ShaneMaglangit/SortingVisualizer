package com.shanemaglangit;

import java.util.ArrayList;
import java.util.Collections;

public class Util {
    public static ArrayList<Integer> getFactors(int num) {
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
