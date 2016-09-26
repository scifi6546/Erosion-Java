import java.io.IOException;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.*;
//
public class Run {
private static BufferedImg img;
	/**
	 * @param args
	 * @throws IOException 
	 */
public static JFrame frame;
public static int WIDTH = 800;
public static int HEIGHT = 600;
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		// TODO Auto-generated method stub
/*		System.out.println("erosion repeats?");
		int repeats = reader.nextInt();
		System.out.println("rock erosion strength?");
		double rockstrength = reader.nextDouble();
		System.out.println("water erosion strength?");
		double waterstrength = reader.nextDouble();
		System.out.println("scale?");
		double scale = reader.nextDouble();
		System.out.println("precipitation?");
		double precipitation = reader.nextDouble();
		System.out.println("evaporation?");
		double evaporation = reader.nexble();
		JFrame output = new JFrame("output");
		*/
		
		int repeats = 20000;
		double rockstrength = .25;
		double waterstrength = .1;
		double scale = 10; 
		double precipitation = .03;
		double evaporation = .00;
		JFrame output = new JFrame("output");
		
		output.setSize(400,400);
        frame = new JFrame("Input");
        frame.setSize(400, 400);
		BufferedImg buffered = new BufferedImg();
		buffered.loadimg();
		frame.add(new JLabel(new ImageIcon(buffered.water)));
		frame.setVisible(true);
		output.setVisible(true);
		JLabel label = new JLabel(new ImageIcon(buffered.water));
		intHeight height = new intHeight(buffered.makeintHeight(),scale);
			for(int i = 0; i<repeats; i++){
				height.erode();
			
					
				if(i%1 == 0){
				buffered.makeBuffered(height);
				buffered.export(i + "");
				output.remove(label);
				label = new JLabel(new ImageIcon(buffered.water));
				output.add(label);
				output.setVisible(true);
				//height.write();
				}
				System.out.println(i);
			
			}
		buffered.makeBuffered(height);
		buffered.export("final");
		output.add(new JLabel(new ImageIcon(buffered.water)));
		
		output.setVisible(true);
		
	}
}