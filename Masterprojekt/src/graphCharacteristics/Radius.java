package graphCharacteristics;

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
 *          This contains an algorithm to calculate radius of a graph, based on
 *          BreadthFirstPaths algorithm.
 * 
 *          The radius of a graph is the minimum graph eccentricity of any graph
 *          vertex in a graph. A disconnected graph therefore has infinite
 *          radius
 */
public class Radius {

	Graphs G = new Graphs();
	private int radius; // the minimum eccentricity of any vertex in the graph
	private int[] eccentricities; // vertex vector with its eccentricity values

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph the given graph
	 * @throws InterruptedException if an error occures
	 */
	public Radius(Graphs graph) throws InterruptedException {

		this.setG(graph);
		radius = Integer.MAX_VALUE;
		eccentricities = new int[graph.getVertices().size()];
		calcRadius();
	}

	/**
	 * calculates the radius for the given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @throws InterruptedException if an error occures
	 */
	public void calcRadius() throws InterruptedException {
		for (int vertex = 0; vertex < this.G.getVertices().size(); vertex++) {
			this.eccentricities[vertex] = breadthFirstSearch(this.G, vertex);

			if (eccentricities[vertex] < radius) {
				radius = eccentricities[vertex];
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

		return eccentricity;
	}

	/**
	 * Gets the diameter
	 * 
	 * @return the diameter
	 */
	public int getRadius() {
		return this.radius;
	}

	/**
	 * Sets the current graph
	 * 
	 * @param g the given graph
	 */
	public void setG(Graphs g) {
		this.G = g;
	}
}
