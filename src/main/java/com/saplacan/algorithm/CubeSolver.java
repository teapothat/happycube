package com.saplacan.algorithm;

import com.saplacan.model.Cube;
import com.saplacan.model.CubeFace;
import com.saplacan.model.InvalidCubeException;

import java.io.*;
import java.util.ArrayList;

public class CubeSolver {

    /**
     * Solves the Cube, prints solution to file.
     * @param faces The cube faces to be arranged in a a cube/
     */
    public static void solveCube(ArrayList<CubeFace> faces) {
        Cube cube = new Cube();
        solveCube(cube, faces, 0);
    }

    /**
     * Backtracking methods that tries all positions starting from each face.
     * It stops at first solution.
     * Tries each face 4 * 2 times:
     *  It has 4 rotation positions.
     *  It can be mirrored so 2 positions for that.
     * @param partial the solution collected so far.
     * @param faces the remaining faces that are not included in the solution.
     * @param index the index of how many solutions have been added.
     */
    private static void solveCube(Cube partial, ArrayList<CubeFace> faces, int index) {
        if (index == 6) {
            CubeSolver.printToFile(partial);
            System.exit(0);  // Exit on first solution
            return;
        }

        for (CubeFace cf : faces) {
            for (int m = 0; m < 2; m++) {  // mirroring options
                cf.mirror();
                // Create a new list so we can remove cf and send it forward
                ArrayList<CubeFace> remaining_without_i = new ArrayList<>(faces);
                remaining_without_i.remove(cf);
                for (int k = 0; k < 4; k++) {  // reversing options
                    if (partial.canAddFace(cf, index)) {
                        partial.addFace(cf, index);
                        solveCube(partial, remaining_without_i, index + 1);
                    }
                    cf.rotateRight();
                }
            }
        }
    }

    private static void printToFile(Cube solution) {
        String pretty_format = solution.prettyPrint();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("solution.txt"), "utf-8"))) {
            writer.write(pretty_format);
        } catch (IOException ex) {
            System.out.println("Error writing solution to file.");
            System.out.println(pretty_format);
        }
    }


}
