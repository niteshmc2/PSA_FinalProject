package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

	
	public double[][] computeDfuncOutput(double[][] input) {
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[0].length; j++) {
				double val = input[i][j];
				double sigmoid = 1/(1+Math.pow(Math.E, -1*val));
				input[i][j] = sigmoid*(1-sigmoid);
			}
		}
		return input;
	}
	
	public double[][] normalize(double[][] a){
		int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j]/255-0.5;
        return c;
	}
	
	public void train(int[] input_img, int target_cls){
		double[][] inputs = Matrix.fromArray(input_img);
		inputs = normalize(inputs);
		double[][] targets = Matrix.clsToArray(target_cls);
		//Compute output
		double[][] h1outputs = hidden1.computeOutput(inputs, hidden1.getWeights());
		double[][] h2outputs = hidden2.computeOutput(h1outputs, hidden2.getWeights());
		double[][] outOutputs = outputL.computeOutput(h2outputs, outputL.getWeights());
		
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
		
		
	}
	
	public double[][] test(int[] input_img, int target_cls) {
		double[][] inputs = Matrix.fromArray(input_img);
		inputs = normalize(inputs);
		//double[][] targets = Matrix.clsToArray(target_cls);
		//Compute output
		double[][] h1outputs = hidden1.computeOutput(inputs, hidden1.getWeights());
		double[][] h2outputs = hidden2.computeOutput(h1outputs, hidden2.getWeights());
		double[][] outOutputs = outputL.computeOutput(h2outputs, outputL.getWeights());
		return outOutputs;
	}
	
	public void trainMult(NeuralNet net, Data data, int upperLimit) {
		for(int i = 0; i < upperLimit; i++){
			System.out.print(i);
			System.out.println("\f");
			net.train(data.getData().get(i).getImage(), data.getData().get(i).getCls());	
		}
	}
	
	public static Data readCSV() throws IOException {
		String csvFile = "data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        Data data = new Data();
        //int counter=0;
        while ((line = br.readLine()) != null) {
        	 //line = br.readLine();
        	 String[] strImage = line.split(cvsSplitBy);
        	 int[] image = new int[strImage.length];
        	 for(int i=0; i<strImage.length; i++) {
        		 image[i] = Integer.parseInt(strImage[i]);
        	 }
        	 Images imgs = new Images(Arrays.copyOfRange(image, 1, 785), image[0]);
        	 data.getData().add(imgs);
        	 //counter++;
        }
        br.close();
		return data;
	}
	
	
	
	public static void main(String[] args) throws IOException{
		
		double[][] conMatrix = Matrix.generateByNum(10, 10, 0);

		Data data = readCSV();

		NeuralNet net = new NeuralNet(data.getData().get(0).getImage().length, 10);
		

		net.trainMult(net, data, 38000);
		int counter = 0;
		for(int i = 38000; i < data.getData().size(); i++){
			double[][] outOutputs = net.test(data.getData().get(i).getImage(), data.getData().get(i).getCls());
			counter++;
			String result="";
			int gusOutCls=0;
			double gusOut = outOutputs[0][0];
			for(int j=0; j<outOutputs.length; j++) {
				if(gusOut < outOutputs[j][0]) {
					gusOut = outOutputs[j][0];
					gusOutCls = j;
				}
				result+="Class-"+(j)+":"+(Math.round(outOutputs[j][0]*100)/100.00)+" | ";
			}
			System.out.println("Expected:"+(data.getData().get(i).getCls())+" -- Guessed:"+gusOutCls+" Array:"+result);
			conMatrix[data.getData().get(i).getCls()][gusOutCls]++;
		}
		
		
		double correct = 0;
		BufferedWriter writer;
        File f = new File("confusionMatrix.csv");
        writer = new BufferedWriter(new FileWriter(f));
        writer.append("0,1,2,3,4,5,6,7,8,9");
        writer.append("\n");
        for(int i = 0; i < 10; i++){
        	String cm = "";
        	for(int j = 0; j < 10; j++){
        		cm += String.valueOf(conMatrix[i][j]) + ",";
        		
        		if(i == j){
        			correct += conMatrix[i][j];
        		}
        		 
        	}
        	
        	writer.append(cm + "\n");
        }
        double acc = (correct/counter)*100;
        writer.append("\n");
        writer.append("Accuracy in %: "+ acc);
        System.out.println("Accuracy in %: "+ acc);
		writer.close();
	
	}
		
}
