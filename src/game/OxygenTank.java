package game;

import edu.monash.fit2099.engine.Item;

public class OxygenTank extends Item {
	
	private int oxygenPoints = 10;

	public OxygenTank() {
		super("Oxygen Tank", 'o');
	}
	
	/**
	 * call this to decrement the oxygen
	 * @return points left, post decrement
	 */
	public int decrementOxygenPoints() {
		this.oxygenPoints = this.oxygenPoints - 1;		
		return oxygenPoints;
	}
	
	/**
	 * getter for the oxygen points
	 * @return
	 */
	public int getOxygenPoints() {
		return this.oxygenPoints;
	}

}