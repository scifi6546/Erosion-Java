//
public class groundpoint {
public double rock;
public double water;
public double precipitation = .5;

public groundpoint(){
	rock = 0;
	water = 0;
	
}
public groundpoint(groundpoint point){
	rock = point.rock;
	water = point.water;
	precipitation = point.precipitation;
}
}