package com.sergiomartinrubio.codewars.five;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class DirReduction {
    private static final Map<String, Integer> CODES_BY_COORDINATES = Map.of(
            "NORTH", 1,
            "SOUTH", 2,
            "EAST", 4,
            "WEST", 5
    );

    public static String[] dirReduc(String[] arr) {
        int newLength = arr.length - 1;
        for (int i = 0; i < newLength; ) {
            if (Math.abs(CODES_BY_COORDINATES.get(arr[i]) - CODES_BY_COORDINATES.get(arr[i + 1])) == 1) {
                int j = i;
                while (j < newLength - 1) {
                    arr[j] = arr[j + 2];
                    j++;
                }
                arr[newLength] = null;
                arr[newLength - 1] = null;
                newLength -= 2;
                i = 0;
            } else {
                i++;
            }
        }
        return Arrays.stream(arr).filter(Objects::nonNull).toArray(String[]::new);
    }
}
