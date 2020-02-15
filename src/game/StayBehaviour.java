package game;

import edu.monash.fit2099.engine.*;

/**
 * This class enables the consequences happening between DrMaybe and the player.
 */
public class StayBehaviour implements ActionFactory {

	private Actor target;

	public StayBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * Ensures DrMaybe does nothing unless Actor player is
	 * within 1 tile from DrMaybe's position
	 * @param actor is the actor in question
	 * @return new SkipTurnAction if Actor is not close enough to target to attack it
	 * @return new AttackAction if Actor is within attacking range of target
	 */

	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		
		int currentDistance = distance(here, there);
		if(currentDistance < 2) {
			return new AttackAction(actor,target);
		}
		return new SkipTurnAction();
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}

