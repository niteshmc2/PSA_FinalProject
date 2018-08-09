package Test;

import Main.NeuralNet;
import static org.junit.Assert.*;

import org.junit.Test;

public class ActivationFuncTest {

	@Test
	public void test() {
		boolean flag = true;
		NeuralNet test = new NeuralNet(783, 10);
		
		double[][] input = new double[100][1];
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 1; j++)
                input[i][j] = -0.5;
        
        double[][] weights = new double[10][100];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 100; j++)
                weights[i][j] = 1;
		
		input = test.getOutputL().computeOutput(input, weights);
		
		double[][] output = new double[10][1];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 1; j++)
            	output[i][j] = 5.242885663363477E-22;
        
        for(int i = 0; i < 10; i++){
			for(int j = 0; j < 1; j++){
				if(input[i][j] != output[i][j]){
					flag = false;
				}
			}
		}
       
		assertTrue(flag);
		
	}

}
