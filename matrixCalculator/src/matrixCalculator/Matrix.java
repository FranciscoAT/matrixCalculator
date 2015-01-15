package matrixCalculator;

import javax.swing.JButton;

public class Matrix {
	private static double[][] A;
	private int row, column, augmentedIndex;
	private boolean augmented;
	private JButton button;
	private String name;
	
	public Matrix(double[][] M, String s){
		A = M;
		row = A.length;
		column = A[0].length;
		augmentedIndex = A[0].length+1;
		augmented = false;
		name = s;
		button = new JButton(name);
		button.setSize(190, 30);
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
	
	public static void changeMatrix(double B[][]){
		A = B;
	}
	
	public JButton getButton(){
		return button;
	}
}
