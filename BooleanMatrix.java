import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashSet;

public class BooleanMatrix {
	public static void main(String[] args) {
		//To check for authenticity, here's a link to the Matrix Power Calculator used for testing higher power values: https://matrix.reshish.com/power.php
		Scanner input = new Scanner(System.in);
		boolean errorOccured = false;
		while (errorOccured != true) {
			try {
				// User inputs exponent which determines the length of row and column
				int exponent = createExpo(input);
				int row = createRow(input);
				int col = createColumn(input);
				int[][] matrix;
				if (row != col) {
					throw new InputMismatchException(
							"Error: This program only accepts square matrix. \nPlease make sure the row and column are all equal to each other!\n");
				} else {
					matrix = createMatrix(row, col);
				}
				int[][] matrixPower = matrixPower(matrix, exponent);
				printMatrixPower(matrixPower, exponent);
				errorOccured = true;
			} catch (Exception e) {
				System.err.println("Please Try Again.");
			}
		}
	}// End of main

	public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
		int[][] storage = new int[matrix1.length][matrix2[0].length];
		for(int row = 0; row< matrix1.length;row++) {
			for(int column = 0; column<matrix1[0].length;column++) {
				HashSet<Integer> values = new HashSet<>();
				for(int middle = 0; middle < matrix2.length;middle++) {
					int product = matrix1[row][middle] * matrix2[middle][column];
					values.add(product);
					if(values.contains(1)) {
						storage[row][column] = 1;
					}else {
						storage[row][column] = 0;
					}
				}
			}
		}
		return storage;
	}

	public static int[][] matrixPower(int[][] matrix1, int exponent) {
		int[][] matrixClone = matrix1.clone();
		int count = 0;
		int[][] matrixPower;
		while (count < exponent) {
			int[][] storage = multiply(matrix1, matrixClone);
			matrix1 = storage;
			count++;
		}
		matrixPower = matrix1.clone();
		return matrixPower;
	}

	// SuppressWarnings were a recommended solution to the warning popUp as "null"
	// is needed.
	@SuppressWarnings("null")
	public static int createExpo(Scanner input) {
		try {
			System.out.println("Please enter a positive integer value for the Exponent:");
			int exponent = Integer.parseInt(input.nextLine());
			if (exponent < 0) {
				throw new InputMismatchException("Program only accepts positive integers for exponents!");
			}
			return exponent;
		} catch (Exception e) {
			System.err.println("An Error has Occured!\nReasoning: " + e.getLocalizedMessage());
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public static int createRow(Scanner input) {
		try {
			System.out.println("Please enter the length of Matrix [A]:");
			int row = Integer.parseInt(input.nextLine());
			if (row < 0) {
				throw new InputMismatchException("Program only accepts positive integers for rows!");
			}
			return row;
		} catch (Exception e) {
			System.err.println("Error Occured!\nReasoning: " + e.getLocalizedMessage());
		}
		return (Integer) null;
	}

	@SuppressWarnings("null")
	public static int createColumn(Scanner input) {
		try {
			System.out.println("Please enter the width of Matrix [A]:");
			int col = Integer.parseInt(input.nextLine());
			if (col < 0) {
				throw new InputMismatchException("Program only accepts positive integers for columns!");
			}
			return col;
		} catch (Exception e) {
			System.err.println("Error Occured!\nReasoning: " + e.getLocalizedMessage());
		}
		return (Integer) null;
	}

	public static int[][] createMatrix(int row, int col) {
		int[][] matrix = new int[row][col];
		System.out.print("Values for Given Matrix [A]: ");
		for (int i = 0; i < row; i++) {
			System.out.println();
			for (int j = 0; j < col; j++) {
				double ranInput = Math.random();
				int ranNums;
				if (ranInput >= 0.5) {
					ranNums = 1;
				} else {
					ranNums = 0;
				}
				matrix[i][j] = ranNums;
				System.out.print(matrix[i][j] + ", ");
			}
		}
		return matrix;
	}

	public static void printMatrixPower(int[][] matrixPower, int exponent) {
		System.out.print("\n\nOutput of [A]^" + exponent);
		for (int i = 0; i < matrixPower.length; i++) {
			System.out.println();
			for (int j = 0; j < matrixPower[0].length; j++) {
				System.out.print(matrixPower[i][j] + ", ");
			}
		}
	}
}
