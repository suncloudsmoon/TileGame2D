package tilegame2d;

import java.awt.image.BufferedImage;

/**
 * Specifies the recommended methods to be inherited for a main character in a
 * simple game.
 * 
 * @author Ganesha Ajjampura
 *
 */
public interface SimpleMainCharacter {
	// In the bufferedImage method, use BufferedImage Array to sort animation!
	/**
	 * Loads and Returns the main character's image. Note: you can use the
	 * BufferedImage Array to sort animation too!
	 * 
	 * @return A BufferedImage that contains the image of the main character.
	 */
	public BufferedImage getMainCharacterImg();

}
