package edu.nyu.cs;

import processing.core.PImage;

/**
 * A representation of a bow. 
 * 
 * @author Emily Kim
 * @version 0.1
 */
public class Bow {
    
    private Game app; // will hold a reference to the main Game object

    private final static String BOW_IMAGE_PATH = "images/bow.png"; // will hold a path to the bow image
    private PImage img; // bow image
    private int x, y; // position of the bow
    private int w, h; // size of the bow

    /**
	 * Get the width of this bow, based on the width of its image.
	 */
	public int getWidth() {
		// the PImage object has a width property
		return this.img.width;
	}
	
	/**
	 * Get the height of this bow, based on the width of its image.
	 */
	public int getHeight() {
		// the PImage object has a height property
		return this.img.height;
	}

    /**
     * This method sets the x position of the bow.
     * @param newX
     */
    public void setX(int newX) {
        if (newX <= 1200 && newX >= 0) {
            this.x = newX; 
        }
    }
	
    /**
	 * Constructor for a bow. 
	 * @param app A reference to the object that controls the flow of the game.  This object also inherits from PApplet, and so contains lots of useful methods that Processing codes into the PApplet class.
	 */
	public Bow(Game app) {
		//set up initial properties for this  alien
		this.app = app; //keep a reference to the PApplet class to handle all Processing-specific functions and variables

		//position it on screen
		this.x = (int) (this.app.width / 2); //x  position centered on screen
		this.y = this.app.height - Game.APP_MARGIN; // y position close to bottom of screen
		
		//load the image and store in PImage  variable
		this.img = this.app.loadImage(Bow.BOW_IMAGE_PATH);

        w = 100; // width of the bow
        h = 100; // height of the bow
	}

    /**
     * This method draws the bow to the PApplet screen.
     */
    public void draw() {
        this.app.image(this.img, this.x, this.y, w, h);
    }

    /**
	 * Shoots an arrow out of the bow.
	 */
	public void shoot() {
		//create a new arrow object positioned at the center of this bow
		int x = (int) (this.x ); //the center x position of this bow
		int y = this.y; // the top of the bow

		//create arrow object
		Shoot arrow = new Shoot(x, y, this.app);
		
	}
}
