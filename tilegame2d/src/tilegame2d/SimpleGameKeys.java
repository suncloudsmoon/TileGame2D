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
 * @author Ganesha Ajjampura
 *
 */
public abstract class SimpleGameKeys {

	private Action goUp;
	private Action goDown;
	private Action goLeft;
	private Action goRight;

	public abstract void moveUp();

	public abstract void moveDown();

	public abstract void moveLeft();

	public abstract void moveRight();

	/**
	 * Configures W A S D keys to an input and action map with key bindings.
	 * 
	 * @param frame A JFrame object specifying the main frame used by the game.
	 */
	public void configureWasdKeyBindings(JFrame frame) {
		goUp = new goUp();
		goDown = new goDown();
		goLeft = new goLeft();
		goRight = new goRight();

		JRootPane rootOfAll = frame.getRootPane();

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
}
