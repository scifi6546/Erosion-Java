
public class intHeight {
	double scale;
	groundarray HeightPoints;
	double gravity = 9.81;//acceleration due to gravity on earth = 9.81
public intHeight(groundarray in, double scaleIn){
	scale = scaleIn;
	HeightPoints = new groundarray(in);
}
	public void erode(){
	water();
}
	public void water(){
		groundarray temp = new groundarray(HeightPoints);//temporary array in which changes are written
		//this part calculates the velocity
		for(int y = 0; y<HeightPoints.points.length; y++){
			for(int x = 0; x<HeightPoints.points[y].length;x++){
				double waterx0 = 0;
				double waterx1 = 0;
				double rockx0 = 0;
				double rockx1 = 0;
				double watery0 = 0;
				double watery1 = 0;
				double rocky0 = 0;
				double rocky1 = 0;
				for(int a = y-1; a<y+2; a++){
					for(int b = x-1; b<x+2; b++){
						if((a-y)!= (b-x) && -(a-y)!= (b-x)){
							double tempwater =0;
							double temprock =0;
							if(a<0 || a>= HeightPoints.points.length || b<0 || b>= HeightPoints.points[y].length){
							tempwater = 0;
							temprock = 0;
							}else{
								tempwater = HeightPoints.points[y][x].water;
								temprock = HeightPoints.points[y][x].rock;
							}
							Integer AminusY = a-y;
							Integer BminusX = b-x;
							if(AminusY.equals(-1)){
								watery0 = tempwater;
								rocky0 = temprock;
							}else if(AminusY.equals(1)){
								watery1 = tempwater;
								rocky1 = temprock;
							} if(BminusX.equals(-1)){
								waterx0 = tempwater;
								rockx0 = temprock;
							}else if(BminusX.equals(1)){
								waterx1 = tempwater;
								rockx1 = temprock;
							}
						}
					}
				}
				double deltaX = (waterx0 +rockx0 - waterx1 +rockx1)/2;
				double deltaY = (watery0 + rocky0 - watery1+ rocky1)/2;
				temp.points[y][x].u+= -deltaX*gravity;
				temp.points[y][x].v += -deltaY*gravity;
			}
		}
		HeightPoints = new groundarray(temp);
		//this part calculates heights
		for(int y = 0; y<HeightPoints.points.length; y++){
			for(int x = 0; x<HeightPoints.points[y].length;x++){
				double waterx0 = 0;
				double tempux0 = 0;
				double waterx1 = 0;
				double tempux1 = 0;
				double watery0 = 0;
				double tempvy0 = 0;
				double watery1 = 0;
				double tempvy1 = 0;
				for(int a = y-1; a<y+2; a++){
					for(int b = x-1; b<x+2; b++){
						if((a-y)!= (b-x) && -(a-y)!= (b-x)){
							double tempwater =0;
							double tempu = 0;
							double tempv = 0;
							if(a<0 || a>= HeightPoints.points.length || b<0 || b>= HeightPoints.points[y].length){
							tempwater = 0;
							tempu = 0;
							tempv = 0;
							}else{ 
								tempwater = HeightPoints.points[y][x].water;
								tempu = HeightPoints.points[y][x].u;
								tempv = HeightPoints.points[y][x].v;
							}
							Integer AminusY = a-y;
							Integer BminusX = b-x;
							if(AminusY.equals(-1)){
								watery0 = tempwater;
								tempux0 = tempu;
							}else if(AminusY.equals(1)){
								watery1 = tempwater;
								tempux1 = tempu;
							} if(BminusX.equals(-1)){
								waterx0 = tempwater;
								tempvy0 = tempv;
							}else if(BminusX.equals(1)){
								waterx1 = tempwater;
								tempvy1 = tempv;
							}
						}
					}
				}
				double deltaX = (waterx0*tempux0 - waterx1*tempux1)/2;
				double deltaY = (watery0*tempvy0 - watery1*tempvy1)/2;
				temp.points[y][x].water+= -deltaX - deltaY;
			}
		}
		HeightPoints = new groundarray(temp);
	}
}