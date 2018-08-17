package Test;
import Main.Matrix;

import static org.junit.Assert.*;

import org.junit.Test;

public class ElemMultiplyTest {

	@Test
	public void test() {
		boolean flag = true;
		
		double[][] a = new double[1][783];
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 783; j++)
                a[i][j] = 10;
        
        double[][] b = new double[1][783];
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 783; j++)
                b[i][j] = 20;
        
        double[][] result = Matrix.elemMultiply(a, b);
        for(int i = 0; i < 1; i++){
			for(int j = 0; j < 783; j++){
				if(!(result[i][j] == 200)){
					flag = false;
				}
			}
		}
        
        assertTrue(flag);
	}

}
