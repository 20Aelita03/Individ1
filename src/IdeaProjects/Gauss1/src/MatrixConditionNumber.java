package IdeaProjects.Gauss1.src;

import Jama.Matrix;

public class MatrixConditionNumber {
    public static void main(String[] args) {
        // Создаем матрицу A
        double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrixA = new Matrix(a);

        // Вычисляем обратную матрицу A^-1
        Matrix matrixAInverse = matrixA.inverse();

        // Вычисляем нормы матрицы A и обратной матрицы A^-1
        double matrixANorm = matrixA.norm1();
        double matrixAInverseNorm = matrixAInverse.norm2();

        // Вычисляем число обусловленности cond(A)
        double condA = matrixANorm * matrixAInverseNorm;
        System.out.println("Число обусловленности матрицы A: " + condA);
    }
}
