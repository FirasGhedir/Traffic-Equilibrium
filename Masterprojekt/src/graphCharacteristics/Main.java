package graphCharacteristics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import player.Player;
import ilog.concert.IloException;

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

	static List<Object> printList = new ArrayList<Object>();
	static String impressum = "\n| Universität Ulm\n| \n| Projekt Algorithm Engineering-Projekt --- WiSe 2018/19\n| \n| @author Firas Ghedir (firas.ghedir@uni-ulm.de)\n| @author Julian Bestler (julian.bestler@uni-ulm.de)\n| \n| @version 1.0\n\n";

	static Graphs graph;

	/**
	 * Help method to configure a JSON file of a given object instance
	 * 
	 * --------------------------------------------
	 * 
	 * @param obj the object to store into JSON file
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
	 * @param obj the object to store into JSON file
	 */
	private static Object createObjectInstanceFromJSON(File file, String path) {

		graph = new Graphs(); // new instance for JSON data

		// check, if file is valide
		String fileName = file.getName().toUpperCase();
		boolean extension = fileName.endsWith(".JSON");

		switch (String.valueOf(extension)) {
		case "true":
			final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to
			// reuse!

			try {
				graph = mapper.readValue(new File(path), Graphs.class); // reads object instance of
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
	 * Help method to print list content to console and txt file
	 * 
	 * --------------------------------------------
	 * 
	 * @param list a given list
	 * @throws IOException
	 */
	private static void printObjects(List<?> list) throws IOException {

//		// Store current System.out before assigning a new value
		PrintStream console = System.out;

		for (Object object : list) {

			/*
			 * Use stored value for output stream
			 */
			System.setOut(console);
			// print object to console

			System.out.println(object);
		}

		// close output stream
		console.close();
	}

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args             the command line arguments
	 * @param adjacency_matrix
	 */
	public static void main(String[] args) throws IloException {

		try {

			/*
			 * =============================================================
			 * ================== CREATE EVERYTHING HERE ===================
			 * =============================================================
			 */

			boolean testGraph = true;

			// test graph with hard coded graph data
			if (testGraph) {
				// --- Graph parameter ---
				Map<String, Vertex> map = new TreeMap<>();
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

				// read in graph an test with it
			} else {

				String path = "./Masterprojekt/files/graphData.json";
				File file = new File(path);
				createObjectInstanceFromJSON(file, path);
			}

			// --- Create CharacteristicsCalculator ---
			CharacteristicsCalculatorMain characteristics = new CharacteristicsCalculatorMain(graph);

			/*
			 * =============================================================
			 * ================== SET EVERYTHING TO PRINT HERE =============
			 * =============================================================
			 */
			printList.add(impressum);
			printList.add(graph);
			printList.add(characteristics);

			printObjects(printList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
