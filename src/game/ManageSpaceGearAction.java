package game;

import edu.monash.fit2099.engine.Action;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import game.Application;
/**
 * this class checks whether the user has the correct space gear,
 * ie if they're on the moon, they need to have a space suit and enough oxygen to breath.
 * if they don't, it sends them back to earth.
 * it also calls decrement oxygen. 
 * In summary, it manages all forms of space gear
 */
public class ManageSpaceGearAction extends Action {

	@Override
	/**
	 * @param actor - the subject actor (usually would be a newPlayer)
	 * @param map - the map they're on, assumed to be a moon map, that's the clients responsibility
	 * its already assumed the client has checked the player is on the moon
	 * responsibility of this class is to:
	 * check the oxygen left in the actors inventory,
	 * decrement the oxygen the player has, or
	 * return them to earth if they have none
	 */
	public String execute(Actor actor, GameMap map) {
		String retVal;
		
		//first check they've got their space suit
		boolean hasSpaceSuit = false;
		for(Item item : actor.getInventory()) {
			if (item instanceof SpaceSuit) {
				hasSpaceSuit = true;
			}
		}
		if (hasSpaceSuit == false){
			map.moveActor(actor, Application.earthGameMap.at(22, 9));
			return menuDescription(actor);
		}
		
		//next get total oxygen in actors inventory for display purposes
		int totalOxygen = 0;
		for(Item item : actor.getInventory()) {
			if (item instanceof OxygenTank) {
				if(((OxygenTank) item).getOxygenPoints() > 0) {
					totalOxygen += ((OxygenTank) item).getOxygenPoints();
				}
			}
		}
		//then decrement the oxygen if they have any
		if(totalOxygen > 0) {
			for(Item item : actor.getInventory()) {
				if (item instanceof OxygenTank) {
					if(((OxygenTank) item).getOxygenPoints() > 0) {
						((OxygenTank) item).decrementOxygenPoints();
						break;
					}
					else {
						actor.removeItemFromInventory(item);
					}
				}
			}
			retVal = "Total oxygen points left: " + Integer.toString(totalOxygen);
			return retVal;
		}
		//else they're out of oxygen
		else {
			map.moveActor(actor, Application.earthGameMap.at(22, 9));
			return menuDescription(actor);
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		return "back to earth - no oxygen - safety system activated";
	}

	@Override
	public String hotKey() {
		return null;
	}

}
