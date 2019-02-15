package edu.smith.cs.csc212.p2;

import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class FallingRock extends Rock {

	public FallingRock(World world) {
		super(world);
//		accessing the same array because FallingRock extends the Rock class.
		this.color = ROCK_COLORS[rand.nextInt(ROCK_COLORS.length)];
	}

	public void draw(Graphics2D g) {
//		Super lets us call the draw method from Rock and does all the details in it!
//		Helps keep things cleaner.
		super.draw(g);
	}

	@Override
	public void step() {
//		Unlike other rocks, these ones will fall 1 tile each step.
		moveDown();
	}

}



