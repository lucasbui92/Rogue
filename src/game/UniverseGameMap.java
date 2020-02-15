package game;

import java.util.List;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
/**
 * this class adds to game map to give an indication on whether the map is in space or not
 * ie so the player can tell whether they will need space gear when they enter
 */
public class UniverseGameMap extends GameMap {
	
	//for maps on earth, the player doesn't need space gear. Then set to false
	//for maps in outer space, they'll need the gear (ie oxygen, space suit), so set to true
	private boolean playerNeedsSpaceGear;

	public UniverseGameMap(GroundFactory groundFactory, List<String> lines, boolean playerNeedsSpaceGear) {
		super(groundFactory, lines);
		this.playerNeedsSpaceGear = playerNeedsSpaceGear;
	}
	
	
	/**
	 * @return boolean whether or not the player needs oxygen tank & space suit on this map
	 */
	public boolean playerNeedsSpaceGear() {
		return this.playerNeedsSpaceGear;
	}

}
