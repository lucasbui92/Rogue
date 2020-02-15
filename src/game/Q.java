package game;

import edu.monash.fit2099.engine.*;

/**
 * This class enables the creation of the friendly NPC Q and its interactions with the player.
 * @author THUAN ANH BUI
 *
 */
public class Q extends Actor {
	
	private Actor player;
	private RocketBody rocBody = new RocketBody();

	public Q(String name, Actor player) {
		super(name, 'Q', 0, 100);
		this.player = player;
		this.addItemToInventory(rocBody);
	}

	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		Location here = map.locationOf(this);
		Location there = map.locationOf(player);
		int currentDistanceX = distanceX(here, there);
		int currentDistanceY = distanceY(here, there);
		
		//Check if Q and the player are in close contact and whether Q has the rocket body. Q will wander
		//around aimlessly
		if (currentDistanceX <= 1 && currentDistanceY <= 1 && (this.getInventory().contains(rocBody) == true)) {
			return new ExchangeItemAction(rocBody, this, player);
		}
		else {
			return super.playTurn(actions,  map,  display);
		}
	}
	
	public int distanceX(Location a, Location b) {
		return Math.abs(a.x() - b.x());
	}
	
	public int distanceY(Location a, Location b) {
		return Math.abs(a.y() - b.y());
	}
}
