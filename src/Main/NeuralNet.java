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
	private double[][] inputs;
	private Layer hidden1;
	private Layer hidden2;
	private Layer outputL;
	private static Data data;
	public NeuralNet(int no_of_inputs, int no_of_outputs) {
		//this.inputs = inputs;
		hidden1 = new Layer(no_of_inputs,300);
		hidden2 = new Layer(300, 100);
		outputL = new Layer(100,no_of_outputs); 
	}
	
	public Layer getHidden1() {
		return hidden1;
	}

	public Layer getHidden2() {
		return hidden2;
	}

	public Layer getOutputL() {
		return outputL;
	}

	public static Data readImage() throws IOException{
		Data data = new Data();
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
        
        
        for(int x = 0; x < 30730000; x += 3073) {
        	int[] img = new int[1024];
        		int cls = bytes[x];
    	        byte[] reds = Arrays.copyOfRange(bytes, x+1, x+1025);
    	        byte[] greens = Arrays.copyOfRange(bytes, x+1025, x+2049);
    	        byte[] blues = Arrays.copyOfRange(bytes, x+2049, x+3073);
    	        
            	for(int z = 0; z < img.length; z++){
            		int r = reds[z] & 0xFF;
            		//System.out.println(r);
                    int g = greens[z] & 0xFF; 
                    int b = blues[z] & 0xFF;
                    int col = (r << 16) | (g << 8) | b ;
                    img[z] = col;
        	}
	      
        	Images imgs = new Images(img, cls);
        	data.addImage(imgs);
        	
        }
        
//        for(int i=20; i<30; i++){
//        	data.getData().get(i).writeImg(String.valueOf(i));
//        }
        System.out.println("Done");
        
        return data;
		
	}
	
	
	public void train(int[] input_img, int target_cls){
		inputs = Matrix.fromArray(input_img);
		double[][] targets = Matrix.clsToArray(target_cls);
		
		double[][] h1outputs = hidden1.computeOutput(inputs);
		double[][] h2outputs = hidden2.computeOutput(h1outputs);
		double[][] outOutputs = outputL.computeOutput(h2outputs);
		
		double[][] outErrors = Matrix.subtract(targets, outOutputs);
		
	}
	
	public static void main(String[] args) throws IOException{
		data = readImage();
		NeuralNet net = new NeuralNet(data.getData().get(0).getImage().length, 10);
		for(int i = 0; i < data.getData().size(); i++){
			net.train(data.getData().get(i).getImage(), data.getData().get(i).getCls());
			
		}
	}
		
}
