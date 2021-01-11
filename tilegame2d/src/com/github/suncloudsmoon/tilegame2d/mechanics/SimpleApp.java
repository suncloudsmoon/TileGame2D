/*
Copyright (c) 2020, 2021, Ganesha Ajjampura
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

package com.github.suncloudsmoon.tilegame2d.mechanics;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.WindowListener;

import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public abstract class SimpleApp extends JPanel implements WindowListener {

	private static final long serialVersionUID = -5145254224541995243L;

	private SystemTray sysTray;
	private SimpleTray tray;
	protected SimpleFrame frame;
	protected JPanel panel;

	private int width;
	private int height;

	public SimpleApp(String title, int width, int height, LookAndFeel feel) throws UnsupportedLookAndFeelException {
		this.width = width;
		this.height = height;

		initializeStuff(title, feel);
	}

	private void initializeStuff(String title, LookAndFeel feel) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(feel);
		frame = new SimpleFrame(title);
		panel = new JPanel();
		panel.setLayout(null);
	}

	public boolean addSystemTrayIcon(TrayIcon icon, SimpleTrayGrid trayGrid) {
		if (SystemTray.isSupported()) {
			sysTray = SystemTray.getSystemTray();
			tray = new SimpleTray(trayGrid, sysTray);
			tray.configure(icon, frame);
			return true;
		} else {
			return false;
		}
	}

	public void start() {
		frame.getContentPane().add(panel);
		frame.setSize(width + 15, height + 15);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
	}
}
