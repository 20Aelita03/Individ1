public class MatrixCN {
    //Задание 2
        public static void main(String[] args) {
            double[][] data = { {1, 2, 3}, {4, 5, 6}, {7, 8, 10} };
            double[][] inverseData = invertMatrix(data);

            double normA = calculateMatrixNorm(data);
            double normAInverse = calculateMatrixNorm(inverseData);

            double cond = normA * normAInverse;
            System.out.println("Число обусловленности матрицы A: " + cond);
        }

        // Находим обратную матрицу методом Гаусса-Жордана
        private static double[][] invertMatrix(double[][] data) {
            int n = data.length;
            double[][] augmented = new double[n][n * 2];
            for (int i = 0; i < n; i++) {
                System.arraycopy(data[i], 0, augmented[i], 0, n);
                augmented[i][i + n] = 1;
            }

            for (int k = 0; k < n; k++) {
                double pivot = augmented[k][k];
                for (int j = 0; j < 2 * n; j++) {
                    augmented[k][j] /= pivot;
                }

                for (int i = 0; i < n; i++) {
                    if (i != k) {
                        double factor = augmented[i][k];
                        for (int j = 0; j < 2 * n; j++) {
                            augmented[i][j] -= factor * augmented[k][j];
                        }
                    }
                }
            }

            double[][] inverse = new double[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(augmented[i], n, inverse[i], 0, n);
            }

            return inverse;
        }

        // Находим норму матрицы как максимальную сумму модулей элементов по строкам
        private static double calculateMatrixNorm(double[][] data) {
            double norm = 0;
            for (int i = 0; i < data.length; i++) {
                double rowSum = 0;
                for (int j = 0; j < data[i].length; j++) {
                    rowSum += Math.abs(data[i][j]);
                }
                norm = Math.max(norm, rowSum);
            }
            return norm;
        }
    }


