package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * This class enables creation and action of Goons.
 * @author THUAN ANH BUI
 *
 */
public class Goon extends Actor {

	public Goon(String name, Actor player) {
		// Goons have fewer hitpoints and a lower priority compared to Grunts 
		super(name, 'G', 2, 25);
		this.addItemToInventory(Key.newInventoryItem("Key", 'k'));
		addBehaviour(new FollowBehaviour(player));
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}
	
	/**
	 * Damage is adjusted to be double that of Grunts
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "smashes");
	}
	
	/**
	 * Enable a new YellAction action to be executed from a list of actions
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new YellAction(otherActor, this));
	}
}
