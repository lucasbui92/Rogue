package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * This class enables Ninja to interact with the players.
 * @author THUAN ANH BUI
 *
 */
public class ThrowPowderAction extends AttackAction {

	private Actor actor;
	private Actor subject;
	
	public ThrowPowderAction(Actor actor, Actor subject) {
		super(actor, subject);
		this.actor = actor;
		this.subject = subject;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		double chance = Math.random();
		String result = "";
		
		//Ninja has weapon but this weapon has no damage
		Weapon stunPowder = new WeaponItem("Stun Powder", 's', 0, "throws at");
		
		//Chance of player getting hit
		if (chance <= 0.5) {
			result = actor + " misses " + subject + ".";
		}
		else {
			result = actor + " " + stunPowder.verb() + " " + subject + " to stun " + subject + ".";
		}
		
		if (!subject.isConscious()) {
			//Drop all the items that are droppable. 
			Item sleepingActor = new Item("Sleeping " + actor, '%');
			map.locationOf(subject).addItem(sleepingActor);
			for (Item item : subject.getInventory()) {
				for (Action action : item.getAllowableActions()) {
					if (action instanceof DropItemAction) {
						action.execute(subject, map);
						break;
					}
				}
			}
			map.removeActor(subject);
			result += System.lineSeparator() + subject + " is knocked out.";
		}
		else {	//While the ninja is alive and within the range of the player, it moves randomly on the map
			Location here = map.locationOf(subject);
			if (actor instanceof Ninja) {
				here = map.locationOf(actor);
			}
			Location moveAway = new Location(map, here.x() - 1, here.y());

			Action action = new MoveActorAction(moveAway, "away from " + subject);
			result += System.lineSeparator() + action.execute(actor, map);
		}
		
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + subject;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
