/* 
	File Name: Unit.java
	Name: Alexander Lu
	Date: Match 27, 2015
	Description: This class is the basis for all of the units to be handled in the game
*/

public abstract class Unit {
	
	//Size of the unit
	protected float size;
	protected float step;
	protected int health;
	protected int maxHealth;
	//X and Y coordinate of the unit
	protected Vector2f coordinates;
	
	//Constructor
	public Unit(float xCor, float yCor, float size, float step, int maxHealth){
		coordinates = new Vector2f(xCor, yCor);
		this.size = size;
		this.step = step;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}
	
	//Accessors
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int getHealth(){
		return health;
	}
	
	public float getX(){
		return coordinates.getX();
	}
	
	public float getY(){
		return coordinates.getY();
	}
	
	public float getSize(){
		return size;
	}
	
	public float getStep(){
		return step;
	}
	
	public void setY(float y){
		coordinates.setY(y);
	}
	
	//Methods for movement
	public void moveUp(float step) {
		coordinates.addY(step);
	}

	public void moveDown(float step) {
		coordinates.addY(-step);
	}

	public void moveLeft(float step) {
		coordinates.addX(-step);
	}

	public void moveRight(float step) {
		coordinates.addX(step);
	}
	
}
