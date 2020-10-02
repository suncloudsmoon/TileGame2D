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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 * Contains methods that simply the process of key bindings.
 * 
 * @version 0.5.8
 * @author Ganesha Ajjampura
 *
 */
public abstract class SimpleGameKeys {

	private Action goUp;
	private Action goDown;
	private Action goLeft;
	private Action goRight;
	private Action spaceBar;
	private Action saveMenu;

	// Key W is pressed
	public abstract void moveUp();

	// Key S is pressed
	public abstract void moveDown();

	// Key A is pressed
	public abstract void moveLeft();

	// Key D is pressed
	public abstract void moveRight();

	// Space bar is pressed
	public abstract void spaceBar();

	// Escape key is pressed
	public abstract void saveMenu();

	/**
	 * Configures the W A S D keys to a binding to ensure that the game keys can
	 * receive input signals when JComponent is in focus.
	 * 
	 * @param sf Instance of SimpleFrame object
	 */
	public void setGameKeyBindings(SimpleFrame sf) {
		goUp = new goUp();
		goDown = new goDown();
		goLeft = new goLeft();
		goRight = new goRight();

		spaceBar = new spaceBar();
		saveMenu = new saveMenu();

		JRootPane rootOfAll = sf.frame.getRootPane();

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

	/**
	 * Configures W A S D keys to an input and action map with key bindings.
	 * 
	 * @param frame A JFrame object specifying the main frame used by the game.
	 */
	public void setGameKeyBindings(JFrame frame) {
		goUp = new goUp();
		goDown = new goDown();
		goLeft = new goLeft();
		goRight = new goRight();

		spaceBar = new spaceBar();
		saveMenu = new saveMenu();

		JRootPane rootOfAll;
		if (frame != null) {
			rootOfAll = frame.getRootPane();
		} else {
			throw new NullPointerException("The JFrame object points to a null location!");
		}

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

	private class goUp extends AbstractAction {

		private static final long serialVersionUID = -5327395479500958279L;

		@Override
		public void actionPerformed(ActionEvent e) {
			moveUp();

		}

	}

	private class goDown extends AbstractAction {

		private static final long serialVersionUID = -2867139883452440832L;

		@Override
		public void actionPerformed(ActionEvent e) {
			moveDown();

		}

	}

	private class goLeft extends AbstractAction {

		private static final long serialVersionUID = -3638859233582503618L;

		@Override
		public void actionPerformed(ActionEvent e) {
			moveLeft();

		}

	}

	private class goRight extends AbstractAction {

		private static final long serialVersionUID = 7041504566770698300L;

		@Override
		public void actionPerformed(ActionEvent e) {
			moveRight();

		}

	}

	private class spaceBar extends AbstractAction {

		private static final long serialVersionUID = 7041504566770698300L;

		@Override
		public void actionPerformed(ActionEvent e) {
			spaceBar();

		}

	}

	private class saveMenu extends AbstractAction {

		private static final long serialVersionUID = 7041504566770698300L;

		@Override
		public void actionPerformed(ActionEvent e) {
			saveMenu();

		}

	}
}