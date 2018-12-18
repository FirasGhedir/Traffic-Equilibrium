package JUnitTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import graphCharacteristics.Diameter;
import graphCharacteristics.Eccentricity;
import graphCharacteristics.MinCut;
import graphCharacteristics.Radius;
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
 *          JUnit test cases for testing graph characteristics
 */
class GraphCharacteristicsTestCases {

	@Test
	public void testAdjacency() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		int[][] adjacencyMatrix = graph.getAdjacencyMatrix();

		int[][] adjacencyMatrixExpected = new int[4][4];
		adjacencyMatrixExpected[0][1] = 1;
		adjacencyMatrixExpected[0][2] = 1;
		adjacencyMatrixExpected[1][3] = 1;
		adjacencyMatrixExpected[2][3] = 1;

		for (int i = 0; i < adjacencyMatrixExpected.length; i++) {
			for (int j = 0; j < adjacencyMatrixExpected.length; j++) {
				assertEquals(adjacencyMatrixExpected[i][j], adjacencyMatrix[i][j]);
			}
		}
	}

	@Test
	public void testAvgVertexDegree() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		double expected = 2;
		double actual = Arrays.stream(graph.getNodePotentialVector(graph.getAdjacencyMatrix())).average()
				.orElse(Double.NaN);
		double delta = 0;

		assertEquals(expected, actual, delta);

	}

	@Test
	public void testMinVertexDegree() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		int expected = 2;
		int actual = Arrays.stream(graph.getNodePotentialVector(graph.getAdjacencyMatrix())).min().getAsInt();

		assertEquals(expected, actual);
	}

	@Test
	public void testMaxVertexDegree() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		int expected = 2;
		int actual = Arrays.stream(graph.getNodePotentialVector(graph.getAdjacencyMatrix())).max().getAsInt();

		assertEquals(expected, actual);
	}

	@Test
	public void testEccentricityVector() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		Eccentricity ec;
		try {
			ec = new Eccentricity(graph);

			int[] eccentricityVector = ec.getEccentricities();

			int[] eccentricityVectorExpected = { 2, 2, 2, 2 };

			assertArrayEquals(eccentricityVectorExpected, eccentricityVector);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAverageEccentricity() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		Eccentricity ec;

		try {
			ec = new Eccentricity(graph);

			double averageEccentricity = ec.getAvgEccentricity();

			double averageEccentricityExpected = 1.5;

			double delta = 0;

			assertEquals(averageEccentricityExpected, averageEccentricity, delta);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDiameter() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		Diameter dia;
		try {
			dia = new Diameter(graph);

			int diameter = dia.getDiameter();

			int diameterExpected = 2;

			assertEquals(diameterExpected, diameter);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRadius() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		Radius rad;
		try {
			rad = new Radius(graph);

			int radius = rad.getRadius();

			int radiusExpected = 2;

			assertEquals(radiusExpected, radius);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMinCut() {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

		MinCut mincut = new MinCut(graph);
		char[] expected = ("0 - 1" + "\n" + "0 - 2").toCharArray();
		char[] actual = mincut.getMincut().toCharArray();
		for (int i = 0; i < actual.length; i++) {
			assertArrayEquals(expected, actual);
		}
	}
}
