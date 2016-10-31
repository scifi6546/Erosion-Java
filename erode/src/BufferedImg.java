import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//

public class BufferedImg {
BufferedImage rockimg;
BufferedImage water;
BufferedImage differenceIMG;
BufferedImage precipitation;
File root;
public BufferedImg(){
	root = new File(System.getProperty("user.home"));
	root = new File(root,"Desktop/erosion");
}
public void loadimg() throws IOException
{
	rockimg = ImageIO.read(new File(root + "/" + "rock.png"));
	water = ImageIO.read(new File(root + "/" + "water.png"));
	precipitation = ImageIO.read(new File(root + "/" + "precipitation.png"));
}
public groundarray makeintHeight(){
	int tall = rockimg.getHeight();
	int width = rockimg.getWidth();
	groundarray height = new groundarray(tall, width);
	for(int y = 0; y< rockimg.getHeight(); y++){
		//System.out.println("");
		for(int x = 0; x<rockimg.getWidth();x++){
			Color imgcolor = new Color(rockimg.getRGB(x,y));
			int col = imgcolor.getRed();
			//System.out.print(imgcolor.getRed() + " ");
			height.points[y][x].rock = col;
		}
	}
	for(int y = 0; y< water.getHeight(); y++){
		//System.out.println("");
		for(int x = 0; x<water.getWidth();x++){
			Color imgcolor = new Color(water.getRGB(x,y));
			int col = imgcolor.getRed();
			//System.out.print(imgcolor.getRed() + " ");
			height.points[y][x].water = col;
		}
	}
	for(int y = 0; y< precipitation.getHeight(); y++){
		//System.out.println("");
		for(int x = 0; x<precipitation.getWidth();x++){
			Color imgcolor = new Color(precipitation.getRGB(x,y));
			int col = imgcolor.getRed();
			//System.out.print(imgcolor.getRed() + " ");
			height.points[y][x].precipitation = col;
		}
	}
	return height;
}
public void makeBuffered(intHeight height){
	rockimg = new BufferedImage(height.HeightPoints.points[0].length,height.HeightPoints.points.length,1);
	water = new BufferedImage(height.HeightPoints.points[0].length,height.HeightPoints.points.length,1);
	differenceIMG = new BufferedImage(height.HeightPoints.points[0].length,height.HeightPoints.points.length,1);
	for(int y = 0; y < height.HeightPoints.points.length; y++){
		for(int x = 0; x < height.HeightPoints.points[y].length; x++){
			int rockheight = (int) height.HeightPoints.points[y][x].rock; 
			if(rockheight> 255)
				rockheight = 255;
			else if(rockheight < 0)
				rockheight = 0;
			Color tempcol = new Color(rockheight,rockheight,rockheight);
			rockimg.setRGB(x, y, tempcol.getRGB());
			int waterheight = (int) height.HeightPoints.points[y][x].water;
			if(waterheight > 255)
				waterheight = 255;
			else if(waterheight < 0)
				waterheight = 0;
			tempcol = new Color(waterheight,waterheight,waterheight);
			water.setRGB(x, y, tempcol.getRGB());
		}
	}
}
public void export(String inbetween) throws IOException{
	File RockOutput = new File(root + "/rock/" + "Rock" + inbetween + ".png");
	ImageIO.write(rockimg,"png", RockOutput);
	File WaterOutput = new File(root + "/water/" + "Water" + inbetween + ".png");
	ImageIO.write(water,"png", WaterOutput);
	//File DifferenceOutput = new File("Difference" + inbetween + ".png");
	//ImageIO.write(differenceIMG,"png", DifferenceOutput);
}

}