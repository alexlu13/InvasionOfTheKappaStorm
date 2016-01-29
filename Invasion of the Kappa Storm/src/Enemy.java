/* 
	File Name: Enemy.java
	Name: Alexander Lu
	Date: Match 27, 2015
	Description: This class is the basis for an Enemy unit, which extends from Unit
*/

public abstract class Enemy extends Unit{
	//protected boolean directionX;
	
	public Enemy(float xCor, float yCor, float size, float step, int health) {
		super(xCor, yCor, size, step, health);
	}

	//Method to move the enemies
	public abstract void move();
	
	//Receive the damage from a bullet
	public void receiveDamage(double damage){
		health -= damage;
		
		//Kill if health is less than 0
		if (health <= 0){
			killEnemy();
		}
	}
	
	protected void killEnemy(){
		
		float spawnPowerUp = (float)(Math.random() * 100);
		System.out.println(spawnPowerUp);
		
		int randomPower = (int)(Math.random() * 2);
		
		if (spawnPowerUp <= PowerUpHandler.getChanceToSpawn()){
			PowerUpHandler.addPowerUp(randomPower, this);
		}
		
		Player.addPoints();
		EnemyHandler.killEnemy(this);
		
		
	}
}
