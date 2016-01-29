/* 
	File Name: GameHandler.java
	Name: Alexander Lu
	Date: Match 27, 2015
	Description: This class handles all of the elements in the game.
*/

import java.util.*;

public class GameHandler {
	
	private static GUI gui;

	
	//Fields for units
	//Note that there are different fields for enemies and players, but they are all connected in the units field
	private static Unit player;
	private static ArrayList<PlayerHelper> playerHelpers;
	
	
	//No constructor because everything in the GameHandler will be static
	
	public static void initializeGame(){
		
		EnemySpawner.setPrevTime();
		
		Input.setInputKeysMouse();
		//Input.setInputKeys();
		
		gui = new GUI();
		
	}
	
	//Initialize the units
	//NOTE: change this so it can read unit information from a file later
	public static void initializeUnits(){
		
		player = new Player(100, GUI.getYResolution() / 2.0f, 55);
		playerHelpers = new ArrayList<PlayerHelper>();
		/*for (int i = 0; i < 360; i++){
			playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), (float)Math.cos(i), (float)Math.sin(i)));
		}
		
		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), 1.0f, 0.0f));

		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), 0.0f, 1.0f));

		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), -1.0f, 0.0f));

		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), 0.0f, -1.0f));
		
		/*
		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), 0.50f, 0.50f));
		
		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), -0.50f, 0.50f));
		
		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), 0.50f, -0.50f));
		
		playerHelpers.add(new PlayerHelper (player.getX(), player.getY(), -0.50f, -0.50f));
		*/
	}
	
	public static void moveHelpers(){
		for (int i = 0; i < playerHelpers.size(); i++){
			playerHelpers.get(i).move();
		}
	}
	
	//Accessor methods
	public static Unit getPlayer(){
		return player;
	}
	
	public static ArrayList getPlayerHelpers(){
		return playerHelpers;
	}
	
	public static void resetGame(){
		EnemySpawner.setPrevTime();
		
		EnemyHandler.resetEnemyList();
		
		Player.setPoints(0);
		
		GUI.setDead(false);
		
		RenderUnit.resetDeathAnimation();
		
		PowerUpHandler.resetPowerUps();
		
		EnemySpawner.resetSpawnDelay();
		
		initializeUnits();
	}
}
