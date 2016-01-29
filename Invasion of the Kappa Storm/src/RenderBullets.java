import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;

public class RenderBullets {
	
	//Render the bullets
	public static void renderBullets(){
		ArrayList<Bullet> bullets = ((Player)GameHandler.getPlayer()).getBullets();
		
		glColor3f(1.0f, 1.0f, 1.0f);
		
		//Draw the bullets from the array list
		for (int i = 0; i < bullets.size(); i++){
			
			glEnable(GL_TEXTURE_2D);
			glBindTexture(GL_TEXTURE_2D, TextureLoader.getBulletTexture());
			
			glBegin(GL_QUADS);{
				
				glTexCoord2f(0, 1);
				glVertex2f(bullets.get(i).getX() - bullets.get(i).getSize() / 2, bullets.get(i).getY() - bullets.get(i).getSize() / 2);
				
				glTexCoord2f(1, 1);
				glVertex2f(bullets.get(i).getX() + bullets.get(i).getSize() / 2, bullets.get(i).getY() - bullets.get(i).getSize() / 2);
				
				glTexCoord2f(1, 0);
				glVertex2f(bullets.get(i).getX() + bullets.get(i).getSize() / 2, bullets.get(i).getY() + bullets.get(i).getSize() / 2);
				
				glTexCoord2f(0, 0);
				glVertex2f(bullets.get(i).getX() - bullets.get(i).getSize() / 2, bullets.get(i).getY() + bullets.get(i).getSize() / 2);
				
			}glEnd();
			
		}
	}
}
