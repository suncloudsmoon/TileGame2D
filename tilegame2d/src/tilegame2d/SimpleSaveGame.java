package tilegame2d;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Contains methods that can create basic file directories, save game data, and
 * retrieve game data.
 * 
 * @author Ganesha Ajjampura
 *
 */
public class SimpleSaveGame {

	public static Logger log;
	private ArrayList<String> savedData = new ArrayList<>();
	private ArrayList<String> savedCategories = new ArrayList<>();;
	private ArrayList<String> sortedStringData = new ArrayList<>();
	private int[] finalIntegerData;
	private String[] sortingProcess;
	private int categoryNum;

	/**
	 * Has the potential to create multiple file directories and the option to
	 * create a log file along with it.
	 * 
	 * @param URL        A String containing the whole directory path of the
	 *                   intended destination.
	 * @param logFileURL A String containing the directory path for creating the log
	 *                   file. The String needs to be null to not create a log file.
	 * @throws SecurityException
	 * @throws IOException
	 */
	public void createBasicFileDirectories(String URL, String logFileURL) throws SecurityException, IOException {
		new File(URL).mkdirs();
		if (logFileURL != null) {
			log = Logger.getLogger("");
			FileHandler handleIt = new FileHandler(URL + "\\Log.log");
			SimpleFormatter formatIt = new SimpleFormatter();
			handleIt.setFormatter(formatIt);
			log.addHandler(handleIt);
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
	 * @throws IOException
	 */
	public void saveData(String URL, String category, int[] saveData, boolean clearDocument) throws IOException {
		BufferedWriter writeIt = new BufferedWriter(new FileWriter(new File(URL), !clearDocument));
		writeIt.append(category + ":");
		for (int i = 0; i < saveData.length; i++) {
			if (i < saveData.length - 1) {
				writeIt.append(saveData[i] + ",");
			} else {
				writeIt.append(Integer.toString(saveData[i]));
				writeIt.newLine();
			}

		}
		writeIt.close();
	}

	/**
	 * Removes a certain category of data from the specified text document.
	 * 
	 * @param URL      A String value that contains the directory path for the text
	 *                 document to be edited.
	 * @param category A String value that represents the actual category listed in
	 *                 the specified text document.
	 * @throws IOException
	 * @throws NullPointerException
	 * @throws CategoryNotFoundException
	 */
	public void clearData(String URL, String category)
			throws IOException, NullPointerException, CategoryNotFoundException {
		BufferedWriter clearIt = new BufferedWriter(new FileWriter(new File(URL)));
		if (category == null) {
			clearIt.append("");
		} else {
			findData(URL, category);
			for (int i = 0; i < savedData.size() && i != categoryNum; i++) {
				clearIt.append(savedData.get(i));
			}

		}
		clearIt.close();
	}

	/**
	 * Locates the relevant text data based on the category supplied and outputs it.
	 * 
	 * @param URL      A String containing the directory path of destination text
	 *                 file.
	 * @param category A String containing the name of the data to be extracted.
	 * @return An Integer Array containing all the numbers to the <b>category</b>.
	 * @throws CategoryNotFoundException
	 * @throws FileNotFoundException
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public int[] findData(String URL, String category)
			throws CategoryNotFoundException, FileNotFoundException, NullPointerException, IOException {
		if (savedData.isEmpty()) {
			readData(URL);
		}

		for (int i = 0; i < savedCategories.size(); i++) {
			if (savedCategories.get(i).startsWith(category)) {
				categoryNum = i;
				sortingProcess = sortedStringData.get(i).split(",");
				finalIntegerData = new int[sortingProcess.length];
				for (int z = 0; z < sortingProcess.length; z++) {
					finalIntegerData[z] = Integer.parseInt(sortingProcess[z]);
				}
				break;
			}
			if (i == savedCategories.size() - 1) {
				throw new CategoryNotFoundException("Unable to find this category: " + category);
			}
		}

		return finalIntegerData;

	}

	/**
	 * Reads a text file and sorts each line into an arrayList. Then, the data is
	 * split into categories and the String data containing the integers.
	 * 
	 * @param URL A String containing the directory path of the text file to be
	 *            read.
	 * @throws FileNotFoundException
	 * @throws NullPointerException
	 * @throws IOException
	 */
	protected void readData(String URL) throws FileNotFoundException, NullPointerException, IOException {
		BufferedReader readIt = new BufferedReader(new FileReader(new File(URL)));

		String line;
		while ((line = readIt.readLine()) != null) {
			savedData.add(line);
		}
		if (!savedData.isEmpty()) {
			for (int i = 0; i < savedData.size(); i++) {
				sortingProcess = savedData.get(i).split(":");
				savedCategories.add(sortingProcess[0]);
				sortedStringData.add(sortingProcess[1]);
			}
		} else {
			readIt.close();
			throw new NullPointerException("Text File is empty!");
		}

		readIt.close();

	}

	public class CategoryNotFoundException extends Exception {

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
