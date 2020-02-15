package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

/**
 * This class allows representation of the door on the game map.
 */
public class Door extends Ground {

	public Door() {
		super('D');
		// passing the displaychar 'D'
	}
	
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
	
	/**
	 * Checks to see whether the actor has a key. If they do, they can pass through the door. If not, they can't.
	 * @param actor is the actor in question
	 * @return true if the player has the key/can pass through, false if not
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		boolean canOpen = false;
		for(Item item : actor.getInventory()) {
			if(item instanceof Key) {
				canOpen = true;
			}
		}
		return canOpen;
	}
	
	
}
