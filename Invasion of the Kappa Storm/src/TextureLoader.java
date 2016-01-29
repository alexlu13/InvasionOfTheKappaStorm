import static org.lwjgl.opengl.GL11.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;


public class TextureLoader {

	private static final int NUM_ENEMY_TEXTURES = 1;
	private static final int NUM_PLAYER_TEXTURES = 1;
	private static final int NUM_PLAYER_DEATH_ANIMATION_TEXTURES = 5;
	private static final int NUM_POWER_UP_TEXTURES = 2;
	
	private static int[] enemyTextures = new int[NUM_ENEMY_TEXTURES];
	private static int[] playerTextures = new int[NUM_PLAYER_TEXTURES];
	private static int[] playerDeathAnimationTextures = new int[NUM_PLAYER_DEATH_ANIMATION_TEXTURES];
	private static int[] powerUpTextures = new int[NUM_POWER_UP_TEXTURES];
	private static int hitMarkerTexture;
	private static int outlineTexture;
	private static int bulletTexture;
	private static int cursorTexture;
	
	public static int getEnemyTexture(int unitID){
		return enemyTextures[unitID];
	}
	
	public static int getPlayerTexture(){
		return playerTextures[0];
	}
	
	public static int getPlayerDeathAnimationTexture(int unitID){
		return playerDeathAnimationTextures[unitID];
	}
	
	public static int getPowerUpTexture(int unitID){
		return powerUpTextures[unitID];
	}
	
	public static int getHitMarkerTexture(){
		return hitMarkerTexture;
	}
	
	public static int getOutlineTexture(){
		return outlineTexture;
	}
	
	public static int getBulletTexture(){
		return bulletTexture;
	}
	
	public static int getCursorTexture(){
		return cursorTexture;
	}
	
	//Method to load all of the textures
	public static void loadAllTextures(){
		enemyTextures[0] = loadTexture("Images/Enemies/temp.png");
		playerTextures[0] = loadTexture("Images/PlayerUnit/AliveUnit/BibleThump.png");
		powerUpTextures[0] = loadTexture("Images/PowerUps/Helpers/FrankerZ.png");
		powerUpTextures[1] = loadTexture("Images/PowerUps/Bombs/Bomb.png");
		
		hitMarkerTexture = loadTexture("Images/Enemies/HitMarker.png");
		outlineTexture = loadTexture("Images/Enemies/Outline0.png");
		bulletTexture = loadTexture("Images/PlayerUnit/BulletTextures/Tears.png");
		cursorTexture = loadTexture("Images/PlayerUnit/Cursor/Cursor.png");
		
		for (int i = 0; i < NUM_PLAYER_DEATH_ANIMATION_TEXTURES; i++){
			playerDeathAnimationTextures[i] = loadTexture("Images/PlayerUnit/DeathAnimation/Death" + i + ".png");
		}
	}
	
	private static int loadTexture(String fileName){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
		
		for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }
		
		buffer.flip();
		
	    int textureID = glGenTextures(); //Generate texture ID
	    glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID
	        
	    //Setup wrap mode
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

	    //Setup texture scaling filtering
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	        
	    //Send texel data to OpenGL
	    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	    return textureID;
	}
}
