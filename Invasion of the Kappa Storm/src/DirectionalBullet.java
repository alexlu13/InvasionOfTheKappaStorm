
public class DirectionalBullet extends Bullet{

	private Vector2f velocity;
	
	private static final float DEFAULT_STEP = GUI.getXResolution() * 0.0025f;

	//Note: dir is the angle, relative to the player, in degrees
	public DirectionalBullet(float xCor, float yCor, int damage, float size, boolean lazer, float dir) {
		super(xCor, yCor, damage, size, lazer);
		
		float angle = (float) Math.toRadians(dir);
		
		velocity = new Vector2f(DEFAULT_STEP * (float)Math.cos(dir), DEFAULT_STEP * (float)Math.sin(dir));
	}
	
	public DirectionalBullet(float xCor, float yCor, int damage, float size, boolean lazer, float dir, float step){
		super(xCor, yCor, damage, size, lazer);
		
		float angle = (float) Math.toRadians(dir);
		
		velocity = new Vector2f(step * (float)Math.cos(dir), step * (float)Math.sin(dir));
	}
	
	public static float getDefaultStep(){
		return DEFAULT_STEP;
	}
	
	@Override
	public void move(){
		Player player = ((Player)GameHandler.getPlayer());
		
		//Check bounds
		if (getX() - DEFAULT_SIZE / 2.0f > GUI.getXResolution() || getX() + DEFAULT_SIZE / 2.0f < 0 ||
				getY() - DEFAULT_SIZE / 2.0f > GUI.getYResolution() || getY() + DEFAULT_SIZE / 2.0f < 0){
			player.removeBullet(this);
		}else{
			coordinates.addX(velocity.getX());
			coordinates.addY(velocity.getY());
			checkHit();
		}
		
	}
}
