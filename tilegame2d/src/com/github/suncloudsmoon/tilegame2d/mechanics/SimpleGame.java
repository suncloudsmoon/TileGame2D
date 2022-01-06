/*
* Copyright (c) 2020-2021, suncloudsmoon
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

package com.github.suncloudsmoon.tilegame2d.mechanics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.LookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class SimpleGame extends SimpleApp {

	private static final long serialVersionUID = 5729623020860286431L;

	protected Graphics2D g2d;

	protected boolean isRunning = true;
	private long previousTime;
	private float delta;
	private long delay;

	public abstract void goToMainMenu();

	public SimpleGame(long delay, String title, int width, int height, LookAndFeel feel)
			throws UnsupportedLookAndFeelException {
		super(title, width, height, feel);
		this.delay = delay;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;
		drawStuff(delta, g2d);
	}

	public void addKeyBindings(SimpleKeySync sk) {
		SimpleKeyConfig keys = new SimpleKeyConfig(sk, this);
		keys.setGameKeyBindings();
	}

	@Override
	public void start(boolean gameStart) {
		super.start(gameStart);
		frame.setResizable(false);
		startGameLoop();
	}

	public void startGameLoop() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isRunning) {
					previousTime = System.nanoTime();
					repaint();
					calculateInGameLoop();

					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					delta = (float) ((System.nanoTime() - previousTime) / (1e+9));
				}

			}
		});
		t.start();
	}

	public abstract void drawStuff(float delta, Graphics2D g2d);

	public abstract void calculateInGameLoop();

	public float getDelta() {
		return delta;
	}

}
