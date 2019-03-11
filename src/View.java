import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class View extends JPanel{
	
	static int frameWidth = 500;
	static int frameHeight = 300;
	static int imageWidth = 165;
	static int imageHeight = 165;
	int imageX;
	int imageY;
	Enum imageDirect;
	int imageNumber;
	BufferedImage[][] pics;
	int picNum = 0;
	final int frameCount = 10;
	
	public View() {
		//Creates the frame
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	loadImages();

	}
	
	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	
	//Draws onto the frame
	public void paint(Graphics g) {
	    picNum = (picNum + 1) % frameCount;
    	g.drawImage(pics[imageNumber][picNum], imageX, imageY, Color.gray, this);
	}
	
	//Updates the direction and coordinates of the image and updates the image itself
	//Then redraws
	public void update(int x, int y, Enum direction) {
		imageX = x;
		imageY = y;
		imageDirect = direction;
		updateImageNumber();
		setBackground(Color.gray);
		repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//Updates which image is chosen if need be
	public void updateImageNumber() {
		if(imageDirect == Direction.NORTH) {
			imageNumber = 0;
		}
		else if(imageDirect == Direction.SOUTH) {
			imageNumber = 1;
		}
		else if(imageDirect == Direction.EAST) {
			imageNumber = 2;
		}
		else if(imageDirect == Direction.WEST) {
			imageNumber =  3;
		}
		else if(imageDirect == Direction.NORTHEAST) {
			imageNumber = 4;
		}
		else if(imageDirect == Direction.NORTHWEST) {
			imageNumber = 5;
		}
		else if(imageDirect == Direction.SOUTHEAST) {
			imageNumber = 6;
		}
		else {
			imageNumber = 7;
		}
	}
	
	//Stores all images and frames of animation in 2D array
	public void loadImages() {
		pics = new BufferedImage[8][10];
		for(int image = 0; image < 8; image++) {
			BufferedImage img = createImage(image);
			for(int frame = 0; frame < frameCount; frame++)
				pics[image][frame] = img.getSubimage(imageWidth*frame, 0, imageWidth, imageHeight);
		}
    }  
	
    //Read image from file and return
    private BufferedImage createImage(int image){
    	BufferedImage bufferedImage;
    	String imgName = "";
    	switch(image) {
    	case 0: imgName = "north";
    			imageNumber = 0;
    			break;
    	case 1: imgName = "south";
    			imageNumber = 1;
    			break;
    	case 2: imgName = "east";
    			imageNumber = 2;
    			break;
    	case 3: imgName = "west";
    			imageNumber = 3;
				break;
    	case 4: imgName = "northeast";
    			imageNumber = 4;
				break;
    	case 5: imgName = "northwest";
    			imageNumber = 5;
				break;
    	case 6: imgName = "southeast";
    			imageNumber = 6;
				break;
    	case 7: imgName = "southwest";
    			imageNumber = 7;
				break;
    	}
    	try {
    		bufferedImage = ImageIO.read(new File("src/images/orc/orc_forward_" + imgName + ".png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}