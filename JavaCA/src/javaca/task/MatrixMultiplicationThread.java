package javaca.task;

/**
 *
 * @author name: Pruthvi Mulik
 * Student number:2021405
 * GitHub link: https://github.com/CCT-Dublin/ca1-Pruthvi1-123.git
 */
public class MatrixMultiplicationThread implements Runnable {
    private final int[][] matrixA;
    private final  int[][] matrixB;
    private final  int[][] result;
    private final  int row;
    private final  int col;

    public MatrixMultiplicationThread(int[][] matrixA, int[][] matrixB, int[][] result, int row, int col) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.row = row;
        this.col = col;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < matrixA[row].length; i++) {
            sum += matrixA[row][i] * matrixB[i][col];
        }
        result[row][col] = sum;
    }
}