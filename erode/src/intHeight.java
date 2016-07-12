//
public class intHeight {
	double scale;
	groundarray HeightPoints;
	double gravity = 9.81;//acceleration due to gravity on earth = 9.81
	groundarray temp;
public intHeight(groundarray in, double scaleIn){
	scale = scaleIn;
	gravity = 9.81;
	HeightPoints = new groundarray(in);
}
	public void erode(){
	watervel();
}
	public void watervel(){
		//System.out.println("watervel");
		temp = new groundarray(HeightPoints);//temporary array in which changes are written
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
				temp.points[y][x].u+= -deltaX*9.81;
				temp.points[y][x].v += -deltaY*9.81;
			}
		}
		HeightPoints = new groundarray(temp);
		movewater();
	}
	
	public void movewater(){
		//System.out.println("movewater");
		temp = new groundarray(HeightPoints);
		//this part calculates heights
		for(int y = 0; y<HeightPoints.points.length; y++){
			for(int x = 0; x<HeightPoints.points[y].length;x++){
				double waterx0 = 0;
				double rocksx0 = 0;
				double tempux0 = 0;
				double waterx1 = 0;
				double tempux1 = 0;
				double rocksx1 = 0;
				double watery0 = 0;
				double tempvy0 = 0;
				double rocksy0 = 0;
				double watery1 = 0;
				double tempvy1 = 0;
				double rocksy1 = 0;
				for(int a = y-1; a<y+2; a++){
					for(int b = x-1; b<x+2; b++){
						if((a-y)!= (b-x) && -(a-y)!= (b-x)){
							double tempwater =0;
							double tempu = 0;
							double tempv = 0;
							double temprock = 0;
							if(a<0 || a>= HeightPoints.points.length || b<0 || b>= HeightPoints.points[y].length){
							tempwater = 0;
							tempu = 0;
							tempv = 0;
							temprock = 0;
							}else{ 
								tempwater = HeightPoints.points[y][x].water;
								tempu = HeightPoints.points[y][x].u;
								tempv = HeightPoints.points[y][x].v;
								temprock = HeightPoints.points[y][x].rock;
							}
							Integer AminusY = a-y;
							Integer BminusX = b-x;
							if(AminusY.equals(-1)){
								watery0 = tempwater;
								tempux0 = tempu;
								rocksx0 = temprock;
							}else if(AminusY.equals(1)){
								watery1 = tempwater;
								tempux1 = tempu;
								rocksx1 = temprock;
							} if(BminusX.equals(-1)){
								waterx0 = tempwater;
								tempvy0 = tempv;
								rocksy0 = temprock;
							}else if(BminusX.equals(1)){
								waterx1 = tempwater;
								tempvy1 = tempv;
								rocksy1 = temprock;
							}
						}
					}
				}
				double deltaX = ((waterx0 + rocksx0) *tempux0 - (waterx1 + rocksx1)*tempux1)/2;
				double deltaY = ((watery0 + rocksy0)*tempvy0 - (watery1 + rocksy1)*tempvy1)/2;
				temp.points[y][x].water+= -deltaX - deltaY;
			}
		}
		HeightPoints = new groundarray(temp);
	}
}
