package Main;

public class Neuron {
	private double[] inputs;
	private float[] weights;
	private float output;
	
	public Neuron(double[] inputs, float[] weights) {
		super();
		this.inputs = inputs;
		this.weights = weights;
	}
	public double[] getInputs() {
		return inputs;
	}
	public void setInputs(double[] inputs) {
		this.inputs = inputs;
	}
	public float[] getWeights() {
		return weights;
	}
	public void setWeights(float[] weights) {
		this.weights = weights;
	}
	public float getOutput() {
		return output;
	}
	public void setOutput(float output) {
		this.output = output;
	}
	
	
}
