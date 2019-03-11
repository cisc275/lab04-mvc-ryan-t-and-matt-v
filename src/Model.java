
/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {
	int xpos = 0;
	int ypos = 0;
	final int xInc=8;
	final int yInc=2;
	int imgHeight;
	int imgWidth;
	int frameHeight;
	int frameWidth;
	Enum direction;
	
	public int getX(){
		return xpos;
	}
	public int getY(){
		return ypos;
	}
	public Enum getDirect(){
		return direction;
	}
	//Checks to see if model has reached edge of screen and redirects appropriately.
	public void changeDirec(){
		if(xpos>=frameWidth-imgWidth){
			if(direction==Direction.NORTHEAST){
				direction=Direction.NORTHWEST;
			}
			else if(direction==Direction.SOUTHEAST){
				direction=Direction.SOUTHWEST;
			}
			else{
				direction=Direction.WEST;
			}
		}
		else if(ypos>=frameHeight-imgHeight){
			if(direction==Direction.SOUTH){
				direction=Direction.NORTH;
			}
			else if(direction==Direction.SOUTHWEST){
				direction=Direction.NORTHWEST;
			}
			else{
				direction=Direction.NORTHEAST;
			}
		}
		else if(xpos<=0){
			if(direction==Direction.WEST){
				direction=Direction.EAST;
			}
			else if(direction==Direction.NORTHWEST){
				direction=Direction.NORTHEAST;
			}
			else{
				direction=Direction.SOUTHEAST;
			}
		}
		else if(ypos<=0){
			if(direction==Direction.NORTH){
				direction=Direction.SOUTH;
			}
			else if(direction==Direction.NORTHWEST){
				direction=Direction.SOUTHWEST;
			}
			else{
				direction=Direction.SOUTHEAST;
			}
		}
	}
	//updates location of the model based on direction and preset incremental movement 
	//amounts
	public void changeLoc(){	
		if(direction == Direction.NORTH){
			ypos-=yInc;
		}
		else if(direction==Direction.NORTHEAST){
			ypos-=yInc;
			xpos+=xInc;
		}
		else if(direction==Direction.EAST){
			xpos+=xInc;
		}
		else if(direction==Direction.SOUTHEAST){
			ypos+=yInc;
			xpos+=xInc;
		}
		else if(direction == Direction.SOUTH){
			ypos+=yInc;
		}
		else if(direction == Direction.SOUTHWEST){
			ypos+=yInc;
			xpos-=xInc;
		}
		else if(direction ==Direction.WEST){
			xpos-=xInc;
		}
		else if(direction ==Direction.NORTHWEST) {
			ypos-=yInc;
			xpos-=xInc;
		}
	}
	//Updates Model location and Direction
	public void updateLocationAndDirection(){
		changeDirec();
		changeLoc();
	}
	//Model Constructor
	public Model(int x, int y, int w, int z){
		frameWidth=x;
		frameHeight=y;
		imgWidth=w;
		imgHeight=z;
	}
}
