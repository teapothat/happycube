package com.saplacan.model;


public class Cube {

    /**
     * List of faces; Order is as follows:
     * 0 1 2
     * . 3 .
     * . 4 .
     * . 5 .
     */
    private CubeFace[] faces;

    public Cube() {
        faces = new CubeFace[6];
    }


    /**
     * Checks if face can be added so that it respects the rules.
     * For each face it checks if faces fits. And also looks for corner collisions.
     * @param face The face to check.
     * @param index The position to add face at.
     * @return Weather or not you can add the piece at position.
     */
    public boolean canAddFace(CubeFace face, int index) {
        switch (index) {
            case 0:
                //  First face added to cube, add it freely
                // Cube will look
                // 0 - -
                // . - .
                // . - .
                // . - .
                return true;
            case 1:
                // Just one more face exists, it needs to fit, 0 left needs to fit 1 right
                // Cube will look
                // 0 1 -
                // . - .
                // . - .
                // . - .
                // Need to check that 0 and 1 fit.
                return faces[0].fits(Side.RIGHT, face, Side.LEFT, false);
            case 2:
                // 2 faces exist but for now 1 left needs to fit 2 right
                // Cube will look
                // 0 1 2
                // . - .
                // . - .
                // . - .
                // Need to check that 1 and 2 fit.
                return faces[1].fits(Side.RIGHT, face, Side.LEFT, false);
            case 3:
                // Needs to fit 3 other faces & check corners
                // Cube will look
                // 0 1 2
                // . 3 .
                // . - .
                // . - .
                // Need to check that 3 fits with 1, 0 and 2 and
                // the corners beteween (0, 1, 3) and(1, 2, 3) are covered and no overlaps
                CubeFace[] touching_faces1 = {faces[0], faces[1], face};
                Corner[] corners1 = {Corner.BOTTOM_RIGHT, Corner.BOTTOM_LEFT, Corner.TOP_LEFT};

                CubeFace[] touching_faces2 = {faces[1], faces[2], face};
                Corner[] corners2 = {Corner.BOTTOM_RIGHT, Corner.BOTTOM_LEFT, Corner.TOP_RIGHT};

                return faces[0].fits(Side.BOTTOM, face, Side.LEFT, true) &&
                        faces[1].fits(Side.BOTTOM, face, Side.TOP, false) &&
                        faces[2].fits(Side.BOTTOM, face, Side.RIGHT, false) &&
                        checkCorner(touching_faces1, corners1) &&
                        checkCorner(touching_faces2, corners2);

            case 4:
                // Also needs to fit 3 other faces & check corners
                // Cube will look
                // 0 1 2
                // . 3 .
                // . 4 .
                // . - .
                // Need to check that 4 fits with 0, 2 and 3 and
                // the corners beteween (0, 3, 4) and(2, 3, 4) are covered and no overlaps
                CubeFace[] touching_faces3 = {faces[3], faces[2], face};
                Corner[] corners3 = {Corner.BOTTOM_RIGHT, Corner.BOTTOM_RIGHT, Corner.TOP_RIGHT};

                CubeFace[] touching_faces4 = {faces[3], faces[0], face};
                Corner[] corners4 = {Corner.BOTTOM_LEFT, Corner.BOTTOM_LEFT, Corner.TOP_LEFT};
                return faces[0].fits(Side.LEFT, face, Side.LEFT, true) &&
                        faces[2].fits(Side.RIGHT, face, Side.RIGHT, true) &&
                        faces[3].fits(Side.BOTTOM, face, Side.TOP, false) &&
                        checkCorner(touching_faces3, corners3) &&
                        checkCorner(touching_faces4, corners4);
            case 5:
                // Last one needs to fit 4 others faces and corners
                // Cube will look
                // 0 1 2
                // . 3 .
                // . 4 .
                // . 5 .
                // Need to check that 5 fits with 0, 1, 2 and 4 and
                // the corners beteween (0, 4, 5), (2, 4, 5), (0, 1, 5) and (1, 2, 5) are covered and no overlaps
                CubeFace[] touching_faces5 = {faces[4], faces[0], face};
                Corner[] corners5 = {Corner.BOTTOM_LEFT, Corner.TOP_LEFT, Corner.TOP_LEFT};

                CubeFace[] touching_faces6 = {faces[4], faces[2], face};
                Corner[] corners6 = {Corner.BOTTOM_RIGHT, Corner.TOP_RIGHT, Corner.TOP_RIGHT};

                CubeFace[] touching_faces7 = {faces[1], faces[0], face};
                Corner[] corners7 = {Corner.TOP_LEFT, Corner.TOP_RIGHT, Corner.BOTTOM_LEFT};

                CubeFace[] touching_faces8 = {faces[1], faces[2], face};
                Corner[] corners8 = {Corner.TOP_RIGHT, Corner.TOP_LEFT, Corner.BOTTOM_RIGHT};


                return faces[0].fits(Side.TOP, face, Side.LEFT, false) &&
                        faces[1].fits(Side.TOP, face, Side.BOTTOM, false) &&
                        faces[2].fits(Side.TOP, face, Side.RIGHT, true) &&
                        faces[4].fits(Side.BOTTOM, face, Side.TOP, false) &&
                        checkCorner(touching_faces5, corners5) &&
                        checkCorner(touching_faces6, corners6) &&
                        checkCorner(touching_faces7, corners7) &&
                        checkCorner(touching_faces8, corners8);
            default:
                // Cannot add more than 6 faces
                return false;

        }

    }

    /**
     * Checks that 3 faces that unite in a corner don't have all a foamy part in it.
     * @param faces - list of 3 faces which will connect on a corner.
     * @param corners - the corner for each face which is affected.
     * @return true if there is 1 and only one foamy piece in the corner, false otherwise.
     */
    private boolean checkCorner(CubeFace[] faces, Corner[] corners) {
        int corner_count = 0;
        for (int i = 0; i< faces.length; i++) {
            if (faces[i].getCorner(corners[i]) == 'o') {
                corner_count ++;
            }
        }
        return corner_count == 1;
    }

    /**
     * Adds a face to the cube. Need to be called only after check.
     * @param face the face to add to the cube.
     * @param index the index to add the face at.
     */
    public void addFace(CubeFace face, int index) {
        faces[index] = face;
        // Remove all other faces after index
        for (int i = index + 1; i < 6; i++) {
            faces[i] = null;
        }
    }

    // Printing cubes

    /**
     * Prints cube in format
     * 0 1 2
     * . 3 .
     * . 4 .
     * . 5 .
     * @return String containing the formated cube.
     */
    public String prettyPrint() {
        try {
            final String EMPTY_CUBE = "     \n" +
                    "     \n" +
                    "     \n" +
                    "     \n" +
                    "     ";
            CubeFace emptyCube = new CubeFace(EMPTY_CUBE);
            CubeFace[] line = {faces[0], faces[1], faces[2]};
            String pretty_cube = CubeFace.prettyPrintAllignedFaces(line);
            for (int i = 3; i < 6; i++) {
                CubeFace[] line2 = {emptyCube, faces[i], emptyCube};
                pretty_cube += CubeFace.prettyPrintAllignedFaces(line2);
            }
            return pretty_cube;
        } catch (InvalidCubeException ex) {
            System.out.println("Invalid Empty Cube, failed to pretty print!");
            return this.toString();
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (CubeFace cubeFace : faces) {
            s += cubeFace + "\n";
        }
        return s;
    }

}
