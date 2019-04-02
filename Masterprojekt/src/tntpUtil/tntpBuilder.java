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
public class tntpBuilder {

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

	/*
	 * Debug
	 */
	boolean debug = true;

	/**
	 * 
	 */
	public tntpBuilder(Graphs g, String graphtype, String nbvertices, String vertices, String commodoties, String id) {

		setGraph(g);
		setTransportationNetwork(transportationNetwork);

		setEdges(g.getEdges());
		setVertices(g.getVertices());

		readVertexParameters();
		readEdgeParameters();

		if (debug) {
			System.out.println("----------------\ntntpBuilder");
			System.out.println("graphtype:           " + graphtype);
			System.out.println("number vertices:     " + nbvertices);
			System.out.println("number players:      " + vertices);
			System.out.println("number commodoties:  " + commodoties);
			System.out.println("id                   " + id);
			System.out.println("----------------");
		}

		buildFlow(graphtype, nbvertices, vertices, commodoties, id);
		buildNet(graphtype, nbvertices, vertices, commodoties, id);
		buildNode();
		buildTrips();

		System.out.println("Building " + graphtype + " with id = " + id + "  was successful...");

	}

	/**
	 * 
	 */
	public void readVertexParameters() {

		if (debug) {
			System.out.println("----------------\nreadVertexParameters");
			for (Vertex vertex : vertices) {
				System.out.println(vertex.getId());
			}
			System.out.println("----------------");
		}

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

		if (debug) {
			System.out.println("----------------\nreadEdgeParameters");
			int index = 0;
			for (Edge edge : edges) {
				System.out.println(index + " -> from  : " + edge.getFrom().getId());
				System.out.println(index + " -> to    : " + edge.getTo().getId());
				System.out.println(index + " -> weight: " + edge.getWeight());
				System.out.println(index + " -> cost:   " + edge.getC());
				System.out.println("--");
				++index;
			}
			System.out.println("----------------");
		}

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
	public void buildFlow(String graphtype, String nbvertices, String vertices, String commodities, String id) {

		path_flow = "./Masterprojekt/files/" + graphtype + "/" + nbvertices + "/" + vertices + "." + commodities
				+ "_ID_" + id + "_flow.tntp";

		try (PrintWriter buildFlowWriter = new PrintWriter(path_flow + ".txt", "UTF-8")) {

			if (debug) {
				System.out.println("----------------\nbuildFlow");
				System.out.println("path: " + path_flow);
				System.out.printf("%-10s %-10s %-15s %-10s\n", "From", "To", "Volume", "Cost");
				for (int i = 0; i < getEdges().size(); i++) {
					System.out.printf("%-10s %-10s %-15s %-10s\n", from[i], to[i], weight[i], cost[i]);
				}
				System.out.println("----------------");
			}

			buildFlowWriter.printf("%-10s %-10s %-15s %-10s\n", "From", "To", "Volume", "Cost");

			for (int i = 0; i < getEdges().size(); i++) {
				buildFlowWriter.printf("%-10s %-10s %-15s %-10s\n", from[i], to[i], weight[i], cost[i]);
			}

			buildFlowWriter.flush();
			buildFlowWriter.close();

		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public void buildNet(String graphtype, String nbvertices, String vertices, String commodities, String id) {

		path_net = "./Masterprojekt/files/" + graphtype + "/" + nbvertices + "/" + vertices + "." + commodities + "_ID_"
				+ id + "_net.tntp";

		try (PrintWriter buildNetWriter = new PrintWriter(path_net + ".txt", "UTF-8")) {

			if (debug) {
				System.out.println("----------------\nbuildFlow");
				System.out.println("path: " + path_flow);
				System.out.printf("%-10s %-10s %-15s %-10s\n", "From", "To", "Volume", "Cost");
				System.out.printf("%-10s %-10s\n", "<NUMBER OF ZONES>", getVertices().size());
				System.out.printf("%-10s %-10s\n", "<NUMBER OF NODES>", getVertices().size());
				System.out.printf("%-10s %-10s\n", "<FIRST THRU NODE>", 1);
				System.out.printf("%-10s %-10s\n", "<NUMBER OF LINKS>", getEdges().size());
				System.out.printf("%-10s\n", "<END OF METADATA>\n\n");
				System.out.printf("%-10s %-10s %-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n", "~", "Init", "node",
						"Term node", "Capacity", "Length", "Free Flow Time", "B", "Power", "Speed limit", "Toll Type");
				for (int i = 0; i < getEdges().size(); i++) {
					System.out.printf("%-10s %-10s %-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n", "", from[i],
							to[i], "-", weight[i], "-", "-", "-", "-", "-", "-");
				}
				System.out.println("----------------");
			}

			buildNetWriter.printf("%-10s %-10s\n", "<NUMBER OF ZONES>", getVertices().size());
			buildNetWriter.printf("%-10s %-10s\n", "<NUMBER OF NODES>", getVertices().size());
			buildNetWriter.printf("%-10s %-10s\n", "<FIRST THRU NODE>", 1);
			buildNetWriter.printf("%-10s %-10s\n", "<NUMBER OF LINKS>", getEdges().size());
			buildNetWriter.printf("%-10s\n", "<END OF METADATA>");

			buildNetWriter.println();
			buildNetWriter.println();

			buildNetWriter.printf("%-10s %-10s %-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n", "~", "Init", "node",
					"Term node", "Capacity", "Length", "Free Flow Time", "B", "Power", "Speed limit", "Toll Type");

			for (int i = 0; i < getEdges().size(); i++) {
				buildNetWriter.printf("%-10s %-10s %-10s %-15s %-10s %-10s %-10s %-10s %-10s %-10s\n", "", from[i],
						to[i], "-", weight[i], "-", "-", "-", "-", "-", "-");

			}

			buildNetWriter.flush();
			buildNetWriter.close();

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
