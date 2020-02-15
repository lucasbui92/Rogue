package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * This class dispenses oxygen tanks from the dispenser
 */

public class DispenseOxygenAction extends Action {
	
	private OxygenDispenser oxygenDispenser;
	private int[] oxygenDispenserLocation;

	public DispenseOxygenAction(OxygenDispenser oxygenDispenser) {
		this.oxygenDispenser = oxygenDispenser;
		this.oxygenDispenserLocation = this.oxygenDispenser.getLocation();
}

	@Override
	/**
	 * creates an oxygen tank object in the next move
	 * @return a string that indicates the status of the dispensing (eg success/fail)
	 */
	public String execute(Actor actor, GameMap map) {
		Location dispensorLocation = map.at(oxygenDispenserLocation[0], oxygenDispenserLocation[1]);
		List<Item> items = dispensorLocation.getItems();
		for (Item item : items){
			if(item instanceof OxygenTank) {
				return "Can not create new oxygen tank while oxygen tank is waiting for pickup.";
			}
		}
		OxygenTank tank = new OxygenTank();
		map.addItem(tank, oxygenDispenserLocation[0], oxygenDispenserLocation[1]);
		return "Tank successfully created.";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Dispense oxygen tank";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
