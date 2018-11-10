package GraphGenerators;

import java.util.*;

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
 *          In the class GridGraphGenerator you can create a grid graph of any
 *          size
 */
public class GridGraphGenerator implements GraphGenerator<Vertex, Edge, Vertex> {

	public static final String CORNER_VERTEX = "Corner Vertex"; // Role for the vertices at the corners.

	private final int rows; // the number of rows
	private final int cols; // the number of columns

	/**
	 * Creates a new GridGraphGenerator object with (rows x cols)-dimension.
	 * 
	 * --------------------------------------------
	 * 
	 * @param rows
	 *            the number of rows
	 * @param cols
	 *            the number of columns
	 */
	public GridGraphGenerator(int rows, int cols) {
		if (rows < 2) {
			throw new IllegalArgumentException("illegal number of rows (" + rows + "). there must be at least two.");
		}
		if (cols < 2) {
			throw new IllegalArgumentException("illegal number of columns (" + cols + "). there must be at least two.");
		}
		this.rows = rows;
		this.cols = cols;
	}

	@Override
	public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap) {
		Map<Integer, Vertex> map = new TreeMap<>();

		// Adding all vertices to the set
		int cornerCtr = 0;
		for (int i = 0; i < rows * cols; i++) {
			Vertex vertex = new Vertex(i + 1);
			target.addVertex(vertex);
			map.put(i + 1, vertex);

			boolean isCorner = (i == 0) || (i == (cols - 1)) || (i == (cols * (rows - 1)))
					|| (i == ((rows * cols) - 1));
			if (isCorner && (resultMap != null)) {
				resultMap.put(CORNER_VERTEX + ' ' + ++cornerCtr, vertex);
			}
		}
		/*
		 * Iterating twice over the key set, for undirected graph edges are added from
		 * upper vertices to lower, and from left to right. The second addEdge call will
		 * return nothing; it will not add a the edge at the opposite direction. For
		 * directed graph, edges in opposite direction are also added.
		 */
		for (int i : map.keySet()) {
			for (int j : map.keySet()) {
				if ((((i % cols) > 0) && ((i + 1) == j)) || ((i + cols) == j)) {
					target.addEdge(map.get(i), map.get(j));
					// --- uncomment the following statement to get a directed graph ---
					// target.addEdge(map.get(j), map.get(i));
				}
			}
		}
	}

}
