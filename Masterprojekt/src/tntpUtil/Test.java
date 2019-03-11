package tntpUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import player.Player;

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
 *          Tests for tntp parser and builder in this class
 */
public class Test {

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		// --- Graph parameter ---
		Map<String, Vertex> map = new TreeMap<>();
		Graphs g = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(g, map);

		// --- player ---
		Player player1 = new Player(1, g.getVertices().get(0), g.getVertices().get(15), 10);
		Player player2 = new Player(2, g.getVertices().get(1), g.getVertices().get(15), 5);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		g.setPlayer(x);
		g.generateEdgesFunctions();// edge functions are totally randomized

		/*
		 * ==========
		 */
		network transportationNetwork = network.other;
		@SuppressWarnings("unused")
		tntpBuilder builder = new tntpBuilder(g, transportationNetwork);
		/*
		 * ==========
		 */
	}
}
