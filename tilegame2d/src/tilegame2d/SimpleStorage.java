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

	public static Logger log;

	public SimpleStorage(String fullURL, String logFileURL) throws SecurityException, IOException {
		createBasicFileDirectories(fullURL, logFileURL);
	}

	/*
	 * Creates multiple file directories and gives the option to create a log file
	 * along with it.
	 */
	private void createBasicFileDirectories(String fullURL, String logFileURL) throws SecurityException, IOException {
		if (fullURL != null && logFileURL != null) {
			new File(fullURL).mkdirs();
			log = Logger.getLogger("");
			FileHandler handler = new FileHandler(fullURL + "\\Log.log");
			SimpleFormatter formatter = new SimpleFormatter();

			handler.setFormatter(formatter);
			log.addHandler(handler);
		}

	}

	/**
	 * Save game data to a new or existing text file in a organized manner.
	 * 
	 * @param URL           A String that contains the directory path to the
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
	public static boolean saveData(String URL, String category, int[] saveData, boolean clearDocument)
			throws IOException {
		if (new File(URL).exists() && category != null && saveData != null) {
			BufferedWriter writeIt = null;
			try {
				writeIt = new BufferedWriter(new FileWriter(new File(URL), !clearDocument));

				writeIt.append(category + ": ");
				for (int i = 0; i < saveData.length - 1; i++) {
					writeIt.append(saveData[i] + ",");
				}
				writeIt.append(Integer.toString(saveData[saveData.length - 1]));
				writeIt.newLine();

				return true;
			} finally {
				writeIt.close();
			}

		}

		return false;
	}

	/**
	 * Removes a certain category of data from the specified text document.
	 * 
	 * @param URL      A String value that contains the directory path for the text
	 *                 document to be edited.
	 * @param category A String value that represents the actual category listed in
	 *                 the specified text document.
	 * @return whether the data in the specified URL was cleared or not
	 * @throws IOException
	 */
	public static boolean eraseData(String URL, String category) throws IOException {
		if (new File(URL).exists() && category != null) {
			BufferedReader reader = null;
			BufferedWriter writer = null;
			try {
				reader = new BufferedReader(new FileReader(new File(URL)));
				writer = new BufferedWriter(new FileWriter(new File(URL)));

				ArrayList<String> rawData = new ArrayList<>();

				String line;
				while ((line = reader.readLine()) != null) {
					rawData.add(line);
				}
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
			} finally {
				reader.close();
				writer.close();
			}

		}

		return false;
	}

	/**
	 * Erases the data of the whole file.
	 * 
	 * @param URL URL link to the file
	 * @return whether the file data has been erased or not
	 * @throws IOException
	 */
	public boolean eraseData(String URL) throws IOException {
		if (new File(URL).exists()) {
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(new File(URL), false));
				writer.append("");
				return true;
			} finally {
				writer.close();
			}
		}

		return false;
	}

	/**
	 * Locates the relevant text data based on the category supplied and outputs it.
	 * 
	 * @param URL      A String containing the directory path of destination text
	 *                 file.
	 * @param category A String containing the name of the data to be extracted.
	 * @return An Integer Array containing all the numbers to the <b>category</b>.
	 * @throws CategoryNotFoundException
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public static String[] getData(String URL, String category) throws CategoryNotFoundException, IOException {
		if (new File(URL).exists() && category != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(new File(URL)));
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.startsWith(category)) {
						String[] data = line.substring(category.length() + 1).split(",");
						return data;
					}
				}
				throw new CategoryNotFoundException("Unable to find category: " + URL);
			} finally {
				reader.close();
			}
		}

		return null;

	}

	/**
	 * Throws an exception if a requested category is not found.
	 * 
	 * @author Ganesha Ajjampura
	 *
	 */
	public static class CategoryNotFoundException extends Exception {

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
