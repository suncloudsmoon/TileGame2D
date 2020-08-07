package tilegame2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class SimpleGame extends JPanel {

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
		animation(g);
	}

	// The width of the JFrame
	private void createBackgroundImg(Color backgroundColor, int width, int height) {
		background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		SimpleGame.backgroundColor = backgroundColor;
		SimpleGame.width = width;
		SimpleGame.height = height;
	}

	private void updateBackgroundImg(Graphics g) {
		g = background.createGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		background(g);
	}

	private void animation(Graphics g) {
		if (backgroundRenderNeeded) {
			updateBackgroundImg(g);
			backgroundRenderNeeded = false;
		}
		g.drawImage(background, 0, 0, null);
		update(g);
	}

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

	public void stop() {
		if (time.isRunning()) {
			time.stop();
		} else {
			throw new IllegalStateException("The timer isn't running!");
		}
	}
}
