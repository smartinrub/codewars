package com.sergiomartinrubio.codewars.five;

public class JosephusSurvivor {
    public static int josephusSurvivor(final int n, final int k) {
        if (n == 1) {
            return n;
        }

        int[] people = new int[n];
        for (int i = 0; i < n; i++) {
            people[i] = i + 1;
        }

        int position = 0;

        while (people.length > 1) {
            position += k - 1;

            while (position >= people.length) {
                position -= people.length;
            }

            System.arraycopy(people, position + 1 , people, position, people.length - position - 1);

            int[] newPeople = new int[people.length - 1];
            System.arraycopy(people, 0, newPeople, 0, people.length - 1);
            people = newPeople;

            if (people.length == 1) {
                return people[0];
            }
        }

        return 0;
    }
}
