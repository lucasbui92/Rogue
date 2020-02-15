package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.GameMap;
/**
 * this class dispenses oxygen tanks in its place when a user calls the action from it
 */

public class OxygenDispenser extends Item {
	
	private int x;
	private int y;

	/**Constructor
	 * 
	 * The dispenser needs to know its own location so it can produce oxygen tanks at that location.
	 * Therefore it is added to the map when it is built
	 * 
	 * @param gameMap the map to build the dispenser on
	 * @param x - x coordinate of location
	 * @param y - y coordinate of location
	 */
	public OxygenDispenser(GameMap gameMap,int x, int y) {
		super("Oxygen Dispensor", 'O');
		this.allowableActions.clear();
		gameMap.addItem(this,x,y);
		this.x = x;
		this.y = y;
		DispenseOxygenAction dispenseOxygenAction = new DispenseOxygenAction(this);
		this.allowableActions.add(dispenseOxygenAction);
	}
	
	
	
	
	public int[] getLocation() {
		int[] retVal = {this.x,this.y};
		return retVal;
	}

}
