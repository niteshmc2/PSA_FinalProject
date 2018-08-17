package Test;
import Main.Matrix;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubtractTest {

	@Test
	public void test() {
		boolean flag = true;
		
		double[][] a = new double[1][783];
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 783; j++)
                a[i][j] = 10;
        
        double[][] b = a;
        
        double[][] result = Matrix.subtract(a, b);
        
        for(int i = 0; i < 1; i++){
			for(int j = 0; j < 783; j++){
				if(!(result[i][j] == 0)){
					flag = false;
				}
			}
		}
        
        assertTrue(flag);
	}

}
