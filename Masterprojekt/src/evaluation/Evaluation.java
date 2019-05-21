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
import java.util.Arrays;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphCharacteristics.CharacteristicsCalculatorMain;
import graphModel.Graphs;
import util.FilenameUtils;

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
	static ArrayList<File> listFilesTmp = new ArrayList<File>();
	static ArrayList<File> listFilesFinal = new ArrayList<File>();
	static ArrayList<File> listOfGamintbFiles = new ArrayList<File>();
	static ArrayList<File> listOfMintbFiles = new ArrayList<File>();
	static ArrayList<File> listOfGraphInstanceFiles = new ArrayList<File>();
	static ArrayList<Integer> numbTollboothsGamintb = new ArrayList<Integer>();
	static ArrayList<Integer> numbTollboothsMintb = new ArrayList<Integer>();
	static ArrayList<Integer> Degeneracy = new ArrayList<Integer>();
	static ArrayList<Integer> Diameter = new ArrayList<Integer>();
	static ArrayList<Double> Eccentricity = new ArrayList<Double>();
	static ArrayList<Integer> MaxVertexDegree = new ArrayList<Integer>();
	static ArrayList<Integer> MinVertexDegree = new ArrayList<Integer>();
	static ArrayList<Double> AvgVertexDegree = new ArrayList<Double>();
	static ArrayList<Integer> Radius = new ArrayList<Integer>();
	static ArrayList<Integer> MinCut = new ArrayList<Integer>();

	// --- streams, reader ---
	static FileInputStream fstream;
	static DataInputStream in;
	static BufferedReader br;

	// --- paths ---
	static String pathInstances;
	static String pathHeuristics;
	static String pathCharacteristics;

	// --- graph ---
	static Graphs graph;

	// --- characteristic calculator
	static CharacteristicsCalculatorMain characteristics;

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
	 * @param graph
	 */
	public static void setGraph(Graphs graph) {
		Evaluation.graph = graph;
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
	 * @param pathHeuristics
	 */
	public static void setPathHeuristics(String pathHeuristics) {
		Evaluation.pathHeuristics = pathHeuristics;
	}

	/**
	 * 
	 * @param pathCharacteristics
	 */
	public static void setPathCharacteristics(String pathCharacteristics) {
		Evaluation.pathCharacteristics = pathCharacteristics;
	}

	/**
	 * 
	 * @param pathInstances
	 */
	public static void setPathInstances(String pathInstances) {
		Evaluation.pathInstances = pathInstances;
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

			try {

				setGraph(buildGraph(file));

				characteristics = new CharacteristicsCalculatorMain(graph);

				// --- max/avg/min vertex degree
				MaxVertexDegree.add(characteristics.getMaxVertexDegree());
				MinVertexDegree.add(characteristics.getMinVertexDegree());
				AvgVertexDegree.add(characteristics.getAvgVertexDegree());

				// --- eccentricity
//				eccentricities = characteristics.getEccentricities();
				Eccentricity.add(characteristics.getAvgEccentricity());

				// --- Diameter
				Diameter.add(characteristics.getDiameter());

				// --- Radius
				Radius.add(characteristics.getRadius());

				// --- Min cut
				String minCutTmp = characteristics.getMinCut();
				String splited[] = minCutTmp.split("\\n");
				int numberOfMinCuts = splited.length;
				MinCut.add(numberOfMinCuts);

				// --- Degeneracy
				Degeneracy.add(characteristics.getDegeneracy());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	public static void ensureDataIsAvailableForAllHeuristics() {

		folder = new File(pathInstances);
		listOfFiles = folder.listFiles();
		listFilesTmp = new ArrayList<>(Arrays.asList(listOfFiles));

		for (int i = 0; i < listFilesTmp.size(); i++) {
			
			String fileNameWithOutExt = FilenameUtils.removeExtension(listFilesTmp.get(i).getName());
			fileNameWithOutExt = FilenameUtils.removeExtension(fileNameWithOutExt);
			int index = Integer.valueOf(fileNameWithOutExt);
			
			int counter = 0;
			for (int j = 0; j < listOfFiles.length; j++) {
				String fileNameWithOutExt2 = FilenameUtils.removeExtension(listOfFiles[j].getName());
				fileNameWithOutExt2 = FilenameUtils.removeExtension(fileNameWithOutExt2);
				int index2 = Integer.valueOf(fileNameWithOutExt2);

				if (index==index2) {
					++counter;
				}
			}
			
			if (counter>2) {
				listFilesFinal.add(listFilesTmp.get(i));
			}
			
		}

		// List to Array again
		int listLenght = listFilesFinal.size();
		listOfFiles = new File[listLenght];

		for (int i = 0; i < listOfFiles.length; i++) {
			listOfFiles[i] = listFilesFinal.get(i);
		}
	}

	/**
	 * 
	 */
	public static void DataExtractor() {

		/*
		 * check, if data for all heuristics available
		 */
		ensureDataIsAvailableForAllHeuristics();
		
		if (debugFlag) {
			// --- relevant data files ---
			System.out.println("\nRelevant data files:\n---------------------");
			for (File fileIterator : listFilesFinal) {System.err.println(fileIterator.getName());}

		}
		
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
				new OutputStreamWriter(new FileOutputStream(pathHeuristics + "/mintb.txt"), "utf-8"))) {
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
		for (Double number : Eccentricity) {
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
		for (Double number : AvgVertexDegree) {
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

		// --- MinCut ---
		String contentGraphInstancesMinCut = "";
		for (Integer number : MinCut) {
			contentGraphInstancesMinCut += number + "\n";
		}
		contentGraphInstancesMinCut = contentGraphInstancesMinCut.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathCharacteristics + "/minCut.txt"), "utf-8"))) {
			writer.write(contentGraphInstancesMinCut);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param instance
	 * @return
	 */
	public static Graphs buildGraph(File instance) {

		String path = pathInstances + "/" + instance.getName();

		Graphs graph = new Graphs();// new instance for JSON data

		final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to
		// reuse!
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			graph = mapper.readValue(new File(path), Graphs.class); // reads object instance of
			// JSON serialization
		} catch (IOException e) {
			e.printStackTrace();
		}

		return graph;
	}

	/**
	 * 
	 */
	public static void init() {

		System.out.println("Start extracting data now... Please wait...");
		DataExtractor();
		writeToFile();
		System.out.println("Extracting data was successfull...");
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
		setPathInstances("./files/Evaluation/Instances/GridGraph/50-100");
		setPathHeuristics("./files/Evaluation/heuristics/GridGraph/50-100");
		setPathCharacteristics("./files/Evaluation/characteristics/GridGraph/50-100");
		// start extracting data
		init();

		/*
		 * --- Poisson ---
		 */
		System.out.println("\n -------------------\n|   Poisson Graph   |\n -------------------\n");
		setPathInstances("./files/Evaluation/Instances/Poisson/50-100");
		setPathHeuristics("./files/Evaluation/heuristics/Poisson/50-100");
		setPathCharacteristics("./files/Evaluation/characteristics/Poisson/50-100");
		// start extracting data
		init();

	}
}