package evaluation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

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
public class Evaluation {

	// --- flags, iterators etc. ----
	static boolean extension;
	static int iterator;
	static boolean debugFlag = false;

	// --- files and folders ----
	static File folder;
	static File[] listOfFiles;

	// --- lists
	static ArrayList<File> listOfGamintbFiles = new ArrayList<File>();
	static ArrayList<File> listOfMintbFiles = new ArrayList<File>();
	static ArrayList<File> listOfGraphInstanceFiles = new ArrayList<File>();
	static ArrayList<Integer> numbTollboothsGamintb = new ArrayList<Integer>();
	static ArrayList<Integer> numbTollboothsMintb = new ArrayList<Integer>();
	static ArrayList<Integer> Degeneracy = new ArrayList<Integer>();
	static ArrayList<Integer> Diameter = new ArrayList<Integer>();
	static ArrayList<Integer> Eccentricity = new ArrayList<Integer>();
	static ArrayList<Integer> MaxVertexDegree = new ArrayList<Integer>();
	static ArrayList<Integer> MinVertexDegree = new ArrayList<Integer>();
	static ArrayList<Integer> AvgVertexDegree = new ArrayList<Integer>();
	static ArrayList<Integer> Radius = new ArrayList<Integer>();

	// --- streams, reader ---
	static FileInputStream fstream;
	static DataInputStream in;
	static BufferedReader br;

	// --- paths ---
//	static String pathInstances = "./Masterprojekt/files/Evaluation/Instances/50-100";
//	static String pathHeuristics = "./Masterprojekt/files/Evaluation/heuristics/50-100";
//	static String pathCharacteristics = "./Masterprojekt/files/Evaluation/characteristics/50-100";

	static String pathInstances = "C:\\Users\\julia\\OneDrive\\Desktop\\Projekt Algorithm Engineering Tests\\ExtractedData\\50-100\\Instances\\50-100";
	static String pathHeuristics = "C:\\Users\\julia\\OneDrive\\Desktop\\Projekt Algorithm Engineering Tests\\ExtractedData\\50-100\\heuristics";
	static String pathCharacteristics = "C:\\Users\\julia\\OneDrive\\Desktop\\Projekt Algorithm Engineering Tests\\ExtractedData\\50-100\\characteristics";
	
	
	/**
	 * 
	 * @param listOfGamintbFiles
	 */
	public static void setListOfGamintbFiles(ArrayList<File> listOfGamintbFiles) {
		Evaluation.listOfGamintbFiles = listOfGamintbFiles;
	}

	/**
	 * 
	 * @param listOfMintbFiles
	 */
	public static void setListOfMintbFiles(ArrayList<File> listOfMintbFiles) {
		Evaluation.listOfMintbFiles = listOfMintbFiles;
	}

	/**
	 * 
	 * @param listOfGraphInstanceFiles
	 */
	public static void setListOfGraphInstanceFiles(ArrayList<File> listOfGraphInstanceFiles) {
		Evaluation.listOfGraphInstanceFiles = listOfGraphInstanceFiles;
	}

	/**
	 * 
	 * @param iterator
	 */
	public static void setIterator(int iterator) {
		Evaluation.iterator = iterator;
	}

	/**
	 * 
	 * @param debugFlag
	 */
	public static void setDebugFlag(boolean debugFlag) {
		Evaluation.debugFlag = debugFlag;
	}

