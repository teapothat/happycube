package com.saplacan.main;

import com.saplacan.algorithm.CubeSolver;
import com.saplacan.model.CubeFace;
import com.saplacan.model.InvalidCubeException;

import java.util.ArrayList;


public class CubeMain {

    public static void main(String [] args) {
        ArrayList<CubeFace> faces = new ArrayList<CubeFace>();
        try {
            faces.add(new CubeFace("  o  \n ooo \nooooo\n ooo \n  o  "));
            faces.add(new CubeFace("o o o\nooooo\n ooo \nooooo\no o o"));
            faces.add(new CubeFace("  o  \n oooo\noooo \n oooo\n  o  "));
            faces.add(new CubeFace(" o o \noooo \n oooo\noooo \noo o "));
            faces.add(new CubeFace(" o o \nooooo\n ooo \nooooo\no o  "));
            faces.add(new CubeFace(" o o \n oooo\noooo \n oooo\noo oo"));

//            faces.add(new CubeFace("  o  \n ooo \nooooo\n ooo \n  o  "));
//            faces.add(new CubeFace("  o o\nooooo\n ooo \nooooo\n o oo"));
//            faces.add(new CubeFace("o o  \nooooo\n ooo \nooooo\n o o "));
//            faces.add(new CubeFace(" o o \n ooo \nooooo\n ooo \n  o  "));
//            faces.add(new CubeFace("o o o\nooooo\n ooo \nooooo\no o o"));
//            faces.add(new CubeFace(" o o \noooo \n oooo\noooo \noo o "));

            CubeSolver.solveCube(faces);
        } catch (InvalidCubeException ex){
            System.out.println("Invalid Input");
        }

    }



}
