package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConwayLifeTest {

    @Test
    public void testGlider() {
        int[][][] gliders = {
                {
                        {1, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}
                },
//                {
//                        {1, 0, 0},
//                        {0, 1, 1},
//                        {1, 1, 0}
//                },
//                {
//                        {1, 0, 1},
//                        {0, 1, 1},
//                        {0, 1, 0}
//                },
//                {
//                        {1, 1, 1, 0, 0, 0, 1, 0},
//                        {1, 0, 0, 0, 0, 0, 0, 1},
//                        {0, 1, 0, 0, 0, 1, 1, 1}
//                },
//                {
//                        {0, 1, 0, 0, 0, 0, 0, 0},
//                        {1, 1, 0, 0, 0, 0, 0, 0},
//                        {1, 0, 1, 0, 0, 1, 0, 1},
//                        {0, 0, 0, 0, 0, 0, 1, 1},
//                        {0, 0, 0, 0, 0, 0, 1, 0}
//                },
        };
        System.out.println("Glider");
        LifeDebug.print(gliders[0]);
        int[][] res = ConwayLife.getGeneration(gliders[0], 1);
        assertTrue("Got \n" + LifeDebug.htmlize(res) + "\ninstead of\n" + LifeDebug.htmlize(gliders[1]), LifeDebug.equals(res, gliders[1]));
    }


    @Test
    public void testGlider2() {
        int[][][] gliders = {
                {
                        {1, 1, 1, 0, 0, 0, 1, 0},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 1, 1, 1}
                },
                {
                        {1, 1, 0, 0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1, 1}
                },
        };
        System.out.println("Glider");
        LifeDebug.print(gliders[0]);
        int[][] res = ConwayLife.getGeneration(gliders[0], 2);
        assertTrue("Got \n" + LifeDebug.htmlize(res) + "\ninstead of\n" + LifeDebug.htmlize(gliders[1]), LifeDebug.equals(res, gliders[1]));
    }

    @Test
    public void testGlider3() {
        int[][][] gliders = {
                {
                        {1, 1, 1, 0, 0, 0, 1, 0},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 1, 1, 1}
                },
                {
                        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}
                },
        };
        System.out.println("Glider");
        LifeDebug.print(gliders[0]);
        int[][] res = ConwayLife.getGeneration(gliders[0], 3);
        assertTrue("Got \n" + LifeDebug.htmlize(res) + "\ninstead of\n" + LifeDebug.htmlize(gliders[1]), LifeDebug.equals(res, gliders[1]));
    }




    @Test
    public void testGlider4() {
        int[][][] gliders = {
                {
                        {1, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {1, 0, 1},
                        {0, 1, 1},
                        {0, 1, 0}
                },
        };
        System.out.println("Glider");
        LifeDebug.print(gliders[0]);
        int[][] res = ConwayLife.getGeneration(gliders[0], 38);
        assertTrue("Got \n" + LifeDebug.htmlize(res) + "\ninstead of\n" + LifeDebug.htmlize(gliders[1]), LifeDebug.equals(res, gliders[1]));
    }
}

class LifeDebug {
    static int LIVE = 1;
    static int DIE = 0;

    public static void print(int[][] cells) {
        int lenY = cells.length;
        int lenX = cells[0].length;
        for (int j = 0; j < lenY; j++) {
            for (int i = 0; i < lenX; i++) {
                System.out.print(cells[j][i]);
            }
            System.out.println("");
        }
    }

    public static String htmlize(int[][] cells) {
        StringBuffer sb = new StringBuffer();
        int lenY = cells.length;
        int lenX = cells[0].length;
        for (int j = 0; j < lenY; j++) {
            for (int i = 0; i < lenX; i++) {
                int i1 = cells[j][i];
                if (i1 == LIVE) {
//System.out.println("▓▓");
                    sb.append("▓▓");
                } else if (i1 == DIE) {
                    sb.append("░░");
//System.out.println("░░");
                }
//                System.out.print(cells[j][i]);
            }
//System.out.println("");
            sb.append("\n");
        }
        return sb.toString();
    }

    public static boolean equals(int[][] res, int[][] cells) {
        boolean isEqual = true;
        int lenY = cells.length;
        int lenX = cells[0].length;
        for (int j = 0; j < lenY; j++) {
            for (int i = 0; i < lenX; i++) {
                int i1 = cells[j][i];
                int i2 = res[j][i];
                if (i1 != i2) {
                    isEqual = false;
                    return isEqual;
                }
            }
        }
        return isEqual;
    }
}