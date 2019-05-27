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
import java.util.Comparator;
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
	static boolean flagMINTB = false;
	static boolean flagGAMINTB = false;
	static boolean flagIgnoreuncompleteFiles = false;

	// --- files and folders ----
	static File folder;
	static File[] listOfFiles;

	// --- lists
	static ArrayList<File> listFailureTmp;
	static ArrayList<File> listFilesTmp;
	static ArrayList<File> listFilesFinal;
	static ArrayList<File> listFilesNotComparable;
	static ArrayList<File> listOfGamintbFiles;
	static ArrayList<File> listOfMintbFiles;
	static ArrayList<File> listOfGraphInstanceFiles;

	static ArrayList<Integer> listOfGamintbRuntime;
	static ArrayList<Integer> listOfMintbRuntime;
	static ArrayList<Integer> numbTollboothsGamintb;
	static ArrayList<Integer> numbTollboothsMintb;

	static ArrayList<Integer> Degeneracy;
	static ArrayList<Integer> Diameter;
	static ArrayList<Double> Eccentricity;
	static ArrayList<Integer> MaxVertexDegree;
	static ArrayList<Integer> MinVertexDegree;
	static ArrayList<Double> AvgVertexDegree;
	static ArrayList<Integer> Radius;
	static ArrayList<Integer> MinCut;
	static ArrayList<Integer> GraphNumberVertices;

	// --- streams, reader ---
	static FileInputStream fstream;
	static DataInputStream in;
	static BufferedReader br;

	// --- paths ---
	static String pathInstances;
	static String pathHeuristics;
	static String pathCharacteristics;
	static String pathFailure;
	static String pathRuntime;

	// --- Failure files
	static File failureFileGAMINTB = new File("./files/Evaluation/heuristics/failure.GAMINTB.txt");
	static File failureFileMINTB = new File("./files/Evaluation/heuristics/failure.MINTB.txt");

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
	 * @param listOfGamintRuntimeFiles
	 */
	public static void setListOfGamintRuntimeFiles(ArrayList<Integer> listOfGamintRuntimeFiles) {
		Evaluation.listOfGamintbRuntime = listOfGamintRuntimeFiles;
	}

	/**
	 * 
	 * @param listOfMintbRuntimeFiles
	 */
	public static void setListOfMintbRuntimeFiles(ArrayList<Integer> listOfMintbRuntimeFiles) {
		Evaluation.listOfMintbRuntime = listOfMintbRuntimeFiles;
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
	 * @return
	 */
	public static Graphs getGraph() {
		return Evaluation.graph;
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
	 * @param flagIgnoreUncompleteFiles
	 */
	public static void setFlagIgnoreUncompleteFiles(boolean flagIgnoreUncompleteFiles) {
		Evaluation.flagIgnoreuncompleteFiles = flagIgnoreUncompleteFiles;
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
	 * @param pathRuntime
	 */
	public static void setPathRuntime(String pathRuntime) {
		Evaluation.pathRuntime = pathRuntime;
	}

	/**
	 * 
	 * @param pathFailure
	 */
	public static void setPathFailure(String pathFailure) {
		Evaluation.pathFailure = pathFailure;
	}

	/**
	 * 
	 * @param listFailureTmp
	 */
	public static void setListFailureTmp(ArrayList<File> listFailureTmp) {
		Evaluation.listFailureTmp = listFailureTmp;
	}

	/**
	 * 
	 * @param listFilesTmp
	 */
	public static void setListFilesTmp(ArrayList<File> listFilesTmp) {
		Evaluation.listFilesTmp = listFilesTmp;
	}

	/**
	 * 
	 * @param listFilesFinal
	 */
	public static void setListFilesFinal(ArrayList<File> listFilesFinal) {
		Evaluation.listFilesFinal = listFilesFinal;
	}

	/**
	 * 
	 * @param listFilesNotComparable
	 */
	public static void setListFilesNotComparable(ArrayList<File> listFilesNotComparable) {
		Evaluation.listFilesNotComparable = listFilesNotComparable;
	}

	/**
	 * 
	 * @param numbTollboothsGamintb
	 */
	public static void setNumbTollboothsGamintb(ArrayList<Integer> numbTollboothsGamintb) {
		Evaluation.numbTollboothsGamintb = numbTollboothsGamintb;
	}

	/**
	 * 
	 * @param numbTollboothsMintb
	 */
	public static void setNumbTollboothsMintb(ArrayList<Integer> numbTollboothsMintb) {
		Evaluation.numbTollboothsMintb = numbTollboothsMintb;
	}

	/**
	 * 
	 * @param graphNumberVertices
	 */
	public static void setGraphNumberVertices(ArrayList<Integer> graphNumberVertices) {
		GraphNumberVertices = graphNumberVertices;
	}

	/**
	 * 
	 * @param degeneracy
	 */
	public static void setDegeneracy(ArrayList<Integer> degeneracy) {
		Degeneracy = degeneracy;
	}

	/**
	 * 
	 * @param diameter
	 */
	public static void setDiameter(ArrayList<Integer> diameter) {
		Diameter = diameter;
	}

	/**
	 * 
	 * @param eccentricity
	 */
	public static void setEccentricity(ArrayList<Double> eccentricity) {
		Eccentricity = eccentricity;
	}

	/**
	 * 
	 * @param maxVertexDegree
	 */
	public static void setMaxVertexDegree(ArrayList<Integer> maxVertexDegree) {
		MaxVertexDegree = maxVertexDegree;
	}

	/**
	 * 
	 * @param minVertexDegree
	 */
	public static void setMinVertexDegree(ArrayList<Integer> minVertexDegree) {
		MinVertexDegree = minVertexDegree;
	}

	/**
	 * 
	 * @param avgVertexDegree
	 */
	public static void setAvgVertexDegree(ArrayList<Double> avgVertexDegree) {
		AvgVertexDegree = avgVertexDegree;
	}

	/**
	 * 
	 * @param minCut
	 */
	public static void setMinCut(ArrayList<Integer> minCut) {
		MinCut = minCut;
	}

	/**
	 * 
	 * @param radius
	 */
	public static void setRadius(ArrayList<Integer> radius) {
		Radius = radius;
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
					}
					if (strLine.startsWith(" Execution Time:")) {
						String[] splited = strLine.split("\\s+");
						listOfGamintbRuntime.add(Integer.valueOf(splited[3]));
						if (debugFlag) {
							System.out.println(iterator + " " + Integer.valueOf(splited[3]));
						}
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
					}
					if (strLine.startsWith(" Execution Time:")) {
						String[] splited = strLine.split("\\s+");
						listOfMintbRuntime.add(Integer.valueOf(splited[3]));
						if (debugFlag) {
							System.out.println(iterator + " " + Integer.valueOf(splited[3]));
						}
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
				GraphNumberVertices.add(graph.getVertices().size());

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
		listFilesTmp.sort(Comparator.naturalOrder());

		for (int i = 0; i < listFilesTmp.size(); i++) {

			String fileNameWithOutExt = FilenameUtils.removeExtension(listFilesTmp.get(i).getName());
			fileNameWithOutExt = FilenameUtils.removeExtension(fileNameWithOutExt);
			int index = Integer.valueOf(fileNameWithOutExt);

			int counter = 0;
			flagMINTB = false;
			flagGAMINTB = false;
			listFailureTmp = new ArrayList<File>();

			if (flagIgnoreuncompleteFiles) {

				for (int j = 0; j < listOfFiles.length; j++) {
					String fileNameWithOutExt2 = FilenameUtils.removeExtension(listOfFiles[j].getName());
					fileNameWithOutExt2 = FilenameUtils.removeExtension(fileNameWithOutExt2);
					int index2 = Integer.valueOf(fileNameWithOutExt2);

					if (index == index2) {
						++counter;
					}
				}

				// if as well instance file, MINTB file as GMINTB file exists: counter value has
				// to be equal 3
				if (counter == 3) {
					listFilesFinal.add(listFilesTmp.get(i));

					// put failure file into list.
				} else {

					listFilesNotComparable.add(listFilesTmp.get(i));
				}

			} else {

				for (int j = 0; j < listOfFiles.length; j++) {
					String fileNameWithOutExt2 = FilenameUtils.removeExtension(listOfFiles[j].getName());
					fileNameWithOutExt2 = FilenameUtils.removeExtension(fileNameWithOutExt2);
					int index2 = Integer.valueOf(fileNameWithOutExt2);

					if (index == index2) {
						if (listOfFiles[j].getName().toUpperCase().endsWith(".MINTB.TXT")) {
							flagMINTB = true;
						} else if (listOfFiles[j].getName().toUpperCase().endsWith(".GAMINTB.TXT")) {
							flagGAMINTB = true;
						}
						++counter;
					}
				}

				// if as well instance file, MINTB file as GMINTB file exists: counter value has
				// to be equal 3
				if (counter == 3) {
					listFilesFinal.add(listFilesTmp.get(i));

					// put failure file into list.
				} else {

					// --- case 1: gamintb file is missing
					if (!flagGAMINTB && flagMINTB) {
						if (listFilesTmp.get(i).getName().toUpperCase().endsWith("MINTB.TXT")) {
							listFilesFinal.add(listFilesTmp.get(i));
							listFilesFinal.add(failureFileGAMINTB);
						} else {
							listFilesFinal.add(listFilesTmp.get(i));
						}
					}
					// --- case 2: mintb file is missing
					if (flagGAMINTB && !flagMINTB) {
						if (listFilesTmp.get(i).getName().toUpperCase().endsWith("GAMINTB.TXT")) {
							listFilesFinal.add(failureFileMINTB);
							listFilesFinal.add(listFilesTmp.get(i));
						} else {
							listFilesFinal.add(listFilesTmp.get(i));
						}
					}
					// --- case 3: as well mintb as gamintb file is missing
					if (!flagGAMINTB && !flagMINTB) {
						listFilesFinal.add(listFilesTmp.get(i));
						listFilesFinal.add(failureFileGAMINTB);
						listFilesFinal.add(failureFileMINTB);
					}

					listFilesNotComparable.add(listFilesTmp.get(i));
				}

			}

			if (debugFlag) {

				System.out.println("\nFiles which can get compared to each other:\n-------------------------");
				for (File file : listFilesFinal) {
					System.out.println(file.getName());
				}
				System.out.println("\nFiles which can get not compared to each other:\n-------------------------");
				for (File file : listFilesNotComparable) {
					System.out.println(file.getName());
				}
			}

		}

		// List to Array again

		int listLenght = listFilesFinal.size();
		listOfFiles = new File[listLenght];

		for (int i1 = 0; i1 < listOfFiles.length; i1++) {
			listOfFiles[i1] = listFilesFinal.get(i1);
		}

		if (debugFlag) {
			System.out.println("\nFinal file list:\n-----------");
			for (File file : listFilesFinal) {
				System.out.println(file.getName());
			}
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
			for (File fileIterator : listFilesFinal) {
				System.err.println(fileIterator.getName());
			}

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

		System.out.println("\n   listOfMintbFile size:          " + listOfMintbFiles.size());
		System.out.println("   listOfGamintbFiles size:       " + listOfGamintbFiles.size());
		System.out.println("   listOfGraphInstanceFiles size: " + listOfGraphInstanceFiles.size() + "\n");

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

		/********************
		 * Failure
		 ********************/
		String contentFailure = "";
		for (File file : listFilesNotComparable) {
			contentFailure += file.getName() + "\n";
		}
		contentFailure = contentFailure.trim();

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathFailure), "utf-8"))) {
			writer.write(contentFailure);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/********************
		 * GAMINTB
		 ********************/
		System.out.println("\n -> GAMINTB vector size:         " + numbTollboothsGamintb.size());
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

		/********************
		 * MINTB
		 ********************/
		System.out.println(" -> MINTB vector size:           " + numbTollboothsMintb.size());
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

		/********************
		 * Runtime
		 ********************/

		// --- GAMINTB ---
		System.out.println("\n -> GAMINTB Runtime vector size: " + listOfGamintbRuntime.size());
		String contentRuntimeGAMINTB = "";
		for (Integer number : listOfGamintbRuntime) {
			contentRuntimeGAMINTB += number + "\n";
		}
		contentRuntimeGAMINTB = contentRuntimeGAMINTB.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathRuntime + "/gamintbRuntime.txt"), "utf-8"))) {
			writer.write(contentRuntimeGAMINTB);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// --- MINTB ---
		System.out.println(" -> MINTB Runtime vector size:   " + listOfMintbRuntime.size() + "\n");
		String contentRuntimeMINTB = "";
		for (Integer number : listOfMintbRuntime) {
			contentRuntimeMINTB += number + "\n";
		}
		contentRuntimeMINTB = contentRuntimeMINTB.trim();

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(pathRuntime + "/mintbRuntime.txt"), "utf-8"))) {
			writer.write(contentRuntimeMINTB);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/******************************
		 * Graph Characteristics
		 *****************************/

		// --- degeneracy ---
		System.out.println("\n -> Degeneracy vector size:      " + Degeneracy.size());
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
		System.out.println(" -> Diameter   vector size:      " + Diameter.size());
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
		System.out.println(" -> Eccentricity vector size:    " + Eccentricity.size());
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
		System.out.println(" -> MaxVertexDegree vector size: " + MaxVertexDegree.size());
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
		System.out.println(" -> MinVertexDgree vector size:  " + MinVertexDegree.size());
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
		System.out.println(" -> AvgVertexDegree vector size: " + AvgVertexDegree.size());
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
		System.out.println(" -> Radius vector size:          " + Radius.size());
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
		System.out.println(" -> MinCut vector size:          " + MinCut.size() + "\n");
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
	 */
	private static void replaceMissingFileData() {

		// replaces missing Mintb file data
		for (int i = 0; i < numbTollboothsMintb.size(); i++) {
			if (numbTollboothsMintb.get(i) == 0) {
				numbTollboothsMintb.set(i, GraphNumberVertices.get(i));
			}
		}

		// replaces missing Gamintb file data
		for (int i = 0; i < numbTollboothsGamintb.size(); i++) {
			if (numbTollboothsGamintb.get(i) == 0) {
				numbTollboothsGamintb.set(i, GraphNumberVertices.get(i));
			}
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

		setListFailureTmp(new ArrayList<File>());
		setListFilesTmp(new ArrayList<File>());
		setListFilesFinal(new ArrayList<File>());
		setListFilesNotComparable(new ArrayList<File>());
		setListOfGamintbFiles(new ArrayList<File>());
		setListOfMintbFiles(new ArrayList<File>());
		setListOfGraphInstanceFiles(new ArrayList<File>());

		setListOfGamintRuntimeFiles(new ArrayList<Integer>());
		setListOfMintbRuntimeFiles(new ArrayList<Integer>());
		setNumbTollboothsGamintb(new ArrayList<Integer>());
		setNumbTollboothsMintb(new ArrayList<Integer>());

		setDegeneracy(new ArrayList<Integer>());
		setDiameter(new ArrayList<Integer>());
		setEccentricity(new ArrayList<Double>());
		setMaxVertexDegree(new ArrayList<Integer>());
		setMinVertexDegree(new ArrayList<Integer>());
		setAvgVertexDegree(new ArrayList<Double>());
		setRadius(new ArrayList<Integer>());
		setMinCut(new ArrayList<Integer>());

		setGraphNumberVertices(new ArrayList<Integer>());

		System.out.println("Start extracting data now... Please wait...");
		DataExtractor();
		writeToFile();
		replaceMissingFileData();
		System.out.println("Extracting data was successfull...");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		setDebugFlag(false);

		/*
		 * --- GridGraph ---
		 */
		System.out.println("\n ---------------\n|   GridGraph   |\n ---------------\n");
		setPathInstances("./files/Evaluation/Instances/GridGraph/50-100");
		setPathHeuristics("./files/Evaluation/heuristics/GridGraph/50-100");
		setPathCharacteristics("./files/Evaluation/characteristics/GridGraph/50-100");
		setPathRuntime("./files/Evaluation/Runtime/GridGraph/50-100");
		setPathFailure("./files/Evaluation/heuristics/failureFilesGridGraph.txt");
		// start extracting data
		init();

		/*
		 * --- Poisson ---
		 */
		System.out.println("\n -------------------\n|   Poisson Graph   |\n -------------------\n");
		setPathInstances("./files/Evaluation/Instances/Poisson/50-100");
		setPathHeuristics("./files/Evaluation/heuristics/Poisson/50-100");
		setPathCharacteristics("./files/Evaluation/characteristics/Poisson/50-100");
		setPathRuntime("./files/Evaluation/Runtime/Poisson/50-100");
		setPathFailure("./files/Evaluation/heuristics/failureFilesPoisson.txt");
		// start extracting data
		init();

		/*
		 * --- HeavyTail ---
		 */
		System.out.println("\n -------------------\n|  HeavyTail Graph  |\n -------------------\n");
		setPathInstances("./files/Evaluation/Instances/HeavyTail/50-100");
		setPathHeuristics("./files/Evaluation/heuristics/HeavyTail/50-100");
		setPathCharacteristics("./files/Evaluation/characteristics/HeavyTail/50-100");
		setPathRuntime("./files/Evaluation/Runtime/HeavyTail/50-100");
		setPathFailure("./files/Evaluation/heuristics/failureFilesHeavyTail.txt");
		// start extracting data
		init();

		/*
		 * edited files below; set missing files of poisson graph as reference
		 */
//		System.out.println("\n ------------------------\n| Poisson Graph (edited) |\n ------------------------\n");
//		setFlagIgnoreUncompleteFiles(true);
//		setPathInstances("./files/Evaluation/Instances/Poisson/50-100");
//		setPathHeuristics("./files/Evaluation/heuristics/Poisson/uncomplete/50-100");
//		setPathCharacteristics("./files/Evaluation/characteristics/Poisson/uncomplete/50-100");
//		setPathRuntime("./files/Evaluation/Runtime/Poisson/uncomplete/50-100");
//		setPathFailure("./files/Evaluation/heuristics/failureFilesPoisson.txt");
//		// start extracting data
//		init();
//		
//		System.out.println("\n --------------------------\n| HeavyTail Graph (edited) |\n --------------------------\n");
//		setFlagIgnoreUncompleteFiles(true);
//		setPathInstances("./files/Evaluation/Instances/HeavyTail/50-100");
//		setPathHeuristics("./files/Evaluation/heuristics/HeavyTail/uncomplete/50-100");
//		setPathCharacteristics("./files/Evaluation/characteristics/HeavyTail/uncomplete/50-100");
//		setPathRuntime("./files/Evaluation/Runtime/HeavyTail/uncomplete/50-100");
//		setPathFailure("./files/Evaluation/heuristics/failureFilesHeavyTail.txt");
//		// start extracting data
//		init();
	}
}