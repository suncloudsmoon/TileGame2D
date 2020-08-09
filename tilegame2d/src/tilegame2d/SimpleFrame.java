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

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Contains methods that automatically creates and sets values for JFrame.
 * 
 * @author Ganesha Ajjampura
 *
 */
public class SimpleFrame {

	public JFrame frame;

	/**
	 * Initializes the frame with default values.
	 * 
	 * @param title  A String value specifying the title of the JFrame object.
	 * @param icon   A ImageIcon object containing the image for the JFrame icon.
	 * @param width  An integer value specifying the width of the JFrame object.
	 * @param height An integer value specifying the height of the JFrame object.
	 */
	public void initializeFrame(String title, ImageIcon icon, int width, int height) {
		frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Initializes the frame with default values and the animation class added.
	 * 
	 * @param sg     A object containing the child class of SimpleGraphics.
	 * @param title  A String value specifying the title of the JFrame object.
	 * @param icon   A ImageIcon object containing the image for the JFrame icon.
	 * @param width  An integer value specifying the width of the JFrame object.
	 * @param height An integer value specifying the height of the JFrame object.
	 */
	public void initializeFrameForAnimation(SimpleGraphics sg, String title, ImageIcon icon, int width, int height) {
		frame = new JFrame();
		sg.screen();
		frame.add(sg);
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
