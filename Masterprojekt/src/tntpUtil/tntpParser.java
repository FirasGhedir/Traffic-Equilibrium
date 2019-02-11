package tntpUtil;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import graphModel.Edge;
import graphModel.Vertex;
import heuristic.SocialOptimum;

/**
 * 
 * @author julian
 *
 */
public class tntpParser {

	// --- streams, reader ---
	static FileInputStream fstream;
	static DataInputStream in;
	static BufferedReader br;

	// --- test case ---
	static String testCase = "";

	static String path_flow;
	static String path_net;
	static String path_node;
	static String path_trips;

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
	public tntpParser(String data) {

		for (network name : network.values()) {
			// System.out.printf("%s \n", name.getName());
			if (data == name.getName()) {
				setTestCase(data);
			}
		}
		if (testCase == "") {
			setTestCase("other");
		}

		System.out.println(getTestCase());

		// --- paths to files ---
		path_flow = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase + "_flow.tntp";
		path_net = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase + "_net.tntp";
		path_node = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase + "_node.tntp";
		path_trips = "./Masterprojekt/files/TransportationNetworks/" + testCase + "/" + testCase + "_trips.tntp";

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
						parseVertices(Integer.parseInt(splited[splited.length - 1]));
					}

					if (strLine.startsWith("<NUMBER OF LINKS>")) {
						String[] splited = strLine.split("\\s+");
						System.out.println("\n The " + testCase + " transportation network has "
								+ Integer.parseInt(splited[splited.length - 1]) + " links.\n");
						/*
						 * --- create edges ----
						 */
						parseEdges(Integer.parseInt(splited[splited.length - 1]));
					}

					if (strLine.startsWith("<END OF METADATA>")) {
						break;
					}
				}

				// --- close streams, reader ---
				fstream.close();
				in.close();
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			break;

		default:
			System.err.println("\nReading out sioux falls parameters did not succeed... :-(");
			break;
		}
	}

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

		switch (String.valueOf(testCase)) {
		case "other":

			if (!flow.exists()) {
				System.err.println(testCase + "_flow does not exist...");
				return false;
			}
			if (!net.exists()) {
				System.err.println(testCase + "_net does not exist...");
				return false;
			}
			if (!node.exists()) {
				System.err.println(testCase + "_node does not exist...");
				return false;
			}
			if (!trips.exists()) {
				System.err.println(testCase + "_trips does not exist...");
				return false;
			}
			break;
		}

		return true;
	}

	/**
	 * 
	 * @param numVertex
	 */
	@SuppressWarnings("resource")
	public static void parseVertices(int numVertex) {

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
	public static void parseEdges(int numEdges) {

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
	 * 
	 * @return
	 */
	public static String getTestCase() {
		return testCase;
	}

	/**
	 * 
	 * @param testCase
	 */
	public static void setTestCase(String testCase) {
		tntpParser.testCase = testCase;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * 
	 * @param edges
	 */
	public static void setEdges(ArrayList<Edge> edges) {
		tntpParser.edges = edges;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * 
	 * @param vertices
	 */
	public static void setVertices(ArrayList<Vertex> vertices) {
		tntpParser.vertices = vertices;
	}
}
