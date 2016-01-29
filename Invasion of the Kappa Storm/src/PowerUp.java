
public abstract class PowerUp extends Unit{

	protected static final float DEFAULT_SIZE = 40.0f;
	protected static final float DEFAULT_STEP = GUI.getXResolution() * 0.000825f;
	protected int powerUpID;
	
	public PowerUp(float xCor, float yCor, float size, float step, int powerUpID) {
		super(xCor, yCor, size, step, -1);
		this.powerUpID = powerUpID;
	}

	public abstract void givePlayerPowerUp();
	
	public int getPowerUpID(){
		return powerUpID;
	}
	
	public static float getDefaultSize(){
		return DEFAULT_SIZE;
	}
	
	public static float getDefaultStep(){
		return DEFAULT_STEP;
	}
	
	public void move(){
		moveLeft(DEFAULT_STEP);
		detectPlayer();
		if (coordinates.getX() + size / 2.0f < 0){
			PowerUpHandler.removePowerUp(this);
		}
	}
	
	public void detectPlayer(){
		Unit player = GameHandler.getPlayer();
		
		if(player.getX() - player.getSize() / 2.0f <= coordinates.getX() + size / 2.0f &&
			player.getX() + player.getSize() / 2.0f >= coordinates.getX() - size / 2.0f &&
			player.getY() - player.getSize() / 2.0f <= coordinates.getY() + size / 2.0f &&
			player.getY() + player.getSize() / 2.0f >= coordinates.getY() - size / 2.0f){
			
			givePlayerPowerUp();
			PowerUpHandler.removePowerUp(this);
			System.out.println("plz");
		}
	}
}
