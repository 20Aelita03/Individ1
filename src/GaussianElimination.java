//Задание 1 полное
public class GaussianElimination {
    public static double[] solve(double[][] A, double[] b) {
        int n = A.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int k = 0; k < n-1; k++) {
            int maxRow = k;
            int maxCol = k;
            double maxVal = Math.abs(A[k][k]);
            for (int i = k; i < n; i++) {
                for (int j = k; j < n; j++) {
                    double val = Math.abs(A[i][j]);
                    if (val > maxVal) {
                        maxVal = val;
                        maxRow = i;
                        maxCol = j;
                    }
                }
            }
            if (maxVal == 0) {
                throw new ArithmeticException("Matrix is singular");
            }
            if (maxRow != k) {
                swapRows(A, maxRow, k);
                swap(p, maxRow, k);
            }
            if (maxCol != k) {
                swapCols(A, maxCol, k);
            }
            for (int i = k+1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                for (int j = k+1; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }
                b[i] -= factor * b[k];
                A[i][k] = 0;
            }
        }
        double[] x = new double[n];
        for (int i = n-1; i >= 0; i--) {
            double sum = b[i];
            for (int j = i+1; j < n; j++) {
                sum -= A[i][j] * x[j];
            }
            x[i] = sum / A[i][i];
        }
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[p[i]] = x[i];
        }
        return result;
    }
    private static void swapRows(double[][] A, int i, int j) {
        double[] temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void swap(int[] p, int i, int j) {
        int temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }

    private static void swapCols(double[][] A, int i, int j) {
        for (int k = 0; k < A.length; k++) {
            double temp = A[k][i];
            A[k][i] = A[k][j];
            A[k][j] = temp;
        }
    }
}
