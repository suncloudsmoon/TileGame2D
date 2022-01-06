/*
* Copyright (c) 2020, suncloudsmoon
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

package com.github.suncloudsmoon.tilegame2d.character;

import java.awt.image.BufferedImage;

/**
 * Specifies the recommended methods to be inherited for a main character in a
 * simple game.
 * 
 * @author suncloudsmoon
 * @version 0.5.0
 */
public interface SimpleMainCharacter {

	/*
	 * Loads and Returns the main character's image. Note: you can use the
	 * BufferedImage Array to sort animation too.
	 */
	public BufferedImage getMainCharacterImg();

	/**
	 * Sets the random position generator (like the Random class) to a minimum &
	 * maximum
	 */
	public void setRandPos(int min, int max);

}
