package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Main.Matrix;

public class ClsToArrayTest {

	@Test
	public void test() {
		boolean flag = true;
		
		int a = 9;
        
        double[][] result = Matrix.clsToArray(a);
        if(!(result[9][0] == 1)){
        	flag = false;
        }
        
        assertTrue(flag);
	}

}
