
public class WaveEnemy extends Enemy{

	private static final int DEFAULT_HEALTH = 40;
	private static final float DEFAULT_STEP = GUI.getXResolution() * 0.0025f;
	private static final float DEFAULT_SIZE = 50.0f;
	
	private float initialYCoordinate;
	
	public WaveEnemy(float xCor, float yCor, float size, int health) {
		super(xCor, yCor, size, DEFAULT_STEP, health);
		initialYCoordinate = yCor;
	}
	
	public static int getDefaultHealth(){
		return DEFAULT_HEALTH;
	}
	
	public static float getDefaultSize(){
		return DEFAULT_SIZE;
	}
	
	public void move(){
		moveLeft(step);
		setY((float)(initialYCoordinate + (100 * Math.sin(Math.toRadians(getX())))));
		//Check for out of bounds
		if (coordinates.getX() + size / 2.0f < 0){
			EnemyHandler.killEnemy(this);
		}
	}
}
