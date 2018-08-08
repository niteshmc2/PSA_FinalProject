package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class NeuralNet {
	//private double[][] inputs;
	private Layer hidden1;
	private Layer hidden2;
	private Layer outputL;
	//private static Data data;
	private double lr;
	
	public NeuralNet(int no_of_inputs, int no_of_outputs) {
		//this.inputs = inputs;
		hidden1 = new Layer(no_of_inputs,300);
		hidden2 = new Layer(300, 100);
		outputL = new Layer(100,no_of_outputs); 
		lr = 0.01;
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

	public static Data readImage(String fileName) throws IOException{
		Data data = new Data();
		File file = new File(fileName);
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
        //File f = new File("MyFile.png");
        
        
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
       // System.out.println("Done");
        
        return data;
		
	}
	
	private double[][] computeDfuncOutput(double[][] input) {
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[0].length; j++) {
				double val = input[i][j];
				double sigmoid = 1/(1+Math.pow(Math.E, -1*val));
				input[i][j] = sigmoid*(1-sigmoid);
			}
		}
		return input;
	}
	
	private double[][] normalize(double[][] a){
		int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j]/16777215-0.5;
        return c;
	}
	
	public void train(int[] input_img, int target_cls){
		double[][] inputs = Matrix.fromArray(input_img);
		inputs = normalize(inputs);
		double[][] targets = Matrix.clsToArray(target_cls);
		//Compute output
		double[][] h1outputs = hidden1.computeOutput(inputs);
		double[][] h2outputs = hidden2.computeOutput(h1outputs);
		double[][] outOutputs = outputL.computeOutput(h2outputs);
		
		//Compute error
		double[][] outErrors = Matrix.subtract(targets, outOutputs);
		
		//Compute Gradients
		double[][] gradients = computeDfuncOutput(outOutputs);
		gradients = Matrix.elemMultiply(gradients, outErrors);
		gradients = Matrix.scalarMultiply(gradients, lr);
		
		//Compute deltas
		double[][] hidden2_T = Matrix.transpose(h2outputs);
		double[][] weight_h20_deltas = Matrix.multiply(gradients, hidden2_T);
	
		//Adjust weights
		outputL.setWeights(Matrix.add(weight_h20_deltas, outputL.getWeights()));
		outputL.setBias(Matrix.add(outputL.getBias(), gradients));
		
		//Compute error
		double[][] wh20_T = Matrix.transpose(outputL.getWeights());
		double[][] h2_errors = Matrix.multiply(wh20_T, outErrors);
		
		//Compute gradients
		double[][] h2_gradients = computeDfuncOutput(h2outputs);
		h2_gradients = Matrix.elemMultiply(h2_gradients, h2_errors);
		h2_gradients = Matrix.scalarMultiply(h2_gradients, lr);
		
		//Compute deltas
		double[][] hidden1_T = Matrix.transpose(h1outputs);
		double[][] weight_h1h2_deltas = Matrix.multiply(h2_gradients, hidden1_T);
		
		//Adjust Weights
		hidden2.setWeights(Matrix.add(weight_h1h2_deltas, hidden2.getWeights()));
		hidden2.setBias(Matrix.add(hidden2.getBias(), h2_gradients));
		
		//Compute error
		double[][] wh1h2_T = Matrix.transpose(hidden2.getWeights());
		double[][] h1_errors = Matrix.multiply(wh1h2_T, h2_errors);
		
		//Compute gradients
		double[][] h1_gradients = computeDfuncOutput(h1outputs);
		h1_gradients = Matrix.elemMultiply(h1_gradients, h1_errors);
		h1_gradients = Matrix.scalarMultiply(h1_gradients, lr);
		
		//Compute deltas
		double[][] input_T = Matrix.transpose(inputs);
		double[][] weight_inh1_deltas = Matrix.multiply(h1_gradients, input_T);
		
		
		//Adjust Weights
		hidden1.setWeights(Matrix.add(weight_inh1_deltas, hidden1.getWeights()));
		hidden1.setBias(Matrix.add(hidden1.getBias(), h1_gradients));
		
		System.out.print("-");
	}
	
	public double[][] test(int[] input_img, int target_cls) {
		double[][] inputs = Matrix.fromArray(input_img);
		inputs = normalize(inputs);
		//double[][] targets = Matrix.clsToArray(target_cls);
		//Compute output
		double[][] h1outputs = hidden1.computeOutput(inputs);
		double[][] h2outputs = hidden2.computeOutput(h1outputs);
		double[][] outOutputs = outputL.computeOutput(h2outputs);
		return outOutputs;
	}
	
	public void trainMult(NeuralNet net, Data data) {
		for(int i = 0; i < data.getData().size(); i++){
			net.train(data.getData().get(i).getImage(), data.getData().get(i).getCls());	
		}
	}
	
	public static void main(String[] args) throws IOException{
		Data data1 = readImage("data_batch_1.bin");
		Data data2 = readImage("data_batch_2.bin");
		Data data3 = readImage("data_batch_3.bin");
		Data data4 = readImage("data_batch_4.bin");
		Data data5 = readImage("data_batch_5.bin");
		
		NeuralNet net = new NeuralNet(data1.getData().get(0).getImage().length, 10);
		
		net.trainMult(net, data1);
		net.trainMult(net, data2);
		net.trainMult(net, data3);
		net.trainMult(net, data4);
		net.trainMult(net, data5);
		
		Data test = readImage("test_batch.bin");
		for(int i = 5; i < test.getData().size(); i+=1000){
			double[][] outOutputs = net.test(test.getData().get(i).getImage(), test.getData().get(i).getCls());	
			String result="";
			int gusOutCls=1;
			double gusOut = outOutputs[0][0];
			for(int j=0; j<outOutputs.length; j++) {
				if(gusOut < outOutputs[j][0]) {
					gusOut = outOutputs[j][0];
					gusOutCls = j+1;
				}
				result+="Class-"+(j+1)+":"+(Math.round(outOutputs[j][0]*100)/100.00)+" | ";
			}
			System.out.println("Expected:"+(test.getData().get(i).getCls()+1)+" -- Guessed:"+gusOutCls+" Array:"+result);
		}
	}
		
}
