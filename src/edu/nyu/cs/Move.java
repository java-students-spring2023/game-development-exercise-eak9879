package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * A class for handling non-user-controlled moving objects.
 * 
 * @author Emily Kim
 * @version 0.1
 */
public class Move {
    
    private Game app; // will hold a reference to the main Game object
    private PImage img; // will hold a reference to an image of the object
    private int x, y; // position of the moving object
    private int speedX; // will hold the speed of the moving object going right
	private int speedY; // will hold the speed of the moving object going upwards

    /**
     * Constructor for a Move object.
     * @param x position of the object within the x axis
     * @param y position of the object within the y axis
     */
    public Move (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the x position of the object.
     * @return
     */
    public int getX() {
        return this.x; 
    }

    /**
     * This method returns the y position of the object.
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * This method sets the x position of the object.
     * @param newX coordinate to update to
     */
    public void setX(int newX) {
        if (newX <= 1300 && newX >= -100) {
            this.x = newX; 
        }
    }

    /**
     * This method sets the y position of the object.
     * @param newY coordinate to update to
     */
    public void setY(int newY) {
        if (newY <= 900 && newY >= -100) {
            this.y = newY; 
        }
    }

    /**
	 * Get the width of this object, based on the  width of its image.
	 * @return int width of this image
	 */
	public int getWidth() {
		return this.img.width; //return the PImage object's width property
	}
	
	/**
	 * Get the height of this object, based on  the width of its image.
	 * @return int height of this image
	 */
	public int getHeight() {
		return this.img.height; //return the PImage object's height property
	}

    /**
     * Draw this plate's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        this.app.image(this.img, x, y);
    }

    /**
	 * Move the object around the screen
	 */
	public void move() {

        speedX = (int) ((Math.random() * (6 - 1)) + 1);
        speedY = -20;

        // update the x position
        x += speedX;

        // update the y position
		y += speedY; 


		
	}

}
