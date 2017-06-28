package com.saplacan.model;

public class CubeFace {

    /**
     * A face represented by a matrix of characters. 'o' is foamy part, ' ' is free part.
     */
    private char[][] face;

    /**
     * The size of the matrix.
     */
    private static final int SIZE_OF_SIDE = 5;

    /**
     * Creates a Cube face from a given string.
     * @param faceString sholud have SIZE_OF_SIDE characteres on a line and SIZE_OF_SIDE lines separated
     *                   by a single character (or use \n).
     * @throws InvalidCubeException if the format is not respected an invalid Cube exception.
     */
    public CubeFace(String faceString) throws InvalidCubeException {
        face = new char[SIZE_OF_SIDE][SIZE_OF_SIDE];
        int str_index = 0;
        try {
            for (int i = 0; i < SIZE_OF_SIDE; i++) {
                for (int j = 0; j < SIZE_OF_SIDE; j++) {
                    char c = faceString.charAt(str_index);
                    if (c != 'o' && c != ' ') {
                        throw new InvalidCubeException("Character not supported: " + c);
                    }

                    face[i][j] = faceString.charAt(str_index);
                    str_index++;
                }
                // Skip new line character
                str_index++;
            }
        } catch (StringIndexOutOfBoundsException ex) {
            int minimim_length = SIZE_OF_SIDE * SIZE_OF_SIDE + SIZE_OF_SIDE -1;
            throw new InvalidCubeException("String for cube face needs to have at least " + minimim_length + " characters.");

        }
    }

    // Logic functions

    /**
     * Rotates the cube once to the right.
     */
    public void rotateRight(){
        char[][] rotated_face = new char[SIZE_OF_SIDE][SIZE_OF_SIDE];

        for (int i = 0; i < SIZE_OF_SIDE; i++) {
            for (int j = 0; j < SIZE_OF_SIDE; j++) {
                rotated_face[i][j] = face[SIZE_OF_SIDE - j - 1][i];
            }
        }
        this.face = rotated_face;
    }

    /**
     * Mirrors the cube.
     */
    public void mirror() {
        for (int i =0; i< SIZE_OF_SIDE; i++) {
            for (int j = 0; j< SIZE_OF_SIDE / 2; j++) {
                char tmp = face[i][j];
                face[i][j] = face[i][SIZE_OF_SIDE - j - 1];
                face[i][SIZE_OF_SIDE - j -1] = tmp;
            }
        }
    }


    /**
     * Checks if the side fits with another side, on specified edges.
     * Checks if middle part fits okay, the corners are checked just so they don't overlap,
     * they might be filled in later by a different face (this check is done in Cube).
     * @param side the side to check on this face.
     * @param otherFace the other face to check against.
     * @param otherSide the side to check on other face.
     * @param reverse sometimes because of cube shape in 3d the shapes will look different,
     *                so to see if it fits we need reverse the edge.
     * @return returns true or false if the sides fit.
     */
    boolean fits(Side side, CubeFace otherFace, Side otherSide, boolean reverse) {
        char[] myFaceSide = extractSide(side);
        char[] otherFaceSide = otherFace.extractSide(otherSide);
        if (reverse) {
            reverseInPlace(otherFaceSide);
        }

        // Middle check
        for (int i = 1; i< SIZE_OF_SIDE - 1; i++) {
            // They need to be different to fit together
            if (myFaceSide[i] == otherFaceSide[i]) {
                return false;
            }
        }

        // Corners may both be empty if they get filled in later
        if (myFaceSide[0] == 'o' && otherFaceSide[0] == 'o' ||
                myFaceSide[SIZE_OF_SIDE-1] == 'o' && otherFaceSide[SIZE_OF_SIDE - 1] == 'o') {
            return false;
        }
        return true;
    }

    /**
     * Returns the given corner value.
     * @param corner The corner to retrieve.
     * @return The value of the face in the specified corner.
     */
    char getCorner(Corner corner) {
        switch (corner) {
            case TOP_LEFT:
                return face[0][0];
            case TOP_RIGHT:
                return face[0][SIZE_OF_SIDE - 1];
            case BOTTOM_RIGHT:
                return face[SIZE_OF_SIDE - 1][SIZE_OF_SIDE -1];
            default:  // case BOTTOM_LEFT:
                return face[SIZE_OF_SIDE - 1][0];
        }
    }


    /**
     * Extracts the value of the given side as an array of chars.
     * @param side The side to extract.
     * @return The side specified of the face as an array of chars.
     */
    char[] extractSide(Side side) {
        switch (side) {
            case TOP:
                return face[0].clone(); // Clone used here because we might reverse the result
                                        // and we don't want to affect the face
            case BOTTOM:
                return face[SIZE_OF_SIDE - 1].clone();
            case LEFT:
                return this.getColumn(0);
            case RIGHT:
                return this.getColumn(SIZE_OF_SIDE - 1);
        }
        return null;
    }

    /**
     * Returns column c in matrix of chars as an array.
     * @param c the number of the column to get.
     * @return the column as an array of chars.
     */
    private char[] getColumn(int c) {
        char[] column = new char[SIZE_OF_SIDE];
        for (int i = 0; i < SIZE_OF_SIDE; i++) {
            column[i] = face[i][c];
        }
        return column;
    }

    /**
     * Reverses array in place.
     * @param myArray the array to reverse.
     */
    private static void reverseInPlace(char[] myArray) {

        for(int i = 0; i < myArray.length / 2; i++)
        {
            char temp = myArray[i];
            myArray[i] = myArray[myArray.length - i - 1];
            myArray[myArray.length - i - 1] = temp;
        }
    }


    // Pretty Print

    /**
     * Prints 3 faces alligned on horizontal.
     * @param faces The faces to be printed.
     * @return A string containing the faces aligned horizontally.
     */
    public static String prettyPrintAllignedFaces(CubeFace[] faces) {
        String result = "";
        for (int l = 0; l < SIZE_OF_SIDE; l++) {
            for (CubeFace f: faces) {
                for (int c = 0; c < SIZE_OF_SIDE; c ++) {
                    result += f.face[l][c];
                }
            }
            result += "\n";
        }
        return result;
    }

    // Object Methods

    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i < SIZE_OF_SIDE; i++) {
            for (int j = 0; j < SIZE_OF_SIDE; j ++){
                s += face[i][j];
            }
            s += i == SIZE_OF_SIDE - 1 ? "" : "\n";
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CubeFace))return false;
        if (obj == this) return true;
        char[][] otherFace = ((CubeFace) obj).face;
        for (int i = 0; i < SIZE_OF_SIDE; i++) {
            for (int j = 0; j < SIZE_OF_SIDE; j ++){
                if (face[i][j] != otherFace[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
