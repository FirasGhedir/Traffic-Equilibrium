import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import GraphGenerators.Graphs;
import GraphGenerators.GridGraphGenerator;
import GraphGenerators.Player;
import GraphGenerators.Vertex;
import ilog.cplex.IloCplex;
import ilog.concert.IloNumVar;
import ilog.concert.IloException;

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
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args             the command line arguments
	 * @param adjacency_matrix
	 */
	public static void main(String[] args) throws IloException{

		try {

			// --- Test for GridGraph ---
			Map<String, Vertex> map = new TreeMap<>();
			Graphs graph = new Graphs();
			GridGraphGenerator test = new GridGraphGenerator(2, 2);
			test.generateGraph(graph, map);

			// === console ===
			System.out.println("Gridgraph:\n #edges:    " + graph.getEdges().size() + "\n #vertices: "
					+ graph.getVertices().size() + "\n");

			// --- create adjacency matrix for the graph
			int[][] adjacency_matrix1 = graph.getAdjacencyMatrix();

			// --- get number of ingoing edges of vertex with ID = 1 ---
			ArrayList<Vertex> listIn = graph.getInNeighbors(graph.getVertices().get(1), adjacency_matrix1);

			// --- get number of outgoing edges of vertex with ID = 1 ---
			ArrayList<Vertex> listOut = graph.getOutNeighbors(graph.getVertices().get(1), adjacency_matrix1);

			// print ingoing edges of of vertex with ID = 1
			System.out.println("----------------------------------------\n in:");
			for (int i = 0; i < listIn.size(); i++) {
				System.out.println(listIn.get(i).getId());
			}

			// print outgoing edges of vertex 2 with ID = 1
			System.out.println("----------------------------------------\n out:");
			for (int i = 0; i < listOut.size(); i++) {
				System.out.println(listOut.get(i).getId());
			}

			// print adjacency matrix
			System.out.println("----------------------------------------\n adjacency matrix:\n");
			printMatrix(adjacency_matrix1, likeAList);

			// print node potential vector
			System.out.println("\n----------------------------------------\n node potential vector:\n");
			int[] nodePotentialVector = graph.getNodePotentialVector(graph.getAdjacencyMatrix());
			printVector(nodePotentialVector);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Player player1= new Player(1);
		Player player2= new Player(2);
		
		
	/*	
		IloCplex cplex = new IloCplex();
		IloNumVar x1 = cplex.numVar(0, 40,"x1");
		IloNumVar x2 = cplex.numVar(0, Double.MAX_VALUE,"x2");
		IloNumVar x3 = cplex.numVar(0, Double.MAX_VALUE,"x3");
 
		cplex.addMinimize(cplex.sum(x1,cplex.prod(2,x2 ),cplex.prod(3, x3),cplex.prod(-16.5, x1,x1),cplex.prod(-11, x2,x2),cplex.prod(-5.5, x3,x3),cplex.prod(6, x1,x2),cplex.prod(11.5, x2,x3)));
		cplex.addLe(cplex.sum(cplex.prod(-1,x1), x2,x3),20);
		cplex.addLe(cplex.sum(cplex.prod(-3,x1), x2,x3),30);
		if(cplex.solve()) {
			System.out.println("obj: " + cplex.getObjValue() );
			System.out.println("x1: " + cplex.getValue(x1));
			System.out.println("x2: " + cplex.getValue(x2));
			System.out.println("x3: " + cplex.getValue(x3));

		} else {
			
			throw new IllegalStateException("Problem not solved.");
		}
		
*/
		
		
	}

}
