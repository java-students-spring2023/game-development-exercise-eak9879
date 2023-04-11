package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * A representation of an plate. 
 * 
 * @author Emily Kim
 * @version 0.1
 */
public class Plate extends Move {

    private Game app; // will hold a reference to the main Game object
    private PImage img; // will hold a reference to an image of a plate
    private int speedX; // will hold the speed in the x direction of the plate object

    /**
     * Overloaded Constructor to create a Plate object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param imgFilePath a reference to the file path of the plate image
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Plate(Game app, String imgFilePath, int x, int y) {
        super(x, y);
        this.app = app; // store a reference to the main game object

        // load the specified image
        this.img = app.loadImage(imgFilePath);
    }

    /**
     * Overloaded Constructor to create a Plate object with a specific speed
     * @param app a reference to the Game object that created this object
     * @param imgFilePath a reference to the file path of the plate image
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     * @param speedX the speed of the plate object
     */
    public Plate(Game app, String imgFilePath, int x, int y, int speedX) {
        this(app, imgFilePath, x, y);
        this.speedX = speedX; 
    }

    /**
	 * Get the width of this arrow, based on  the width of its image.
	 */
	public int getWidth() {
		return 48; //return the PImage object's width property
	}
	
	/**
	 * Get the height of this arrow, based on  the width of its image.
	 */
	public int getHeight() {
		return 30; //return the PImage object's height property
	}

    /**
	 * Kill this plate.
	 * Simply removes this plate from the PApplet's list of plates;
	 */
	public void kill() {
		this.app.getPlates().remove(this); //remove this plate from the list of plates
	}

    /**
     * Draw this plate's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        this.app.image(this.img, getX(), getY(), 48, 30);
    }

    /**
     * Move the plate from left to right.
     */
    public void move() {
        speedX = (int) ((Math.random() * (6 - 1)) + 1);

        // update the plate's coordinates
        setX(getX() + speedX);
    }
    
}
