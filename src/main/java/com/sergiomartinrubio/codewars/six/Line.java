package com.sergiomartinrubio.codewars.six;

import java.util.HashMap;
import java.util.Map;

public class Line {
    public static String Tickets(int[] peopleInLine) {
        Map<Integer, Integer> countByBills = new HashMap<>();
        int ticketPrice = 25;

        for (int bill : peopleInLine) {
            int change = bill - ticketPrice;

            if (change == 0) {
                countByBills.merge(bill, 1, Integer::sum);
                continue;
            }

            while (change > 0) {
                if (change == 75) {
                    Integer count = countByBills.get(50);

                    if (count == null || count == 0) {
                        count = countByBills.get(25);
                        if (count == null || count == 0) {
                            return "NO";
                        }
                        countByBills.put(25, count - 1);
                        change = change - 25;
                    } else {
                        countByBills.put(50, count - 1);
                        change = change - 50;
                    }
                } else if (change == 50) {
                    Integer count = countByBills.get(25);
                    if (count == null || count == 0) {
                        return "NO";
                    } else {
                        countByBills.put(25, count - 1);
                        change = change - 25;
                    }
                } else if (change == 25) {
                    Integer count = countByBills.get(25);
                    if (count == null || count == 0) {
                        return "NO";
                    }
                    countByBills.put(25, count - 1);
                    change = change - 25;
                }
            }
            countByBills.merge(bill, 1, Integer::sum);
        }

        return "YES";
    }
}
