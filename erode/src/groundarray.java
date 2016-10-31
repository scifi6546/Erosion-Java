public class groundarray {
public groundpoint[][] points;
public velocitypoint[][] velocity;
public groundarray(int height, int width){
	points = new groundpoint[height][width];
	for(int y = 0; y< points.length; y++){
		for(int x = 0; x < points[y].length; x++){
			points[y][x] = new groundpoint();
		}
	}
	velocity = new velocitypoint[height+1][width+1];
	for(int y = 0; y< points.length+1; y++){
		for(int x = 0; x < points[0].length+1; x++){
			velocity[y][x] = new velocitypoint();
		}
	}
}
public groundarray(groundarray temp){
	points = new groundpoint[temp.points.length][temp.points[0].length];
	for(int y = 0; y<temp.points.length; y++){
		for(int x = 0; x< temp.points[y].length; x++){
			points[y][x] = new groundpoint(temp.points[y][x]);
		}
	}
	velocity = new velocitypoint[temp.points.length + 1][temp.points[0].length + 1];
	for(int y = 0; y<temp.velocity.length; y++){
		for(int x = 0; x< temp.velocity[y].length; x++){
			velocity[y][x] = new velocitypoint(temp.velocity[y][x]);
			
		}
	}
}
public double getU(int y, int x){
	return velocity[y][x].u;
}
public double getV(int y, int x){
	return velocity[y][x].v;
}
public double getwaterH(int y, int x){
	return points[y][x].water;
}
public double getrockH(int y, int x){
	return points[y][x].rock;
}
public void setU(double in, int y, int x){
	in = velocity[y][x].u;
}
public void setV(double in, int y, int x){
	in = velocity[y][x].v;
}
public void setwaterH(double in, int y, int x){
	in = points[y][x].water;
}
public void setrockH(double in, int y, int x){
	in = points[y][x].rock;
}
}