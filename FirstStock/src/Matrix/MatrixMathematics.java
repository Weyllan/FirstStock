package Matrix;

import Matrix.NonCarreException;

public class MatrixMathematics {

    private MatrixMathematics() {
    }

    //Transposé d'une matrice
    public static Matrix transpose(Matrix matrix) {
        Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
        for (int i = 0; i < matrix.getNrows(); i++) {
            for (int j = 0; j < matrix.getNcols(); j++) {
                transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
            }
        }
        return transposedMatrix;
    }

    //Matrice Inverse
    public static Matrix inverse(Matrix matrix) throws NonCarreException {
        return (transpose(cofactor(matrix)).multiplyByNumber(1.0 / determinant(matrix)));
    }

    // Déterminant de la matrice
    public static double determinant(Matrix matrix) throws NonCarreException {
        if (!matrix.isSquare()) {
            throw new NonCarreException("La matrice doit être carrée");
        }
        if (matrix.size() == 1) {
            return matrix.getValueAt(0, 0);
        }

        if (matrix.size() == 2) {
            return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - (matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
        }
        double sum = 0.0;
        for (int i = 0; i < matrix.getNcols(); i++) {
            sum += changeSign(i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, 0, i));
        }
        return sum;
    }

    //Renvoie 1 si i est pair,  -1 si impaire
    private static int changeSign(int i) {
        if (i % 2 == 0) {
            return 1;
        }
        return -1;
    }

    // Création d'une sous matrice excluant la colonne et la ligne données en numéro
    public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
        Matrix mat = new Matrix(matrix.getNrows() - 1, matrix.getNcols() - 1);
        int r = -1;
        for (int i = 0; i < matrix.getNrows(); i++) {
            if (i == excluding_row) {
                continue;
            }
            r++;
            int c = -1;
            for (int j = 0; j < matrix.getNcols(); j++) {
                if (j == excluding_col) {
                    continue;
                }
                mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
            }
        }
        return mat;
    }

    // Retourne la comatrice
    public static Matrix cofactor(Matrix matrix) throws NonCarreException {
        Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
        for (int i = 0; i < matrix.getNrows(); i++) {
            for (int j = 0; j < matrix.getNcols(); j++) {
                mat.setValueAt(i, j, changeSign(i) * changeSign(j) * determinant(createSubMatrix(matrix, i, j)));
            }
        }

        return mat;
    }

    //Addition de 2 matrice
    public static Matrix add(Matrix matrix1, Matrix matrix2) throws InvalideDimensionException {
        if (matrix1.getNcols() != matrix2.getNcols() || matrix1.getNrows() != matrix2.getNrows()) {
            throw new InvalideDimensionException("Les deux matrices doivent être de taille identique");
        }
        Matrix sumMatrix = new Matrix(matrix1.getNrows(), matrix1.getNcols());
        for (int i = 0; i < matrix1.getNrows(); i++) {
            for (int j = 0; j < matrix1.getNcols(); j++) {
                sumMatrix.setValueAt(i, j, matrix1.getValueAt(i, j) + matrix2.getValueAt(i, j));
            }

        }
        return sumMatrix;
    }

    // Soustraction de 2 matrice
    public static Matrix subtract(Matrix matrix1, Matrix matrix2) throws InvalideDimensionException {
        return add(matrix1, matrix2.multiplyByNumber(-1));
    }

    // Multiplie 2 matrice entre elle
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        Matrix multipliedMatrix = new Matrix(matrix1.getNrows(), matrix2.getNcols());

        for (int i = 0; i < multipliedMatrix.getNrows(); i++) {
            for (int j = 0; j < multipliedMatrix.getNcols(); j++) {
                double sum = 0.0;
                for (int k = 0; k < matrix1.getNcols(); k++) {
                    sum += matrix1.getValueAt(i, k) * matrix2.getValueAt(k, j);
                }
                multipliedMatrix.setValueAt(i, j, sum);
            }
        }
        return multipliedMatrix;
    }
}
