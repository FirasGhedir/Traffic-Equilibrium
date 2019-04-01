package graphGenerator;

import java.util.*;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.GraphTests;
import graphModel.Vertex;

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
 *          In the class GridGraphGenerator you can create a complete grap of
 *          any size
 */
public class CompleteGraphGenerator implements GraphGenerator<Vertex, Edge, Vertex> {

	private final int size;

	/**
	 * 
	 * @param size
	 */
	public CompleteGraphGenerator(int size) {
		if (size < 0) {
			throw new IllegalArgumentException("size must be non-negative");
		}
		this.size = size;
	}

	/**
	 * 
	 */
	public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap) {
		if (size < 1) {
			return;
		}
		GraphTests.requireDirectedOrUndirected(target);
		boolean isDirected = target.getType().isDirected();

		List<Vertex> nodes = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Vertex vertex = new Vertex(i);
			target.addVertex(vertex);
			nodes.add(vertex);
		}

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				Vertex v = nodes.get(i);
				Vertex u = nodes.get(j);
				System.out.println(v == null);
				target.addEdge(v, u);
				if (isDirected) {
					target.addEdge(u, v);
				}
			}
		}
	}


}
