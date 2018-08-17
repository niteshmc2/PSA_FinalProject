package Main;

public class Matrix {

    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = Math.random()-0.5;
        return a;
    }

    // return B = A^T
    public static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] b = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b[j][i] = a[i][j];
        return b;
    }

    // return c = a + b
    public static double[][] add(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    // return c = a - b
    public static double[][] subtract(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] - b[i][j];
        return c;
    }

    // return c = a * b
    public static double[][] multiply(double[][] a, double[][] b) {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }
    
    static double[][] fromArray(int[] arr) {
        int m = arr.length;
        int n = 1;
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = arr[i];
        return a; 
      }
    static double[][] fromArray(double[] arr) {
        int m = arr.length;
        int n = 1;
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = arr[i];
        return a; 
      }

    static double[][] generateByNum(int m, int n, int x){
    	double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = x;
        return a;
    }
    
    public static double[][] elemMultiply(double[][] a, double[][] b){
    	int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] * b[i][j];
        return c;
    }
    
    public static double[][] scalarMultiply(double[][] a, double b){
    	int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		c[i][j] = a[i][j] * b;	
        	}
        }
        return c;
    }

    public static double[][] clsToArray(int x){
    	int m = 10;
    	int n = 1;
    	double[][] a = new double[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if(i == x)
            		a[i][j] = 1;
            	else
            		a[i][j] = 0;
        	}
        }
            
        return a; 
    }
    
//    static int arraytoCls(double[][] a){
//    	int m = 10;
//    	int n = 1;
//    	//double[][] a = new double[m][n];
//        for (int i = 0; i < m; i++) {
//        	for (int j = 0; j < n; j++) {
//        		if(a[i][j] == 1)
//            		return 1;
//        	}
//        }
//        return -1;
//    }
}

