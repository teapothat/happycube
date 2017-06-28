package com.saplacan.model;
import org.junit.Test;


import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;


public class TestCubeFace {

    @Test
    public void testConstructor() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        assertEquals("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ", cubeFace.toString());
    }

    @Test(expected=InvalidCubeException.class)
    public void testConstructorInvalidCharacters() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace("~  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");

    }

    @Test(expected=InvalidCubeException.class)
    public void testConstructorInsufficientCharacters() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace("   o \n" +
                " ooo \n" +
                "ooooo\n");
    }


    @Test
    public void testRotateRight() throws InvalidCubeException
    {
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        cubeFace.rotateRight();
        assertEquals("  o o\n" +
                " ooo \n" +
                "oooo \n" +
                " oooo\n" +
                "  o  ", cubeFace.toString());
    }

    @Test
    public void testMirror() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace(" o o \n" +
                "oooo \n" +
                " oooo\n" +
                "oooo \n" +
                "oo o ");
        cubeFace.mirror();
        assertEquals(" o o \n" +
                " oooo\n" +
                "oooo \n" +
                " oooo\n" +
                " o oo", cubeFace.toString());

    }


    @Test
    public void testExtractSideUP() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");

        char[] side = cubeFace.extractSide(Side.TOP);
        char[] expected = {'o', ' ', ' ', 'o', ' '};
        assertEquals(true, Arrays.equals(side, expected));
    }

    @Test
    public void testExtractSideDOWN() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");

        char[] side = cubeFace.extractSide(Side.BOTTOM);
        char[] expected = {' ', ' ', 'o', ' ', ' '};
        assertEquals(true, Arrays.equals(side, expected));
    }

    @Test
    public void testExtractSideRIGHT() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");

        char[] side = cubeFace.extractSide(Side.RIGHT);
        char[] expected = {' ', ' ', 'o', ' ', ' '};
        System.out.println(side);
        assertEquals(true, Arrays.equals(side, expected));
    }

    @Test
    public void testExtractSideLEFT() throws InvalidCubeException {
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");

        char[] side = cubeFace.extractSide(Side.LEFT);
        char[] expected = {'o', ' ', 'o', ' ', ' '};
        assertEquals(true, Arrays.equals(side, expected));
    }

    @Test
    public void testFitsNotReverse() throws InvalidCubeException{
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        CubeFace secondFaceFit = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                " oo o");

        CubeFace secondFaceNotFit = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o o");

        assertEquals(true, cubeFace.fits(Side.TOP, secondFaceFit, Side.BOTTOM, false));
        assertEquals(false, cubeFace.fits(Side.TOP, secondFaceNotFit, Side.BOTTOM, false));
    }

    @Test
    public void testFitsReverse() throws InvalidCubeException{
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  oo ");
        CubeFace secondFaceFit = new CubeFace("o  o \n" +
                " ooo \n" +
                " oooo\n" +
                "ooooo\n" +
                "ooo o");

        CubeFace secondFaceNotFit = new CubeFace(" o o \n" +
                " ooo \n" +
                "ooooo\n" +
                "oooo \n" +
                "  o o");

        assertEquals(true, cubeFace.fits(Side.BOTTOM, secondFaceFit, Side.LEFT, true));
        assertEquals(false, cubeFace.fits(Side.BOTTOM, secondFaceNotFit, Side.LEFT, true));
    }

}
