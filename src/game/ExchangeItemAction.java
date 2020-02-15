package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * This class enables the actor and the player to exchange items.
 * @author THUAN ANH BUI
 *
 */
public class ExchangeItemAction extends Action {
	
	private Item item;
	private Actor player;
	private Actor actor;

	public ExchangeItemAction(Item item, Actor actor, Actor player) {
		this.item = item;
		this.actor = actor;
		this.player = player;
	}

	/**
	 * Check to see if the player possessing the rocket plans.
	 * Remove the plans and the NPC or print the quote from Q.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		for (Item newItem : player.getInventory()) {
			if (newItem instanceof RocketPlans) {
				DropItemAction dropPlan = new DropItemAction(newItem);
				result += System.lineSeparator() + dropPlan.execute(player, map);
				map.locationOf(player).removeItem(newItem);
				DropItemAction dropBody = new DropItemAction(item);
				result += System.lineSeparator() + dropBody.execute(actor, map);			
				result += System.lineSeparator() + menuDescription(actor);
				map.removeActor(actor);
				return result;
			}
		}
		return actor + " says " + "'I can "
				+ "give you something that will help, but I’m going to need the plans'.";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " waves fairwell.";
	}

	@Override
	public String hotKey() {
		return "";
	}

}
