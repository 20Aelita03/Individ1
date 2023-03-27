import java.util.Arrays;
//Задание 3 не точно
public class GaussMethod {
    public static double[] solve(double[][] A, double[] b, double[] delta_b) {
        int n = A.length;
        double[][] Ab = new double[n][n + 1];

        // формируем расширенную матрицу
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Ab[i][j] = A[i][j];
            }
            Ab[i][n] = b[i] + delta_b[i];
        }

        // приводим матрицу к ступенчатому виду
        for (int k = 0; k < n; k++) {
            int maxRow = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(Ab[i][k]) > Math.abs(Ab[maxRow][k])) {
                    maxRow = i;
                }
            }
            double[] tmp = Ab[k];
            Ab[k] = Ab[maxRow];
            Ab[maxRow] = tmp;
            for (int i = k + 1; i < n; i++) {
                double factor = Ab[i][k] / Ab[k][k];
                for (int j = k + 1; j < n + 1; j++) {
                    Ab[i][j] -= factor * Ab[k][j];
                }
            }
        }

        // обратный ход
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += Ab[i][j] * x[j];
            }
            x[i] = (Ab[i][n] - sum) / Ab[i][i];
        }
        return x;
    }
    public static double relativeError(double[] x1, double[] x2) {
        double normX = 0.0;
        double normDeltaX = 0.0;
        for (int i = 0; i < x1.length; i++) {
            normX += x1[i] * x1[i];
            normDeltaX += (x1[i] - x2[i]) * (x1[i] - x2[i]);
        }
        normX = Math.sqrt(normX);
        normDeltaX = Math.sqrt(normDeltaX);
        return normDeltaX / normX;
    }

    public static void main(String[] args) {
        double[][] A = {{2.0, -1.0, 0.0}, {-1.0, 2.0, -1.0}, {0.0, -1.0, 2.0}};
        double[] b = {1.0, 2.0, 3.0};
        double[] delta_b = {0.01, 0.01, 0.01};
        double[] x = solve(A, b, delta_b);
        double[] trueX = {1.0, 2.0, 3.0};
        double relErr = relativeError(x, trueX);
        System.out.println("Solution: " + Arrays.toString(x));
        System.out.println("Relative error: " + relErr);
    }
}




