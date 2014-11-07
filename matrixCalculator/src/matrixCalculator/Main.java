package matrixCalculator;

import java.util.Scanner;
public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	public static void main(String args[]){
		double[][] A = {{1, 1, 1},{2, 2, 2}};
		double[][] B = {{1, 2},{3, 4},{5, 6}};
		double[][] C = matrixMath.transpose(B);
		//printMatrix(C);
		System.out.println(matrixMath.getDet(A));
		
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
