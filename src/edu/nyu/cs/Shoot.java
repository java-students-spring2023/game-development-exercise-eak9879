package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * A representation of a arrow. 
 * 
 * @author Emily Kim
 * @version 0.1
 */
public class Shoot extends Move {
    
    private Game app; // will hold a reference to the main Game object

	private final static String ARROW_IMAGE_PATH = "images/arrow.png"; // image  file
	private PImage img; // will hold the image to use for this arrow
	private int speedY = -20; // speed of the arrow going upwards
	
    /**
	 * Constructor for an Arrow object.  
	 * @param x The x position at which to draw this arrow.
	 * @param y The y position at which to draw this arrow.
	 * @param app A reference to the object that controls the flow of the game.  This app object also inherits from 
     *            PApplet, and so contains lots of useful methods that Processing codes into the PApplet class.
	 */
	public Shoot(int x, int y, Game app) {
        super(x, y);
		this.app = app; //keep a reference to the PApplet-descendent object to handle all  Processing-specific functions and variables
		
		//load the image and store in PImage variable
		this.img = app.loadImage(Shoot.ARROW_IMAGE_PATH);
		
		//add this Arrow object to the  PApplet-decendent object's list of arrows
		this.app.getArrows().add(this);

	}
    
    /**
	 * Get the width of this arrow, based on  the width of its image.
	 */
	public int getWidth() {
		return 18; //return the PImage object's width property
	}
	
	/**
	 * Get the height of this arrow, based on  the width of its image.
	 */
	public int getHeight() {
		return 50; //return the PImage object's height property
	}
	
	/**
	 * Slide the arrow further up the screen.
	 */
	public void move() {
		int newY = getY() + speedY; // calculate move by whichever amount is specified in speedY variable.
		
		//check bounds
		boolean outOfBoundsToTheTop = newY < 0 +  this.getHeight(); 

		//if out of bounds...
		if (outOfBoundsToTheTop) {
			 //remove it from the array of  arrows
			this.app.getArrows().remove(this); 
		}
		
		//make update to position
		setY(newY);
		
	}
	
	/**
	 * Kill this arrow.
	 * Simply removes this arrow from the PApplet's list of arrows;
	 */
	public void kill() {
		this.app.getArrows().remove(this); //remove this  arrow from the list of aliens
	}

	/**
	 * Draws this arrow to the PApplet screen. 
	 */
	public void draw() {
		//draw the image using PApplet's image  method
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
		this.app.image(this.img, getX(), getY(), 18, 50);
	}
	
	/**
	 * Static method to check collision between any arrow and any plate
	 */
	public static boolean isCollision(Shoot arrow, Plate plate) {
		boolean collision = false; //flag to indicate whether a collision has been detected
		
		//check whether arrow is within the box representing the plate
		if (arrow.getX() + 9 >= plate.getX() - 24 && arrow.getX() - 9 <= plate.getX() + 24) {
			if (arrow.getY() + 25 >= plate.getY() - 15 && arrow.getY() - 25 <= plate.getY() + 15) {
				collision = true;
			}
		}
		
		return collision;
	}

}
