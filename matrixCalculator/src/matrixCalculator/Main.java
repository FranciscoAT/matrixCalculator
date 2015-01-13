 package matrixCalculator;

import java.util.Scanner;
public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	public static void main(String args[]){
		GUI runInstance = new GUI(); 
		
		/*
		double[][] A = {{5,3,1, 1}, {0, 2, 1, 2}, {5, 0, 2, 4}};
		double[][] B = {{1, 2},{3, 4},{5, 6}};
		double[][] C = matrixMath.rowReduce(A, 3);
		printMatrix(C);
		*/
	}
	
	
	//ALL METHODS BELOW ARE TEMPORARY
	public static double[][] createMatrix(){
		System.out.println( "Enter the number of rows in matrix A:  " );
		int nRows = sc.nextInt( );
		System.out.println( "Enter the number of columns in matrix A:  " );
		int nCols = sc.nextInt( );
		System.out.print("Enter a "+ nRows+" by "+nCols+" matrix row by row+("+nRows+"x"+nCols+"): ");
		double[][] A = new double[nRows][nCols];
		for (int i = 0; i < nRows; i++){
			for (int j = 0; j < nCols; j++){
				A[i][j] = sc.nextDouble();
			}
		}
		return A;
	}
	
	public static void printMatrix(double[][] A){
		if(A == null){
			System.out.println("ERROR MATRIX IS EMPTY");
		}
		else{
			for(int i = 0; i<A.length; i++){
				for(int j = 0; j<A[0].length; j++){
					System.out.print(A[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
}
