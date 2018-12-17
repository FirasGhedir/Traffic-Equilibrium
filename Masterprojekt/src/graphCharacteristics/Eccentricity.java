package graphCharacteristics;

import java.util.Arrays;

import graphModel.Graphs;
import sun.misc.Queue;

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
 *          This contains an algorithm to calculate eccentricity, based on
 *          BreadthFirstPaths algorithm.
 * 
 *          The eccentricity of a graph vertex v in a connected graph G is the
 *          maximum graph distance between v and any other vertex u of G. For a
 *          disconnected graph, all vertices are defined to have infinite
 *          eccentricity.
 */
public class Eccentricity {

	private double avgEccentricity; // the average vertex eccentricity of the graph
	private int[] eccentricities; // vertex vector with its eccentricity values

	/**
	 * Computes the eccentricity of each vertex in the graph and calculates the
	 * radius and diameter based on the eccentricity for each vertex.
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph the given graph
	 * @throws InterruptedException
	 */
	public Eccentricity(Graphs graph) throws InterruptedException {
		
		eccentricities = new int[graph.getVertices().size()];
		avgEccentricity = Double.MAX_VALUE;

		for (int vertex = 0; vertex < graph.getVertices().size(); vertex++) {
			eccentricities[vertex] = breadthFirstSearch(graph, vertex);
		}
	}

	/**
	 * Use breadth first search to compute the eccentricity for the graph
	 * 
	 * Note: for more information about Breadth first search:
	 * 
	 * [https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/]
	 * 
	 * What it does: Breadth first search queues the source vertex, then iteratively
	 * dequeues the vertex and enqueues its adjacent vertices, until no unvisited
	 * connected vertices remain. Each vertex is marked as visited the first time it
	 * is encountered, and a distance to the source vertex recorded.
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph        the given graph
	 * @param sourceVertex the source vertex
	 * @throws InterruptedException
	 */
	private int breadthFirstSearch(Graphs graph, int sourceVertex) throws InterruptedException {

		Queue<Integer> queue = new Queue<>();

		boolean[] isVisited = new boolean[graph.getVertices().size()];
		int[] distanceToSource = new int[graph.getVertices().size()];

		isVisited[sourceVertex] = true;
		distanceToSource[sourceVertex] = 0;
		queue.enqueue(sourceVertex);

		int eccentricity = 0;
		while (!queue.isEmpty()) {
			int thisVertex = queue.dequeue();
			for (int adjacentVertex : graph.adjacentTo(thisVertex)) {
				if (!isVisited[adjacentVertex]) {
					distanceToSource[adjacentVertex] = distanceToSource[thisVertex] + 1;
					isVisited[adjacentVertex] = true;
					queue.enqueue(adjacentVertex);

					if (distanceToSource[adjacentVertex] > eccentricity) {
						eccentricity = distanceToSource[adjacentVertex];
					}
				}
			}
		}

		this.avgEccentricity = Arrays.stream(eccentricities).average().orElse(Double.NaN);

		return eccentricity;
	}

	/**
	 * Get the eccentricities for each vertex in this graph.
	 * 
	 * --------------------------------------------
	 * 
	 * @return the eccentricities for each vertex
	 */
	public int[] getEccentricities() {
		return this.eccentricities;
	}

	/**
	 * Get the average eccentricities in the given graph.
	 * 
	 * --------------------------------------------
	 * 
	 * @return the average eccentricity
	 */
	public double getAvgEccentricity() {
		return this.avgEccentricity;
	}
}