package edu.nyu.cs;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library

/**
 * In this game, you will be shooting down flying plates with a bow and arrow. You will be given a score of 
 * how many plates you shot down at the end of the game. 
 * 
 * @author Emily Kim
 * @version 0.1
 */
public class Game extends PApplet {

  private PImage imgSky; // will hold a photo of sky

	private ArrayList<Shoot> arrows = new  ArrayList<Shoot>(); // will hold an ArrayList of Shoot objects (arrows)
  private ArrayList<Plate> plates = new ArrayList<Plate>(); // will hold an ArrayList of Plate objects
  private int score = 0; // the user's score
  private Bow bow; // will hold the user's bow (cursor)

  private final int NUM_PLATES = 20; // the number of plates to create
  private final int POINTS_PER_PLATE = 1; // the number of points to award the user for each star they destroy
  public final static int APP_MARGIN = 100; // a default integer for spacing the screen's drawings

  /**
	 * Getter for the ArrayList of Plate objects currently on the screen
	 * @return ArrayList of Plate objects
	 */
	public ArrayList<Plate> getPlates() {
		return this.plates;
	}

  /**
	 * Setter for the ArrayList of Plate objects currently on the screen.
	 * @param plates An ArrayList of Plate objects
	 */
	public void setPlates(ArrayList<Plate> plates) {
		this.plates = plates;
	}

	/**
	 * Getter for the ArrayList of Arrow objects currently on the screen
	 * @return ArrayList of Arrow objects
	 */
	public ArrayList<Shoot> getArrows() {
		return this.arrows;
	}

	/**
	 * Setter for the ArrayList of Arrow objects currently on the screen.
	 * @param arrows An ArrayList of Arrow objects
	 */
	public void setArrows(ArrayList<Shoot> arrows) {
		this.arrows = arrows;
	}

	/**
	 * This method will be automatically called by Processing when the program runs.
	 */
	public void setup() {

    // load up an image of sky
		String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
		String pathSky = Paths.get(cwd, "images", "sky.png").toString(); // the relative path to the sky image
    this.imgSky = loadImage(pathSky); 

    // create some plates
    for (int i=0; i<this.NUM_PLATES; i++) {
      // create a plate and add it to the array list
      int x = (int) (Math.random() * (this.width/2 - APP_MARGIN)) - APP_MARGIN;
      int y = (int) (Math.random() * ((this.height - 250) + 20)) + 20;

  		String pathPlate = Paths.get(cwd, "images", "plate.png").toString(); // the relative path to the plate image
      Plate plate = new Plate(this, pathPlate, x, y);
      this.plates.add(plate);
    }

    // create bow
    this.bow = new Bow(this);

	}

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`, `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
	 */
	public void draw() {

    image(this.imgSky, this.width/2, this.height/2); // sky background

    this.bow.draw(); // user's bow

    //loop through ArrayList of arrows
		for (int i=0; i<this.arrows.size(); i++) {
			Shoot arrow = this.arrows.get(i);
			arrow.move(); //have the arrow  move itself to a new location
			arrow.draw(); //have the arrow  draw itself to the screen
		}

    // create plates and draw them on the screen
    for (int i=0; i<this.plates.size(); i++) {
      Plate plate = this.plates.get(i); // get the current Plate object from the ArrayList
      plate.move(); 
      plate.draw(); 
    }

    ArrayList<Plate> platesToRemove = new  ArrayList<Plate>(); //will hold the next generation of plates
		ArrayList<Shoot> arrowsToRemove = new  ArrayList<Shoot>(); //will hold the next generation of arrows
		
    // check for collisions btwn any plates and any arrows
    for (Shoot arrow : this.arrows) {
			for (Plate plate : this.plates) {
				if (Shoot.isCollision(arrow, plate)) {
          score += POINTS_PER_PLATE;  
					platesToRemove.add(plate); // add this plate to the list  that we will remove
					arrowsToRemove.add(arrow);  //add this arrow to the  list that we will remove
          
				}
			}
		}
		
		//enter all plates we marked as removable 
		for (Plate plate : platesToRemove) {
			plate.kill(); //tell the plate to kill itself
		}
		
		//remove all arrows we marked as removable
		for (Shoot arrow:arrowsToRemove) {
			arrow.kill(); //tell the arrow to  kill itself
		}

    // show the score at the bottom of the window
    String scoreString = String.format("SCORE: %d", this.score);
    text(scoreString, this.width/2, this.height-50);

	}

	/**
	 * This method is automatically called by Processing every time the user clicks a mouse button.
	 * - The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   * - The `mouseButton` variable is automatically assigned the value of either the PApplet.LEFT or PApplet.RIGHT constants, depending upon which button was pressed.
   */
	public void mouseClicked() {
		System.out.println(String.format("Mouse clicked at: %d:%d.", this.mouseX, this.mouseY));

    this.bow.shoot();
	}

  /**
   * This method is used to replace the mouse cursor with a bow.
   */
  public void mouseMoved() {
    noCursor();
    this.bow.setX(mouseX);
  }

  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		this.size(1200, 800); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}
  }

}
