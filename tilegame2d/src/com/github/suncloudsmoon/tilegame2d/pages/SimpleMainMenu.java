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

package com.github.suncloudsmoon.tilegame2d.pages;

import javax.swing.JLabel;

/**
 * Outlines the basic structure of what a simple main menu class might look
 * like.
 * 
 * @version 0.5.0
 * @author Ganesha Ajjampura
 *
 */
public interface SimpleMainMenu {
	// Three images in total
	/*
	 * Outputs a JLabel containing the play button image along with its specified
	 * bounds.
	 */
	public JLabel playButton();

	/*
	 * Outputs a JLabel containing the help button image along with its specified
	 * bounds.
	 */
	public JLabel helpButton();

	/*
	 * Outputs a JLabel containing the settings button image along with its
	 * specified bounds.
	 */
	public JLabel settingsButton();

	/*
	 * Outputs a JLabel containing the background image along with its specified
	 * bounds.
	 */
	public JLabel backgroundImg();

	/*
	 * Can be configured to include a play button, a help button, a settings button,
	 * and/or background image.
	 */
	public void display();

}
