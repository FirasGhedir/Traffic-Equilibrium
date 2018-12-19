package graphCharacteristics;

import java.util.LinkedList;

import java.util.Queue;

import graphModel.Graphs;

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
 *          This contains an algorithm to calculate the min cuts of an graph,
 *          based on the following steps:
 * 
 *          1) Run Ford-Fulkerson algorithm and consider the final residual
 *          graph.
 * 
 *          2) Find the set of vertices that are reachable from the source in
 *          the residual graph.
 * 
 *          3) All edges which are from a reachable vertex to non-reachable
 *          vertex are minimum cut edges. Store all such edges.
 */
public class MinCut {

	Graphs G = new Graphs();
	private String mincut;
	private int source;
	private int target;

	/**
	 * Constructor
	 * 
	 * @param graph the given graph
	 */
	public MinCut(Graphs graph) {

		// --- calculate min cuts vor each player ---
		for (int i = 0; i < graph.getPlayers().size(); i++) {
			this.setSource(graph.getPlayers().get(i).getSource().getId());
			this.setTarget(graph.getPlayers().get(i).getSink().getId());
			this.setMincut(minCut(graph.getAdjacencyMatrix(), getSource(), getTarget()));
		}
	}

	/**
	 * Use Breadth-first-search (BFS) to compute the min cuts for the graph
	 * 
	 * Note: for more information about Breadth-first-search:
	 * 
	 * [https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/]
	 * 
	 * What it does: Breadth-first-search queues the source vertex, then iteratively
	 * dequeues the vertex and enqueues its adjacent vertices, until no unvisited
	 * connected vertices remain. Each vertex is marked as visited the first time it
	 * is encountered, and a distance to the source vertex recorded.
	 * 
	 * --------------------------------------------
	 * 
	 * @param adjacencyMatrix the adjacency matrix of the graph
	 * @param source          the source vertex
	 * @param target          the sink vertex
	 * @param parent          the parent to store the path
	 * @return a String with all the paths from source 'source' to sink 'target' in
	 *         the graph
	 */
	private static boolean breadthFirstSearch(int[][] adjacencyMatrix, int source, int target, int[] parent) {

		// --- Create a visited array and mark all vertices as not visited
		boolean[] visited = new boolean[adjacencyMatrix.length];

		// --- Create a queue, enqueue source vertex and mark source vertex as visited
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(source);
		visited[source] = true;
		parent[source] = -1;

		// --- Standard breadth First Search loop
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0; i < adjacencyMatrix.length; i++) {
				if (adjacencyMatrix[v][i] > 0 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
					parent[i] = v;
				}
			}
		}
		return (visited[target] == true);
	}

	/**
	 * Use Depth-first search (DFS) to compute the min cuts for the graph
	 * 
	 * Note: for more information about Depth-first search:
	 * 
	 * [https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/]
	 * 
	 * What it does: the depthFirstSearch function finds all reachable vertices from
	 * source. The function marks visited[i] as true if i is reachable from source.
	 * The initial values in visited[] must be false. We can also use BFS to find
	 * reachable vertices
	 * 
	 * --------------------------------------------
	 * 
	 * @param adjacencyMatrix the adjacencyMatrix to the given graph
	 * @param source          the source vertex
	 * @param visited         the the boolean vector of flags, which indicate, if a
	 *                        vertex is visited or not
	 */
	private static void depthFirstSearch(int[][] adjacencyMatrix, int source, boolean[] visited) {
		visited[source] = true;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[source][i] > 0 && !visited[i]) {
				depthFirstSearch(adjacencyMatrix, i, visited);
			}
		}
	}

	/**
	 * the main min cut algorithm
	 * 
	 * --------------------------------------------
	 * 
	 * @param adjacencyMatrix the adjacency matrix to the given graph
	 * @param source          the source vertex
	 * @param target          the target vertex
	 * @return all the min cut edge pairs represented as Srting
	 */
	private static String minCut(int[][] adjacencyMatrix, int source, int target) {

		int u, v;

		int[][] rGraph = new int[adjacencyMatrix.length][adjacencyMatrix.length];
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				rGraph[i][j] = adjacencyMatrix[i][j];
			}
		}

		int[] parent = new int[adjacencyMatrix.length];

		while (breadthFirstSearch(rGraph, source, target, parent)) {

			int pathFlow = Integer.MAX_VALUE;
			for (v = target; v != source; v = parent[v]) {
				u = parent[v];
				pathFlow = Math.min(pathFlow, rGraph[u][v]);
			}

			for (v = target; v != source; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] = rGraph[u][v] - pathFlow;
				rGraph[v][u] = rGraph[v][u] + pathFlow;
			}
		}

		// Flow is maximum now, find vertices reachable from s
		boolean[] isVisited = new boolean[adjacencyMatrix.length];
		depthFirstSearch(rGraph, source, isVisited);

		// Store all edges as a String that are from a reachable vertex to non-reachable
		// vertex in the original graph
		String mincuts = "";
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				if (adjacencyMatrix[i][j] > 0 && isVisited[i] && !isVisited[j]) {
					if (mincuts.equals("")) {
						mincuts += (i + " - " + j);
					} else {
						mincuts += ("\n" + i + " - " + j);
					}
				}
			}
		}
		return mincuts;
	}

	/**
	 * Getter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the given graph
	 */
	public Graphs getG() {
		return this.G;
	}

	/**
	 * Setter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param g the given graph
	 */
	public void setG(Graphs g) {
		G = g;
	}

	/**
	 * Getter method for min cut pairs
	 * 
	 * --------------------------------------------
	 * 
	 * @return the current min cut pairs
	 */
	public String getMincut() {
		return this.mincut;
	}

	/**
	 * Setter method for min cut pairs
	 * 
	 * --------------------------------------------
	 * 
	 * @param mincut the current mincat pairs
	 */
	public void setMincut(String mincut) {
		this.mincut = mincut;
	}

	/**
	 * Getter method for source vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @return the source vertex
	 */
	public int getSource() {
		return this.source;
	}

	/**
	 * Setter method for source vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param source the source vertex
	 */
	public void setSource(int source) {
		this.source = source;
	}

	/**
	 * Getter method for target vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @return the current target vertex
	 */
	public int getTarget() {
		return this.target;
	}

	/**
	 * Setter method for target vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param target the current target vertex
	 */
	public void setTarget(int target) {
		this.target = target;
	}
}
