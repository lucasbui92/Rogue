package game;

import edu.monash.fit2099.engine.*;

/**
 * This class represents the water pistol
 * @author THUAN ANH BUI
 *
 */
public class WaterPistol extends Item {
	
	private boolean containWater;

	public WaterPistol(boolean containWater) {
		super("Water Pistol", 'p');
		this.containWater = containWater;
	}

	public void fillWater() {
		containWater = true;
	}
	
	public void emptyPistol() {
		containWater = false;
	}
	
	public boolean waterStatus() {
		return containWater;
	}
}
