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

import java.util.ArrayList;

/**
 * Gives you the coordinates for certain physics actions, like jumping and
 * random movements.
 * 
 * @author Ganesha Ajjampura
 * @version 0.5.3
 */
public class SimplePhysics {

	private ArrayList<Integer> randCoordinateX, randCoordinateY;

	private int[] randXCoords, randYCoords;

	/**
	 * Jumps to a specific height based on the y coordinate.
	 * 
	 * @param acceleration An Integer value that specifies the acceleration value
	 *                     for the object.
	 * @param maxHeight    An Integer value that specifies the max height of the
	 *                     object.
	 * @param yMin         An Integer value that specifies the starting y coordinate
	 *                     for the object.
	 * @return An Integer array consisting of the y coordinates throughout the
	 *         jumping process.
	 */
	public static int[] jump(int acceleration, int maxHeight, int yMin) {
		ArrayList<Integer> coordinates = new ArrayList<>();

		int maxNum = (int) Math.round(Math.sqrt(2 * maxHeight / acceleration) * 2);
		for (int i = 0; i < maxNum; i++) {
			coordinates.add((int) Math.round((0.5 * acceleration * Math.pow(i, 2))));
		}

		int[] yCoordinates = new int[coordinates.size()];
		for (int i = 0; i < coordinates.size(); i++) {
			yCoordinates[i] = coordinates.get(i);
		}
		return yCoordinates;
	}

	/**
	 * Computes random coordinates from the start to the destination.
	 * 
	 * @param speed An Integer value that specifies the speed of the object.
	 * @param x     An Integer value that specifies the start x coordinate of the
	 *              object.
	 * @param y     An Integer value that specifies the start y coordinate of the
	 *              object.
	 * @param dx    An Integer value that specifies the final x coordinate of the
	 *              object.
	 * @param dy    An Integer value that specifies the final y coordinate of the
	 *              object.
	 */
	public void setUniformMovement(int speed, int x, int y, int dx, int dy) {
		randCoordinateX = new ArrayList<>();
		randCoordinateY = new ArrayList<>();

		int vx, vy = 0;
		for (vx = x; vx <= Math.abs(dx - x); vx += speed) {
			randCoordinateX.add(vx);
			randCoordinateY.add(vy);
		}

		for (vy = y; vy <= Math.abs(dy - y); vy += speed) {
			randCoordinateX.add(vx);
			randCoordinateY.add(vy);
		}

		randXCoords = SimpleConversion.convertIntegerList(randCoordinateX);
		randYCoords = SimpleConversion.convertIntegerList(randCoordinateY);

	}

	/**
	 * Gives random X coordinates for the setRandomLongMovement method.
	 * 
	 * @return Random X Coordinates
	 */
	public int[] getRandomLongMovementX() {
		return randXCoords;
	}

	/**
	 * Gives random Y coordinates for the setRandomLongMovement method.
	 * 
	 * @return Random Y Coordinates
	 */
	public int[] getRandomLongMovementY() {
		return randYCoords;
	}

}
