package com.sergiomartinrubio.codewars.six;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, List<Integer>> indexesByNumber = new HashMap<>();
        int[] tuple = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            List<Integer> indexes = indexesByNumber.get(numbers[i]);
            if (indexes == null) {
                List<Integer> newIndexes = new ArrayList<>();
                newIndexes.add(i);
                indexesByNumber.put(numbers[i], newIndexes);
            } else {
                indexesByNumber.get(numbers[i]).add(i);
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            List<Integer> indexes = indexesByNumber.get(target - numbers[i]);

            if (indexes != null) {
                tuple[0] = indexes.get(0);
                tuple[1] = i;
            }
        }

        return tuple;
    }
}
