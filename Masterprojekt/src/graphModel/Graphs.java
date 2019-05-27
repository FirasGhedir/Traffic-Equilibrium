package graphModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
 *          A collection of utilities to assist with graph manipulation.
 */
public class Graphs implements Graph<Vertex, Edge> {

	private static String adjacencyMatrixAsString;
	private static String gridGraphDataAsString;
	final static int d_max = 100;
	final static int d_min = 10;
	final static int b_max = 10;
	final static int b_min = 0;
	final static int z_max = 10;
	final static int z_min = 1;
	final static double Pi = 1.57079632679;
	final static int max_players = 6;
	final static int accuracy = 1000;
	Graphe graphe = new Graphe();
	public ArrayList<Vertex> vertices;
	public ArrayList<Edge> edges;
	private ArrayList<Player> players = new ArrayList<>();
	private Random rand = new Random();
	private List<Integer>[] adj;
	private LinkedList<Integer>[] adjListArray;
	ArrayList<Double> beta;
	List<Player> P;
	String path;

	/**
	 * 
	 */
	public Graphs() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		beta = new ArrayList<>();
		P = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	public Graphs(Graphs that) {
		this.vertices = (ArrayList<graphModel.Vertex>) that.vertices.clone();
		this.edges = (ArrayList<graphModel.Edge>) that.edges.clone();
		this.players = (ArrayList<Player>) that.players.clone();
		this.rand = that.rand;
		if (this.adj != null && that.adj != null)
			this.adj = that.adj.clone();
		if (this.adjListArray != null && that.adjListArray != null)
			this.adjListArray = that.adjListArray.clone();
		if (this.beta != null && that.beta != null)
			this.beta = (ArrayList<Double>) that.beta.clone();
		if (this.P != null && that.P != null)
			this.P = that.P;
	}

	public void generateEdgesFunctions() {
		for (int i = 0; i < this.edges.size(); i++) {
			// this.edges.get(i).setCostA(1);
			// this.edges.get(i).setCostB(1);
			// ax+b is randomly generated
			this.edges.get(i).setCostA(Math.tan(Pi * rand.nextDouble()));
			this.edges.get(i).setCostB(b_min + (b_max - b_min) * rand.nextDouble());

		}
	}

	@Override
	public Edge getEdge(Vertex sourceVertex, Vertex targetVertex) {
		for (int i = 0; i < getEdges().size(); i++) {
			if (getEdges().get(i).getTo().equals(targetVertex) && getEdges().get(i).getFrom().equals(sourceVertex)) {
				return getEdges().get(i);
			}

		}
		return null;

	}

	@Override
	public Edge addEdge(Vertex sourceVertex, Vertex targetVertex) {
		edges.add(new Edge(sourceVertex, targetVertex, 0));
		return null;
	}

	@Override
	public boolean addEdge(Vertex sourceVertex, Vertex targetVertex, Edge e) {
		edges.add(new Edge(sourceVertex, targetVertex, 0));
		return false;
	}

	@Override
	public boolean addVertex(Vertex v) {
		vertices.add(v);
		return true;
	}

	@Override
	public boolean containsEdge(Edge e) {
		return this.edges.contains(e);
	}

	@Override
	public boolean containsVertex(Vertex v) {
		return this.vertices.contains(v);
	}

