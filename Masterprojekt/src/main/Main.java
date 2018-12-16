package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import graphCharacteristics.Diameter;
import graphCharacteristics.Eccentricity;
import graphCharacteristics.Radius;
import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.Socialoptimum;
import player.Player;
import ilog.concert.IloException;

/**
 * Universit�t Ulm
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
	 * Delimiter | for each row when printing matrix
	 */
	static Consumer<int[]> likeAList = (row) -> {
		System.out.print("|");
		Arrays.stream(row).forEach((el) -> System.out.print(" " + el + " "));
		System.out.println("|");
	};

	/**
	 * Prints a 2D array
	 * 
	 * --------------------------------------------
	 * 
	 * @param matrix     the given matrix
	 * @param rowPrinter prints each row of the matrix
	 */
	public static void printMatrix(int[][] matrix, Consumer<int[]> rowPrinter) {
		Arrays.stream(matrix).forEach((row) -> rowPrinter.accept(row));
	}

	/**
	 * Prints a 1D array
	 * 
	 * --------------------------------------------
	 * 
	 * @param vector the given vector
	 */
	public static void printVector(int[] vector) {
		System.out.println(Arrays.toString(vector));
	}

	/**
	 * Prints the adjacency list of a given graph; Prints a LinkedLists in a 1D
	 * array;
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph the given graph
	 */
	public static void printAdjacencyList(Graphs graph) {
		for (int i = 0; i < graph.getAdjacencyMatrix().length; i++) {
			System.out.println("Knoten " + graph.getVertices().get(i).getId() + ": " + graph.getAdjacencyList()[i]);
			for (int j = 0; j < graph.getAdjacencyList()[i].size(); j++) {
				System.out.println("-> " + graph.getAdjacencyList()[i].get(j));
			}
		}
	}

	private static void printTitle(String title) {
		System.out.println("\n ------------------------------\n|     " + title + ":\n ------------------------------");
	}

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args             the command line arguments
	 * @param adjacency_matrix
	 */
	public static void main(String[] args) throws IloException {

		try {

			// --- Graph parameter
			Map<String, Vertex> map = new TreeMap<>();
			Graphs graph = new Graphs();
			GridGraphGenerator test = new GridGraphGenerator(2, 2);
			test.generateGraph(graph, map);

			// --- create adjacency matrix for the graph
			int[][] adjacency_matrix1 = graph.getAdjacencyMatrix();

			// --- get number of ingoing edges of vertex with ID = 1 ---
			ArrayList<Vertex> listIn = graph.getInNeighbors(graph.getVertices().get(1), adjacency_matrix1);

			// --- get number of outgoing edges of vertex with ID = 1 ---
			ArrayList<Vertex> listOut = graph.getOutNeighbors(graph.getVertices().get(1), adjacency_matrix1);

			// --- create node potential vector
			int[] nodePotentialVector = graph.getNodePotentialVector(graph.getAdjacencyMatrix());

			// --- eccentricity
			Eccentricity ec = new Eccentricity(graph);
			int[] ecc = ec.getEccentricities();
			
			// --- Diameter
			Diameter dia = new Diameter(graph);
			int diameter = dia.getDiameter();
			
			// --- Radius
			Radius rad = new Radius(graph);
			int radius = rad.getRadius();

			// --- social optimum ---
			// graph.setPlayers(2);
			graph.getPlayers().add(new Player(0, graph.getVertices().get(0), graph.getVertices().get(3), 5));
			graph.getPlayers().add(new Player(1, graph.getVertices().get(1), graph.getVertices().get(3), 4));
			graph.getEdges().get(0).setA(2);
			graph.getEdges().get(0).setB(0);
			graph.getEdges().get(1).setA(4);
			graph.getEdges().get(1).setB(0);
			graph.getEdges().get(2).setA(3);
			graph.getEdges().get(2).setB(0);
			graph.getEdges().get(3).setA(5);
			graph.getEdges().get(3).setB(0);

			/*
			 * print GridGraph
			 */
			printTitle("Gridgraph");
			System.out.println(
					"#edges:    " + graph.getEdges().size() + "\n #vertices: " + graph.getVertices().size() + "\n");

			/*
			 * print ingoing edges of of vertex with ID = 1
			 */
			printTitle("Ingoing edges");
			for (int i = 0; i < listIn.size(); i++) {
				System.out.println(listIn.get(i).getId());
			}

			/*
			 * print outgoing edges of vertex 2 with ID = 1
			 */
			printTitle("Outgoing edges");
			for (int i = 0; i < listOut.size(); i++) {
				System.out.println(listOut.get(i).getId());
			}

			/*
			 * print adjacency matrix
			 */
			printTitle("Adjacency Matrix");
			printMatrix(adjacency_matrix1, likeAList);

			/*
			 * print adjacency list
			 */
			printTitle("Adjacency List");
			printAdjacencyList(graph);

			/*
			 * print node potential vector
			 */
			printTitle("Node potential vector");
			printVector(nodePotentialVector);

			/*
			 * eccentricity
			 */
			printTitle("Eccentricity");
			printVector(ecc);
			System.out.println(" -> Average eccentricity:        " + ec.getAvgEccentricity());
			
			/*
			 * diameter
			 */
			printTitle("Diameter");
			System.out.println(" -> Diameter (Max eccentricity): " + diameter);

			/*
			 * radius
			 */
			printTitle("Radius");
			System.out.println(" -> Radius   (Min eccentricity): " + radius);
			
			/*
			 * social optimum
			 */
			printTitle("Social optimum");
			new Socialoptimum();
			Socialoptimum.step1(graph);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
