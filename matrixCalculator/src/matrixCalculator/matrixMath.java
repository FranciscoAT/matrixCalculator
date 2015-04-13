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
	 * NOTE: If no augmented matrix aug = A.length +1
	 * @param A (matrix)
	 * @param aug (int) Location (starting from 0) of column of augmented matrix
	 * @return Row reduced A matrix 
	 */
	public static double[][] rowReduce(double[][] A, int aug){
		int rank;
		if(A.length<aug)
			rank = A.length;
		else
			rank = aug;
		double[][] R = new double[A.length][A[0].length];
		for(int i = 0; i<R.length; i++){
			for(int j = 0; j<R[0].length; j++ ){
				R[i][j] = A[i][j];
			}
		}
		for(int c = 0; c <rank; c++){
				if(R[c][c] == 0){
					for(int r = c+1; r<R.length; r++){
						if(R[r][c] != 0){
							double T[] = new double[R.length];
							for(int i = 0; i<R.length; i++){
								T[i] = R[c][i];
								R[c][i] = R[r][i];
								R[r][i] = T[i];
							}
						}
					}
				}
			
			double constant = R[c][c];
			for(int i = 0; i <R[0].length; i++){
				if(constant != 0)
					R[c][i] = R[c][i]/constant;
			}
			for(int i =0; i<R.length; i++){
				if(R[i][c] != 0 && i != c){
					constant = R[i][c]*-1;
					for(int j = 0; j<R[0].length; j++){
						R[i][j] += R[c][j]*constant;
					}
				}
			}
		}
		R = sortByRank(R, aug);
		return R;
	} //end rowReduction method
	
	
	/**
	 * Returns inverse matrix of double A
	 * NOTE: Matrix must not be augmented!
	 * @param A
	 * @return
	 */
	public static double[][] getInverse(double[][] A){
		if(A.length != A[0].length)
			return null;
		if(getDet(A) == 0)
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
	private static double[][] generateIdentity(int l){
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
	
	
	private static double[][] sortByRank(double[][] A, int aug){
		int index = 0;
		for(int i = 0; i < A.length-index; i++){
			boolean zeroRow = true;
			for(int j = 0; j<aug; j++){
				if(A[i][j] != 0)
					zeroRow = false;
			}
			if(zeroRow){
				for(int k = i; k<A.length-1; k++){
					for(int j = 0; j <A[0].length; j++){
						double T = A[k][j];
						A[k][j] =A[k+1][j];
						A[k+1][j] = T;
					}
				}
				index ++;
			}
		}
		return A;
	} //end sortByRank method
	

} //end matrixMath class
