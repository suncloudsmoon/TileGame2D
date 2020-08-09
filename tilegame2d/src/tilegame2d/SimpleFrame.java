package tilegame2d;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Contains methods that automatically creates and sets values for JFrame.
 * 
 * @author Ganesha Ajjampura
 *
 */
public class SimpleFrame {

	public JFrame frame;

	/**
	 * Initializes the frame with default values.
	 * 
	 * @param title  A String value specifying the title of the JFrame object.
	 * @param icon   A ImageIcon object containing the image for the JFrame icon.
	 * @param width  An integer value specifying the width of the JFrame object.
	 * @param height An integer value specifying the height of the JFrame object.
	 */
	public void initializeFrame(String title, ImageIcon icon, int width, int height) {
		frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Initializes the frame with default values and the animation class added.
	 * 
	 * @param sg     A object containing the child class of SimpleGraphics.
	 * @param title  A String value specifying the title of the JFrame object.
	 * @param icon   A ImageIcon object containing the image for the JFrame icon.
	 * @param width  An integer value specifying the width of the JFrame object.
	 * @param height An integer value specifying the height of the JFrame object.
	 */
	public void initializeFrameForAnimation(SimpleGraphics sg, String title, ImageIcon icon, int width, int height) {
		frame = new JFrame();
		sg.screen();
		frame.add(sg);
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
