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
	 * @param matrix
	 *            the given matrix
	 * @param rowPrinter
	 *            prints each row of the matrix
	 */
	public static void printMatrix(int[][] matrix, Consumer<int[]> rowPrinter) {
		Arrays.stream(matrix).forEach((row) -> rowPrinter.accept(row));
	}

	/**
	 * Prints a 1D array
	 * 
	 * --------------------------------------------
	 * 
	 * @param vector
	 *            the given vector
	 */
	public static void printVector(int[] vector) {
		System.out.println(Arrays.toString(vector));
	}

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args
	 *            the command line arguments
	 * @param adjacency_matrix
	 */
	public static void main(String[] args) throws IloException {

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

			Player player1 = new Player(1);
			Player player2 = new Player(2);
			player1.setSource(graph.getVertices().get(0));
			player1.setSink(graph.getVertices().get(3));
			player2.setSource(graph.getVertices().get(1));
			player2.setSink(graph.getVertices().get(3));
			player1.setDemand(5);
			player2.setDemand(4);

			IloCplex cplex = new IloCplex();

			// f>0 for every player in the graph
			IloNumVar f1 = cplex.numVar(0, Double.MAX_VALUE, "f1");
			IloNumVar f2 = cplex.numVar(0, Double.MAX_VALUE, "f2");
			IloNumVar f3 = cplex.numVar(0, Double.MAX_VALUE, "f3");
			IloNumVar f4 = cplex.numVar(0, Double.MAX_VALUE, "f4");
			IloNumVar f5 = cplex.numVar(0, Double.MAX_VALUE, "f5");
			IloNumVar f6 = cplex.numVar(0, Double.MAX_VALUE, "f6");
			IloNumVar f7 = cplex.numVar(0, Double.MAX_VALUE, "f7");
			IloNumVar f8 = cplex.numVar(0, Double.MAX_VALUE, "f8");

		/*	cplex.prod(cplex.sum(f1, f2), cplex.sum(cplex.prod(2, f1), cplex.prod(2, f2))); // (2f1+2f2)(f1+f2)
			cplex.prod(cplex.sum(f3, f4), cplex.sum(cplex.prod(3, f3), cplex.prod(3, f4))); // (3f3+3f4)(f3+f4)
			cplex.prod(cplex.sum(f5, f6), cplex.sum(cplex.prod(4, f5), cplex.prod(4, f6)));
			cplex.prod(cplex.sum(f7, f8), cplex.sum(cplex.prod(5, f7), cplex.prod(5, f8)));
			
			
*/
			cplex.addMinimize(cplex.sum(cplex.prod(cplex.sum(f1, f2), cplex.sum(cplex.prod(2, f1), cplex.prod(2, f2),cplex.constant(1))),
					cplex.prod(cplex.sum(f3, f4), cplex.sum(cplex.prod(3, f3), cplex.prod(3, f4))),
					cplex.prod(cplex.sum(f5, f6), cplex.sum(cplex.prod(4, f5), cplex.prod(4, f6))),
					cplex.prod(cplex.sum(f7, f8), cplex.sum(cplex.prod(5, f7), cplex.prod(5, f8)))));
			
				cplex.addEq(0, cplex.min(cplex.sum(f7,f8),cplex.sum(f3,f4)));
                cplex.addEq(player1.getDemand(),f1);
                cplex.addEq(player2.getDemand(),f6);
	     		//cplex.addEq(-player2.getDemand(),cplex.min(cplex.sum(f7,f8),cplex.sum(f1, f2)));	
				//cplex.addEq(player1.getDemand(),cplex.sum(f7,f8,cplex.sum(f5, f6)));	

			if (cplex.solve()) {
				System.out.println("obj: " + cplex.getObjValue());
				System.out.println("f1: " + cplex.getValue(f1));
				System.out.println("f2: " + cplex.getValue(f2));
				System.out.println("f3: " + cplex.getValue(f3));
				System.out.println("f4: " + cplex.getValue(f4));
				System.out.println("f5: " + cplex.getValue(f5));
				System.out.println("f6: " + cplex.getValue(f6));
				System.out.println("f7: " + cplex.getValue(f7));
				System.out.println("f8: " + cplex.getValue(f8));

			} else {

				throw new IllegalStateException("Problem not solved.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
