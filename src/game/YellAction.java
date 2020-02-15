package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * This class enables the attack of Goons to take place.
 * @author THUAN ANH BUI
 *
 */
public class YellAction extends AttackAction {
	
	private String yellQuote = "I WILL CRUSH YOU!!";
	private Random rand = new Random();
	private Actor actor;
	private Actor subject;
	
	public YellAction(Actor actor, Actor subject) {
		super(actor, subject);
		this.actor = actor;
		this.subject = subject;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		double chance = Math.random();
		if (rand.nextBoolean()) {
			if (actor instanceof Goon && chance <= 0.1) {
				return actor + " misses " + subject + " and yells " + yellQuote + ".";
			}
			return actor + " misses " + subject + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + subject + " for " + damage + " damage.";
		if (actor instanceof Goon && chance <= 0.1) {
			result += " And yells " + this.yellQuote;
		}
		
		subject.hurt(damage);
		if (!subject.isConscious()) {
			//Drop all the items that are droppable. 
			Item sleepingActor = new Item("Sleeping " + subject, '%');
			map.locationOf(subject).addItem(sleepingActor);
			for (Item item : subject.getInventory()) {
				for (Action action : item.getAllowableActions()) {
					if (action instanceof DropItemAction) {
						result += System.lineSeparator() + action.execute(subject, map);
						break;
					}
				}
			}
			map.removeActor(subject);
			result += System.lineSeparator() + subject + " is knocked out.";
		}

		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + subject + ".";
	}
}
