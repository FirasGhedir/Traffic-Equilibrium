package JUnitTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
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
 *          JUnit test cases for testing graphs
 */
class GraphTestCases {

	@Test
	public void testNumberOfVerticesAndEdges() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		assertEquals(4, graph.getEdges().size()); // #edges
		assertEquals(4, graph.getVertices().size()); // #vertices
	}

	@Test
	public void testNodeInOut() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		int[][] adjacency_matrix = graph.getAdjacencyMatrix();

		// --- get number of ingoing edges of vertex with ID = 1 ---
		ArrayList<Vertex> listIn = graph.getInNeighbors(graph.getVertices().get(1), adjacency_matrix);

		// --- get number of outgoing edges of vertex with ID = 1 ---
		ArrayList<Vertex> listOut = graph.getOutNeighbors(graph.getVertices().get(1), adjacency_matrix);

		// --- ingoing edges of of vertex with ID = 1
		for (int i = 0; i < listIn.size(); i++) {
			assertEquals(0, listIn.get(i).getId());
		}

		// --- outgoing edges of vertex 2 with ID = 1
		for (int i = 0; i < listOut.size(); i++) {
			assertEquals(3, listOut.get(i).getId());
		}
	}

	@Test
	public void testNodePotentialVector() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		int[] nodePotentialVector = graph.getNodePotentialVector(graph.getAdjacencyMatrix());

		int[] nodePotentialVectorExpected = { 2, 2, 2, 2 };
		assertArrayEquals(nodePotentialVectorExpected, nodePotentialVector);
	}

}
