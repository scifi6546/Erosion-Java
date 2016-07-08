public class groundarray {
public groundpoint[][] points;
public groundarray(int height, int width){
	points = new groundpoint[height][width];
	for(int y = 0; y< points.length; y++){
		for(int x = 0; x < points[y].length; x++){
			points[y][x] = new groundpoint();
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
}
}