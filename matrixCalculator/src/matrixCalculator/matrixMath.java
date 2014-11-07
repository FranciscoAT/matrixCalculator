package matrixCalculator;

public class matrixMath {
	
	/**
	 * @param A (matrix)
	 * @param B (matrix)
	 * @return (matrix) C = A+B || null = A!+B
	 */
	public static double[][] add(double A[][], double B[][]){
		if(A.length != B.length || A[0].length != B[0].length)
			return null;
		double T[][] = new double[A.length][A[0].length];
		for(int i = 0; i<T.length; i++){
			for(int j = 0; j<T[0].length; j++){
				T[i][j] = A[i][j]+B[i][j];
			}
		}
		return T;
	} //end add method
	
	/**
	 * @param A (matrix)
	 * @param B (matrix)
	 * @return (matrix) C = A-B || null = A!-B
	 */
	public static double[][] subtract(double A[][], double B[][]){
		if(A.length != B.length || A[0].length != B[0].length)
			return null;
		double T[][] = new double[A.length][A[0].length];
		for(int i = 0; i<T.length; i++){
			for(int j = 0; j<T[0].length; j++){
				T[i][j] = A[i][j]-B[i][j];
			}
		}
		return T;
	} //end subtract method
	
	/**
	 * @param A (matrix)
	 * @param k (constant)
	 * @return (matrix) B = k*A
	 */
	public static double[][] constantM(double[][] A, double k){
		for(int i = 0; i<A.length; i++){
			for(int j = 0; j<A[0].length; j++){
				A[i][j] = k*A[i][j];
			}
		}
		return A;
	} //end constantM method
	
	/**
	 * @param A (matrix)
	 * @param B (matrix)
	 * @return (matrix) C = [A][B] || null = [A]![B]
	 */
	public static double[][] multiply(double[][] A, double[][] B){
		if(A[0].length != B.length)
			return null;
		double[][] T = new double[A.length][B[0].length];
		int t = 0;
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < B[0].length; j++){
				for(int k = 0; k<A[0].length; k++){
					t+= A[i][k]*B[k][j];
				}
				T[i][j] = t;
				t = 0;
			}
		}
		return T;
	} //end multiply method
	
	/**
	 * @param A (matrix)
	 * @return C = A^T (A transposed)
	 */
	public static double[][] transpose(double[][] A){
		double[][] T = new double[A[0].length][A.length];
		for(int i = 0; i<A.length; i++){
			for(int j = 0; j<A[0].length; j++){
				T[j][i] = A[i][j];
			}
		}
		return T;
	} //end transpose method
	
	/**
	 * @param A (matrix)
	 * @return det(A) = d (double) || det!(A) = Double.NaN (double)
	 */
	public static double getDet(double[][] A){
		if(A.length != A[0].length)
			return Double.NaN;
		double det = 0;
		int cofact = 0;
		if(A.length == 1)
			return A[0][0];
		for(int i = 0; i<A.length; i++){
			double[][] T = new double[A.length-1][A.length-1];
			for(int j = 1; j<A.length; j++){
				for(int k = 0; k<A.length; k++){
					if(k<i)
						T[j-1][k] = A[j][k];
					else if(k >i)
						T[j-1][k-1] = A[j][k];
				}
			}
			if(i%2 == 0)
				cofact = 1;
			else
				cofact = -1;
			det += cofact*A[0][i]*(getDet(T));
		}
		return det;
	} //end getDet method
	
	/**
	 * NOTE: Does not account for impossible matrices
	 * @param A (matrix)
	 * @param aug (int) Location (starting from 0) of column of augmented matrix
	 * @return Row reduced A matrix 
	 */
	public static double[][] rowReduce(double[][] A, int aug){
		for(int c = 0; c <aug; c++){
			if(c<A[0].length){
				if(A[c][c] == 0){
					for(int r = c+1; r<A[0].length; r++){
						if(A[r][c] != 0){
							double T[] = new double[A.length];
							for(int i = 0; i<A.length; i++){
								T[i] = A[c][i];
								A[c][i] = A[r][i];
								A[r][i] = T[i];
							}
						}
					}
				}
			}
			
			double constant = A[c][c];
			for(int i = 0; i <A[0].length; i++){
				if(constant != 0)
					A[c][i] = A[c][i]/constant;
			}
			for(int i =0; i<A.length; i++){
				if(A[i][c] != 0 && i != c){
					constant = A[i][c]*-1;
					for(int j = 0; j<A[0].length; j++){
						A[i][j] += A[c][j]*constant;
					}
				}
			}
		}
		return A;
	} //end rowReduction method
	
	
	public static double[][] getInverse(double[][] A){
		if(A.length != A[0].length)
			return null;
		double T[][] = new double[A.length][A.length*2];
		double I[][] = generateIdentity(A.length);
		for(int i = 0; i<A.length; i++){
			for(int j = A.length; j<T[0].length; j++){
				T[i][j] = I[i][j-A.length];
			}
			for(int j = 0; j<A.length; j++){
				T[i][j] = A[i][j];
			}
		}
		T = rowReduce(T, A.length);
		for(int i = 0; i<A.length; i++){
			for(int j = A.length; j<T[0].length; j++){
				if(T[i][j] == -0)
					T[i][j] = T[i][j]*-1;
				I[i][j-A.length] = T[i][j];
			}
		}
		return I;
	}
	
	/**
	 * Generates identity matrix w/ side length l
	 * @param l (int, side length)
	 * @return Identity Matrix: I (side length L)
	 */
	public static double[][] generateIdentity(int l){
		double[][] T = new double[l][l];
		for(int i = 0; i<l; i++){
			for(int j = 0; j<l; j++){
				if(i == j)
					T[i][j] = 1;
				else
					T[i][j] = 0;
			}
		}
		return T;
	} //end generateIdentity
	

} //end matrixMath class
