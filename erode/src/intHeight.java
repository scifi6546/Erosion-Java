import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//
public class intHeight {
	double scale;
	public groundarray HeightPoints;
	public groundarray original;
	double gravity = 9.81;//acceleration due to gravity on earth = 9.81
	private double deltatime = .0001;


	public intHeight(groundarray in, double scaleIn){
		scale = scaleIn;
		gravity = 9.81;
		HeightPoints = new groundarray(in);
		original = new groundarray(in);
	}
	public void erode(){
		groundarray stepone = new groundarray(HeightPoints);
		HeightPoints = watervel(HeightPoints,deltatime);
		HeightPoints = movewater(HeightPoints,deltatime);
		System.out.println(toString()); //this prints out the array in terminal

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
							tempwater = in.getwaterH(a,b);
							temprock = in.getrockH(a,b);
						}else{
							tempwater = original.getwaterH(y,x);
							temprock =  original.getwaterH(y,x);
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
				double deltaX = ( (waterxn1 +rockxn1) - (waterx0 +rockx0))/(scale) ;
				double deltaY = ((wateryn1 + rockyn1) - (watery0 + rocky0))/(scale);
				temp.velocity[y][x].u += (deltaX*gravity)*deltaT;
				temp.velocity[y][x].v += (deltaY*gravity)*deltaT;
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
							tempwater = in.getwaterH(a,b);
							temprock = in.getrockH(a,b);
							tempu = in.getU(a,b);
							tempv = in.getV(a,b);
						}else {
							tempwater = in.getwaterH(y,x);
							temprock = in.getrockH(y,x);
							if(a>=0 && b>=0){
							tempu = in.getU(a,b);
							tempv = in.getV(a,b);
							}else{
								tempu = in.getU(y, x);
								tempv = in.getV(y, x);
							}
							
						}
						
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
				double tempWATERxn1 = (waterxn1 + waterx0)/2;
				double tempWATERyn1 = (wateryn1 + watery0)/2;
				double tempWATERx1 = (waterx0 + waterx1)/2;
				double tempWATERy1 = (watery0 + watery1)/2;
				double deltaX = ((tempux0*tempWATERxn1)-(tempux1 * tempWATERx1))/scale;
				double deltaY = ((tempvy0*tempWATERyn1)-(tempvy1*tempWATERy1))/scale;
				temp.points[y][x].water+= (deltaX + deltaY)*deltaT;

			}
		}
		return temp;
	}
	public String toString(){
		String out = new String();
		out +="height\n";
		for(int y = 0;y<HeightPoints.points.length;y++){
			for(int x = 0; x<HeightPoints.points[y].length; x++){
				out+=HeightPoints.getwaterH(y, x) + ", ";
			}
			out += "\n";
		}
		out+= "\n\nV";
		for(int y = 0;y<HeightPoints.points.length+1;y++){
			for(int x = 0; x<HeightPoints.points[0].length+1; x++){
				out+=HeightPoints.getU(y, x)+ ", ";
			}
			out += "\n";
		}
		out+= "\n\nV";
		for(int y = 0;y<HeightPoints.points.length+1;y++){
			for(int x = 0; x<HeightPoints.points[0].length+1; x++){
				out+=HeightPoints.getV(y, x)+ ", ";
			}
			out += "\n";
		}
		return out;
	}
	public void print (File directory, String Name) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(directory + Name + ".txt");
	}
}
