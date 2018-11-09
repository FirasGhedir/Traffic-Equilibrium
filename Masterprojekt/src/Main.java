import java.util.Map;
import java.util.TreeMap;

import GraphGenerators.Edge;
import GraphGenerators.Graphs;
import GraphGenerators.GridGraphGenerator;
import GraphGenerators.RandomGraphGenerator;
import GraphGenerators.Vertex;

/**
 * Universitšt Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 */
public class Main {

	/**
	 * The main method
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		// --- Test for GridGraph ---
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(3, 2);
		test.generateGraph(graph, map);

		// --- Test for RandomGraph ---
//		Map<String, Vertex> map2 = new TreeMap<>();
//		Graphs graph2 = new Graphs();
//		RandomGraphGenerator<Vertex, Edge> test2 = new RandomGraphGenerator<Vertex, Edge>(3, 2);
//		test2.generateGraph(graph2, map2);
		
		// === console ===
		System.out.println(
				"Gridgraph:\n #edges:    " + graph.getEdges().size() + "\n #vertices: " + graph.getVertices().size());
//		System.out.println(
//				"RandomGraph:\n #edges:    " + graph2.getEdges().size() + "\n #vertices: " + graph2.getVertices().size());

	}

}
