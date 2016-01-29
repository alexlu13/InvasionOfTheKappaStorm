
public class Bomb {

	private static final int DEFAULT_DAMAGE = 1000;
	
	public void detonate(){
		
		Unit player = GameHandler.getPlayer();
		
		for (int i = 0; i < 360; i++){
			
			DirectionalBullet temp = new DirectionalBullet (player.getX(), player.getY(), DEFAULT_DAMAGE, DirectionalBullet.getDefaultSize(), true, i);
			
			((Player)player).shootDirectionalBullet(temp);
		}
		
	}
	
}
