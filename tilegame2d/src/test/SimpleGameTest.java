/*
* Copyright (c) 2021 Ganesha Ajjampura
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

package test;

import java.awt.Graphics2D;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.LookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.suncloudsmoon.tilegame2d.mechanics.SimpleGame;
import com.github.suncloudsmoon.tilegame2d.mechanics.SimpleKeySync;
import com.github.suncloudsmoon.tilegame2d.mechanics.SimpleTrayGrid;

public class SimpleGameTest extends SimpleGame implements SimpleKeySync, SimpleTrayGrid {

	private static final long serialVersionUID = -2428201238467393395L;
	
	private int x;
	private int y;
	private double speed;

	public SimpleGameTest(int x, int y, double speed, long delay, String title, int width, int height, LookAndFeel feel)
			throws UnsupportedLookAndFeelException, IOException {
		super(delay, title, width, height, feel);
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		initializeStuff();
	}
	
	private void initializeStuff() throws IOException {
		BufferedImage img = ImageIO.read(new File("res\\Home.png"));
		TrayIcon icon = new TrayIcon(img, "Simple Test options");
		super.addSystemTrayIcon(icon, this);
		super.addKeyBindings(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		super.frame.setVisible(false);
		super.tray.start();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToMainMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawStuff(float delta, Graphics2D g2d) {
		g2d.drawString("FPS: " + (1 / delta) + " FPS", 10, 10);
		g2d.fill3DRect(x, y, 100, 100, true);
	}

	@Override
	public void calculateInGameLoop() {
		// TODO Auto-generated method stub
		
	}
	
	// SimpleGaming interface methods

	@Override
	public void saveMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveUp(float delta) {
		y -= speed * delta;
		System.out.println(delta);
	}

	@Override
	public void moveDown(float delta) {
		y += speed * delta;
		
	}

	@Override
	public void moveLeft(float delta) {
		x -= speed * delta;
		
	}

	@Override
	public void moveRight(float delta) {
		x += speed * delta;
		
	}

	@Override
	public void spaceBarPressed(float delta) {
		System.out.println("You pressed a space bar!");
		
	}

	@Override
	public void userClicked(ActionEvent e) {
		System.out.println("Clicked!");
		
	}
	
	
}
