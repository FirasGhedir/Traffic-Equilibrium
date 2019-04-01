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
	int[] array;

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

		Random r = new Random();
		UFinit(size);
		while (check()) {
			int x = 0;
			int y = 0;
			do {
				x = r.nextInt(nodes.size());
				y = r.nextInt(nodes.size());
			} while (x == y);

			Vertex v = nodes.get(x);
			Vertex u = nodes.get(y);
			if (!(target.containsEdge(v, u) && target.containsEdge(u, v))) {
				target.addEdge(v, u);
				UFunion(x,y);
				if (isDirected) {
					target.addEdge(u, v);
				}
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				Vertex v = nodes.get(i);
				Vertex u = nodes.get(j);
				target.addEdge(v, u);
				if (isDirected) {
					target.addEdge(u, v);
				}
			}
		}
	}

	public void UFinit(int n) {
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i;
		}
	}

	public void UFunion(int i, int j) {
		double z = Math.random();
		if (z == 0) {
			array[i] = j;
		} else {
			array[j] = i;
		}
	}

	public int UFfind(int i) {
		if (i == this.array[i]) {
			return i;
		} else {
			int j = UFfind(array[i]);
			array[i] = j;
			return j;
		}

	}

	public boolean check() {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i)
				count++;
		}

		if (count == 1)
			return false;
		else
			return true;
	}

}
