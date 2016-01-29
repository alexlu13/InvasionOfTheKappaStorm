import java.util.ArrayList;

/* 
	File Name: Bullet.java
	Name: Alexander Lu
	Date: Match 30, 2015
	Description: This class contains the information required for a bullet
*/

public class Bullet extends Unit{
	
	protected static final float STEP = GUI.getXResolution() * 0.02f;
	protected static final float DEFAULT_SIZE = 50.0f;
	protected static final float BULLET_BOUNDS = 50.0f;
	
	protected boolean lazer;
	
	private int damage;
	
	private int bulletType;
	
	//Constructor
	public Bullet(float xCor, float yCor, int damage, float size, boolean lazer){
		super(xCor, yCor, size, STEP, -1);
		this.damage = damage;
		this.lazer = lazer;
	}

	//Movement methods
	public void move(){
		//Get list of enemies and player
		
		Player player = ((Player)GameHandler.getPlayer());
		
		//Check bounds
		if (getX() > GUI.getXResolution() + getBulletBounds()){
			player.removeBullet(this);
		}else{
			
			//Move bullet right
			moveRight(step);
			checkHit();
		}
	}
	
	public void checkHit(){
		ArrayList<Enemy> enemies = EnemyHandler.getEnemies();
		Player player = ((Player)GameHandler.getPlayer());
		//Check for collision
		for (int q = 0; q < enemies.size(); q++){
			if (getX() + getSize() / 2.0f >= enemies.get(q).getX() - enemies.get(q).getSize() / 2.0f && 
				getX() + getSize() / 2.0f <= enemies.get(q).getX() + enemies.get(q).getSize() / 2.0f &&
				getY() + getSize() / 2.0f >= enemies.get(q).getY() - enemies.get(q).getSize() / 2.0f &&
				getY() - getSize() / 2.0f <= enemies.get(q).getY() + enemies.get(q).getSize() / 2.0f){
				
					//Do the damage to the enemy and remove the bullet if it hits an enemy
					((Enemy)enemies.get(q)).receiveDamage(getDamage());
					
					if (!lazer){
						player.removeBullet(this);
					}
			}
		}
	}
	
	//Accessors
	public static float getDefaultSize(){
		return DEFAULT_SIZE;
	}
	
	public static float getBulletBounds(){
		return BULLET_BOUNDS;
	}

	public static float getDefaultStep(){
		return STEP;
	}
	
	public float getStep() {
		return step;
	}
	
	public double getDamage(){
		return damage;
	}
}
