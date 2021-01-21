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

package com.github.suncloudsmoon.tilegame2d.mechanics;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

public class SimpleTray implements ActionListener, MouseMotionListener, WindowStateListener {

	private SimpleTrayGrid stg;
	private SystemTray tray;
	private TrayIcon icon;
	private boolean isSupported;

	public SimpleTray(SimpleTrayGrid stg, SystemTray tray) {
		this.stg = stg;
		this.tray = tray;
	}

	public void configure(TrayIcon icon, JFrame frame) {
		this.icon = icon;
		frame.addWindowStateListener(this);
		icon.addActionListener(this);
		icon.addMouseMotionListener(this);
	}

	public void start() {
		try {
			tray.add(icon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remove() {
		tray.remove(icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		stg.userClicked(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		if (e.getNewState() == JFrame.ICONIFIED) {
			start();
		} else if (e.getNewState() == JFrame.NORMAL) {
			remove();
		}

	}

}
