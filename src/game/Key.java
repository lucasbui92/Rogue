package game;

import edu.monash.fit2099.engine.Item;

public class Key extends Item {

	/**
	 * constructor
	 * A key is an arbitrary object that allows any actor with it in their inventory to open any door
	 */
	public Key() {
		super("Key", 'k'); //passing the item name as 'Key' and the displaychar as 'k'
	}

}
