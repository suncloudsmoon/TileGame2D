package tilegame2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Contains the basic framework for creating a simple game.
 * 
 * @author Ganesha Ajjampura
 *
 */
public abstract class SimpleGraphics extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Timer time;
	private static ActionListener gameContinue;

	private static Color backgroundColor;
	private static int width;
	private static int height;

	private static boolean backgroundRenderNeeded = true;
	private static boolean backgroundImageCreated = false;

	private static BufferedImage background;

	public abstract void background(Graphics g);

	public abstract void update(Graphics g);

	public abstract void screen();

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		startAnimation(g);
	}

	private void createBackgroundImg(Color backgroundColor, int width, int height) {
		background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		SimpleGraphics.backgroundColor = backgroundColor;
		SimpleGraphics.width = width;
		SimpleGraphics.height = height;
	}

	/**
	 * Replaces the old background image with a new background image with the
	 * specified drawings made with Graphics g object.
	 * 
	 * @param g A Graphics object
	 */
	protected void updateBackgroundImg(Graphics g) {
		g = background.createGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		background(g);
	}

	private void startAnimation(Graphics g) {
		if (backgroundRenderNeeded) {
			updateBackgroundImg(g);
			backgroundRenderNeeded = false;
		}
		g.drawImage(background, 0, 0, null);
		update(g);
	}

	/**
	 * Starts to repaint the component with a <b>delay</b>.
	 * 
	 * @param delay            An integer specifying the amount of time between the
	 *                         component's refresh.
	 * @param backgroundWidth  An integer value specifying the width of the
	 *                         background image.
	 * @param backgroundHeight An integer value specifying the width of the
	 *                         background image.
	 * @param backgroundColor  A Color object specifying the color of the background
	 *                         image.
	 */
	public void start(int delay, int backgroundWidth, int backgroundHeight, Color backgroundColor) {
		if (backgroundImageCreated == false) {
			createBackgroundImg(backgroundColor, backgroundWidth, backgroundHeight);
			backgroundImageCreated = true;
		}
		gameContinue = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();

			}

		};
		time = new Timer(delay, gameContinue);
		time.setRepeats(true);
		time.start();
	}

	/**
	 * Stops the timer if it's running.
	 */
	public void stop() {
		if (time.isRunning()) {
			time.stop();
		}
	}

	/**
	 * Checks the boolean value of <b>backgroundRender</b>.
	 * 
	 * @return A boolean value indicating whether <b>backgroundRenderNeeded</b> is
	 *         true or false.
	 */
	public static boolean isBackgroundRenderNeeded() {
		return backgroundRenderNeeded;
	}

	/**
	 * Sets <b>backgroundRenderNeeded</b> to the specified condition.
	 * 
	 * @param backgroundRenderNeeded A boolean value specifying whether a background
	 *                               update is necessary.
	 */
	public static void setBackgroundRenderNeeded(boolean backgroundRenderNeeded) {
		SimpleGraphics.backgroundRenderNeeded = backgroundRenderNeeded;
	}

}
