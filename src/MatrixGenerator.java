import java.util.Random;
// Задание 5
public class MatrixGenerator {
    public static void main(String[] args) {
        // Задаем размерность матрицы
        int n = 5;

        // Генерируем случайный ненулевой вектор b длины n
        double[] b = new double[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            b[i] = random.nextDouble() * 10;
        }

        // Генерируем случайную матрицу A, используя одноранговое разложение
        double[] u = new double[n];
        double[] v = new double[n];
        double lambda = random.nextDouble() * 10;
        for (int i = 0; i < n; i++) {
            u[i] = random.nextDouble() * 10;
            v[i] = random.nextDouble() * 10;
        }
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = u[i] * v[j] * lambda;
            }
        }
        // Добавляем случайный шум к матрице A
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] += random.nextDouble() * 0.1;
            }
        }

        // Проверяем, что матрица A неособенная
        if (determinant(A) == 0) {
            System.out.println("Матрица A вырожденная");
            return;
        }

        // Выводим матрицу A и вектор b
        System.out.println("Матрица A:");
        printMatrix(A);
        System.out.println("Вектор b:" );
        printVector(b);
    }

    private static void printVector(double[] b) {
       String n = String.valueOf(b);
        System.out.println(n);
    }

    // Метод для вычисления определителя матрицы
    public static double determinant(double[][] A) {
        int n = A.length;
        double det = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double factor = A[j][i] / A[i][i];
                for (int k = i; k < n; k++) {
                    A[j][k] -= factor * A[i][k];
                }
            }
            det *= A[i][i];
        }
        return det;
    }

    // Метод для вывода матрицы на экран
    public static void printMatrix(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.printf("%8.4f ", A[i][j]);
            }
            System.out.println();
        }
    }
}
