package game;

import edu.monash.fit2099.engine.*;

/**
 * This class overrides the Ground class representing the water pond
 * @author THUAN ANH BUI
 *
 */
public class WaterPond extends Ground {

	public WaterPond() {
		super('$');
	}

	/**
	 * This method checks whether the player can fill water when standing next to the water pond
	 * @param actor is the player
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		for (Item i : actor.getInventory()) {
			if (i instanceof WaterPistol && ((WaterPistol) i).waterStatus() == false) {
				((WaterPistol) i).fillWater();
				System.out.println("The water pistol is filled with water.");
			}
		}
		return false;
	}
	
}
