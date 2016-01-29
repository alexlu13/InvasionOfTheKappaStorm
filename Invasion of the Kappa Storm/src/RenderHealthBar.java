import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

public class RenderHealthBar {
	
	private static final float DIST_FROM_TOP = 10.0f;
	private static final float DIST_FROM_TEXT = 5.0f;
	private static final float DIST_FROM_UNIT = 3.0f;
	
	private static final float LENGTH_OF_BAR = 100.0f;
	private static final float HEIGHT_OF_ENEMY_BAR = 5.0f;
	
	//Render the enemies' health as a bar
	public static void renderEnemyHealthBar(){
		
		ArrayList enemies = EnemyHandler.getEnemies();
		
		glDisable(GL_TEXTURE_2D);
		
		for (int i = 0; i < enemies.size(); i++){

			//Draw the life bar
			glColor3f(1.0f, 1.0f, 1.0f);
			glBegin(GL_QUADS);{
				
				glVertex2f(((Unit) enemies.get(i)).getX() - ((Unit) enemies.get(i)).getSize() / 2.0f, 
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT);
				
				glVertex2f(((Unit) enemies.get(i)).getX() + ((Unit) enemies.get(i)).getSize() / 2.0f, 
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT);
				
				glVertex2f(((Unit) enemies.get(i)).getX() + ((Unit) enemies.get(i)).getSize() / 2.0f, 
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT + HEIGHT_OF_ENEMY_BAR);
				
				glVertex2f(((Unit) enemies.get(i)).getX() - ((Unit) enemies.get(i)).getSize() / 2.0f, 
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT + HEIGHT_OF_ENEMY_BAR);
				
			}glEnd();
			
			//Draw the amount of life remaining
			glColor3f(1.0f, 0.0f, 0.0f);
			glBegin(GL_QUADS);{
						
				glVertex2f(((Unit) enemies.get(i)).getX() - ((Unit) enemies.get(i)).getSize() / 2.0f,
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT);
				
				glVertex2f((((Unit) enemies.get(i)).getX() - ((Unit) enemies.get(i)).getSize() / 2.0f) + 
						(((Unit) enemies.get(i)).getSize() * getPercentHPLeft((Unit)enemies.get(i))), 
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT);
				
				glVertex2f((((Unit) enemies.get(i)).getX() - ((Unit) enemies.get(i)).getSize() / 2.0f) +
						(((Unit) enemies.get(i)).getSize() * getPercentHPLeft((Unit)enemies.get(i))),
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT + HEIGHT_OF_ENEMY_BAR);
				
				glVertex2f(((Unit) enemies.get(i)).getX() - ((Unit) enemies.get(i)).getSize() / 2.0f, 
						((Unit) enemies.get(i)).getY() + ((Unit)enemies.get(i)).getSize() / 2.0f + DIST_FROM_UNIT + HEIGHT_OF_ENEMY_BAR);
					
			}glEnd();
		}
	}
	
	private static float getPercentHPLeft(Unit unit){
		
		return ((float)unit.getHealth()) / unit.getMaxHealth();
	}
	
	public static void renderHealthBar(){
		
		Player player = (Player) GameHandler.getPlayer();
		
		glDisable(GL_TEXTURE_2D);
		
		//Draw the life bar
		glColor3f(1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);{
			
			glVertex2f(0, GUI.getYResolution() - DIST_FROM_TOP * 2 - DIST_FROM_TEXT);
			
			glVertex2f(LENGTH_OF_BAR, GUI.getYResolution() - DIST_FROM_TOP * 2 - DIST_FROM_TEXT);
			
			glVertex2f(LENGTH_OF_BAR, GUI.getYResolution() - DIST_FROM_TOP - DIST_FROM_TEXT);
			
			glVertex2f(0, GUI.getYResolution() - DIST_FROM_TOP - DIST_FROM_TEXT);
			
		}glEnd();
		
		//Draw the amount of life remaining
		
		glColor3f(1.0f, 0.0f, 0.0f);
		glBegin(GL_QUADS);{
					
			glVertex2f(0, GUI.getYResolution() - DIST_FROM_TOP * 2 - DIST_FROM_TEXT);
		
			glVertex2f(LENGTH_OF_BAR * getPercentHPLeft((Unit) player), GUI.getYResolution() - DIST_FROM_TOP * 2 - DIST_FROM_TEXT);
			
			glVertex2f(LENGTH_OF_BAR * getPercentHPLeft((Unit) player), GUI.getYResolution() - DIST_FROM_TOP - DIST_FROM_TEXT);
			
			glVertex2f(0, GUI.getYResolution() - DIST_FROM_TOP - DIST_FROM_TEXT);
				
		}glEnd();
		
	}
	
	//Render the player's health as text
	public static void renderHealthBarText(){
		
		glDisable(GL_TEXTURE_2D);
		
		Player player = (Player) GameHandler.getPlayer();
		
		SimpleText.drawString(player.getHealth() + "/" + player.getMaxHealth(), 0, GUI.getYResolution() - DIST_FROM_TOP);
	}
}
