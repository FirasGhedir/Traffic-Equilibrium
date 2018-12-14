package graphCharacteristics;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;

import graphModel.Graph;
import graphModel.Graphs;
import sun.misc.Queue;

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
 *          This contains an algorithm to calculate eccentricity, based on
 *          BreadthFirstPaths algorithm
 */
public class Eccentricity {

	private int[] eccentricities;
	private int radius;
	private int diameter;

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
		radius = Integer.MAX_VALUE;
		diameter = Integer.MIN_VALUE;

		for (int vertex = 0; vertex < graph.getVertices().size(); vertex++) {
			eccentricities[vertex] = breadthFirstSearch(graph, vertex);

			if (eccentricities[vertex] < radius) {
				radius = eccentricities[vertex];
			}
			if (eccentricities[vertex] > diameter) {
				diameter = eccentricities[vertex];
			}
		}
	}

	/**
	 * Use breadth first search to compute the eccentricity for the graph
	 * 
	 * Note: for more information about Breadth first search:
	 * 
	 * [https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/]
	 * 
	 * What it does: Breadth first search queues the source vertex, then
	 * iteratively dequeues the vertex and enqueues its adjacent vertices, until no
	 * unvisited connected vertices remain. Each vertex is marked as visited the
	 * first time it is encountered, and a distance to the source vertex recorded.
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
	 * Gets the radius of this graph.
	 * 
	 * --------------------------------------------
	 * 
	 * @return the radius
	 */
	public int getRadius() {
		return this.radius;
	}

	/**
	 * Gets the diameter of this graph.
	 * 
	 * --------------------------------------------
	 * 
	 * @return the diameter
	 */
	public int getDiameter() {
		return this.diameter;
	}
}