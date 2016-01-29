import java.util.ArrayList;


public class EnemyHandler {

	private static final int ZIG_ZAG_ENEMY = 0;
	private static final int WAVE_ENEMY = 1;
	private static final int ACCELERATING_STRAIGHT_ENEMY = 2;
	private static final int NON_ACCELERATING_STRAIGHT_ENEMY = 3;
	
	private static ArrayList<Unit> enemies = new ArrayList<Unit>();

	//Accessor for list of enemies
	public static ArrayList getEnemies(){
		return enemies;
	}
	
	//Method to destroy all enemies
	public static void resetEnemyList(){
		enemies = new ArrayList<Unit>();
	}
	
	//Spawn a new enemy at the right side of the screen
	//Note: direction will not make a difference if the enemy is not a Zig Zag Enemy
	public static void spawnEnemy(int type, float size, float xCor, float yCor, float dir){
		
		if (type == ZIG_ZAG_ENEMY){
			enemies.add(new ZigZagEnemy (xCor, yCor, size, ZigZagEnemy.getDefaultHealth(), (int)dir == 1));
		}else if (type == WAVE_ENEMY){
			enemies.add(new WaveEnemy (xCor, yCor, size, WaveEnemy.getDefaultHealth()));
		}else if (type == ACCELERATING_STRAIGHT_ENEMY){
			enemies.add(new AcceleratingStraightEnemy(xCor, yCor, size, AcceleratingStraightEnemy.getDefaultHealth(), dir));
		}else if (type == NON_ACCELERATING_STRAIGHT_ENEMY){
			enemies.add(new NonAcceleratingStraightEnemy(xCor, yCor, size, AcceleratingStraightEnemy.getDefaultHealth(), dir));
		}
	}

	
	//Kill an enemy
	public static void killEnemy(Enemy enemy){
		enemies.remove(enemy);
	}
	
	//Move enemies
	public static void moveEnemies(){
		for (int i = 0; i < enemies.size(); i++){
			((Enemy)enemies.get(i)).move();
		}
	}
	
	//Check if the player is hit by an enemy
	public static void checkPlayerHit(){
		Player player = (Player) GameHandler.getPlayer();
		for (int i = 0; i < enemies.size(); i++){
			if (player.getX() - player.getSize() / 2.0f <= enemies.get(i).getX() + enemies.get(i).getSize() / 2.0f &&
				player.getX() + player.getSize() / 2.0f >= enemies.get(i).getX() - enemies.get(i).getSize() / 2.0f &&
				player.getY() - player.getSize() / 2.0f <= enemies.get(i).getY() + enemies.get(i).getSize() / 2.0f &&
				player.getY() + player.getSize() / 2.0f >= enemies.get(i).getY() - enemies.get(i).getSize() / 2.0f){
				
				//Kill the enemy if it crashes into the player
				killEnemy((Enemy)enemies.get(i));
				
				//Kill the player if he is out of health
				if(((Player)player).damage()){
					GUI.setDead(true);
				}
				
			}
		}
	}
}
