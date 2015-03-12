package matrixCalculator;


public class Matrix{
	private double[][] A;
	private int row, column, augmentedIndex;
	private boolean augmented;
	private String name;
	
	public Matrix(String name, int numRow, int numColumn){
		augmented = false;
		this.row = numRow;
		this.column = numColumn;
		this.name = name;
	}
	
	public void setMatrixValues (double[][] M){
		A = M;
		row = A.length;
		column = A[0].length;
		augmentedIndex = A[0].length+1;
	}
	
	public double[][] getMatrix(){
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
	
	public String getName(){
		return name;
	}
	
	public void changeName(String s){
		name = s;
	}
	
}
