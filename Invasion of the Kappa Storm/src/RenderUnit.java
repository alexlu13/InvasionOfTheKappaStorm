/* 
	File Name: RenderUnit.java
	Name: Alexander Lu
	Date: Match 27, 2015
	Description: This class acquires the unit list from the GameHandler and draws them onto the screen
*/

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

public class RenderUnit {

	private static int deathTimer = 0;
	private static int deathAnimationCounter = 0;
	
	//Method to render the unit
	public static void renderPlayerUnit (){
		
		//Get the list of units from the game handler
		Unit player = GameHandler.getPlayer();
		
		float angle = (float)Math.toDegrees(Math.atan2(Input.getMouseY() - player.getY(), Input.getMouseX() - player.getX()));
		
		//Render the player unit around its X and Y coordinates
		
		glEnable(GL_TEXTURE_2D);
		
		//Bind the texture

		glBindTexture(GL_TEXTURE_2D, TextureLoader.getPlayerTexture());
		glPushMatrix();
		glTranslatef(player.getX(), player.getY(), 0f);

		glRotatef(angle, 0.0f, 0.0f, 1.0f);
		glTranslatef(-player.getX(), -player.getY(), 0f);
		
		glColor3f(1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);{
			
			glTexCoord2f(0, 1);
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(1, 1);
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(1, 0);
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() + player.getSize() / 2);
			
			glTexCoord2f(0, 0);
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() + player.getSize() / 2);
			
		}glEnd();
		glPopMatrix();
		/*glBegin(GL_QUADS);{
			
			glTexCoord2f(0.5f - (float)Math.cos(angle), 0.5f + (float)Math.sin(angle));
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(0.5f + (float)Math.cos(angle), 0.5f + (float)Math.sin(angle));
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(0.5f + (float)Math.cos(angle), 0.5f - (float)Math.sin(angle));
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() + player.getSize() / 2);
			
			glTexCoord2f(0.5f - (float)Math.cos(angle), 0.5f - (float)Math.sin(angle));
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() + player.getSize() / 2);
			
			
		}glEnd();*/
		
		glBindTexture(GL_TEXTURE_2D, TextureLoader.getOutlineTexture());
		
		glBegin(GL_QUADS);{
			
			glTexCoord2f(0, 1);
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(1, 1);
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(1, 0);
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() + player.getSize() / 2);
			
			glTexCoord2f(0, 0);
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() + player.getSize() / 2);

		}glEnd();
		
		//glColor3f(0.902f, 0.0f, 1.0f);
		SimpleText.drawString("Kills: " + Player.getPoints(), 150, GUI.getYResolution() - 12);
		SimpleText.drawString("Time: " + (int) (Player.getTimeSurvived() / 1000), 300, GUI.getYResolution() - 12);
	}
	
	public static void renderPlayerHelpers(){
		ArrayList<PlayerHelper> helpers = GameHandler.getPlayerHelpers();
		
		glEnable(GL_TEXTURE_2D);
		
		//Bind the texture
		
		glBindTexture(GL_TEXTURE_2D, TextureLoader.getPowerUpTexture(0));
		
		glColor3f(1.0f, 1.0f, 1.0f);
		
		for (int i = 0; i < helpers.size(); i++){
			glBegin(GL_QUADS);{
				
				glTexCoord2f(0, 1);
				glVertex2f(helpers.get(i).getX() - helpers.get(i).getSize() / 2, helpers.get(i).getY() - helpers.get(i).getSize() / 2);
				
				glTexCoord2f(1, 1);
				glVertex2f(helpers.get(i).getX() + helpers.get(i).getSize() / 2, helpers.get(i).getY() - helpers.get(i).getSize() / 2);
				
				glTexCoord2f(1, 0);
				glVertex2f(helpers.get(i).getX() + helpers.get(i).getSize() / 2, helpers.get(i).getY() + helpers.get(i).getSize() / 2);
				
				glTexCoord2f(0, 0);
				glVertex2f(helpers.get(i).getX() - helpers.get(i).getSize() / 2, helpers.get(i).getY() + helpers.get(i).getSize() / 2);
				
				
			}glEnd();
			
			
			glBegin(GL_QUADS);{
				
				glTexCoord2f(0, 1);
				glVertex2f(helpers.get(i).getX() - helpers.get(i).getSize() / 2, helpers.get(i).getY() - helpers.get(i).getSize() / 2);
				
				glTexCoord2f(1, 1);
				glVertex2f(helpers.get(i).getX() + helpers.get(i).getSize() / 2, helpers.get(i).getY() - helpers.get(i).getSize() / 2);
				
				glTexCoord2f(1, 0);
				glVertex2f(helpers.get(i).getX() + helpers.get(i).getSize() / 2, helpers.get(i).getY() + helpers.get(i).getSize() / 2);
				
				glTexCoord2f(0, 0);
				glVertex2f(helpers.get(i).getX() - helpers.get(i).getSize() / 2, helpers.get(i).getY() + helpers.get(i).getSize() / 2);
				
				
			}glEnd();
		}
	}
	
	public static void renderAvailableBombs(){
		int numBombs = ((Player)GameHandler.getPlayer()).getBombs().size();
		
		glEnable(GL_TEXTURE_2D);
		
		glBindTexture(GL_TEXTURE_2D, TextureLoader.getPowerUpTexture(1));
		
		glBegin(GL_QUADS);{
			
			glTexCoord2f(0, 1);
			glVertex2f(400, GUI.getYResolution() - 12);
			
			glTexCoord2f(1, 1);
			glVertex2f(412, GUI.getYResolution() - 12);
			
			glTexCoord2f(1, 0);
			glVertex2f(412, GUI.getYResolution());
			
			glTexCoord2f(0, 0);
			glVertex2f(400, GUI.getYResolution());
			
		}glEnd();
		
		glDisable(GL_TEXTURE_2D);
		
		SimpleText.drawString("x " + numBombs, 415, GUI.getYResolution() - 12);
	}
	
	//Draw the enemies
	public static void renderEnemies(){

		ArrayList<Unit> enemies = EnemyHandler.getEnemies();
		
		glColor3f(1.0f, 1.0f, 1.0f);
		
		//Re-enable textures to draw temporary enemy sprite
		glEnable(GL_TEXTURE_2D);
		
		
		for (int i = 0; i < enemies.size(); i++){
		//Draw the unit around its X and Y coordinate
			
			glBindTexture(GL_TEXTURE_2D, TextureLoader.getOutlineTexture());
			
			glBegin(GL_QUADS);{
				
				glTexCoord2f(0, 1);
				glVertex2f(enemies.get(i).getX() - enemies.get(i).getSize() / 2, enemies.get(i).getY() - enemies.get(i).getSize() / 2);
				
				glTexCoord2f(1, 1);
				glVertex2f(enemies.get(i).getX() + enemies.get(i).getSize() / 2, enemies.get(i).getY() - enemies.get(i).getSize() / 2);
				
				glTexCoord2f(1, 0);
				glVertex2f(enemies.get(i).getX() + enemies.get(i).getSize() / 2, enemies.get(i).getY() + enemies.get(i).getSize() / 2);
				
				glTexCoord2f(0, 0);
				glVertex2f(enemies.get(i).getX() - enemies.get(i).getSize() / 2, enemies.get(i).getY() + enemies.get(i).getSize() / 2);

			}glEnd();
			
			glBindTexture(GL_TEXTURE_2D, TextureLoader.getEnemyTexture(0));
			
			glBegin(GL_QUADS);{
			
				glTexCoord2f(0, 1);
				glVertex2f(enemies.get(i).getX() - enemies.get(i).getSize() / 2, enemies.get(i).getY() - enemies.get(i).getSize() / 2);
				
				glTexCoord2f(1, 1);
				glVertex2f(enemies.get(i).getX() + enemies.get(i).getSize() / 2, enemies.get(i).getY() - enemies.get(i).getSize() / 2);
				
				glTexCoord2f(1, 0);
				glVertex2f(enemies.get(i).getX() + enemies.get(i).getSize() / 2, enemies.get(i).getY() + enemies.get(i).getSize() / 2);
				
				glTexCoord2f(0, 0);
				glVertex2f(enemies.get(i).getX() - enemies.get(i).getSize() / 2, enemies.get(i).getY() + enemies.get(i).getSize() / 2);
				
			}glEnd();
			
			
		}
	}
	
	public static void renderHitMarker(Enemy enemy){
		glColor3f(1.0f, 1.0f, 1.0f);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, TextureLoader.getHitMarkerTexture());
		
		glBegin(GL_QUADS);{
			
			glTexCoord2f(0, 1);
			glVertex2f(enemy.getX() - enemy.getSize() / 2, enemy.getY() - enemy.getSize() / 2);
			
			glTexCoord2f(1, 1);
			glVertex2f(enemy.getX() + enemy.getSize() / 2, enemy.getY() - enemy.getSize() / 2);
			
			glTexCoord2f(1, 0);
			glVertex2f(enemy.getX() + enemy.getSize() / 2, enemy.getY() + enemy.getSize() / 2);
			
			glTexCoord2f(0, 0);
			glVertex2f(enemy.getX() - enemy.getSize() / 2, enemy.getY() + enemy.getSize() / 2);
			
		}glEnd();
	}
	
	//Draw the player's death animation
	public static void playDeathAnimation(){
		deathTimer++;
		
		if(deathTimer >= 60 && deathAnimationCounter < 4){
			deathTimer = 0;
			deathAnimationCounter++;
		}
		
		Unit player = GameHandler.getPlayer();

		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, TextureLoader.getPlayerDeathAnimationTexture(deathAnimationCounter));
		
		glColor3f(1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);{
			
			glTexCoord2f(0, 1);
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(1, 1);
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() - player.getSize() / 2);
			
			glTexCoord2f(1, 0);
			glVertex2f(player.getX() + player.getSize() / 2, player.getY() + player.getSize() / 2);
			
			glTexCoord2f(0, 0);
			glVertex2f(player.getX() - player.getSize() / 2, player.getY() + player.getSize() / 2);
			
			
		}glEnd();

		SimpleText.drawString("Kills: " + Player.getPoints(), 150, GUI.getYResolution() - 12);
		SimpleText.drawString("Time: " + (int)(Player.getTimeSurvived() / 1000), 300, GUI.getYResolution() - 12);
	}
	
	public static void resetDeathAnimation(){
		deathTimer = 0;
		deathAnimationCounter = 0;
	}
}
