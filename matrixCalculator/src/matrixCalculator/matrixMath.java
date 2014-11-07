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
		int cofact;
		if(A.length == 1)
			return A[0][0];
		for(int i = 0; i<A.length; i++){
			double[][] T = new double[A.length-1][A.length-1];
			for(int j = 1; j<A.length-1; j++){
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
			det += cofact*A[0][i]*getDet(T);
		}
		return det;
	} //end getDet method

} //end matrixMath class
