package game;

import edu.monash.fit2099.demo.KickAction;
import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allows representation of DrMaybe in the game map.
 */
public class DrMaybe extends Actor {
	
	

	public List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	
	/**
	 * Constructor
	 * passes to super class: name, displaychar M, Priority 6, health points 25
	 */

	// DrMaybe has 25 hitpoints and is denoted by 'Dr Maybe'
	public DrMaybe(String name) {
		super(name, 'M', 6, 25);	//priority change necessary?
		this.inventory.add(new RocketEngine());

	}


	/**
	 * adds specific behaviour to actionFactories List
	 * 
	 */

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	/**
	 *accesses Actor's possible actions
	 *@return action if possible action exists
	 *@return null if no possible action exists
	 */

	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				
				return action;
		}
		
		return null;
	}
	

	/**
	 * Sets DrMaybe's base damage output to 2
	 * Sets DrMaybe's attack description to "smacks"
	 */

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(2, "smacks");
}}
