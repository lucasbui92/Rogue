package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.demo.Crater;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;

public class Application {
	public static UniverseGameMap earthGameMap;

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door(), new WaterPond(), new Crater()); 

		List<String> map = Arrays.asList(
				"..........................",
				"....#######....#######....",
				"....#.....#....#.....#....",
				"....#.....#....#.....#....",
				"..$.###D###....###D###....",
				"..........................",
				"..........................",
				"..........................",
				"..........................",
				"..........................",
				"..........................");

		earthGameMap = new UniverseGameMap(groundFactory, map, false);
		world.addMap(earthGameMap);
		
		NewPlayer player = new NewPlayer("Player", '@', 1, 100);
        world.addPlayer(player, earthGameMap, 2, 2);
		
		RocketPlans rocketPlans = new RocketPlans();
		earthGameMap.addItem(rocketPlans, 18, 2);
		
		Grunt grunt = new Grunt("Mongo", player);
		earthGameMap.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		earthGameMap.addActor(grunt2, 10, 10);
		
		Goon goon = new Goon("Imp", player);
		earthGameMap.addActor(goon, 3, 5);
		
		Ninja ninja = new Ninja("Ninja", player);
		earthGameMap.addActor(ninja, 8, 8);
		
		Q q = new Q("Q", player);
		earthGameMap.addActor(q, 2, 10);
		
		DrMaybe DrMaybe = new DrMaybe("Dr Maybe");
        DrMaybe.actionFactories.add(new StayBehaviour(player));

        earthGameMap.addActor(DrMaybe, 18, 3);
        
        //player.addItemToInventory(new RocketBody());
        //player.addItemToInventory(new RocketEngine());
        
        /*WaterPistol waterPistol1 = new WaterPistol(false);
        earthGameMap.addItem(waterPistol1, 2, 3);
            
        YugoMaxx yugo = new YugoMaxx("Yugo Maxx", player);
        earthGameMap.addActor(yugo, 4, 5);*/
        
        //Creation and manipulation of moonMap below
  		List<String> moonMap = Arrays.asList(
  				"oooooooo..........oooooooo",
  				"....oooo....oooo.......ooo",
  				"....o................o....",
  				"....o................o....",
  				"..oooo......ooo.....oooooo",
  				"............oooooo........",
  				"..oooo...............oo...",
  				"..oo....ooo..........ooo..",
  				"..o.....ooo...............",
  				"....oo...............ooooo",
  				"....ooooo.......ooo.......");
          

  		UniverseGameMap moonGameMap = new UniverseGameMap(groundFactory, moonMap, true);
  		world.addMap(moonGameMap);
        
        Key key = new Key();
		earthGameMap.addItem(key, 12, 8);                    //adding a key for testing purposes
		
		RocketPlans RocketPlans = new RocketPlans();
		earthGameMap.addItem(rocketPlans, 18, 2);           //adding rocket plans inside the second locked room
              
		//Furniture with RocketAction allows travel to moon map if RocketEngine and RocketBody are both in
		Item rocketpad = Item.newFurniture("Rocketpad", '^');
		earthGameMap.addItem(rocketpad, 1, 1);
		rocketpad.getAllowableActions().add(new RocketAction(moonGameMap.at(14, 8), "to the Moon!"));
		  
		Item rocketpad2 = Item.newFurniture("Rocketpad", '^');
		moonGameMap.addItem(rocketpad2, 14, 8);
		rocketpad2.getAllowableActions().add(new MoveActorAction(earthGameMap.at(1, 1), "back to Earth!"));
		  
		Grunt spaceGrunt = new Grunt("Space Grunt", player);
		moonGameMap.addActor(spaceGrunt, 5, 3);
		  
		Goon spaceGoon = new Goon("Space Goon", player);
		moonGameMap.addActor(spaceGoon, 16,4);
		  
		Ninja spaceNinja = new Ninja("Space Ninja", player);
		moonGameMap.addActor(spaceNinja, 7, 8);
		  
		YugoMaxx yugoMaxx = new YugoMaxx("Yugo Maxx", player);
        moonGameMap.addActor(yugoMaxx, 4, 5);
        
        WaterPistol waterPistol = new WaterPistol(false);
        moonGameMap.addItem(waterPistol, 8, 5);
        
        OxygenDispenser oxygenDispensor = new OxygenDispenser(earthGameMap, 5, 10);
        
        SpaceSuit spaceSuit = new SpaceSuit();
        earthGameMap.addItem(spaceSuit,21,10);
        
		world.run();
	}
}
