
public class Vector {
public double x;
public double y;
public Vector(){
	x = 0; 
	y = 0;
}
public Vector(double xi, double yi){
	x= xi;
	y = yi;
}
public Vector(Vector in){
	x = in.x;
	y = in.y;
}
}
