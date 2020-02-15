package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This class enables the option for the user to stop the game
 * @author THUAN ANH BUI
 *
 */
public class QuitGameAction extends Action {

	public QuitGameAction() {
	}

	/**
	 * This method will remove the player from the game map thus stopping the game
	 * @param actor is the player
	 * @param map is the current map the player is on
	 * @return a description after the user hitting the hotkey
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " quits the game";
	}
	
	@Override
	public String hotKey() {
		return "0";
	}

}
