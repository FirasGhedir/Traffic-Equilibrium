package graphModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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

	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<Edge> edges = new ArrayList<Edge>();
	ArrayList<Player> players = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	List<Integer>[] adj;
	LinkedList<Integer>[] adjListArray;

	/**
	 * 
	 */
	public Graphs() {

	}

	@Override
	public Edge getEdge(Vertex sourceVertex, Vertex targetVertex) {
		Edge x = new Edge(sourceVertex, targetVertex, 0);
		if (edges.contains(x)) {
			return x;
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
		return e.getWeight();
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
		return null;
	}

	@Override
	public GraphType getType() {
		return null;
	}

	@Override
	public Set<Vertex> vertexSet() {
		return null;
	}

	/**
	 * return this.the in going neighbors of the specified vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param vertex          the given vertex
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return this.an Arraylist of verteces with all ingoing edges for each vertex
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
	 * @return this.return this.an Arraylist of verteces with all outgoing edges for each
	 *         vertex
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
	public int[] getNodePotentialVector(int[][] adjacencymatrix) {

		int[] nodePotentialVector = new int[adjacencymatrix.length];

		int index = 0;
		for (int i : nodePotentialVector) {
			int tmp = 0;
			for (int j = 0; j < adjacencymatrix.length; j++) {
				tmp += adjacencymatrix[j][i] + adjacencymatrix[i][j];
			}
			nodePotentialVector[index] = tmp;
			++index;
		}

		return nodePotentialVector;
	}

	/**
	 * return this.an iterator over the neighbors of Vertex named v
	 * 
	 * --------------------------------------------
	 *
	 * @param v the int number of a Vertex
	 * @return this.an Iterator over Vertices that are adjacent to the Vertex named v,
	 *         empty set if v is not in graph
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
	public void setPlayers(int n) {
		int x, y, z;
		x = 0;
		y = 0;
		z = 0;
		for (int i = 0; i < n; i++) {
			System.out.println("Please insert the Source of Player " + i + " : ");
			x = scan.nextInt();
			System.out.println("Please insert the sink of Player " + i + " : ");
			y = scan.nextInt();
			System.out.println("Please insert the demand of player " + i + " : ");
			z = scan.nextInt();
			players.add(i, new Player(i, this.getVertices().get(x), this.getVertices().get(y), z));
		}
	}

	/**
	 * return this.the current players as ArrayList
	 * 
	 * --------------------------------------------
	 * 
	 * @return this.the current players as ArrayList
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	

}