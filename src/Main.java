
public class Main {
    public static void main(String[] args) {
        double[][] A = {{2, 1, -1}, {-3, -1, 2}, {-2, 1, 2}};
        double[] b = {8, -11, -3};

        double[][] D = {{7, 1, -1, 4, 9}, {-3, -1, 2, 6, 3}, {-2, 1, 2, 8, 7},
                {10, 4, 11, 8, 1, -5}, {-3, 8, 9, 4, -2}};

        double[] c = {8, -11, -3, 9, 1};


        double[] x = Gauss.gaussianElimination(A, b);
        double[] z =GaussianElimination.solve(D, c);

        for (int i = 0; i < x.length; i++) {
            System.out.println("x" + (i + 1) + " = " + x[i]);
        }
        for (int k = 0; k < z.length; k++) {
            System.out.println("z" + (k + 1) + " = " + z[k]);
        }
        double[] [] R = {{1, 1}, {2,1}};
        double r =MatrixCondition.computeConditionNumber(R);
        System.out.println(r);
    }

}