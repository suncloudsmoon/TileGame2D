package tilegame2d;

import javax.swing.JLabel;

/**
 * Outlines the basic structure of what a simple main menu class might look
 * like.
 * 
 * @author Ganesha Ajjampura
 *
 */
public interface SimpleMainMenu {
	// Three images
	/**
	 * Outputs a JLabel containing the play button image along with its specified
	 * bounds.
	 * 
	 * @return A JLabel containing the imageicon of the play button.
	 */
	public JLabel playButton();

	/**
	 * Outputs a JLabel containing the help button image along with its specified
	 * bounds.
	 * 
	 * @return A JLabel containing the imageicon of the help button.
	 */
	public JLabel helpButton();

	/**
	 * Outputs a JLabel containing the settings button image along with its
	 * specified bounds.
	 * 
	 * @return A JLabel containing the imageicon of the settings button.
	 */
	public JLabel settingsButton();

	/**
	 * Outputs a JLabel containing the background image along with its specified
	 * bounds.
	 * 
	 * @return A JLabel containing the imageicon of the background image.
	 */
	public JLabel backgroundImg();

	/**
	 * Can be configured to include a play button, a help button, a settings button,
	 * and/or background image.
	 */
	public void display();

}
