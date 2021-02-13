package com.sergiomartinrubio.codewars.six;

import java.util.ArrayList;
import java.util.List;

public class FindOutlier {
    static int find(int[] integers) {
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();

        for (int integer : integers) {
            if (integer % 2 == 0) {
                if (odds.size() > 1) {
                    return integer;
                } else if (odds.size() == 1 && evens.size() == 1) {
                    return odds.get(0);
                }
                evens.add(integer);
            } else {
                if (evens.size() > 1) {
                    return integer;
                } else if (odds.size() == 1 && evens.size() == 1) {
                    return evens.get(0);
                }
                odds.add(integer);
            }
        }

        return 0;
    }
}
