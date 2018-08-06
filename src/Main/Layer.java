package Main;

import java.util.ArrayList;

public class Layer {

	private ArrayList<Neuron> neurons;

	private double[][] weights;
		
	public Layer(int no_of_inputs, int no_of_neurons) {
		weights = Matrix.random(no_of_neurons,no_of_inputs);
		initNeurons(no_of_neurons);
	}
	
	private void initNeurons(int no_of_neurons) {
		neurons = new ArrayList<>();
		for(int i=0; i<=no_of_neurons; i++) {
			Neuron n = new Neuron();
			neurons.add(n);
		}
	}
	
	public double[][] computeOutput(double[][] input){
		double[][] product = Matrix.multiply(weights, input);
		for(int i = 0; i < product.length; i++) {
			for(int j = 0; j < product[0].length; j++) {
				product[i][j]= 1/(1+Math.exp(-product[i][j]));
			}
		}
		return product;
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
