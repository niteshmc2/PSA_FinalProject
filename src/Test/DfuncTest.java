package Test;

import Main.Matrix;
import Main.NeuralNet;
import static org.junit.Assert.*;

import org.junit.Test;

public class DfuncTest {

	@Test
	public void test() {
		boolean flag = true;
		NeuralNet test = new NeuralNet(783, 10);
		
		double[][] input = new double[1][783];
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 783; j++)
                input[i][j] = 0;
        
        input = test.computeDfuncOutput(input);
        
        double[][] output = new double[1][783];
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 783; j++)
                output[i][j] = 0.25;
        
        for(int i = 0; i < 1; i++){
			for(int j = 0; j < 783; j++){
				if(input[i][j] != output[i][j]){
					flag = false;
				}
			}
		}
		assertTrue(flag);
	}

}