	/**
	 * 
	 * @param listOfGamintbFiles
	 */
	public static void buildGamintbData(ArrayList<File> listOfGamintbFiles) {

		setListOfGamintbFiles(listOfGamintbFiles);
		setIterator(0);

		for (File file : listOfGamintbFiles) {

			try {

				// streams, reader
				fstream = new FileInputStream(file);
				in = new DataInputStream(fstream);
				br = new BufferedReader(new InputStreamReader(in));

				String strLine = null;

				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					if (strLine.startsWith("Number of Toll Stations:")) {
						String[] splited = strLine.split("\\s+");
						numbTollboothsGamintb.add(Integer.valueOf(splited[4]));
						++iterator;
					}
				}

				// --- close streams, reader ---
				fstream.close();
				in.close();
				br.close();

			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param listOfMintbFiles
	 */
	public static void buildMintbData(ArrayList<File> listOfMintbFiles) {

		setListOfMintbFiles(listOfMintbFiles);
		setIterator(0);

		for (File file : listOfMintbFiles) {

			try {

				// streams, reader
				fstream = new FileInputStream(file);
				in = new DataInputStream(fstream);
				br = new BufferedReader(new InputStreamReader(in));

				String strLine = null;

				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					if (strLine.startsWith("Number of Toll Stations:")) {
						String[] splited = strLine.split("\\s+");
						numbTollboothsMintb.add(Integer.valueOf(splited[4]));
						++iterator;
					}
				}

				// --- close streams, reader ---
				fstream.close();
				in.close();
				br.close();

			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param listOfGraphInstancesFiles
	 */
	public static void buildGraphInstancesData(ArrayList<File> listOfGraphInstancesFiles) {

		setListOfGraphInstanceFiles(listOfGraphInstancesFiles);

		for (File file : listOfGraphInstancesFiles) {

			/*
			 * TODO
			 * 
			 * - generate graph instance out of JSON 
			 * - run graph characteristics calculator
			 * - create list with relevant data (degeneracy, diameter, eccentricity,
			 * maxVertexDegree, minVertexDegree, avgVertexDegree, Radius)
			 */

		}
	}

	/**
	 * 
	 */
	public static void DataExtractor() {

		folder = new File(pathInstances);
		listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {

			if (file.isFile()) {

				// --- GAMINTB files ---
				extension = file.getName().toUpperCase().endsWith(".GAMINTB.TXT");

				switch (String.valueOf(extension)) {
				case "true":
					listOfGamintbFiles.add(file);
				}

				// --- MINTB files ---
				extension = file.getName().toUpperCase().endsWith(".MINTB.TXT");

				switch (String.valueOf(extension)) {
				case "true":
					listOfMintbFiles.add(file);
				}

				// --- Graph instances files ---
				extension = !(file.getName().toUpperCase().endsWith(".MINTB.TXT"))
						&& !(file.getName().toUpperCase().endsWith(".GAMINTB.TXT"));

				switch (String.valueOf(extension)) {
				case "true":
					listOfGraphInstanceFiles.add(file);

				}
			}
		}

		buildGamintbData(listOfGamintbFiles);
		buildMintbData(listOfMintbFiles);
		buildGraphInstancesData(listOfGraphInstanceFiles);

		// --- test ---
		if (debugFlag) {

			System.out.println("\n#Tollbooths GAMINTB:\n");
			for (int elem : numbTollboothsGamintb)
				System.out.println(elem);

			System.out.println("\n#Tollbooths MINTB:\n");
			for (int elem : numbTollboothsMintb)
				System.out.println(elem);
		}
	}

	/**
	 * 
	 */
	public static void writeToFile() {

		/*
		 * GAMINTB
		 */

		String contentGAMINTB = "";
		for (Integer number : numbTollboothsGamintb) {
			contentGAMINTB += number + "\n";
		}
		contentGAMINTB = contentGAMINTB.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathHeuristics + "/gamintb.txt"), "utf-8"))) {
			writer.write(contentGAMINTB);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * MINTB
		 */

		String contentMINTB = "";
		for (Integer number : numbTollboothsMintb) {
			contentMINTB += number + "\n";
		}
		contentMINTB = contentMINTB.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/mintb.txt"), "utf-8"))) {
			writer.write(contentMINTB);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Graph Instances
		 */

		// --- degeneracy ---
		String contentGraphInstancesDegeneracy = "";
		for (Integer number : Degeneracy) {
			contentGraphInstancesDegeneracy += number + "\n";
		}
		contentGraphInstancesDegeneracy = contentGraphInstancesDegeneracy.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/degeneracy.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesDegeneracy);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- diameter ---
		String contentGraphInstancesDiameter = "";
		for (Integer number : Diameter) {
			contentGraphInstancesDiameter += number + "\n";
		}
		contentGraphInstancesDiameter = contentGraphInstancesDiameter.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/diameter.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesDiameter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- eccentricity ---
		String contentGraphInstancesEccentricity = "";
		for (Integer number : Eccentricity) {
			contentGraphInstancesEccentricity += number + "\n";
		}
		contentGraphInstancesEccentricity = contentGraphInstancesEccentricity.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/eccentricity.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesEccentricity);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- Max vertex degree ---
		String contentGraphInstancesMaxVertexDegree = "";
		for (Integer number : MaxVertexDegree) {
			contentGraphInstancesMaxVertexDegree += number + "\n";
		}
		contentGraphInstancesMaxVertexDegree = contentGraphInstancesMaxVertexDegree.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/maxVertexDegree.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesMaxVertexDegree);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- Min vertex degree ---
		String contentGraphInstancesMinVertexDegree = "";
		for (Integer number : MinVertexDegree) {
			contentGraphInstancesMinVertexDegree += number + "\n";
		}
		contentGraphInstancesMinVertexDegree = contentGraphInstancesMinVertexDegree.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/minVertexDegree.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesMinVertexDegree);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- Avg vertex degree ---
		String contentGraphInstancesAvgVertexDegree = "";
		for (Integer number : AvgVertexDegree) {
			contentGraphInstancesAvgVertexDegree += number + "\n";
		}
		contentGraphInstancesAvgVertexDegree = contentGraphInstancesAvgVertexDegree.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/avgVertexDegree.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesAvgVertexDegree);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- Radius ---
		String contentGraphInstancesRadius = "";
		for (Integer number : Radius) {
			contentGraphInstancesRadius += number + "\n";
		}
		contentGraphInstancesRadius = contentGraphInstancesRadius.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/radius.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesRadius);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

//		setDebugFlag(true);
		setDebugFlag(false);

		DataExtractor();

		writeToFile();
	}
}
