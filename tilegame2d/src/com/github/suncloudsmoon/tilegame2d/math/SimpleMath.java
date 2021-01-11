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

package com.github.suncloudsmoon.tilegame2d.math;

import java.util.Random;

/**
 * Simplifies the process of finding specfic data from a dataset.
 * 
 * @author Ganesha Ajjampura
 * @version 0.0.1
 */
public class SimpleMath {

	private SimpleMath() {

	}

	// All numbers should be positive in order for this to work
	// Inclusive to exclusive
	public static int getRandValue(int b, int e, Random rand) {
		return rand.nextInt(e - b) + b;
	}

	/**
	 * Finds the mean of a given dataset.
	 * 
	 * @param meanValues A double array that contains all the numbers needed to find
	 *                   the mean.
	 * @return A double value that contains the mean value.
	 */
	public static double findMean(double[] meanValues) {
		double addedNum = 0;
		for (double value : meanValues) {
			addedNum += value;
		}
		return addedNum / 2;
	}

	/**
	 * Finds the mode of a given dataset.
	 * 
	 * @param modeValues A double array list of numbers that are necessary for the
	 *                   calculation of mode.
	 * @return A double array containing a list of all modes in the dataset.
	 */
	public static double findMode(double[] modeValues) {
		int modeNum = 0;

		if (modeValues.length > 0) {
			int[] modeRepeat = new int[modeValues.length];

			for (int i = 0; i < modeValues.length; i++) {
				for (int j = 0; j < modeValues.length; j++) {
					if (modeValues[i] == modeValues[j]) {
						modeRepeat[i]++;
					}
				}
			}

			int previousRecord = 0;
			for (int i = 0; i < modeRepeat.length; i++) {
				if (modeRepeat[i] > previousRecord) {
					modeNum = i;
					previousRecord = modeRepeat[i];
				}
			}
		} else {
			throw new IllegalArgumentException("The double[] length must be greather than 0!");
		}

		return modeValues[modeNum];
	}

	/**
	 * Throws a Runtime exception if no modes are found in a particular set of
	 * numbers.
	 * 
	 * @author Ganesha Ajjampura
	 *
	 */
	public static class ModeNotFoundException extends RuntimeException {

		private static final long serialVersionUID = -8902909430621854017L;

		/**
		 * Throws a message when the mode for a particular set of numbers is not found.
		 * 
		 * @param message A string value that contains the reason for the exception.
		 */
		public ModeNotFoundException(String message) {
			super(message);
		}
	}
}
