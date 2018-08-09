package Test;
import Main.Matrix;
import Main.NeuralNet;
import static org.junit.Assert.*;

import org.junit.Test;

public class NormalizeTest {

	@Test
	public void test() {
		boolean flag  = true;
		NeuralNet test = new NeuralNet(783, 10);
		double[][] a = new double[1][783];
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 783; j++)
                a[i][j] = (int)Math.random()*255;
        
		double[][] output = test.normalize(a);
		for(int i = 0; i < 1; i++){
			for(int j = 0; j < 783; j++){
				if(!(output[i][j] >= -0.5 && output[i][j] <= 0.5)){
					flag = false;
				}
			}
		}
		
		assertTrue(flag);
	}

}
