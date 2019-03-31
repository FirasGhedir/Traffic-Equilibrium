package tntpUtil;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;

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
 *          This class accesses tools to build '.tntp' files, whereas tntp
 *          stands for 'Transportation Network Test Problems'
 */
public class ss {

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
	Graphs graph;
	network transportationNetwork;

	static ArrayList<Vertex> vertices = new ArrayList<>();
	static ArrayList<Edge> edges = new ArrayList<>();

	static int[] id;
	static int[] from;
	static int[] to;
	static double[] weight;
	static double[] cost;

	/**
	 * 
	 */
	public ss(Graphs g, network transportationNetwork) {

		setGraph(g);
		setTransportationNetwork(transportationNetwork);

		setEdges(g.getEdges());
		setVertices(g.getVertices());

		readVertexParameters();
		readEdgeParameters();

		buildFlow();
		buildNet();
		buildNode();
		buildTrips();

		System.out.println("Building " + getTransportationNetwork().getName() + " tntp files was successful...");

	}

	/**
	 * 
	 */
	public void readVertexParameters() {

		id = new int[getVertices().size()];

		int index = 0;
		for (Vertex vertex : vertices) {

			id[index] = vertex.getId();

			++index;
		}
	}

	/**
	 * 
	 */
	public void readEdgeParameters() {

		from = new int[getEdges().size()];
		to = new int[getEdges().size()];
		weight = new double[getEdges().size()];
		cost = new double[getEdges().size()];

		int index = 0;
		for (Edge edge : edges) {

			from[index] = edge.getFrom().getId();
			to[index] = edge.getTo().getId();
			weight[index] = edge.getWeight();
			cost[index] = edge.getC();

			++index;
		}
	}

	/**
	 * 
	 */
	public void buildFlow() {

		path_flow = "./Masterprojekt/files/TransportationNetworks/" + "other" + "/"
				+ "algo" + "_flow.tntp";

		PrintWriter writer;
		try {
			writer = new PrintWriter(path_flow + ".txt", "UTF-8");

			writer.printf("%-10s %-10s %-15s %-10s\n", "From", "To", "Volume", "Cost");

			for (int i = 0; i < getEdges().size(); i++) {
				writer.printf("%-10s %-10s %-15s %-10s\n", from[i], to[i], weight[i], cost[i]);
			}

			writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public void buildNet() {

		path_net = "./Masterprojekt/files/TransportationNetworks/" + "other" + "/"
				+ "algo" + "_net.tntp";

		PrintWriter writer;
		try {
			writer = new PrintWriter(path_net + ".txt", "UTF-8");

			writer.printf("%-10s %-10s\n", "<NUMBER OF ZONES>", getVertices().size());
			writer.printf("%-10s %-10s\n", "<NUMBER OF NODES>", getVertices().size());
			writer.printf("%-10s %-10s\n", "<FIRST THRU NODE>", 1);
			writer.printf("%-10s %-10s\n", "<NUMBER OF LINKS>", getEdges().size());
			writer.printf("%-10s\n", "<END OF METADATA>");

			writer.println();
			writer.println();

			writer.printf("%-10s %-10s %-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n", "~", "Init", "node",
					"Term node", "Capacity", "Length", "Free Flow Time", "B", "Power", "Speed limit", "Toll Type");

			for (int i = 0; i < getEdges().size(); i++) {
				writer.printf("%-10s %-10s %-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n", "", from[i], to[i], "-",
						weight[i], "-", "-", "-", "-", "-", "-");

			}

			writer.close();

		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void buildNode() {
		// TODO
	}

	/**
	 * 
	 */
	public void buildTrips() {
		// TODO
	}

	/**
	 * 
	 * @return
	 */
	public Graphs getGraph() {
		return this.graph;
	}

	/**
	 * 
	 * @param graph
	 */
	public void setGraph(Graphs graph) {
		this.graph = graph;
	}

	/**
	 * 
	 * @return
	 */
	public network getTransportationNetwork() {
		return this.transportationNetwork;
	}

	/**
	 * 
	 * @param transportationNetwork
	 */
	public void setTransportationNetwork(network transportationNetwork) {
		this.transportationNetwork = transportationNetwork;
	}

	/**
	 * 
	 * @return
	 */
	public static ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * 
	 * @param edges
	 */
	public static void setEdges(ArrayList<Edge> edges) {
		tntpBuilder.edges = edges;
	}

	/**
	 * 
	 * @return
	 */
	public static ArrayList<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * 
	 * @param vertices
	 */
	public static void setVertices(ArrayList<Vertex> vertices) {
		tntpBuilder.vertices = vertices;
	}
}