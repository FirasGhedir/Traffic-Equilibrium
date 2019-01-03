package main;

// Java program to demonstrate redirection in System.out.println() 
import java.io.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import geneticHeuristic.GaMINTB;
import graphCharacteristics.CharacteristicsCalculator;
import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.DSSP;
import heuristic.SocialOptimum;
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

	private static String path = "./Masterprojekt/files/graphData.txt";

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

			// --- Create CharacteristicsCalculator ---
			CharacteristicsCalculator characteristics = new CharacteristicsCalculator(graph);

			// --- social optimum ---
			SocialOptimum systemOptimalFlow = new SocialOptimum(graph);

			// --- DSSP ---
			DSSP dssp = new DSSP(graph);

			// --- RMINTB ---
//			RMINTB rmintb = new RMINTB(graph);

			// --- GAMINTB ---
			GaMINTB gamintb = new GaMINTB(graph, 10, 30);

			/*
			 * =============================================================
			 * ================== PRINT EVERYTHING HERE ====================
			 * =============================================================
			 */
			// // Uncomment this to get txt files for each program iteration for each graph
			// String uniqueID = UUID.randomUUID().toString(); // create unique IDs
			// path = "./Masterprojekt/files/graphData(" + uniqueID + ").txt";

			// Creating a File object that represents the disk file.
			//PrintStream outputToTxtFile = new PrintStream(new File(path));
			// Store current System.out before assigning a new value
			PrintStream console = System.out;

			/*
			 * Assign o to output stream
			 */
			//System.setOut(outputToTxtFile);
			// --- print graph data ---
	//		System.out.println(graph);
			// --- print graph characteristics ---
	//		System.out.println(characteristics);
			// --- print Social Optimum ---
			System.out.println(systemOptimalFlow);
			// --- print DSSP ---
			System.out.println(dssp);
			// --- print RMINTB ---
//			System.out.println(rmintb);
			// --- print GAMINTB ---
			System.out.println(gamintb);

			/*
			 * Use stored value for output stream
			 */
			System.setOut(console);
			// --- print graph data ---
		//	System.out.println(graph);
			// --- print graph characteristics ---
		//	System.out.println(characteristics);
			// --- print Social Optimum ---
			System.out.println(systemOptimalFlow);
			// --- print DSSP ---
			System.out.println(dssp);
			// --- print RMINTB ---
//			System.out.println(rmintb);
			// --- print GAMINTB ---
			System.out.println(gamintb);

			// close output stream
			//outputToTxtFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
