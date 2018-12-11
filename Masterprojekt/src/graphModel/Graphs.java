package graphModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Universit�t Ulm
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

	private static final Iterable<Integer>[] AdjacencyList = null;
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<Edge> edges = new ArrayList<Edge>();

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
		return edges.contains(e);
	}

	@Override
	public boolean containsVertex(Vertex v) {
		return vertices.contains(v);
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
		return edges.contains(e);
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
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
	 * Return the in going neighbors of the specified vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param vertex          the given vertex
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return an Arraylist of verteces with all ingoing edges for each vertex
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
	 * Return the out going neighbors of the specified vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param vertex          the given vertex
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return return an Arraylist of verteces with all outgoing edges for each
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
	 * --------------------------------------------
	 * 
	 * @return the adjacency matrix of the graph
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
	 * Creates the node potential vector for a given graph
	 * --------------------------------------------
	 * 
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return the node potential vector out of the adjacency matrix
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
	 * Creates and fills a adjacency list of a graph
	 * --------------------------------------------
	 * 
	 * @return the adjacency list of the graph
	 */
	public ArrayList<ArrayList<Integer>> getAdjacencyList() {

		// create empty adjacency lists - one for each node
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();

		try {

			for (int i = 0; i < vertices.size(); i++) {
				adjList.add(new ArrayList<Integer>());
			}

			// fill adjacency list
			for (int i = 0; i < vertices.size(); i++) {
				for (int j = 0; j < edges.size(); j++) {
					if (edges.get(j).getFrom().equals(vertices.get(i))) {
						adjList.get(i).add(j);
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

		return adjList;
	}

	/**
	 * Gets the vertices adjacent to the specified vertex.
	 *
	 * @param vertex The vertex to get adjacent vertices for.
	 *
	 * @return The vertices adjacent to the specified vertex.
	 */
	public Iterable<Integer> adjacentTo(int vertex) {
		return AdjacencyList[vertex];
	}
}