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

package com.github.suncloudsmoon.tilegame2d.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Creates basic file directories, save game data, and retrieve game data.
 * 
 * <pre>
<b>Example Usage:</b>
public StoreItems() {
   String path = "C:\\Items\\LocalData";
   String logFile = "C:\\Items";
   SimpleStorage item1 = new SimpleStorage(path, logFile);
}
 * </pre>
 * 
 * @author Ganesha Ajjampura
 * @version 0.7.2
 */
public class SimpleStorage {

	/**
	 * Used to log error messages.
	 */
	public Logger log;

	/**
	 * Creates a logger that can be used to log activities. If the directory doesn't
	 * exist, the method will create the directory itself.
	 * 
	 * @param fullURL directory where the log file should be stored
	 * @throws IOException
	 */
	public SimpleStorage(String fullURL) throws IOException {
		createBasicFileDirectories(fullURL);
	}

	/*
	 * Creates multiple file directories and gives the option to create a log file
	 * along with it.
	 */
	private void createBasicFileDirectories(String fullURL) throws IOException {
		if (fullURL != null) {
			String parent = new File(fullURL).getParent();
			new File(parent).mkdirs();

			log = Logger.getLogger("");
			FileHandler handler = new FileHandler(fullURL);
			SimpleFormatter formatter = new SimpleFormatter();
			handler.setFormatter(formatter);
			log.addHandler(handler);
		}

	}

	/**
	 * Returns data from a given directory. <b> Note: the only current supported
	 * file format is txt file!</b>
	 * 
	 * @param directory Where the file is located.
	 * @return the data inside the file
	 * @throws IOException
	 */
	public static String getData(String directory) throws IOException {
		StringBuilder text = new StringBuilder();
		try (BufferedReader r = new BufferedReader(new FileReader(directory))) {
			String line;
			while ((line = r.readLine()) != null) {
				text.append(line);
			}
		}
		return text.toString();
	}

	/**
	 * Saves string data to a directory specified.
	 * 
	 * @param data      Data to be stored
	 * @param directory Directory with filename and extension for the data to be
	 *                  stored in.
	 * @return If the save operation completed successfully.
	 * @throws IOException
	 */
	public static boolean saveData(String data, String directory) throws IOException {
		File f = new File(directory);
		if (f.exists()) {
			try (BufferedWriter w = new BufferedWriter(new FileWriter(directory))) {
				w.write(data);
			}
			return true;
		}
		return false;
	}

	/**
	 * Erases the data of the whole file.
	 * 
	 * @param URL link to the file
	 * @return whether the file data has been erased or not
	 * @throws IOException
	 */
	public boolean eraseData(String url) throws IOException {
		File f = new File(url);
		if (f.exists()) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, false))) {
				writer.append("");
				return true;
			}
		}
		return false;
	}

	/**
	 * Save game data to a new or existing text file in a organized manner.
	 * 
	 * @deprecated Use the JSON Library instead of using categories.
	 * @param url           A String that contains the directory path to the
	 *                      intended text file.
	 * @param category      A String that defines the name of the integer array data
	 *                      to be saved.
	 * @param saveData      An Integer Array that contains the game data to be
	 *                      saved.
	 * @param clearDocument A boolean value that indicates whether the
	 *                      BufferedWriter should clear the text document before
	 *                      writing or not.
	 * @return whether the method saved data or not
	 * @throws IOException
	 */
	public static boolean saveData(String url, String category, int[] saveData, boolean clearDocument)
			throws IOException {
		File f = new File(url);
		if (f.exists() && category != null && saveData != null) {
			try (BufferedWriter writeIt = new BufferedWriter(new FileWriter(f, !clearDocument))) {

				writeIt.append(category + ": ");
				for (int i = 0; i < saveData.length - 1; i++) {
					writeIt.append(saveData[i] + ",");
				}
				writeIt.append(Integer.toString(saveData[saveData.length - 1]));
				writeIt.newLine();

				return true;
			}

		}

		return false;
	}

	/**
	 * Removes a certain category of data from the specified text document.
	 * 
	 * @deprecated Use the JSON Library instead of using categories.
	 * @param url      A String value that contains the directory path for the text
	 *                 document to be edited.
	 * @param category A String value that represents the actual category listed in
	 *                 the specified text document.
	 * @return whether the data in the specified URL was cleared or not
	 * @throws IOException
	 */
	public static boolean eraseData(String url, String category) throws IOException {
		File f = new File(url);
		if (f.exists() && category != null) {
			ArrayList<String> rawData = new ArrayList<>();

			try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
				String line;
				while ((line = reader.readLine()) != null) {
					rawData.add(line);
				}

			}

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
				boolean isErasedData = false;
				for (int i = 0; i < rawData.size(); i++) {
					if (rawData.get(i).startsWith(category)) {
						writer.append("");
						isErasedData = true;
					} else {
						writer.append(rawData.get(i));
					}
				}
				return isErasedData;
			}

		}

		return false;
	}

	/**
	 * Locates the relevant text data based on the category supplied and outputs it.
	 * 
	 * @deprecated Use the JSON Library instead of using categories.
	 * @param URL      A String containing the directory path of destination text
	 *                 file.
	 * @param category A String containing the name of the data to be extracted.
	 * @return An Integer Array containing all the numbers to the <b>category</b>.
	 * @throws CategoryNotFoundException
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public static String[] getData(String url, String category) throws IOException {
		if (new File(url).exists() && category != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(new File(url)))) {
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.startsWith(category)) {
						return line.substring(category.length() + 1).split(",");
					}
				}
				throw new CategoryNotFoundException("Unable to find category: " + url);
			}
		}
		return new String[0];
	}

	/**
	 * Throws an exception if a requested category is not found.
	 * 
	 * @deprecated Use the JSON Library instead of using categories.
	 * @author Ganesha Ajjampura
	 *
	 */
	public static class CategoryNotFoundException extends RuntimeException {

		private static final long serialVersionUID = -2216734743214245120L;

		/**
		 * Thrown to indicate that a particular category has not been found.
		 * 
		 * @param message A String value that contains the reason of this error.
		 */
		public CategoryNotFoundException(String message) {
			super(message);
		}
	}
}
