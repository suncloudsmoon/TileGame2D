/*
Copyright (c) 2020 Ganesha Ajjampura

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package tilegame2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Contains the basic framework for creating a simple game.
 * 
 * @author Ganesha Ajjampura
 * @version 0.6.2
 */
public abstract class SimpleGraphics extends JPanel {

	private static final long serialVersionUID = -1790446721372453207L;

	// Objects
	private Timer time;
	private ActionListener gameContinue;
	private BufferedImage background;

	// Time Calculation
	private float previousTime;
	private float currentTime;

	// Background
	private Color backgroundColor;
	private int width;
	private int height;

	// Keep track of stuff that is already stored in memory
	private boolean backgroundRenderNeeded = true;
	private boolean backgroundImageCreated = false;

	// Must be implemented in the child class
	public abstract void background(Graphics g);

	public abstract void update(Graphics g, float delta);

	public abstract void run();

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		startAnimation(g);
	}

	private void createBackgroundImg(Color backgroundColor, int width, int height) {
		this.backgroundColor = backgroundColor;
		this.width = width;
		this.height = height;

		background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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

	// Calls on the abstract update() method to draw stuff according to that method!
	private void startAnimation(Graphics g) {
		if (backgroundRenderNeeded) {
			updateBackgroundImg(g);
			backgroundRenderNeeded = false;
		}
		g.drawImage(background, 0, 0, null);
		currentTime = System.nanoTime();
		update(g, currentTime - previousTime);
	}

	/**
	 * Starts to repaint the component with a <b>delay</b>.
	 * 
	 * @param delay An integer specifying the amount of time between the component's
	 *              refresh.
	 * @param bW    An integer value specifying the width of the background image.
	 * @param bH    An integer value specifying the width of the background image.
	 * @param bC    A Color object specifying the color of the background image.
	 */
	public void start(int delay, int bW, int bH, Color bC) {

		if (backgroundImageCreated == false) {
			createBackgroundImg(backgroundColor, bW, bH);
			backgroundImageCreated = true;
		}
		gameContinue = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				previousTime = currentTime;
				repaint();
			}

		};
		time = new Timer(delay, gameContinue);
		time.setRepeats(true);
		time.start();
	}

	/**
	 * Starts to repaint the component with a <b>delay</b>.
	 * 
	 * @param title A String value that specifies the title of the JFrame.
	 * @param icon  A ImageIcon object that contains the icon for the JFrame.
	 * @param delay An integer specifying the amount of time between the component's
	 *              refresh.
	 * @param w     An integer value specifying the width of the background image.
	 * @param h     An integer value specifying the width of the background image.
	 * @param c     A Color object specifying the color of the background image.
	 * @param sg    A SimpleGraphics child class object that is used to draw stuff.
	 */
	public void start(String title, ImageIcon icon, int delay, int w, int h, Color c, SimpleGraphics sg) {

		SimpleFrame sf = new SimpleFrame(title, icon, w, h, sg);

		if (backgroundImageCreated == false) {
			createBackgroundImg(backgroundColor, w, h);
			backgroundImageCreated = true;
		}
		gameContinue = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				previousTime = currentTime;
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
	public boolean stop() {
		if (time.isRunning()) {
			time.stop();
			return true;
		}
		return false;
	}

	/**
	 * Checks the boolean value of <b>backgroundRender</b>.
	 * 
	 * @return A boolean value indicating whether <b>backgroundRenderNeeded</b> is
	 *         true or false.
	 */
	public boolean isBackgroundRenderNeeded() {
		return backgroundRenderNeeded;
	}

	/**
	 * Sets <b>backgroundRenderNeeded</b> to the specified condition.
	 * 
	 * @param backgroundRenderNeeded A boolean value specifying whether a background
	 *                               update is necessary.
	 */
	public void setBackgroundRenderNeeded(boolean backgroundRenderNeeded) {
		this.backgroundRenderNeeded = backgroundRenderNeeded;
	}

}
