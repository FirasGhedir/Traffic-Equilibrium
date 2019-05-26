package evaluation;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import graphCharacteristics.CharacteristicsCalculatorMain;
import graphModel.Graphs;

/**
 * University Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          In the Evaluation class all relevant data files files get build for
 *          evaluation
 */
public class CheckTollboothsManually {

	// --- flags, iterators etc. ----
	static boolean extension;
	static int iterator;
	static boolean debugFlag;
	static boolean flagMINTB;
	static boolean flagGAMINTB;

	// --- files and folders ----
	static File folder;
	static File[] listOfFiles;

	// --- lists
	static ArrayList<File> listOfGamintbFiles;
	static ArrayList<File> listOfMintbFiles;

	static ArrayList<Integer> numbTollboothsGamintb;
	static ArrayList<Integer> numbTollboothsMintb;

	// --- streams, reader ---
	static FileInputStream fstream;
	static DataInputStream in;
	static BufferedReader br;

	// --- paths ---
	static String path;
	static String pathInstances;

	// --- graph ---
	static Graphs graph;

	// --- characteristic calculator
	static CharacteristicsCalculatorMain characteristics;

	/**
	 * 
	 * @param listOfGamintbFiles
	 */
	public static void setListOfGamintbFiles(ArrayList<File> listOfGamintbFiles) {
		CheckTollboothsManually.listOfGamintbFiles = listOfGamintbFiles;
	}

	/**
	 * 
	 * @param listOfMintbFiles
	 */
	public static void setListOfMintbFiles(ArrayList<File> listOfMintbFiles) {
		CheckTollboothsManually.listOfMintbFiles = listOfMintbFiles;
	}

	/**
	 * 
	 * @param graph
	 */
	public static void setGraph(Graphs graph) {
		CheckTollboothsManually.graph = graph;
	}

	/**
	 * 
	 * @param iterator
	 */
	public static void setIterator(int iterator) {
		CheckTollboothsManually.iterator = iterator;
	}

	/**
	 * 
	 * @param debugFlag
	 */
	public static void setDebugFlag(boolean debugFlag) {
		CheckTollboothsManually.debugFlag = debugFlag;
	}

	/**
	 * 
	 * @param pathInstances
	 */
	public static void setPath(String path) {
		CheckTollboothsManually.path = path;
	}

	/**
	 * 
	 * @param pathHeuristics
	 */
	public static void setPathInstances(String pathInstances) {
		CheckTollboothsManually.pathInstances = pathInstances;
	}

	/**
	 * 
	 * @param numbTollboothsGamintb
	 */
	public static void setNumbTollboothsGamintb(ArrayList<Integer> numbTollboothsGamintb) {
		CheckTollboothsManually.numbTollboothsGamintb = numbTollboothsGamintb;
	}

	/**
	 * 
	 * @param numbTollboothsMintb
	 */
	public static void setNumbTollboothsMintb(ArrayList<Integer> numbTollboothsMintb) {
		CheckTollboothsManually.numbTollboothsMintb = numbTollboothsMintb;
	}

	/**
	 * 
	 */
	public static void init() {

		setListOfGamintbFiles(new ArrayList<File>());
		setListOfMintbFiles(new ArrayList<File>());

		setNumbTollboothsGamintb(new ArrayList<Integer>());
		setNumbTollboothsMintb(new ArrayList<Integer>());

		folder = new File(pathInstances);
		listOfFiles = folder.listFiles();
		ArrayList<File> listFilesTmp = new ArrayList<>(Arrays.asList(listOfFiles));
		listFilesTmp.sort(Comparator.naturalOrder());

		ArrayList<String> listFilesToCheck = new ArrayList<String>();

		try {

			// streams, reader
			fstream = new FileInputStream(path);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));

			String strLine = null;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				listFilesToCheck.add(strLine);
			}
			// --- close streams, reader ---
			fstream.close();
			in.close();
			br.close();

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		System.out.println("lines to add: \n");
		for (File file : listFilesTmp) {
			for (String string : listFilesToCheck) {
				if (file.getName().startsWith(string)) {
					int numberTollbooths = (int) countTollbooths(file);
					System.out.println(file.getName() + " :\n-----------------\n Execution Time:  1000000 ms\r\n"
							+ "Number of Toll Stations: " + numberTollbooths + "\n");
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private static double countTollbooths(File file) {

		setIterator(0);

		try {

			// streams, reader
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));

			String strLine = null;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if (!strLine.startsWith("Feasibility")) {
					String[] splited = strLine.split("\\s+");
					if (Double.valueOf(splited[2]) > 0) {
						++iterator;
					}
				}
			}

			// --- close streams, reader ---
			fstream.close();
			in.close();
			br.close();

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		return iterator;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

//		setDebugFlag(true);
		setDebugFlag(false);

		/*
		 * --- GridGraph ---
		 */
		System.out.println("\n ---------------\n|   GridGraph   |\n ---------------\n");
		setPath("./files/Evaluation/checkTollboothsManually/GridGraph/50-100/uncompleteFiles.txt");
		setPathInstances("./files/Evaluation/Instances/GridGraph/50-100");
		// start extracting data
		init();

		/*
		 * --- Poisson ---
		 */
		System.out.println("\n -------------------\n|   Poisson Graph   |\n -------------------\n");
		setPath("./files/Evaluation/checkTollboothsManually/Poisson/50-100/uncompleteFiles.txt");
		setPathInstances("./files/Evaluation/Instances/Poisson/50-100");
		// start extracting data
		init();

		/*
		 * --- HeavyTail ---
		 */
//		System.out.println("\n -------------------\n|  HeavyTail Graph  |\n -------------------\n");
//		setPath("./files/Evaluation/checkTollboothsManually/HeavyTail/50-100/uncompleteFiles.txt");
//		setPathInstances("./files/Evaluation/Instances/HeavyTail/50-100");
//		// start extracting data
//		init();

	}
}