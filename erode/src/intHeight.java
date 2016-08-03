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
				double waterxn1 = 0;
				double rockx0 = 0;
				double rockxn1 = 0;
				double watery0 = 0;
				double wateryn1 = 0;
				double rocky0 = 0;
				double rockyn1 = 0;
				for(int a = y-1; a<=y; a++){
					for(int b = x-1; b<=x; b++){
						
							double tempwater =0;
							double temprock =0;
							if(a>=0 && a< HeightPoints.points.length && b>=0 && b< HeightPoints.points[y].length){
								tempwater = HeightPoints.points[a][b].water;
								temprock = HeightPoints.points[a][b].rock;
							}
							Integer AminusY = a-y;
							Integer BminusX = b-x;
							if(a-y==-1 && b-x == 0){
								wateryn1 = tempwater;
								rockyn1 = temprock;
							}else if(AminusY.equals(0) &&  BminusX.equals(0)){
								watery0 = tempwater;
								rocky0 = temprock;
								waterx0 = tempwater;
								rockx0 = temprock;
							} if(BminusX.equals(-1) && AminusY.equals(0)){
								waterxn1 = tempwater;
								rockxn1 = temprock;
							}
						
					}
				}
				double deltaX = ( (waterxn1 +rockxn1) - (waterx0 +rockx0))/(scale) ;
				double deltaY = ((wateryn1 + rockyn1) - (watery0 + rocky0))/(scale);
				temp.points[y][x].u += deltaX*9.81;
				temp.points[y][x].v += deltaY*9.81;
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
				double waterxn1 = 0;
				double tempuxn1 = 0;
				double rocksxn1 = 0;
				double watery0 = 0;
				double tempvy0 = 0;
				double rocksy0 = 0;
				double wateryn1 = 0;
				double tempvyn1 = 0;
				double rocksyn1 = 0;
				for(int a = y-1; a<y+1; a++){
					for(int b = x-1; b<x+1; b++){
						
							double tempwater =0;
							double temprock =0;
							double tempu = 0;
							double tempv = 0;
							if(a>=0 && a< HeightPoints.points.length && b>=0 && b< HeightPoints.points[y].length){
								tempwater = HeightPoints.points[a][b].water;
								temprock = HeightPoints.points[a][b].rock;
								tempu = HeightPoints.points[a][b].u;
								tempv = HeightPoints.points[a][b].v;
							}
							Integer AminusY = a-y;
							Integer BminusX = b-x;
							if(a-y == -1 && b-x == 0){
								wateryn1 = tempwater;
								rocksyn1 = temprock;
								tempvyn1 = tempv;
							}else if(a-y ==0 &&  b-x == 0){
								watery0 = tempwater;
								rocksy0 = temprock;
								waterx0 = tempwater;
								rocksx0 = temprock;
								tempux0 = tempu;
								tempvy0 = tempv;
							} if(b-x == -1 && a-y == 0){
								waterxn1 = tempwater;
								rocksxn1 = temprock;
								tempuxn1 = tempu;
							}
						
					}
				}
				
				double deltaX = ((waterxn1*tempuxn1) -(waterx0 *tempux0))/(scale);
				double deltaY = ((wateryn1*tempvyn1) - (watery0*tempvy0))/(scale);
				temp.points[y][x].water+= deltaX + deltaY;
		}
			}
		
		HeightPoints = new groundarray(temp);
	}
}
