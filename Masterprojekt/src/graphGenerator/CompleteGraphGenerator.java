package graphGenerator;

import java.util.*;

import graphModel.Graph;
import graphModel.GraphTests;

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
public class CompleteGraphGenerator<V, E> implements GraphGenerator<V, E, V> {

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
	@Override
	public void generateGraph(Graph<V, E> target, Map<String, V> resultMap) {
		if (size < 1) {
			return;
		}
		GraphTests.requireDirectedOrUndirected(target);
		boolean isDirected = target.getType().isDirected();

		List<V> nodes = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			nodes.add(target.addVertex());
		}

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				V v = nodes.get(i);
				V u = nodes.get(j);
				target.addEdge(v, u);
				if (isDirected) {
					target.addEdge(u, v);
				}
			}
		}
	}
}
