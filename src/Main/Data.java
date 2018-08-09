package Main;

import java.util.ArrayList;

public class Data {
	private ArrayList<Images> data;
	
	public Data() {
		data =  new ArrayList<>();
	}
	
	public ArrayList<Images> getData() {
		return data;
	}

	public void addImage(Images img) {
		data.add(img);
	}
	
	
}
