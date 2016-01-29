
public class PlayerHelper extends Unit{

	private static final int MAX_HEALTH = 3;
	private static final float DEFAULT_SIZE = 25;
	private static final float DEFAULT_STEP = GUI.getXResolution() * 0.003f;
	
	private static final float DEFAULT_RADIUS = 50.0f;
	
	private float circleRadius;
	private Vector2f circlePosition;
	private Vector2f velocity;
	private Vector2f acceleration;
	
	public PlayerHelper(float xCor, float yCor, float posX, float posY) {
		super(xCor, yCor, DEFAULT_SIZE, DEFAULT_STEP, MAX_HEALTH);
		
		float angle = (float) Math.atan2(posY, posX);
		circleRadius = DEFAULT_RADIUS;
		coordinates.addX(circleRadius * (float)Math.cos(angle));
		coordinates.addY(circleRadius * (float)Math.sin(angle));
		circlePosition = new Vector2f(xCor, yCor);

		velocity = new Vector2f(DEFAULT_STEP * (float)Math.sin(angle), DEFAULT_STEP * -(float)Math.cos(angle));
		//System.out.println(velocity);
		acceleration = new Vector2f(0, 0);
		
	}
	
	public static float getDefaultSize(){
		return DEFAULT_SIZE;
	}
	
	public void move(){
		float angle = (float)Math.atan2((coordinates.getY() - circlePosition.getY()), (coordinates.getX() - circlePosition.getX()));
		
		//float velocity = (float)Math.hypot(this.velocity.getX(), this.velocity.getY());
		float velocity = (float) Math.sqrt(Math.pow(this.velocity.getX(), 2) + Math.pow(this.velocity.getY(), 2));
		
		acceleration.setY(-(velocity * velocity / circleRadius) * (float)Math.sin(angle));
		acceleration.setX(-(velocity * velocity / circleRadius) * (float)Math.cos(angle));
		this.velocity.addX((float)acceleration.getX());
		this.velocity.addY((float)acceleration.getY());
		
		super.moveRight(this.velocity.getX());
		super.moveUp(this.velocity.getY());
	}
	
	public void shootBullet(){
		((Player)(GameHandler.getPlayer())).shootBulletFromHelper(this);
	}
	
	public void shootDirectionalBullet(float dir){
		((Player)(GameHandler.getPlayer())).shootDirectionalBulletFromHelper(this, dir);
	}
	
	@Override
	public void moveLeft(float step){
		coordinates.addX(-step);
		circlePosition.addX(-step);
	}
	
	@Override
	public void moveRight(float step){
		coordinates.addX(step);
		circlePosition.addX(step);
	}
	
	@Override
	public void moveDown(float step){
		coordinates.addY(-step);
		circlePosition.addY(-step);
	}
	
	@Override
	public void moveUp(float step){
		coordinates.addY(step);
		circlePosition.addY(step);
	}
}
