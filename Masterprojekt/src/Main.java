import java.util.Map;
import java.util.TreeMap;

import GraphGenerators.Graphs;
import GraphGenerators.GridGraphGenerator;
import GraphGenerators.Vertex;

/**
 * Universität Ulm
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
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		GridGraphGenerator test = new GridGraphGenerator(3, 2);
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		test.generateGraph(graph, map);
		System.out.println(graph.getEdges().size());
		System.out.println(graph.getVertices().size());

	}

}
