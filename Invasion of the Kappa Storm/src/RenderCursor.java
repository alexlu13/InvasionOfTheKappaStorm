import static org.lwjgl.opengl.GL11.*;
public class RenderCursor {

	private static final int CURSOR_SIZE = 50;
	
	public static void renderCursor(){
		
		glEnable(GL_TEXTURE_2D);
		glColor3f(1.0f, 1.0f, 1.0f);
		
		glBindTexture(GL_TEXTURE_2D, TextureLoader.getCursorTexture());
		
		glBegin(GL_QUADS);{
			
			glTexCoord2f(0, 1);
			glVertex2f(Input.getMouseX() - CURSOR_SIZE / 2.0f, Input.getMouseY() - CURSOR_SIZE / 2.0f);
			
			glTexCoord2f(1, 1);
			glVertex2f(Input.getMouseX() + CURSOR_SIZE / 2.0f, Input.getMouseY() - CURSOR_SIZE / 2.0f);
			
			glTexCoord2f(1, 0);
			glVertex2f(Input.getMouseX() + CURSOR_SIZE / 2.0f, Input.getMouseY() + CURSOR_SIZE / 2.0f);
			
			glTexCoord2f(0, 0);
			glVertex2f(Input.getMouseX() - CURSOR_SIZE / 2.0f, Input.getMouseY() + CURSOR_SIZE / 2.0f);
			
		}glEnd();
	}
	
}
