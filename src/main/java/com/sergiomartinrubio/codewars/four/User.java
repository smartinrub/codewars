package com.sergiomartinrubio.codewars.four;

import java.util.Map;

import static java.util.Map.entry;

public class User {

    public int rank = -8;
    public int progress = 0;

    public static final Map<Integer, Integer> POSITIONS_BY_RANK = Map.ofEntries(
            entry(-8, 1),
            entry(-7, 2),
            entry(-6, 3),
            entry(-5, 4),
            entry(-4, 5),
            entry(-3, 6),
            entry(-2, 7),
            entry(-1, 8),
            entry(1, 9),
            entry(2, 10),
            entry(3, 11),
            entry(4, 12),
            entry(5, 13),
            entry(6, 14),
            entry(7, 15),
            entry(8, 16));

    public void incProgress(int rank) {
        if (this.rank != 8) {
            if (POSITIONS_BY_RANK.get(rank).equals(POSITIONS_BY_RANK.get(this.rank))) {
                progress = progress + 3;
            } else if (POSITIONS_BY_RANK.get(this.rank) - POSITIONS_BY_RANK.get(rank) == 1) {
                progress = progress + 1;
            } else if ((POSITIONS_BY_RANK.get(rank) > POSITIONS_BY_RANK.get(this.rank))) {
                int rankDifference = POSITIONS_BY_RANK.get(rank) - POSITIONS_BY_RANK.get(this.rank);
                progress = progress + (10 * rankDifference * rankDifference);
            }

            while (progress >= 100) {
                progress -= 100;
                if (this.rank == -1) {
                    this.rank = 1;
                } else {
                    this.rank = this.rank + 1;
                }

                if (this.rank == 8) {
                    this.progress = 0;
                }
            }
        }
    }
}
