
public class NonAcceleratingStraightEnemy extends Enemy{
	
	private static final float DEFAULT_STEP = GUI.getXResolution() * 0.0025f;
	private static final float DEFAULT_SIZE = 40.0f;
	private static final int DEFAULT_HEALTH = 30;
	
	private boolean bounced = false;
	
	private Vector2f velocity;
	
	public NonAcceleratingStraightEnemy(float xCor, float yCor, float size, int health, float angle) {
		super(xCor, yCor, size, DEFAULT_STEP, health);
		
		velocity = new Vector2f(step * (float) Math.cos(Math.toRadians(angle)), step * (float) Math.sin(Math.toRadians(angle)));
		
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
		moveLeft(velocity.getX());
		moveUp(velocity.getY());
		
		if (coordinates.getX() + size / 2.0f < 0){
			EnemyHandler.killEnemy(this);
		}
		if(!bounced && coordinates.getY() + size / 2.0f < 0 || coordinates.getY() - size / 2.0f > GUI.getYResolution()){
			velocity.setY(velocity.getY() * -1);
			bounced = true;
		}
	}
	
}
