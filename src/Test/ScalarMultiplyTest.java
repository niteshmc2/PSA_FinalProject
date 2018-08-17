package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Main.Matrix;

public class ScalarMultiplyTest {

	@Test
	public void test() {
		boolean flag = true;
		
		double[][] a = new double[1][783];
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 783; j++)
                a[i][j] = 10;
        
        double b = 15 ;
        
        double[][] result = Matrix.scalarMultiply(a, b);
        for(int i = 0; i < 1; i++){
			for(int j = 0; j < 783; j++){
				if(!(result[i][j] == 150)){
					flag = false;
				}
			}
		}
        
        assertTrue(flag);
	}

}