	@Override
	public int degreeOf(Vertex vertex) {
		int counter = 0;
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getFrom().equals(vertex)) {
				counter++;
			}

		}

		return counter;
	}

	@Override
	public boolean removeEdge(Edge e) {
		edges.remove(e);
		return true;
	}

	@Override
	public boolean removeVertex(Vertex v) {
		vertices.remove(v);
		return true;
	}

	@Override
	public double getEdgeWeight(Edge e) {
		return e.getL();
	}

	@Override
	public void setEdgeWeight(Edge e, double weight) {
		e.setWeight(weight);

	}

	@Override
	public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
		Edge e = new Edge(sourceVertex, targetVertex, 0);
		return this.edges.contains(e);
	}

	public ArrayList<Vertex> getVertices() {
		return this.vertices;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public Vertex addVertex() {
		this.getVertices().add(new Vertex(50));
		return null;
	}

	@Override
	public GraphType getType() {
		return graphe;
	}

	@Override
	public Set<Vertex> vertexSet() {
		Set<Vertex> tmp = new HashSet<>(getVertices());
		return tmp;
	}

	@Override
	public Vertex getEdgeSource(Edge e) {
		return e.getFrom();
	}

	@Override
	public Vertex getEdgeTarget(Edge e) {

		return e.getTo();
	}

	/**
	 * Gets the vertex opposite another vertex across an edge.
	 *
	 * @param g graph containing e and v
	 * @param e edge in g
	 * @param v vertex in g
	 * @param   <V> the graph vertex type
	 * @param   <E> the graph edge type
	 *
	 * @return vertex opposite to v across e
	 */
	@SuppressWarnings("hiding")
	public static <Vertex, Edge> Vertex getOppositeVertex(Graph<Vertex, Edge> g, Edge e, Vertex v) {
		Vertex source = g.getEdgeSource(e);
		Vertex target = g.getEdgeTarget(e);
		if (v.equals(source)) {
			return target;
		} else if (v.equals(target)) {
			return source;
		} else {
			throw new IllegalArgumentException("no such vertex: " + v.toString());
		}
	}

	/**
	 * return this.the in going neighbors of the specified vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param vertex          the given vertex
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return this.an Arraylist of vertices with all ingoing edges for each vertex
	 */
	public ArrayList<Vertex> getOutNeighbors(Vertex vertex, int[][] adjacencymatrix) {
		ArrayList<Vertex> tmp = new ArrayList<>();
		for (int i = 0; i < adjacencymatrix[vertex.getId()].length; i++) {
			if (adjacencymatrix[vertex.getId()][i] == 1) {
				tmp.add(this.vertices.get(i));
			}

		}

		return tmp;

	}

	/**
	 * return this.the out going neighbors of the specified vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param vertex          the given vertex
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return this.return this.an Arraylist of verteces with all outgoing edges for
	 *         each vertex
	 */
	public ArrayList<Vertex> getInNeighbors(Vertex vertex, int[][] adjacencymatrix) {

		ArrayList<Vertex> list = new ArrayList<>();

		for (int i = 0; i < adjacencymatrix.length; i++) {
			if (i == vertex.getId())
				continue;
			if (adjacencymatrix[i][vertex.getId()] == 1)
				list.add(this.vertices.get(i));

		}

		return list;
	}

	/**
	 * Delimiter | for each row when printing matrix
	 */
	static Consumer<int[]> likeAList = row -> {
		adjacencyMatrixAsString += ("|");
		Arrays.stream(row).forEach((el) -> adjacencyMatrixAsString += (" " + el + " "));
		adjacencyMatrixAsString += ("|\n");
	};

	/**
	 * Prints a 2D array
	 * 
	 * --------------------------------------------
	 * 
	 * @param matrix     the given matrix
	 * @param rowPrinter prints each row of the matrix
	 * @return
	 */
	public static String printMatrix(int[][] matrix, Consumer<int[]> rowPrinter) {
		Arrays.stream(matrix).forEach((row) -> rowPrinter.accept(row));
		return adjacencyMatrixAsString;
	}

	/**
	 * Prints a 1D array
	 * 
	 * --------------------------------------------
	 * 
	 * @param vector the given vector
	 */
	public static void printVector(int[] vector) {
		System.out.println(Arrays.toString(vector));
	}

	/**
	 * Creates and fills a adjacency matrix of a graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return this.the adjacency matrix of the graph
	 */
	public int[][] getAdjacencyMatrix() {

		int[][] adjacencyMatrix = new int[vertices.size()][vertices.size()];

		try {

			// fill each rows with zeros
			for (int[] row : adjacencyMatrix) {
				Arrays.fill(row, 0);
			}

			// fill adjacency matrix
			for (int i = 0; i < vertices.size(); i++) {
				for (int j = 0; j < edges.size(); j++) {
					if (edges.get(j).getFrom().equals(vertices.get(i))) {
						adjacencyMatrix[i][edges.get(j).getTo().getId()] = 1;
					}
				}
			}

		} catch (Exception e) {
			if (this.getEdges().size() > (long) this.getVertices().size() * (this.getVertices().size() - 1) / 2
					+ this.getVertices().size()) {
				throw new IllegalArgumentException("Too many edges");
			}
			if (this.getEdges().size() < 0) {
				throw new IllegalArgumentException("Too few edges");
			}
		}

		return adjacencyMatrix;
	}

	/**
	 * Creates and fills a adjacency matrix of a graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return this.the adjacency matrix of the graph
	 */
	@SuppressWarnings("unchecked")
	public List<Integer>[] getAdjacencyList() {

		try {

			int[][] adjacencyMatrix = this.getAdjacencyMatrix();
			adjListArray = new LinkedList[vertices.size()];

			// Create a new list for each vertex such that adjacent nodes can be stored
			for (int i = 0; i < vertices.size(); i++) {
				adjListArray[i] = new LinkedList<Integer>();
			}
			int i = 0;
			for (List<Integer> list : adjListArray) {

				// rows
				for (int j = 0; j < adjacencyMatrix.length; j++) {
					if (!(adjacencyMatrix[i][j] == 0)) {
						list.add(j);
					}
				}

				// columns
				for (int k = 0; k < adjacencyMatrix.length; k++) {
					if (!(adjacencyMatrix[k][i] == 0)) {
						list.add(k);
					}
				}

				++i;
			}

		} catch (Exception e) {
			if (this.getEdges().size() > (long) this.getVertices().size() * (this.getVertices().size() - 1) / 2
					+ this.getVertices().size()) {
				throw new IllegalArgumentException("Too many edges");
			}
			if (this.getEdges().size() < 0) {
				throw new IllegalArgumentException("Too few edges");
			}
		}

		return this.adjListArray;
	}

	/**
	 * Creates the node potential vector for a given graph
	 * --------------------------------------------
	 * 
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return this.the node potential vector out of the adjacency matrix
	 */
	public int[] getNodePotentialVector() {

		int[] nodePotentialVector = new int[this.getVertices().size()];

		for (Vertex vertex : this.getVertices()) {
			nodePotentialVector[vertex.getId()] = this.degreeOf(vertex);
		}

		return nodePotentialVector;
	}

	/**
	 * Gets an iterator over the neighbors of Vertex named v
	 * 
	 * --------------------------------------------
	 *
	 * @param v the int number of a Vertex
	 * @return this.an Iterator over Vertices that are adjacent to the Vertex named
	 *         v, empty set if v is not in graph
	 */
	public Iterable<Integer> adjacentTo(int v) {
		adj = getAdjacencyList();
		return this.adj[v];
	}

	/**
	 * Sets the players for a given number
	 * 
	 * --------------------------------------------
	 * 
	 * @param n the number of players
	 */
	public void generatePlayers() {

		double max = this.getVertices().size() * 0.15;
		double min = this.getVertices().size() * 0.06;
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		ArrayList<Integer> idcontainer = new ArrayList<>();

		for (int i = 0; i < x; i++) {
			int tmp = 0;
			int tmp1 = 0;

			do {
				tmp = rand.nextInt(getVertices().size());

			} while (idcontainer.contains(tmp));
			idcontainer.add(tmp);
			do {
				tmp1 = rand.nextInt(getVertices().size());
			} while (idcontainer.contains(tmp1));
			idcontainer.add(tmp1);
			this.getPlayers().add(new Player(i, this.getVertices().get(tmp), this.getVertices().get(tmp1),
					d_min + rand.nextInt((d_max - d_min) + 1)));
		}
	}

	/**
	 * Gets the current players as ArrayList
	 * 
	 * --------------------------------------------
	 * 
	 * @return the current players as ArrayList
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Sets the current players as ArrayList
	 * 
	 * --------------------------------------------
	 * 
	 * @param players the current players as ArrayList
	 */
	public void setPlayer(ArrayList<Player> players) {
		this.players = players;
	}

	@SuppressWarnings("hiding")
	public static <Vertex, Edge> boolean testIncidence(Graph<Vertex, Edge> g, Edge e, Vertex v) {
		return (g.getEdgeSource(e).equals(v)) || (g.getEdgeTarget(e).equals(v));
	}

	/**
	 * Prints a title in a fancy frame on the console
	 * 
	 * @param title the title to print
	 */
	private static String printTitle(String title) {
		return ("\n+------------------------------\n|     " + title + ":\n+------------------------------\n");
	}

	/**
	 * The toString() method returns the string representation of the object
	 * CharacteristicsCalculation.
	 */
	@Override
	public String toString() {

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
				this.getClass().getSimpleName() + " (" + this.getClass().getName() + ")");
		System.out.format(leftAlignFormat, "\t", "");
		System.out.format(limiter + "%n%n");

		/*
		 * return the string representation of the object
		 */

		gridGraphDataAsString = "";
		adjacencyMatrixAsString = "";

		gridGraphDataAsString += "#edges:    " + getEdges().size() + "\n#vertices: " + getVertices().size() + "\n";
		adjacencyMatrixAsString += Graphs.printMatrix(getAdjacencyMatrix(), likeAList);

		return (printTitle("Gridgraph") + "\n" + gridGraphDataAsString + printTitle("Adjacency Matrix") + "\n"
				+ adjacencyMatrixAsString
		// + printTitle("Node potential vector") + "\n" +
		// Arrays.toString(getNodePotentialVector(getAdjacencyMatrix()))
		);
	}

	@Override
	public Set<Edge> edgeSet() {
		Set<Edge> tmp = new HashSet<Edge>(edges);
		return tmp;
	}

	@Override
	public ArrayList<Edge> outgoingEdgesOf(Vertex v) {

		ArrayList<Edge> tmp = new ArrayList<>();
		for (int i = 0; i < getEdges().size(); i++) {
			if (getEdges().get(i).getFrom().equals(v))
				tmp.add(getEdges().get(i));
		}
		return tmp;
	}

	public void fillbeta() {
		for (int i = 0; i < getEdges().size(); i++) {
			getBeta().add(getEdges().get(i).getBetta());
		}
	}

	public ArrayList<Double> getBeta() {
		return beta;
	}

	public void setBeta(ArrayList<Double> beta) {
		this.beta = beta;
	}

	public List<Player> getP() {
		return P;
	}

	public void setP(List<Player> p) {
		P = p;
	}

	public void generatecomodity() {

		double max = this.getVertices().size() * 0.15;
		double min = this.getVertices().size() * 0.06;
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);

		for (int k = 0; k < x; k++) {
			int ss = (int) (Math.random() * (this.getVertices().size() + 1 - 1));
			ArrayList<Integer> list = new ArrayList<>();
			list.add(ss);
			while (ss != 0 && list.size() < 10) {
				ss = recursive(ss);
				list.add(ss);
			}
			List<Integer> deduped = list.stream().distinct().collect(Collectors.toList());

			this.players.add(converter(deduped, k));
			System.out.println(deduped.get(0) + " " + deduped.get(1));
			list.clear();
			deduped.clear();
			System.out.println("########");
		}
	}

	public int recursive(int id) {
		Vertex v = this.getVertices().get(id);
		for (int j = 0; j < this.getEdges().size(); j++) {
			if (this.getEdges().get(j).getFrom().equals(v)) {
				Vertex u = this.getEdges().get(j).getTo();
				return u.getId();
			}
		}
		return 0;
	}

	public Player converter(List<Integer> deduped, int id) {
		return new Player(id, getVertices().get(deduped.get(0)), getVertices().get(deduped.get(deduped.size() - 1)),
				d_min + rand.nextInt((d_max - d_min) + 1));
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

}