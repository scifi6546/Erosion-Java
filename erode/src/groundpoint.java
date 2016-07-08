public class groundpoint {
public double rock;
public double water;
public double precipitation = .5;
public double rockhardness = .3; 
static double waterhardness = .9;
static double evaporation = .00;
public double watererosion = .1;
public Vector watermovement;
double rockdifference;
double disolved = 0;
public double u;
public double v;

public groundpoint(){
	rock = 0;
	water = 0;
	watermovement = new Vector();
	rockdifference = 0;
	u = 0;
	v = 0;
}
public groundpoint(groundpoint point){
	rock = point.rock;
	water = point.water;
	precipitation = point.precipitation;
	rockhardness = point.rockhardness;
	waterhardness = point.waterhardness;
	evaporation = point.evaporation;
	watererosion = point.watererosion;
	watermovement = new Vector(point.watermovement);
	u = point.u;
	v = point.v;
}
}