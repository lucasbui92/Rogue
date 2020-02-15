package game;

import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * This class represents the character YugoMaxx
 * @author THUAN ANH BUI
 *
 */
public class YugoMaxx extends Actor {
	
	private Actor player;
	private int hitPoints;

	public YugoMaxx(String name, Actor player) {
		super(name, 'Y', 2, 50);
		this.hitPoints = 50;
		this.player = player;
		this.addItemToInventory(new Exoskeleton());
	}
	
	/**
	 * This method removes AttackAction and DropItemAction from YugoMaxx to avoid duplication and confusion.
	 * Instead, YugoMaxx should use YugoMaxxAttack action to attack the player
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		//Remove the old AttackAction in order to use other type of attack action
		for (Action action : actions) {
			if (action instanceof AttackAction || action instanceof DropItemAction) {
				if (!(action instanceof YugoAttackAction)) {
					actions.remove(action);
				}
			}
		}
		return super.playTurn(actions,  map,  display);
	}
	
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "smashes");
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new YugoAttackAction(otherActor, this));
	}

	public Item checkExoskeletonExist() {
		for (Item i : this.getInventory()) {
			if (i instanceof Exoskeleton) {
				return i;
			}
		}
		return null;
	}
}
