package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.DSSP;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
import player.Player;

/**
 * Universität Ulm
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
 *          In the Main class you can create new graphs, test and work with
 *          them.
 */
public class Main {

	private static String path = "./Masterprojekt/files/graphData.txt";
	static List<Object> printList = new ArrayList<Object>();
	static String impressum = "\n| Universität Ulm\n| \n| Projekt Algorithm Engineering-Projekt --- WiSe 2018/19\n| \n| @author Firas Ghedir (firas.ghedir@uni-ulm.de)\n| @author Julian Bestler (julian.bestler@uni-ulm.de)\n| \n| @version 1.0\n\n";

	/**
	 * Help method to print list content to console and txt file
	 * 
	 * --------------------------------------------
	 * 
	 * @param list
	 *            a given list
	 * @throws IOException
	 */
	private static void printObjects(List<?> list) throws IOException {

		// // Uncomment this to get txt files for each program iteration for each graph
		// String uniqueID = UUID.randomUUID().toString(); // create unique IDs
		// path = "./Masterprojekt/files/graphData(" + uniqueID + ").txt";

		// Creating a File object that represents the disk file.
		// PrintStream outputToTxtFile = new PrintStream(new File(path));
		// Store current System.out before assigning a new value
		PrintStream console = System.out;

		for (Object object : list) {

			/*
			 * Assign o to output stream
			 */
			// System.setOut(outputToTxtFile);
			// print object to txt file
			System.out.println(object);

			/*
			 * Use stored value for output stream
			 */
			System.setOut(console);
			// print object to console

			System.out.println(object);
		}

		// close output stream
		// outputToTxtFile.close();
	}

	/**
	 * Help method to configure a JSON file of a given object instance
	 * 
	 * --------------------------------------------
	 * 
	 * @param obj
	 *            the object to store into JSON file
	 */
	private static void buildJSON(Object obj) {

		final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to reuse!

		// configure JSON
		File file = new File("./Masterprojekt/files/graphData.json");
		try {
			mapper.writeValue(file, obj); // writes JSON serialization of object instance
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Help method to create an object instance out of a given JSON file
	 * 
	 * --------------------------------------------
	 * 
	 * @param obj
	 *            the object to store into JSON file
	 */
	private static Graphs createObjectInstanceFromJSON(File file) {

		Graphs graph = new Graphs(); // new instance for JSON data

		// check, if file is valide
		String fileName = file.getName().toUpperCase();
		boolean extension = fileName.endsWith(".JSON");

		switch (String.valueOf(extension)) {
		case "true":
			final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			try {
				graph = mapper.readValue(new File("./Masterprojekt/files/graphData.json"), Graphs.class); // reads
																											// object
																											// instance
																											// of
				// JSON serialization
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graph;
		default:
			System.err.println("The given file is not valid to create an object instance out of it...");
			break;
		}
		return graph;
	}

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args
	 *            the command line arguments
	 * @param adjacency_matrix
	 */
	public static void main(String[] args) throws IloException {

		try {

			/*
			 * =============================================================
			 * ================== CREATE EVERYTHING HERE ===================
			 * =============================================================
			 */

			// --- Graph parameter ---
			Map<String, Vertex> map = new TreeMap<>();
			Graphs graph = new Graphs();
			GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
			test.generateGraph(graph, map);

			// --- player ---
			Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 10);
			Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 5);

			ArrayList<Player> x = new ArrayList<>();
			x.add(0, player1);
			x.add(1, player2);

			graph.setPlayer(x);
			graph.generateEdgesFunctions();// edge functions are totally randomized

			// buildJSON(graph);
			graph.getPlayers().get(0).setDemand(4);
			Graphs graphClone = createObjectInstanceFromJSON(new File("./graphData.json"));
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + graphClone.getVertices().size());
			System.out.println(graphClone.getPlayers().get(0).getDemand());
			// --- Create CharacteristicsCalculator ---
			// CharacteristicsCalculator characteristics = new
			// CharacteristicsCalculator(graph);

			// --- social optimum ---
			SocialOptimum systemOptimalFlow = new SocialOptimum(graph);

			// --- DSSP ---
			DSSP dssp = new DSSP(graph);
			TestCorrectness correct = new TestCorrectness();

			System.out.println(correct.test(graph, player1.getSource(), player1.getSink()));

			// --- RMINTB ---
			// RMINTB rmintb = new RMINTB(graph);

			// --- GAMINTB ---
			// GaMINTB gamintb = new GaMINTB(graph, 10, 30);

			/*
			 * =============================================================
			 * ================== SET EVERYTHING TO PRINT HERE =============
			 * =============================================================
			 */
			printList.add(impressum);
			printList.add(graph);
			// list.add(characteristics);
			printList.add(systemOptimalFlow);
			printList.add(dssp);
			// list.add(rmintb);
			// printList.add(gamintb);

			printObjects(printList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
