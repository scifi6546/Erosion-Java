//
public class intHeight {
	double scale;
	groundarray HeightPoints;
	groundarray original;
	double gravity = 9.81;//acceleration due to gravity on earth = 9.81
	private double deltatime = .005;
	
	
public intHeight(groundarray in, double scaleIn){
	scale = scaleIn;
	gravity = 9.81;
	HeightPoints = new groundarray(in);
	original = new groundarray(in);
}
	public void erode(){
	groundarray stepone = new groundarray(HeightPoints);
	stepone = movewater(watervel(stepone,deltatime/2), deltatime/2);
	stepone = watervel(stepone, deltatime/2);
	HeightPoints = movewater(stepone, deltatime);
	
}
	public groundarray watervel(groundarray in, double deltaT){
		//System.out.println("watervel");
		groundarray temp = new groundarray(in);//temporary array in which changes are written
		//this part calculates the velocity
		for(int y = 0; y<in.points.length; y++){
			for(int x = 0; x<in.points[y].length;x++){
				double waterx0 = 0;
				double waterx1 = 0;
				double waterxn1 = 0;
				double rockx0 = 0;
				double rockx1 = 0;
				double rockxn1 = 0;
				double watery0 = 0;
				double watery1 = 0;
				double wateryn1 = 0;
				double rocky0 = 0;
				double rocky1 = 0;
				double rockyn1 = 0;
				for(int a = y-1; a<=y+1; a++){
					for(int b = x-1; b<=x+1; b++){
						
							double tempwater =95;
							double temprock =0;
							if(a>=0 && a< in.points.length && b>=0 && b< HeightPoints.points[y].length){
								tempwater = in.points[a][b].water;
								temprock = in.points[a][b].rock;
							}else{
								tempwater = original.points[y][x].water;
								temprock =  original.points[y][x].rock;
							}
							if(a-y==-1 && b-x == 0){
								wateryn1 = tempwater;
						rockyn1 = temprock;
							} if(a-y==0 &&  b-x==0){
								watery0 = tempwater;
								rocky0 = temprock;
								waterx0 = tempwater;
								rockx0 = temprock;
							} 
							if(a-y ==1 && b-x ==0){
								watery1 = tempwater;
								rocky1 = temprock;
							}
							if(b-x == -1 && a-y == 0){
								waterxn1 = tempwater;
								rockxn1 = temprock;
							} 
							if(b-x == 1 && a-y ==0){
								waterx1 = tempwater;
								rockx1 = temprock;
							}
						
					}
				}
				double deltaX = ( (waterxn1 +rockxn1) - (waterx1 +rockx1))/(2* scale) ;
				double deltaY = ((wateryn1 + rockyn1) - (watery1 + rocky1))/(2* scale);
				temp.points[y][x].u += (deltaX*gravity)*deltaT;
				temp.points[y][x].v += (deltaY*gravity)*deltaT;
			}
		}
		return temp;
	}
	
	public groundarray movewater(groundarray in, double deltaT){
		//System.out.println("movewater");
		groundarray temp = new groundarray(in);
		//this part calculates heights
		for(int y = 0; y<in.points.length; y++){
			for(int x = 0; x<in.points[y].length;x++){
				double waterx0 = 0;
				double rocksx0 = 0;
				double tempux0 = 0;
				
				double waterx1 = 0;
				double rocksx1 = 0;
				double tempux1 = 0;
				
				double waterxn1 = 0;
				double tempuxn1 = 0;
				double rocksxn1 = 0;
				
				double watery0 = 0;
				double tempvy0 = 0;
				double rocksy0 = 0;
				
				double watery1 = 0;
				double tempvy1 = 0;
				double rocksy1 = 0;
				
				double wateryn1 = 0;
				double tempvyn1 = 0;
				double rocksyn1 = 0;
				for(int a = y-1; a<=y+1; a++){
					for(int b = x-1; b<=x+1; b++){
						
							double tempwater =95;
							double temprock =0;
							double tempu = 0;
							double tempv = 0;
							if(a>=0 && a< in.points.length && b>=0 && b< in.points[y].length){
								tempwater = in.points[a][b].water;
								temprock = in.points[a][b].rock;
								tempu = in.points[a][b].u;
								tempv = in.points[a][b].v;
							}else {
								tempwater = original.points[y][x].water;
								temprock = original.points[y][x].rock;
								tempu = original.points[y][x].u;
								tempv = original.points[y][x].v;
							}
							Integer AminusY = a-y;
							Integer BminusX = b-x;
							if(a-y == -1 && b-x == 0){
								wateryn1 = tempwater;
								rocksyn1 = temprock;
								tempvyn1 = tempv;
							} if(a-y ==0 &&  b-x == 0){
								watery0 = tempwater;
								rocksy0 = temprock;
								waterx0 = tempwater;
								rocksx0 = temprock;
								tempux0 = tempu;
								tempvy0 = tempv;
							} 
							if(a-y == 1 && b-x==0){
								watery1 = tempwater;
								rocksy1 = temprock;
								tempvy1 = tempv;
							}
							if(b-x == -1 && a-y == 0){
								waterxn1 = tempwater;
								rocksxn1 = temprock;
								tempuxn1 = tempu;
							} if(b-x==1 && a-y == 0){  
								waterx1 = tempwater;
								rocksx1 = temprock;
								tempux1 = tempu;
							}
						
					}
				}
				
				double deltaXH = (waterxn1-waterx1)/(2*scale);
				double deltaYH = (wateryn1-watery1)/(2*scale);
				double deltaX = ((waterxn1 * tempuxn1) - (waterx1 * tempux1))/ (2*scale);
				double deltaY = ((wateryn1 * tempvyn1) - (watery1 * tempvy1))/ (2*scale);
				double deltaU = (tempuxn1 - tempux1)/(2*scale);
				double deltaV = (tempvyn1 - tempvy1)/(2*scale);
				temp.points[y][x].water+= (deltaX + deltaY)*deltaT;
				
		}
			}
		return temp;
	}
	public void rock(){
		//System.out.println("watervel");
		groundarray temp = new groundarray(HeightPoints);//temporary array in which changes are written
		//this part calculates the velocity
		for(int y = 0; y<HeightPoints.points.length; y++){
			for(int x = 0; x<HeightPoints.points[y].length;x++){
				double disolvedV = .1*HeightPoints.points[y][x].deltaV;
				temp.points[y][x].rock -= disolvedV;
				temp.points[y][x].disolved += disolvedV;
			}
		}
		HeightPoints = new groundarray(temp);
	}
}
