package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.junit.Assert.*;

public class BefungeInterpreterTest {
    @Test
    public void tests() {
        assertEquals(
                "123456789",
                new BefungeInterpreter().interpret(">987v>.v\nv456<  :\n>321 ^ _@"));

    }

    @Test
    public void test2() {
        assertEquals(
                "40320",
                new BefungeInterpreter().interpret("08>:1-:v v *_$.@ \n  ^    _$>\\:^  ^    _$>\\:^"));

    }

    @Test
    public void test4() {
        assertEquals(
                "Hello World!",
                new BefungeInterpreter().interpret(">25*\"!dlroW olleH\":v \n                v:,_@\n                >  ^"));

    }

    @Test
    public void test6() {
        assertEquals(
                "01->1# +# :# 0# g# ,# :# 5# 8# *# 4# +# -# _@",
                new BefungeInterpreter().interpret("01->1# +# :# 0# g# ,# :# 5# 8# *# 4# +# -# _@"));

    }

    @Test
    public void test7() {
        assertEquals(
                "23571113171923293137",
                new BefungeInterpreter().interpret("2>:3g\" \"-!v\\  g30          <\n |!`\"&\":+1_:.:03p>03g+:\"&\"`|\n @               ^  p3\\\" \":<\n2 2345678901234567890123456789012345678"));

    }



}