//Задание1 не точное
public class Gauss {
    public static double[] gaussianElimination(double[][] A, double[] b) {
        int n = A.length;

        // прямой ход метода Гаусса с выбором ведущего элемента
        for (int i = 0; i < n; i++) {
            // поиск максимального элемента в i-ом столбце
            int maxRow = i;
            double maxEl = Math.abs(A[i][i]);
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(A[k][i]) > maxEl) {
                    maxEl = Math.abs(A[k][i]);
                    maxRow = k;
                }
            }

            // перестановка строк, чтобы максимальный элемент был на диагонали
            if (maxRow != i) {
                double[] temp = A[i];
                A[i] = A[maxRow];
                A[maxRow] = temp;

                double t = b[i];
                b[i] = b[maxRow];
                b[maxRow] = t;
            }

            // обнуление элементов под диагональю
            for (int j = i + 1; j < n; j++) {
                double factor = A[j][i] / A[i][i];
                b[j] -= factor * b[i];
                for (int k = i; k < n; k++) {
                    A[j][k] -= factor * A[i][k];
                }
            }
        }

        // обратный ход метода Гаусса
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }

        return x;
    }

}
