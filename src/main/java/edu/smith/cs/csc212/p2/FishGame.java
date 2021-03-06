package edu.smith.cs.csc212.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class manages our model of gameplay: missing and found fish, etc.
 * @author jfoley
 *
 */
public class FishGame {
	/**
	 * This is the world in which the fish are missing. (It's mostly a List!).
	 */
	World world;
	/**
	 * The player (a Fish.COLORS[0]-colored fish) goes seeking their friends.
	 */
	Fish player;
	/**
	 * The home location.
	 */
	FishHome home;

//	Snail creation?
//	Snail snail;
	
	/**
	 * These are the missing fish!
	 */
	List<Fish> missing;
	
	/**
	 * These are fish we've found!
	 */
	List<Fish> found;
	
	/**
	 * Number of steps!
	 */
	int stepsTaken;
	
	/**
	 * Score!
	 */
	int score;
	
	/**
	 * Create a FishGame of a particular size.
	 * @param w how wide is the grid?
	 * @param h how tall is the grid?
	 */
	public FishGame(int w, int h) {
		world = new World(w, h);
		
		missing = new ArrayList<Fish>();
		found = new ArrayList<Fish>();
		
		// Add a home!
		home = world.insertFishHome();

		Random rand = ThreadLocalRandom.current();
		int rock_spawn = rand.nextInt(5) + 5;
		for (int i = 0; i < rock_spawn; i++) {
			world.insertRockRandomly(i);
		}
//		Crating a random amount of snails.
		int rand_snail_spawn = rand.nextInt(6) + 2;
		for (int i = 0; i < rand_snail_spawn; i++) {
			world.insertSnailRandomly();
		}
		
//		Create FallingRock.
		int rand_FallingRock = rand.nextInt(5)+ 3;
		for (int i = 0; i < rand_FallingRock; i++) {
			world.insertFallingRockRandomly(i);
		}
		
		// Make the player out of the 0th fish color.
		player = new Fish(0, world, false);
		
		// Start the player at "home".
		player.setPosition(home.getX(), home.getY());
		player.markAsPlayer();
		world.register(player);
//		"register" is what draws/creates the actual object graphics
		
		// Generate fish of all the colors but the first into the "missing" List.
		for (int ft = 1; ft < Fish.COLORS.length; ft++) {
			Fish friend = world.insertFishRandomly(ft);
			missing.add(friend);
		}
	}
	
	/**
	 * How we tell if the game is over: if missingFishLeft() == 0.
	 * @return the size of the missing list.
	 */
	public int missingFishLeft() {
		return missing.size();
	}
	
	/**
	 * This method is how the PlayFish app tells whether we're done.
	 * @return true if the player has won (or maybe lost?).
	 */
	public boolean gameOver() {
		// TODO(P2) We want to bring the fish home before we win!
		
		return missing.isEmpty();
	}

	/**
	 * Update positions of everything (the user has just pressed a button).
	 */
	
	public void step() {
		// Keep track of how long the game has run.
		this.stepsTaken += 1;
				
		// These are all the objects in the world in the same cell as the player.
		List<WorldObject> overlap = this.player.findSameCell();
		// The player is there, too, let's skip them.
		overlap.remove(this.player);
		
		// If we find a fish, remove it from missing.
		for (WorldObject wo : overlap) {
			// It is missing if it's in our missing list.
			if (missing.contains(wo)) {
				// Remove this fish from the missing list.
//				We are explicitly telling java this is a Fish class object that we are removing so it doesn't get confused.
				missing.remove((Fish) wo);
//				Now we add the fish to our found list (the found fish follow us around).
				found.add((Fish) wo);
				
				// Increase score when you find a fish!
				score += 10;
			}
		}
		// Make sure missing fish *do* something.
		wanderMissingFish();
		// When fish get added to "found" they will follow the player around.
		World.objectsFollow(player, found);
		// Step any world-objects that run themselves.
		world.stepAll();
	}
	
	/**
	 * Call moveRandomly() on all of the missing fish to make them seem alive.
	 */
	private void wanderMissingFish() {
		Random rand = ThreadLocalRandom.current();
		for (Fish lost : missing) {
//			If fastScared is true, they have a greater chance to move.
			if (lost.fastScared == true && (rand.nextDouble() < 0.8)) {
					lost.moveRandomly();
				}
			// Otherwise it's the same old 30% of the time, lost fish move randomly.
			else if (rand.nextDouble() < 0.3) {
//					For each lost fish, the game checks a percentage if they move, if yes, we call this method for the percentage that do move.
					 lost.moveRandomly();
			}
		}
	}
	/**
	 * This gets a click on the grid. We want it to destroy rocks that ruin the game.
	 * @param x - the x-tile.
	 * @param y - the y-tile.
	 */
	
	public void click(int x, int y) {
		System.out.println("Clicked on: "+x+","+y+ " world.canSwim(player,...)="+world.canSwim(player, x, y));
		List<WorldObject> atPoint = world.find(x, y);
		// TODO(P2) allow the user to click and remove rocks.
		}
	}

