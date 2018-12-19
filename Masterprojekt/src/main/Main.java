package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import graphCharacteristics.CharacteristicsCalculator;
import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.DSSP;
import heuristic.SocialOptimum;
import player.Player;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

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

			// --- Create CharacteristicsCalculator ---
			CharacteristicsCalculator characteristics = new CharacteristicsCalculator(graph);

			// --- player ---
			Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(6), 15);
			Player player2 = new Player(2, graph.getVertices().get(0), graph.getVertices().get(10), 8);
			Player player3 = new Player(3, graph.getVertices().get(4), graph.getVertices().get(14), 10);
			Player player4 = new Player(4, graph.getVertices().get(4), graph.getVertices().get(13), 25);
			Player player5 = new Player(5, graph.getVertices().get(0), graph.getVertices().get(11), 15);
			Player player6 = new Player(5, graph.getVertices().get(1), graph.getVertices().get(15), 19);
			Player player7 = new Player(5, graph.getVertices().get(4), graph.getVertices().get(11), 20);
			ArrayList<Player> x = new ArrayList<>();
			x.add(0, player1);
			x.add(1, player2);
			x.add(2, player3);
			x.add(3, player4);
			x.add(4, player5);
			x.add(5, player6);
			x.add(6, player7);
			graph.setPlayer(x);
			graph.generateedgesfunctions();// edge functions are totally randomized

			// --- social optimum ---
			SocialOptimum systemOptimalFlow = new SocialOptimum(graph);

			// --- DSSP ---
			DSSP dssp = new DSSP(graph);

			/*
			 * =============================================================
			 * ================== PRINT EVERYTHING HERE ====================
			 * =============================================================
			 */

			// --- print graph data ---
			System.out.println(graph);

			// --- print graph characteristics ---
			System.out.println(characteristics);

			// --- print Social Optimum ---
			System.out.println(systemOptimalFlow);

			// --- print DSSP ---
			System.out.println(dssp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
