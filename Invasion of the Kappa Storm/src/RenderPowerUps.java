import static org.lwjgl.opengl.GL11.*;
import java.util.*;

public class RenderPowerUps {

	public static void renderPowerUps(){
		
		ArrayList<PowerUp> powerUps = PowerUpHandler.getPowerUps();
		
		glEnable(GL_TEXTURE_2D);
		
		for (int i = 0; i < powerUps.size(); i++){
		
			glBindTexture(GL_TEXTURE_2D, TextureLoader.getPowerUpTexture(powerUps.get(i).getPowerUpID()));
			
			glBegin(GL_QUADS);{
	
				glTexCoord2f(0, 1);
				glVertex2f(powerUps.get(i).getX() - PowerUp.getDefaultSize() / 2.0f, powerUps.get(i).getY() - PowerUp.getDefaultSize() / 2.0f);
				
				glTexCoord2f(1, 1);
				glVertex2f(powerUps.get(i).getX() + PowerUp.getDefaultSize() / 2.0f, powerUps.get(i).getY() - PowerUp.getDefaultSize() / 2.0f);
				
				glTexCoord2f(1, 0);
				glVertex2f(powerUps.get(i).getX() + PowerUp.getDefaultSize() / 2.0f, powerUps.get(i).getY() + PowerUp.getDefaultSize() / 2.0f);
				
				glTexCoord2f(0, 0);
				glVertex2f(powerUps.get(i).getX() - PowerUp.getDefaultSize() / 2.0f, powerUps.get(i).getY() + PowerUp.getDefaultSize() / 2.0f);
			}
			glEnd();
		}
		glDisable(GL_TEXTURE_2D);
	}
	
	public static void renderNumberOfBombs(){
		
	}
}
