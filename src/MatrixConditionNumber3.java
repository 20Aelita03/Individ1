public class MatrixConditionNumber3 {
    //полностью сделанный 4 номер
    public static void main(String[] args) {
        double[][] A = {{1, 2}, {4, 5}}; // пример матрицы A
        double[][] U = gaussianElimination(A); // ступенчатый вид матрицы A

        double condA = conditionNumber(A); // число обусловленности A
        double condU = conditionNumber(U); // число обусловленности U

        double diff = Math.abs(condA - condU); // разница между числами обусловленности

        System.out.println("Condition number of A: " + condA);
        System.out.println("Condition number of U: " + condU);
        System.out.println("Difference: " + diff);
    }

    // метод Гаусса для приведения матрицы к ступенчатому виду
    public static double[][] gaussianElimination(double[][] A) {
        int n = A.length;
        double[][] U = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                U[i][j] = A[i][j];
            }
        }
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double factor = U[i][k] / U[k][k];
                for (int j = k; j < n; j++) {
                    U[i][j] -= factor * U[k][j];
                }
            }
        }

        return U;
    }

    // вычисление числа обусловленности матрицы
    public static double conditionNumber(double[][] A) {
        double[][] Ainv = inverse(A);
        double normA = matrixNorm(A);
        double normAinv = matrixNorm(Ainv);
        return normA * normAinv;
    }

    // вычисление обратной матрицы
    public static double[][] inverse(double[][] A) {
        int n = A.length;
        double[][] Ainv = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Ainv[i][j] = (i == j) ? 1.0 : 0.0;
            }
        }
        for (int k = 0; k < n; k++) {
            double pivot = A[k][k];
            for (int j = 0; j < n; j++) {
                A[k][j] /= pivot;
                Ainv[k][j] /= pivot;
            }
            for (int i = k + 1; i < n; i++) {
                double factor = A[i][k];
                for (int j = 0; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                    Ainv[i][j]-= factor * Ainv[k][j];
                }
            }
        }

        for (int k = n - 1; k >= 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                double factor = A[i][k];
                for (int j = 0; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                    Ainv[i][j] -= factor * Ainv[k][j];
                }
            }
        }

        return Ainv;
    }
    // вычисление нормы матрицы
    public static double matrixNorm(double[][] A) {
        double maxColSum = 0.0;
        int n = A.length;
        for (int j = 0; j < n; j++) {
            double colSum = 0.0;
            for (int i = 0; i < n; i++) {
                colSum += Math.abs(A[i][j]);
            }
            maxColSum = Math.max(maxColSum, colSum);
        }
        return maxColSum;
    }

}
