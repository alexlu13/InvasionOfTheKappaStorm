/* 
	File Name: Input.java
	Name: Alexander Lu
	Date: March 27, 2015
	Description: This class handles the keyboard (and perhaps mouse in the future) input and updates the units
	accordingly.
*/

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	
	private static int leftKey;
	private static int rightKey;
	private static int downKey;
	private static int upKey;
	private static int shootKey;
	private static int slowKey;
	private static int resetKey;
	private static int bombKey;
	
	private static boolean usingMouse = false;
	
	private static final float FRAMES_BEFORE_NEXT_SHOT = 0.1f;
	private static final float FRAMES_BEFORE_NEXT_BOMB = 7.0f;
	private static final float BULLET_DELAY = GUI.getMaxFPS() * FRAMES_BEFORE_NEXT_SHOT;
	private static final float BOMB_DELAY = GUI.getMaxFPS() * FRAMES_BEFORE_NEXT_BOMB;
	
	private static int mouseX;
	private static int mouseY;
	
	private static long prevTime = -1;
	private static long prevBombTime = -1;
	
	//Initialize the input keys
	public static void setInputKeys(){
		leftKey = Keyboard.KEY_LEFT;
		rightKey = Keyboard.KEY_RIGHT;
		downKey = Keyboard.KEY_DOWN;
		upKey = Keyboard.KEY_UP;
		shootKey = Keyboard.KEY_A;
		slowKey = Keyboard.KEY_LSHIFT;
		resetKey = Keyboard.KEY_SPACE;
		bombKey = Keyboard.KEY_E;
	}
	
	public static void setInputKeysMouse(){
		leftKey = Keyboard.KEY_A;
		rightKey = Keyboard.KEY_D;
		downKey = Keyboard.KEY_S;
		upKey = Keyboard.KEY_W;
		//shootKey = Keyboard.KEY_A;
		slowKey = Keyboard.KEY_LSHIFT;
		resetKey = Keyboard.KEY_SPACE;
		bombKey = Keyboard.KEY_E;
		
		Mouse.setGrabbed(true);
		
		usingMouse = true;
	}
	
	public static boolean isUsingMouse(){
		return usingMouse;
	}
	
	public static int getMouseX(){
		return mouseX;
	}
	
	public static int getMouseY(){
		return mouseY;
	}
	
	//Poll the input
	public static void pollInput(){
		if (!GUI.isDead()){
			
			if(usingMouse){
				mouseX = Mouse.getX();
				mouseY = Mouse.getY();
			}
			
			Unit player = GameHandler.getPlayer();
			ArrayList <PlayerHelper> playerHelpers = GameHandler.getPlayerHelpers();
			
			if (Keyboard.isKeyDown(slowKey)){
				((Player)player).slowDown(true);
			}else{
				((Player)player).slowDown(false);
			}
			
			//Move the unit according to the keyboard input
			if (Keyboard.isKeyDown(leftKey)){
				((Player)player).moveLeft();
			}
			
			if (Keyboard.isKeyDown(rightKey)){
				((Player)player).moveRight();
			}
			
			if (Keyboard.isKeyDown(downKey)){
				((Player)player).moveDown();
			}
			
			if (Keyboard.isKeyDown(upKey)){
				((Player)player).moveUp();
			}
			
			//Shoot a bullet if sufficient time has passed
			if (!usingMouse && Keyboard.isKeyDown(shootKey)){
				
				long curTime = GUI.getTime();
				
				//Check the bullet delay
				if (curTime - prevTime >= BULLET_DELAY){
					((Player)player).shootBullet();
					
					for (int i = 0; i < playerHelpers.size(); i++){
						((PlayerHelper)playerHelpers.get(i)).shootBullet();
					}
					
					prevTime = curTime;
					
				}
			}else if (usingMouse && Mouse.isButtonDown(0)){
				long curTime = GUI.getTime();
				
				float dir = (float) (Math.atan2(mouseY - player.getY(), mouseX - player.getX()));
				
				//Check the bullet delay
				if (curTime - prevTime >= BULLET_DELAY){
					((Player)player).shootDirectionalBullet(dir);
					
					for (int i = 0; i < playerHelpers.size(); i++){
						((PlayerHelper)playerHelpers.get(i)).shootDirectionalBullet(dir);
					}
					
					prevTime = curTime;
				}
			}
			
			if (Keyboard.isKeyDown(bombKey)){
				
				long curTime = GUI.getTime();
				
				//Check the bullet delay
				if (curTime - prevBombTime >= BOMB_DELAY){
					((Player)player).useBomb();
				}
				prevBombTime = curTime;
			}
		}
		//Reset game if dead
		if (GUI.isDead() && Keyboard.isKeyDown(resetKey)){
			GameHandler.resetGame();
		}
	}
	
}
