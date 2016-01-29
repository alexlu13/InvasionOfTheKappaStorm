
public class ZigZagEnemy extends Enemy{
	
	private boolean movingUp;
	
	private static final float DEFAULT_STEP = GUI.getXResolution() * 0.0025f;
	private static final int DEFAULT_HEALTH = 150;
	private static final float DEFAULT_SIZE = 100.0f;
	
	public ZigZagEnemy(float xCor, float yCor, float size, int health, boolean movingUp) {
		super(xCor, yCor, size, DEFAULT_STEP, health);
		this.movingUp = movingUp;
	}

	public static int getDefaultHealth(){
		return DEFAULT_HEALTH;
	}
	
	public static float getDefaultSize(){
		return DEFAULT_SIZE;
	}
	
	public void move(){//Move in the appropriate y direction
		if(movingUp){
			moveUp(step);
		}else{
			moveDown(step);
		}
		
		//Change the y direction if it's going to be out of bounds
		if (coordinates.getY() + size / 2.0f >= GUI.getYResolution()){
			movingUp = false;
		}else if (coordinates.getY() - size / 2.0f <= 0){
			movingUp = true;
		}
		
		//Adjust the x coordinates
		moveLeft(step);
		
		//Check for out of bounds
		if (coordinates.getX() + size / 2.0f < 0){
			EnemyHandler.killEnemy(this);
		}
	}
	
	@Override
	protected void killEnemy(){
		super.killEnemy();
		
		EnemySpawner.spawnNonAcceleratingSquadronInCirclePattern(this );
	}
}
