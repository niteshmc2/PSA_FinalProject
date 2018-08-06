package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class NeuralNet {
	private double[] inputs;
	private Layer hidden1;
	private Layer hidden2;
	private Layer outputL;
	
	public NeuralNet(int no_of_inputs, int no_of_outputs) {
		//this.inputs = inputs;
		hidden1 = new Layer(no_of_inputs,300);
		hidden2 = new Layer(300, 100);
		outputL = new Layer(100,no_of_outputs); 
	}
	
	
	
	public static void main(String[] args) throws IOException {	
		File file = new File("data_batch_1.bin");
		//FileReader fr = new FileReader(f);
		InputStream insputStream = new FileInputStream(file);
		int length =(int) file.length();
		System.out.println("Long length: "+file.length());
		System.out.println("int length: "+length);
		byte[] bytes = new byte[length];
		insputStream.read(bytes);
		insputStream.close();
		int clas = bytes[0];
		System.out.println(clas);
		System.out.println(bytes[0]);
        File f = new File("MyFile.png");
        int[][] images = new int[10000][1025];
        int[] img = new int[1025];
        int cls = Arrays.copyOfRange(bytes,0, 2)[0];
        byte[] reds = Arrays.copyOfRange(bytes, 1, 1025);
        byte[] greens = Arrays.copyOfRange(bytes, 1025, 2050);
        byte[] blues = Arrays.copyOfRange(bytes, 2050, 3074);
        for(int x = 0; x < 10000; x++) {
        	
        	for(int y = 0; y < img.length - 1; y++){
        		int r = reds[y] & 0xFF;
        		//System.out.println(r);
                int g = greens[y] & 0xFF; 
                int b = blues[y] & 0xFF;
                int col = (r << 16) | (g << 8) | b ;
                images[x][y] = col;
                
        	}
        	images[x][1024] = cls;
        	
        }
        	
        System.out.println("Done");
            //ImageIO.write(img, "PNG", f);
		//NeuralNet net = new NeuralNet(data, 10);
		
	}
}
