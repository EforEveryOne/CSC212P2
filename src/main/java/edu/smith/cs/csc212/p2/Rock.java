package edu.smith.cs.csc212.p2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

/**
 * It would be awful nice to have multi-colored rocks at random.
 * This is not <a href="https://en.wikipedia.org/wiki/Dwayne_Johnson">the Rock</a>, but a Rock.
 * @author jfoley
 */
public class Rock extends WorldObject {
	/**
	 * I took these colors from Wikipedia's Cool and Warm Gray sections.
	 * https://en.wikipedia.org/wiki/Shades_of_gray#Cool_grays
	 * https://en.wikipedia.org/wiki/Shades_of_gray#Warm_grays
	 */
	
//	Currently only 9 colors for rocks.
	Color[] ROCK_COLORS = new Color[] {
			new Color(144,144,192),
			new Color(145,163,176),
			new Color(112,128,144),
			new Color(94,113,106),
			new Color(76,88,102),
			new Color(170,152,169),
			new Color(152,129,123),
			new Color(138,129,141),
			new Color(72,60,50)
	};
//	private Color color;
	
	public Color color;
	
	

// Assign each rock to a number, pick a random number, get a random color, return I, pass I into the setColor(i);
//	public Color setColor() {
//		for (int i = 0; i < ROCK_COLORS.length; i++) {
//			Color color = ROCK_COLORS[i];
//		}
//		return ROCK_COLORS[this.color];
//		return null;

		
	
	// TODO(lab): introduce a member here that indexes the ROCK_COLORS array.
	
	
	/**
	 * Construct a Rock in our world.
	 * @param color 
	 * @param color2 - the grid world.
	 */
	public Rock(World world) {
		super(world);
//		in the rock object, we are putting a color value to it. and the color when a rock is created
//		will be randomly selected form the array of rocks, based on the array lengthy.
//		in the draw method is where the rock actually gets its color.
		this.color = ROCK_COLORS[rand.nextInt(ROCK_COLORS.length)];
		
		
		
		// TODO(lab): initialize your rock color index to a random number!
		// Note that all WorldObjects have a ``rand`` available so you don't need to make one.
	}

	/**
	 * Draw a rock!
	 */
	@Override
	public void draw(Graphics2D g) {
//		done i think
//		this is where the rock gets its color, from the ROCK_COLORS array.
		g.setColor(this.color);
		
		RoundRectangle2D rock = new RoundRectangle2D.Double(-.5,-.5,1,1,0.3,0.3);
		g.fill(rock);
	}

	@Override
	public void step() {
		// Rocks don't actually *do* anything.		
	}

}
