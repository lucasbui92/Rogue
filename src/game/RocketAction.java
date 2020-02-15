package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * An Action that moves the Actor.
 */
public class RocketAction extends Action {

	private Location moveToLocation;
	private String direction;
	private String hotKey;

	/**
	 * Constructor to create an Action that will move the Actor to a Location in a given Direction, using
	 * a given menu hotkey.
	 *
	 * Note that this constructor does not check whether the supplied Location is actually in the given direction
	 * from the Actor's current location.  This allows for (e.g.) teleporters, etc.
	 *
	 * @param moveToLocation the destination of the move
	 * @param direction the direction of the move (to be displayed in menu)
	 * @param hotKey the menu hotkey for this move
	 */
	public RocketAction(Location moveToLocation, String direction) {
		this.moveToLocation = moveToLocation;
		this.direction = direction;
		this.hotKey = "";
	}

	/**
	 * Allow the Actor to be moved, only if their inventory contains an instance of both RocketEngine and RocketBody
	 *
	 * Overrides Action.execute()
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return description of travel
	 * @return string stating they cannot travel if item requirements are not met
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		for(Item item : actor.getInventory()) {
			if(item instanceof RocketEngine) {
				for(Item item2 : actor.getInventory()) {
					if(item2 instanceof RocketBody) {
						map.moveActor(actor, moveToLocation);
						return menuDescription(actor);}}}
		}
		return "You do not have the required items to use the rocket";
	}

	/**
	 * Returns a description of this movement suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player moves east"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " moves " + direction;
	}

	/**
	 * Returns this Action's hotkey.
	 *
	 * @return the hotkey
	 */
	@Override
	public String hotKey() {
		return hotKey;
	}
}