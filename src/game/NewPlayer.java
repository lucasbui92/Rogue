package game;

import edu.monash.fit2099.engine.*;

/**
 * This class inherits from the Player class to improve some functionality
 * @author THUAN ANH BUI
 *
 */
public class NewPlayer extends Player {

	public NewPlayer(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);	
	}

	/**
	 * The player will have its AttackAction action removed in order to YugoMaxxAttack action to work. In addition,
	 * it also has the QuitGameAction to enable the user to exit the game.
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		//Remove the old AttackAction in order to use other type of attack action
		for (Action action : actions) {
			if (action instanceof AttackAction) {
				if (!(action instanceof YugoAttackAction)) {
					actions.remove(action);
				}
			}
		}
		actions.add(new QuitGameAction());
		
		if (((UniverseGameMap) map).playerNeedsSpaceGear()) {
			ManageSpaceGearAction manageSpaceGear = new ManageSpaceGearAction();
			System.out.println(manageSpaceGear.execute(this, map));
		}
		
		return showMenu(actions, display);
	}
	
	/**
	 * This method enables the player to have a YugoMaxx attack action instead of the AttackAction
	 * @param otherActor represents the enemy the player attacks
	 * @param direction unused in this method
	 * @param map represents the map both actors are on
	 * @return a new action to be added to the list of actions
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new YugoAttackAction(otherActor, this));
	}
}
