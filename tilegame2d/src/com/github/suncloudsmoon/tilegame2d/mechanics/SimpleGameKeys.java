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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 * Contains methods that simply the process of key bindings.
 * 
 * @version 0.5.8
 * @author Ganesha Ajjampura
 *
 */
public class SimpleGameKeys {

	private SimpleFrame sf;
	private SimpleGaming sg;

	private Action goUp;
	private Action goDown;
	private Action goLeft;
	private Action goRight;
	private Action spaceBar;
	private Action saveMenu;

	public SimpleGameKeys(SimpleGaming sg, SimpleFrame sf) {
		this.sf = sf;
		this.sg = sg;
	}

	/**
	 * Configures the W A S D keys to a binding to ensure that the game keys can
	 * receive input signals when JComponent is in focus.
	 * 
	 * @param sf Instance of SimpleFrame object
	 */
	public void setGameKeyBindings() {
		goUp = new GoUp();
		goDown = new GoDown();
		goLeft = new GoLeft();
		goRight = new GoRight();

		spaceBar = new SpaceBar();
		saveMenu = new SaveMenu();

		JRootPane rootOfAll = sf.getRootPane();

		// Move Up
		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "goUp");
		rootOfAll.getActionMap().put("goUp", goUp);

		// Move Down
		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "goDown");
		rootOfAll.getActionMap().put("goDown", goDown);

		// Move Left
		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "goLeft");
		rootOfAll.getActionMap().put("goLeft", goLeft);

		// Move Right
		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "goRight");
		rootOfAll.getActionMap().put("goRight", goRight);

		// Space Bar
		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "spaceBar");
		rootOfAll.getActionMap().put("spaceBar", spaceBar);

		// Save Menu
		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "saveMenu");
		rootOfAll.getActionMap().put("saveMenu", saveMenu);
	}

	private class GoUp extends AbstractAction {

		private static final long serialVersionUID = -5327395479500958279L;

		@Override
		public void actionPerformed(ActionEvent e) {
			sg.moveUp();

		}

	}

	private class GoDown extends AbstractAction {

		private static final long serialVersionUID = -2867139883452440832L;

		@Override
		public void actionPerformed(ActionEvent e) {
			sg.moveDown();

		}

	}

	private class GoLeft extends AbstractAction {

		private static final long serialVersionUID = -3638859233582503618L;

		@Override
		public void actionPerformed(ActionEvent e) {
			sg.moveLeft();

		}

	}

	private class GoRight extends AbstractAction {

		private static final long serialVersionUID = 7041504566770698300L;

		@Override
		public void actionPerformed(ActionEvent e) {
			sg.moveRight();

		}

	}

	private class SpaceBar extends AbstractAction {

		private static final long serialVersionUID = 7041504566770698300L;

		@Override
		public void actionPerformed(ActionEvent e) {
			sg.spaceBarPressed();

		}

	}

	private class SaveMenu extends AbstractAction {

		private static final long serialVersionUID = 7041504566770698300L;

		@Override
		public void actionPerformed(ActionEvent e) {
			sg.saveMenu();

		}

	}
}