package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	private int[] image;
	private int cls;
	
	public Images(int[] image, int cls) {
		this.image = image;
		this.cls = cls;
	}
	public int[] getImage() {
		return image;
	}
	public void setImage(int[] image) {
		this.image = image;
	}
	public int getCls() {
		return cls;
	}
	public void setCls(int cls) {
		this.cls = cls;
	}
	
	public void writeImg(String fileName) throws IOException{
		BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB );
		File f = new File(fileName+".png");
		System.out.println(getCls());
		for(int x = 0; x < 28; x++){
		                for(int y = 0; y < 28; y++){
//		                		int r = reds[y+x*32] & 0xFF;
//		                		//System.out.println(r);
//		                        int g = greens[y+x*32] & 0xFF; 
//		                        int b = blues[y+x*32] & 0xFF;
//		                        int col = (r << 16) | (g << 8) | b;
		                    int col = image[y+x*28];    
		                	img.setRGB(y, x, col);
		                }
		            }
		            System.out.println("Done");
		            ImageIO.write(img, "PNG", f);
		            
	}
	
}
