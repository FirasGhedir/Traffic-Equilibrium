package TestCaseSiouxFalls;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import bai_A.Mintb_FC;
import genetic.heuristic.Chromosom;
import genetic.heuristic.GaMINTB;
import genetic.heuristic.Population;
import graphGenerator.GridGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
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
public class SiouxFalls {

	static SocialOptimum systemOptimalFlow;
	static FileInputStream fstream;

	static ArrayList<Vertex> vertices = new ArrayList<>();
	static ArrayList<Edge> edges = new ArrayList<>();

	static int[] id;

	static int[] from;
	static int[] to;
	static double[] weight;

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args             the command line arguments
	 * @param adjacency_matrix
	 * @throws IOException
	 * @throws IloException
	 */
	public static void main(String[] args) throws IOException, IloException {
		
		/*
		 * print title
		 */
		String leftAlignFormat = "|| %-10s %-70s  ||%n";
		String limiter = "++========================================================================================++";
		// String dashedLimiter =
		// "++----------------------------------------------------------------------------------------++";
		System.out.format("%n");
		System.out.format(limiter + "%n");
		System.out.format(leftAlignFormat, "\t", "");
		System.out.format(leftAlignFormat, "\t",
				"Sioux Falls" + " (" + "test case" + ")");
		System.out.format(leftAlignFormat, "\t", "");
		System.out.format(limiter + "%n%n");
		

		/*
		 * =============================================================
		 * ======================= ADD VERTICES ========================
		 * =============================================================
		 */

		try {
			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_node.tntp");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			int numVertex = 0;
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("Node") != true) {
					++numVertex;
				}
			}

			System.out.println("\n -> " + numVertex + " vertices added....\n");

			id = new int[numVertex];

			strLine = null;
			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_node.tntp");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			// Read File Line By Line
			int index2 = 0;
			while ((strLine = br.readLine()) != null) {
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("Node") != true) {
					id[index2] = Integer.parseInt(splited[0]);
					++index2;
				}
			}

			int vertex = 0;
			while (vertex < numVertex) {

				++vertex;

				Vertex tmpVertex = new Vertex(id[vertex - 1]);

				vertices.add(vertex - 1, tmpVertex);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * =============================================================
		 * ========================= ADD EDGES =========================
		 * =============================================================
		 */

		try {
			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_flow.tntp");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			int numEdge = 0;
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("From") != true) {
					numEdge++;
				}
			}

			System.out.println("\n -> " + numEdge + " edges added....\n");

			from = new int[numEdge];
			to = new int[numEdge];
			weight = new double[numEdge];

			strLine = null;
			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_flow.tntp");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			// Read File Line By Line
			int index2 = 0;
			while ((strLine = br.readLine()) != null) {
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("From") != true) {
					from[index2] = Integer.parseInt(splited[0]);
					to[index2] = Integer.parseInt(splited[1]);
					weight[index2] = Double.parseDouble(splited[2]);
					++index2;
				}
			}
			
			int edge = 0;
			int index=0;
			while (edge < numEdge) {
				
				++edge;

				Edge tmpEdge = new Edge(vertices.get(from[index]-1), vertices.get(to[index]-1), weight[index]);

				edges.add(index, tmpEdge);
				
				++index;

			}

			System.out.println(
					edges.get(0).getFrom().getId() + " " + edges.get(0).getTo().getId() + " " + edges.get(0).getWeight());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * =============================================================
		 * ======================= Create graph ========================
		 * =============================================================
		 */

		Graphs g = new Graphs();
		g.setEdges(edges);
		g.setVertices(vertices);

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(5, 5);
		test.generateGraph(graph, map);

		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 14);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 40);

		ArrayList<Player> players = new ArrayList<>();
		players.add(0, player1);
		players.add(1, player2);
		graph.setPlayer(players);
		
//		graph.generatePlayers();

		systemOptimalFlow = new SocialOptimum(graph);
		System.out.println(systemOptimalFlow);

		/*
		 * =============================================================
		 * ======================== Test MINTB =========================
		 * =============================================================
		 */

//		Mintb_FC pp = new Mintb_FC();
//		pp.run(graph);

		/*
		 * =============================================================
		 * ======================= Test GaMINTB ========================
		 * =============================================================
		 */

		GaMINTB start = new GaMINTB();

		Population population = new Population(40);

		population.generatechromosomes(graph);

		for (int i = 0; i < 40; i++) {
			population.run(start.getBestsolutions(), graph, population);
		}

		Optional<Chromosom> alpha = start.getBestsolutions().stream()
				.min(Comparator.comparingInt(Chromosom::getEfficiency));
		start.savebestsolution(graph, alpha.get());

		System.err.println("\n========== termination ==========\n");
		System.out.println("Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : "
				+ alpha.get().getEfficiency() + " || Feasibility  : " + alpha.get().isFeasible());

		TestCorrectness correct = new TestCorrectness();
		System.out.println(
				correct.test(graph, graph.getPlayers().get(0).getSource(), graph.getPlayers().get(0).getSink()));
	}
}
