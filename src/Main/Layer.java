package Main;

import java.util.ArrayList;

public class Layer {

	private ArrayList<Neuron> neurons;

	private double[][] weights;
	private double[][] bias; 
		
	public Layer(int no_of_inputs, int no_of_neurons) {
		weights = Matrix.random(no_of_neurons,no_of_inputs);
		initNeurons(no_of_neurons);
		initBias(no_of_neurons);
	}
	
	private void initNeurons(int no_of_neurons) {
		neurons = new ArrayList<>();
		for(int i=0; i<=no_of_neurons; i++) {
			Neuron n = new Neuron();
			neurons.add(n);
		}
	}
	
	private void initBias(int no_of_neurons){
		bias = Matrix.generateByNum(no_of_neurons, 1, 1);
	}
	
	
	
	public double[][] computeOutput(double[][] input){
		double[][] product = Matrix.multiply(weights, input);
		product = Matrix.add(product, bias);
		for(int i = 0; i < product.length; i++) {
			for(int j = 0; j < product[0].length; j++) {
				product[i][j]= 1/(1+Math.pow(Math.E, -1*product[i][j]));
			}
		}
		return product;
	}
	
	
	
	
	public double[][] getBias() {
		return bias;
	}

	public void setBias(double[][] bias) {
		this.bias = bias;
	}

	public double[][] getWeights() {
		return weights;
	}

	public void setWeights(double[][] weights) {
		this.weights = weights;
	}

	public ArrayList<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(ArrayList<Neuron> neurons) {
		this.neurons = neurons;
	}
	
}
