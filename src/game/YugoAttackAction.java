package game;

import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * This class is the main attack class between the player and YugoMaxx and vice versa
 * @author THUAN ANH BUI
 *
 */
public class YugoAttackAction extends AttackAction {
	private Actor actor;
	private Actor subject;
	private Random rand = new Random();
	
	public YugoAttackAction(Actor actor, Actor subject) {
		super(actor, subject);
		this.actor = actor;
		this.subject = subject;
	}
	
	/**
	 * Instead of YugoMaxx dropping any item after getting defeated, this method enables the check if YugoMaxx
	 * can be attacked once the suit is destroyed. In addition, it also has the functionality of telling
	 * the user if the player wins or loses
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		Item suit = null;
		double chance = Math.random();
		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + subject + ".";
		}
		
		//Destroy the Exoskeleton suit before an attack can deal damage
		for (Item i : actor.getInventory()) {
			if (i instanceof WaterPistol && subject instanceof YugoMaxx) {
				suit = ((YugoMaxx) subject).checkExoskeletonExist();
				if (((WaterPistol) i).waterStatus() == true && suit != null) {
					if (chance <= 0.7) {
						subject.removeItemFromInventory(suit);
						map.locationOf(subject).removeItem(suit);
						result += "The " + suit + " has been destroyed\n";
					}
					else {
						result += actor + " misses squirting water towards Yugo Maxx\n";
					}
					((WaterPistol) i).emptyPistol();
				}
			}
		}
		
		//Check if the Exoskeleton suit is destroyed
		int count = 0;
		for (Item i : map.locationOf(subject).getItems()) {
			if (subject instanceof YugoMaxx && i instanceof Exoskeleton) {
				count += 1;
			}
		}
		
		if (actor instanceof NewPlayer && subject instanceof YugoMaxx) {
			if (count > 0) {
				result += subject + " is invincible due to equipping the " + suit;
				subject.hurt(0);
			}
			else {
				int damage = weapon.damage();
				result += actor + " " + weapon.verb() + " " + subject + " for " + damage + " damage.";
				subject.hurt(damage);
			}
		}
		else {
			int damage = weapon.damage();
			result += actor + " " + weapon.verb() + " " + subject + " for " + damage + " damage.";
			subject.hurt(damage);
		}
		
		if (!subject.isConscious()) {
			map.removeActor(subject);
			result += System.lineSeparator() + subject + " is knocked out.";
			if (subject instanceof NewPlayer) {
				result += System.lineSeparator() + subject + " loses.";
			}
			else {
				result += System.lineSeparator() + subject + " wins.";
			}
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
