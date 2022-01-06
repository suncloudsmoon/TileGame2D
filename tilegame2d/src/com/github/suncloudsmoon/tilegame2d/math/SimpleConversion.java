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

package com.github.suncloudsmoon.tilegame2d.math;

import java.util.List;

/**
 * Converts an ArrayList of specified type into the default array.
 * 
 * <pre>
<b>Example Usage:</b>
public class Move {
	public Move() {
		ArrayList<Integer> x = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			x.add(i);
		}
		// Convert the Integer arraylist into int array
		int[] xCoordinates = convertIntegerList(x);
	}
}
 * </pre>
 * 
 * @author suncloudsmoon
 * @version 0.5.2
 *
 */
public class SimpleConversion {

	// Prevents someone from creating instances of this class
	private SimpleConversion() {

	}

	/**
	 * Converts an list type of boolean values into a boolean[] array.
	 * 
	 * @param at List of boolean values
	 * @return boolean array with all the values in {@code List<Boolean> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static boolean[] convertBooleanList(List<Boolean> at) {
		boolean[] booleanList = new boolean[at.size()];
		for (int i = 0; i < booleanList.length; i++) {
			booleanList[i] = at.get(i);
		}
		return booleanList;
	}

	/**
	 * Converts an list type of byte values into a byte[] array.
	 * 
	 * @param at List of byte values
	 * @return byte array with the values of {@code List<Byte> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static byte[] convertByteList(List<Byte> at) {
		byte[] byteList = new byte[at.size()];
		for (int i = 0; i < byteList.length; i++) {
			byteList[i] = at.get(i);
		}
		return byteList;

	}

	/**
	 * Converts an list type of character values into a char[] array.
	 * 
	 * @param at list type containing characters in arrays
	 * @return char array with the values in {@code List<Character> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static char[] convertCharList(List<Character> at) {
		char[] charList = new char[at.size()];
		for (int i = 0; i < charList.length; i++) {
			charList[i] = at.get(i);
		}
		return charList;
	}

	/**
	 * Converts an List type of short type values into a short[] array.
	 * 
	 * @param at List type with short type values
	 * @return short[] with all the values in {@code List<Short> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static short[] convertShortTypeList(List<Short> at) {
		short[] shortList = new short[at.size()];
		for (int i = 0; i < shortList.length; i++) {
			shortList[i] = at.get(i);
		}
		return shortList;
	}

	/**
	 * Converts an List of Integer type into an int[] array.
	 * 
	 * @param at List type of integers
	 * @return int[] with all the values of {@code List<Integer> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static int[] convertIntegerList(List<Integer> at) {
		int[] integerList = new int[at.size()];
		for (int i = 0; i < integerList.length; i++) {
			integerList[i] = at.get(i);
		}
		return integerList;
	}

	/**
	 * Converts a List type of floats into a float[] array.
	 * 
	 * @param at List type of floats
	 * @return float[] with values in {@code List<Float> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static float[] convertFloatList(List<Float> at) {
		float[] floatList = new float[at.size()];
		for (int i = 0; i < floatList.length; i++) {
			floatList[i] = at.get(i);
		}
		return floatList;
	}

	/**
	 * Converts an List type of double into an double[] array.
	 * 
	 * @param at An List that needs to be converted into an double array.
	 * @return double[] with values in {@code List<Double> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static double[] convertDoubleList(List<Double> at) {
		double[] doubleList = new double[at.size()];
		for (int i = 0; i < doubleList.length; i++) {
			doubleList[i] = at.get(i);
		}
		return doubleList;
	}

	/**
	 * Converts a List type of long into a long[] array.
	 * 
	 * @param at List type of long
	 * @return long[] with the values in {@code List<Long> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static long[] convertLongList(List<Long> at) {
		long[] longList = new long[at.size()];
		for (int i = 0; i < longList.length; i++) {
			longList[i] = at.get(i);
		}
		return longList;
	}

	/**
	 * Converts an List of String type into a String[] array.
	 * 
	 * @param at List type of String
	 * @return String[] with values in {@code List<String> at}
	 * @deprecated use the steam() class to convert array into List
	 */
	public static String[] convertStringList(List<String> at) {
		String[] stringList = new String[at.size()];
		for (int i = 0; i < stringList.length; i++) {
			stringList[i] = at.get(i);
		}
		return stringList;
	}
}
