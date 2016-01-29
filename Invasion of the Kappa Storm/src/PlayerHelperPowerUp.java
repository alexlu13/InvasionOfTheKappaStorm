import java.util.*;

public class PlayerHelperPowerUp extends PowerUp{

	private static int POWER_UP_ID = 0;
	
	public PlayerHelperPowerUp(float xCor, float yCor, float size, float step) {
		super(xCor, yCor, size, step, POWER_UP_ID);
	}

	public void givePlayerPowerUp() {
		ArrayList<PlayerHelper> helpers = GameHandler.getPlayerHelpers();
		Unit player = GameHandler.getPlayer();
		helpers.add(new PlayerHelper (player.getX(), player.getY(), 1.0f, 0.0f));
	}
}
