import java.util.*;
public class PowerUpHandler {

	private static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	
	private static final int HELPERS = 0;
	private static final int BOMBS = 1;
	private static final int HEALTH = 2;
	
	
	private static final float CHANCE_TO_SPAWN = 0.75f;
	
	public static ArrayList getPowerUps(){
		return powerUps;
	}
	
	public static float getChanceToSpawn(){
		return CHANCE_TO_SPAWN;
	}
	
	public static void addPowerUp(int type, Enemy enemy){
		
		switch(type){
		
		case HELPERS:
			powerUps.add(new PlayerHelperPowerUp(enemy.getX(), enemy.getY(), PowerUp.getDefaultSize(), PowerUp.getDefaultStep()));
			break;
		
		case BOMBS:
			powerUps.add(new BombPowerUp(enemy.getX(), enemy.getY(), PowerUp.getDefaultSize(), PowerUp.getDefaultStep()));
			break;
		}
	}
	
	public static void removePowerUp(PowerUp power){
		powerUps.remove(power);
	}
	
	public static void movePowerUps(){
		for (int i = 0; i < powerUps.size(); i++){
			//System.out.println(powerUps.get(i).getX() + " " + powerUps.get(i).getY());
			powerUps.get(i).move();
		}
	}
	
	public static void resetPowerUps(){
		powerUps = new ArrayList<PowerUp>();
	}
	
}
