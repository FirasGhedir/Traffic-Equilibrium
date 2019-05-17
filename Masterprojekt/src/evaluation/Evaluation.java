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
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphCharacteristics.CharacteristicsCalculatorMain;
import graphCharacteristics.Degeneracy;
import graphCharacteristics.Diameter;
import graphCharacteristics.Eccentricity;
import graphCharacteristics.MaxMinAvgVertexDegree;
import graphCharacteristics.MinCut;
import graphCharacteristics.Radius;
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
	static ArrayList<Double> Eccentricity = new ArrayList<Double>();
	static ArrayList<Integer> MaxVertexDegree = new ArrayList<Integer>();
	static ArrayList<Integer> MinVertexDegree = new ArrayList<Integer>();
	static ArrayList<Double> AvgVertexDegree = new ArrayList<Double>();
	static ArrayList<Integer> Radius = new ArrayList<Integer>();
	static ArrayList<String> MinCut = new ArrayList<String>();

	// --- streams, reader ---
	static FileInputStream fstream;
	static DataInputStream in;
	static BufferedReader br;

	// --- paths ---
	static String pathInstances = "./files/Evaluation/Instances/50-100";
	static String pathHeuristics = "./files/Evaluation/heuristics/50-100";
	static String pathCharacteristics = "./files/Evaluation/characteristics/50-100";

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
				MinCut.add(characteristics.getMinCut());

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
		} catch (IOException  e) {
			e.printStackTrace();
		}

		return graph;
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
