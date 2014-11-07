package matrixCalculator;

public class Matrix {
	public double[][] A;
	public int row, column;
	
	public Matrix(double[][] M){
		A = M;
		row = A.length;
		column = A[0].length;
	}
	
	public double[][]getMatrix(){
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
	
	public int getNumRows(){
		return row;
	}
	
	public int getNumColumns(){
		return column;
	}
}
