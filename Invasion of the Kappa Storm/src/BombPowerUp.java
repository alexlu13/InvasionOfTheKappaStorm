import java.util.ArrayList;


public class BombPowerUp extends PowerUp{

	private static final int POWER_UP_ID = 1;
	
	public BombPowerUp(float xCor, float yCor, float size, float step) {
		super(xCor, yCor, size, step, POWER_UP_ID);
	}

	@Override
	public void givePlayerPowerUp() {
		ArrayList<Bomb> bombs = ((Player)GameHandler.getPlayer()).getBombs();
		bombs.add(new Bomb());
	}

}
