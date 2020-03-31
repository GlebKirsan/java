package processor;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
    int rows;
    int cols;

    double[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[this.rows][this.cols];
    }

    public void read(Scanner scanner) {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    public Matrix mult(double multiplier) {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] *= multiplier;
            }
        }
        return this;
    }

    public Matrix mult(Matrix other) {
        Matrix result = new Matrix(rows, other.cols);

        for (int i = 0; i < rows; ++i) {
            for (int k = 0; k < other.rows; ++k) {
                for (int j = 0; j < other.cols; ++j) {
                    result.matrix[i][j] += matrix[i][k] * other.matrix[k][j];
                }
            }
        }

        return result;
    }

    public Matrix add(Matrix other) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                result.matrix[i][j] = matrix[i][j] + other.matrix[i][j];
            }
        }

        return result;
    }

    public Matrix transpose(int type) {
        switch (type) {
            case 1:
                return transpose();
            case 2:
                return transposeSideDiagonal();
            case 3:
                return transposeVerticalLine();
            case 4:
                return transposeHorizontalLine();
            default:
                return this;
        }
    }

    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public Matrix transposeSideDiagonal() {
        Matrix result = new Matrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[cols - 1 - j][rows - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    public Matrix transposeVerticalLine() {
        Matrix result = new Matrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols / 2; j++) {
                result.matrix[i][cols - j - 1] = matrix[i][j];
                result.matrix[i][j] = matrix[i][cols - j - 1];
            }
        }
        return result;
    }

    public Matrix transposeHorizontalLine() {
        Matrix result = new Matrix(cols, rows);

        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[rows - i - 1][j] = matrix[i][j];
                result.matrix[i][j] = matrix[rows - i - 1][j];
            }
        }
        return result;
    }

    public Matrix getMinor(int row, int column) {
        Matrix minor = new Matrix(rows - 1, cols - 1);
        int ii = 0;
        for (int i = 0; i < rows; ++i) {
            if (i == row) {
                continue;
            }

            int jj = 0;
            for (int j = 0; j < cols; ++j) {
                if (j == column) {
                    continue;
                }
                minor.matrix[ii][jj] = matrix[i][j];
                ++jj;
            }
            ++ii;
        }
        return minor;
    }

    public double getCofactor(int row, int column) {
        Matrix minor = getMinor(row, column);
        int one = (row + column) % 2 == 0 ? 1 : -1;
        return one * minor.determinant();
    }

    public double determinant() {
        if (rows == 2 && cols == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;
        for (int i = 0; i < cols; ++i) {
            if (matrix[0][i] == 0) {
                continue;
            }
            det += matrix[0][i] * getCofactor(0, i);
        }

        return det;
    }

    public Matrix inverse() {
        double det = determinant();
        Matrix cofactors = new Matrix(rows, cols);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                cofactors.matrix[i][j] = getCofactor(i, j);
            }
        }

        return cofactors.transpose().mult(1 / det);
    }

    @Override
    public String toString() {
        StringBuilder stringMatrix = new StringBuilder();
        for (double[] row : matrix) {
            String rowStr = "%5.2f";
            for (int i = 0; i < row.length; ++i) {
                if (Math.abs(row[i]) < 1e-5) {
                    row[i] = 0;
                }
                rowStr = String.format(rowStr, row[i]);
                if (i + 1 != row.length) {
                    rowStr += " %5.2f";
                }
            }
            stringMatrix.append(rowStr).append('\n');
        }

        return stringMatrix.toString();
    }
}
