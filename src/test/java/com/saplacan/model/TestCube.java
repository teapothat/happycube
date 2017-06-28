package com.saplacan.model;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestCube {

    @Test
    public void testCanAddFace0True() throws InvalidCubeException {
        Cube cube = new Cube();
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        assertTrue(cube.canAddFace(cubeFace, 0));
    }

    @Test
    public void testCanAddFace1True() throws InvalidCubeException {
        Cube cube = new Cube();
        CubeFace cubeFace = new CubeFace("o  o \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        cube.addFace(cubeFace, 0);
        CubeFace cubeFace1 = new CubeFace("o  o \n" +
                "oooo \n" +
                " oooo\n" +
                "oooo \n" +
                "  o  ");
        assertTrue(cube.canAddFace(cubeFace1, 1));
    }

    @Test
    public void testCanAddFace1FalseCornersOverlap() throws InvalidCubeException {
        Cube cube = new Cube();
        CubeFace cubeFace = new CubeFace("o  oo\n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        cube.addFace(cubeFace, 0);
        CubeFace cubeFace1 = new CubeFace("o  o \n" +
                "oooo \n" +
                " oooo\n" +
                "oooo \n" +
                "  o  ");
        assertFalse(cube.canAddFace(cubeFace1, 1));
    }

    @Test
    public void testCanAddFace1FalseCannotConnect() throws InvalidCubeException {
        Cube cube = new Cube();
        CubeFace cubeFace = new CubeFace("o  oo\n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        cube.addFace(cubeFace, 0);
        CubeFace cubeFace1 = new CubeFace("   o \n" +
                " ooo \n" +
                " oooo\n" +
                "oooo \n" +
                "  o  ");
        assertFalse(cube.canAddFace(cubeFace1, 1));
    }

    @Test
    public void testCanAddFace3CornerProblem() throws InvalidCubeException {
        Cube cube = new Cube();

        CubeFace[] faces = {
                new CubeFace("  o  \n" +
                        " ooo \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "  o  "),
                new CubeFace("o o o\n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "ooooo\n" +
                        "o o  "),
                new CubeFace(" o o \n" +
                        " ooo \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "ooo  ")
        };
        for (int i = 0; i< faces.length; i++) {
            cube.addFace(faces[i], i);
        }
        CubeFace cubeFace1 = new CubeFace(" o oo\n" +
                "ooooo\n" +
                " ooo \n" +
                "ooooo\n" +
                "  o o");
        assertFalse(cube.canAddFace(cubeFace1, 3));
    }

    @Test
    public void testPrettyPrint() throws InvalidCubeException {
        CubeFace[] faces = {
                new CubeFace("  o  \n" +
                        " ooo \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "  o  "),
                new CubeFace("o o o\n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "ooooo\n" +
                        "o o o"),
                new CubeFace(" o o \n" +
                        " ooo \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "  o  "),
                new CubeFace(" o o \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "ooooo\n" +
                        "  o o"),
                new CubeFace("oo o \n" +
                        "ooooo\n" +
                        " ooo \n" +
                        "ooooo\n" +
                        "  o  "),
                new CubeFace("oo oo\n" +
                        "oooo \n" +
                        " oooo\n" +
                        "oooo \n" +
                        " o o ")
        };
        Cube cube = new Cube();
        for (int i = 0; i< faces.length; i++) {
            cube.addFace(faces[i], i);
        }
        assertEquals("  o  o o o o o \n" +
                " ooo ooooo ooo \n" +
                "ooooo ooo ooooo\n" +
                " ooo ooooo ooo \n" +
                "  o  o o o  o  \n" +
                "      o o      \n" +
                "     ooooo     \n" +
                "      ooo      \n" +
                "     ooooo     \n" +
                "       o o     \n" +
                "     oo o      \n" +
                "     ooooo     \n" +
                "      ooo      \n" +
                "     ooooo     \n" +
                "       o       \n" +
                "     oo oo     \n" +
                "     oooo      \n" +
                "      oooo     \n" +
                "     oooo      \n" +
                "      o o      \n", cube.prettyPrint());


    }




}
