
public class AcceleratingStraightEnemy extends Enemy{

	private static final float DEFAULT_STEP = GUI.getXResolution() * 0.0001f;
	private static final float DEFAULT_SIZE = 40.0f;
	private static final int DEFAULT_HEALTH = 30;
	
	private Vector2f acceleration;
	private Vector2f velocity;
	
	public AcceleratingStraightEnemy(float xCor, float yCor, float size, int health, float initialVelocity) {
		super(xCor, yCor, size, DEFAULT_STEP, health);
		acceleration = new Vector2f(step, 0);
		velocity = new Vector2f(initialVelocity, 0);
	}

	public static float getDefaultStep(){
		return DEFAULT_STEP;
	}
	
	public static int getDefaultHealth(){
		return DEFAULT_HEALTH;
	}
	
	public static float getDefaultSize(){
		return DEFAULT_SIZE;
	}
	
	@Override
	public void move() {
		velocity.addX(acceleration.getX());
		moveLeft(velocity.getX());
		
		if (coordinates.getX() + size / 2.0f < 0){
			EnemyHandler.killEnemy(this);
		}
	}
	
	

}
