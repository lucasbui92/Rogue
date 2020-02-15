package game;

import edu.monash.fit2099.engine.*;

/**
 * This class enables the creation of Ninja and its actions
 * @author THUAN ANH BUI
 *
 */
public class Ninja extends Actor {
	
	private Actor player;

	public Ninja(String name, Actor player) {
		super(name, 'N', 3, 30);
		this.player = player;
		this.addItemToInventory(Key.newInventoryItem("Key", 'k'));
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		Location here = map.locationOf(this);
		Location there = map.locationOf(player);
		int currentDistanceX = distanceX(here, there);
		int currentDistanceY = distanceY(here, there);

		//Check to see if the location between the player and the ninja is under 5 spaces
		if (currentDistanceX <= 5 && currentDistanceY <= 5) {
			ThrowPowderAction action = new ThrowPowderAction(this, player);
			return action;
		}
		
		//If no player is around, the ninja does nothing
		return new SkipTurnAction();
	}

	public int distanceX(Location a, Location b) {
		return Math.abs(a.x() - b.x());
	}
	
	public int distanceY(Location a, Location b) {
		return Math.abs(a.y() - b.y());
	}
}
