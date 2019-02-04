package TransportationNetworks;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
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

	// --- streams, reader ---
	static FileInputStream fstream;
	static DataInputStream in;
	static BufferedReader br;

	// --- test case ---
	static String testCase = "SiouxFalls";

	// --- paths to files ---
	static String path_flow = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase
			+ "_flow.tntp";
	static String path_net = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase + "_net.tntp";
	static String path_node = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase
			+ "_node.tntp";
	static String path_trips = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase
			+ "_trips.tntp";

	/*
	 * --- other ---
	 */
	static ArrayList<Vertex> vertices = new ArrayList<>();
	static ArrayList<Edge> edges = new ArrayList<>();

	static int[] id;
	static int[] from;
	static int[] to;
	static double[] weight;

	static SocialOptimum systemOptimalFlow;

	/**
	 * 
	 */
	public static void printTitle() {
		String leftAlignFormat = "|| %-10s %-70s  ||%n";
		String limiter = "++========================================================================================++";
		// String dashedLimiter =
		// "++----------------------------------------------------------------------------------------++";
		System.out.format("%n");
		System.out.format(limiter + "%n");
		System.out.format(leftAlignFormat, "\t", "");
		System.out.format(leftAlignFormat, "\t", testCase + " (" + "test" + ")");
		System.out.format(leftAlignFormat, "\t", "");
		System.out.format(limiter + "%n%n");
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isValid() {

		// check, if files are valid
		File flow = new File(path_flow);
		File net = new File(path_net);
		File node = new File(path_node);
		File trips = new File(path_trips);

		if (!flow.exists()) {
			System.err.println(testCase + ".flow does not exist...");
			return false;
		}
		if (!net.exists()) {
			System.err.println(testCase + ".net does not exist...");
			return false;
		}
		if (!node.exists()) {
			System.err.println(testCase + ".node does not exist...");
			return false;
		}
		if (!trips.exists()) {
			System.err.println(testCase + ".trips does not exist...");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param numVertex
	 */
	@SuppressWarnings("resource")
	public static void getVertices(int numVertex) {

		id = new int[numVertex];

		try {
			// streams, reader
			FileInputStream fstream = new FileInputStream(path_node);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			int index = 0;
			String strLine = null;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("Node") != true) {
					id[index] = Integer.parseInt(splited[0]);
					++index;
				}
			}

			int vertex = 0;
			// Build ArrayList of the new vertices
			while (vertex < numVertex) {
				++vertex;
				Vertex tmpVertex = new Vertex(id[vertex - 1]);
				vertices.add(vertex - 1, tmpVertex);
			}

			// --- close streams, reader ---
			fstream.close();
			in.close();
			br.close();

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param numEdges
	 */
	public static void getEdges(int numEdges) {

		from = new int[numEdges];
		to = new int[numEdges];
		weight = new double[numEdges];

		try {

			// --- streams,reader ---
			FileInputStream fstream = new FileInputStream(path_net);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			int index = 0;
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {

				if (!strLine.startsWith("<") && !strLine.startsWith("~") && !strLine.isEmpty() == true) {
					String[] splited = strLine.split("\\s+");
					from[index] = Integer.parseInt(splited[1]);
					to[index] = Integer.parseInt(splited[2]);
					weight[index] = Double.parseDouble(splited[3]);
					++index;
				}
			}

			int edge = 0;
			index = 0;
			while (edge < numEdges) {

				++edge;
				Edge tmpEdge = new Edge(vertices.get(from[index] - 1), vertices.get(to[index] - 1), weight[index]);
				edges.add(index, tmpEdge);
				++index;
			}

			// --- close streams, reader ---
			fstream.close();
			in.close();
			br.close();

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

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

		switch (String.valueOf(isValid())) {

		case "true":

			try {

				/*
				 * =============================================================
				 * ================== ADD EDGES AND VERTICES ===================
				 * =============================================================
				 */

				// --- streams,reader ---
				fstream = new FileInputStream(path_net);
				in = new DataInputStream(fstream);
				br = new BufferedReader(new InputStreamReader(in));

				String strLine;
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {

					if (strLine.startsWith("<NUMBER OF NODES>")) {
						String[] splited = strLine.split("\\s+");
						System.out.println("The " + testCase + " transportation network has "
								+ Integer.parseInt(splited[splited.length - 1]) + " nodes.\n");
						/*
						 * --- create vertices ----
						 */
						getVertices(Integer.parseInt(splited[splited.length - 1]));
					}

					if (strLine.startsWith("<NUMBER OF LINKS>")) {
						String[] splited = strLine.split("\\s+");
						System.out.println("\n The " + testCase + " transportation network has "
								+ Integer.parseInt(splited[splited.length - 1]) + " links.\n");
						/*
						 * --- create edges ----
						 */
						getEdges(Integer.parseInt(splited[splited.length - 1]));
					}

					if (strLine.startsWith("<END OF METADATA>")) {
						break;
					}
				}

				// --- close streams, reader ---
				fstream.close();
				in.close();
				br.close();

				/*
				 * =============================================================
				 * ====================== TEST HEURISTICS ======================
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

//				graph.generatePlayers();

				systemOptimalFlow = new SocialOptimum(graph);
				System.out.println(systemOptimalFlow);

				/*
				 * =============================================================
				 * ======================== Test MINTB =========================
				 * =============================================================
				 */

//				Mintb_FC pp = new Mintb_FC();
//				pp.run(graph);

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
				System.out.println(
						"Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : "
								+ alpha.get().getEfficiency() + " || Feasibility  : " + alpha.get().isFeasible());

				TestCorrectness correct = new TestCorrectness();
				System.out.println(correct.test(graph, graph.getPlayers().get(0).getSource(),
						graph.getPlayers().get(0).getSink()));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			break;

		default:
			System.err.println("\nReading out sioux falls parameters did not succeed... :-(");
			break;
		}
	}
}
