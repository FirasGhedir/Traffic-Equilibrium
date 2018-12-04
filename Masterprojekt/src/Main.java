import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import GraphGenerators.Graphs;
import GraphGenerators.GridGraphGenerator;
//import GraphGenerators.RandomGraphGenerator;
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
 * 
 *          _____________________________________________
 * 
 *          In the Main class you can create new graphs, test and work with
 *          them.
 */
public class Main {

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		// --- Test for GridGraph ---
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		// --- Test for RandomGraph ---
		// Map<String, Vertex> map2 = new TreeMap<>();
		// Graphs graph2 = new Graphs();
		// RandomGraphGenerator<Vertex, Edge> test2 = new RandomGraphGenerator<Vertex,
		// Edge>(3, 2);
		// test2.generateGraph(graph2, map2);

		// === console ===
		System.out.println(
				"Gridgraph:\n #edges:    " + graph.getEdges().size() + "\n #vertices: " + graph.getVertices().size());
		// System.out.println(
		// "RandomGraph:\n #edges: " + graph2.getEdges().size() + "\n #vertices: " +
		// graph2.getVertices().size());

		int[][] array = graph.adjazenzmatrix();
		ArrayList<Vertex> tmp = graph.getoutNeighbors(graph.getVertices().get(1), array);
		ArrayList<Vertex> tmp1 = graph.getinNeighbors(graph.getVertices().get(1), array);
		
		System.out.println("----------------------------------------");

		System.out.println("in");

		for(int i=0; i<tmp1.size() ; i++) {
			System.out.println(tmp1.get(i).getId());
		}
		
		System.out.println("----------------------------------------");
		System.out.println("out");

		for(int i=0; i<tmp.size() ; i++) {
			System.out.println(tmp.get(i).getId());
		}
		
		System.out.println("----------------------------------------");


		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " ");

			}
			System.out.println();

		}
	}

}
