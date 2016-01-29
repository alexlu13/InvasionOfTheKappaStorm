/* 
	File Name: GUI.java
	Name: Alexander Lu
	Date: Match 27, 2015
	Description: This class renders all of the necessary things onto the screen
*/

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.io.*;

import mp3Support.*;

public class GUI {
	
	//Fields and constants
	private static boolean dead = false;
	
	private static int xResolution;
	private static int yResolution;
	
	private final static int MAX_FPS = 60;
	
	private static long time;
	
	private static Looper looper;
	
	private static boolean deathSongPlaying = false;
	
	//Default constructor to initialize the graphics
	public GUI(){
		xResolution = 1366;
		yResolution = 768;
		looper = new Looper("Music/DarudeTankstorm.mp3");
		looper.start();
		start();
	}
	
	//Accessors and mutators
	public static int getXResolution(){
		return xResolution;
	}
	
	public static int getYResolution(){
		return yResolution;
	}
	
	public static long getTime(){
		return time;
	}
	
	public static int getMaxFPS(){
		return MAX_FPS;
	}
	
	public static boolean isDead(){
		return dead;
	}
	
	public static void setDead(boolean isDead){
		dead = isDead;
	}
	
	//Initialize everything needed
	private void start(){

		time = System.currentTimeMillis();
		//Initialize screen and other important things
		try{
			//Display.setDisplayMode(new DisplayMode(xResolution, yResolution));
			Display.setDisplayMode(Display.getDesktopDisplayMode());
			Display.setVSyncEnabled(true);
			Display.setFullscreen(true);
			Display.create();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Display.setTitle("Invazun of de Kappa (GreayFaecNoSpaec) Strom");
		
		glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glMatrixMode(GL_MODELVIEW);
	    glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	    glOrtho(0, xResolution, 0, yResolution, 1, -1);
	    glEnable(GL_TEXTURE_2D);
	    
	  //Load the textures
	  	TextureLoader.loadAllTextures();
	    
		GameHandler.initializeUnits();
	  	
	    render();
	}
	
	//Main loop for rendering
	private void render(){
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			
			time = System.currentTimeMillis();
			
			//Update display and lock frame rate
			Display.sync(MAX_FPS);
			Display.update();
			
			//Clear the display
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			//Check if unit is not dead
			if(!dead){
				
				if (deathSongPlaying){
					looper.stop();
					looper = new Looper("Music/DarudeTankstorm.mp3");
					looper.start();
					deathSongPlaying = false;
				}
				
				EnemySpawner.updateSpawnDelay();
				
				//Render the units
				RenderUnit.renderEnemies();
				RenderUnit.renderPlayerUnit();
				RenderUnit.renderPlayerHelpers();
				RenderUnit.renderAvailableBombs();
				RenderBullets.renderBullets();
				
				//Render the power ups and move them
				RenderPowerUps.renderPowerUps();
				PowerUpHandler.movePowerUps();
				
				GameHandler.moveHelpers();
				
				//Render the health bar of the player
				RenderHealthBar.renderHealthBarText();
				RenderHealthBar.renderHealthBar();
				
				//Render the health bar of the enemies
				RenderHealthBar.renderEnemyHealthBar();
				
				//Hit detection
				((Player)GameHandler.getPlayer()).updateBullets();
				EnemyHandler.checkPlayerHit();
				
				//Move enemies
				EnemyHandler.moveEnemies();
				EnemySpawner.checkTimeForSpawningEnemy();
				
				//Poll input
				Input.pollInput();
				
				if (Input.isUsingMouse()){
					RenderCursor.renderCursor();
				}
				
			//Otherwise, play death animation and stop movement
			}else{
				
				if(!deathSongPlaying){
					looper.stop();
					looper = new Looper("Music/DeathSong.mp3");
					looper.start();
					deathSongPlaying = true;
				}
				RenderUnit.renderEnemies();
				RenderUnit.playDeathAnimation();
				RenderUnit.renderPlayerHelpers();
				RenderHealthBar.renderHealthBarText();
				RenderHealthBar.renderHealthBar();
				RenderHealthBar.renderEnemyHealthBar();
				Input.pollInput();
			}
		}
		looper.stop();
		//Destroy the display window
		Display.destroy();
		
	}
}
