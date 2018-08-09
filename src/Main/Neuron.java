package Main;

public class Neuron {
	private double[] weights;
	private float output;
	
	public Neuron(double[] weights) {
		this.weights = weights;
	}
	
	public double[] getWeights() {
		return weights;
	}
	public void setWeights(double[] weights) {
		this.weights = weights;
	}
	public float getOutput() {
		return output;
	}
	public void setOutput(float output) {
		this.output = output;
	}
	
	
}
