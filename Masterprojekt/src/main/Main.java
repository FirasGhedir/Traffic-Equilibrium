package main;

import java.util.Map;
import java.util.TreeMap;

import graphCharacteristics.CharacteristicsCalculator;
import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import player.Player;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

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

	/**
	 * Prints a title in a fancy frame on the console
	 * 
	 * @param title the title to print
	 */
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

			// --- Create CharacteristicsCalculator
			CharacteristicsCalculator characteristics = new CharacteristicsCalculator(graph);

			// --- social optimum ---
			Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(3), 5);
			Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(3), 4);

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

			cplex.addMinimize(cplex.sum(cplex.prod(cplex.sum(f1, f2), cplex.sum(cplex.prod(2, f1), cplex.prod(2, f2))),
					cplex.prod(cplex.sum(f3, f4), cplex.sum(cplex.prod(3, f3), cplex.prod(3, f4))),
					cplex.prod(cplex.sum(f5, f6), cplex.sum(cplex.prod(4, f5), cplex.prod(4, f6))),
					cplex.prod(cplex.sum(f7, f8), cplex.sum(cplex.prod(5, f7), cplex.prod(5, f8)))));

			IloAddable x1 = cplex.addEq(0, cplex.sum(f7, cplex.prod(-1, f5)));
			IloAddable x2 = cplex.addEq(0, cplex.sum(f6, cplex.prod(-1, f8)));
			IloAddable x3 = cplex.addEq(0, cplex.sum(f1, cplex.prod(-1, f3)));
			IloAddable x4 = cplex.addEq(0, cplex.sum(f2, f6));
			IloAddable x5 = cplex.addEq(player1.getDemand(), cplex.sum(f1, f5));
			IloAddable x6 = cplex.addEq(player2.getDemand(), f4);
			IloAddable x7 = cplex.addEq(player1.getDemand(), cplex.sum(f1, f5));
			IloAddable x8 = cplex.addEq(player2.getDemand(), cplex.sum(f4, f8));

			IloAddable[] beta = { x1, x2, x3, x4, x5, x6, x7, x8 };

			/*
			 * print graph data
			 */
			System.out.println(graph);

			/*
			 * print adjacency list
			 */
			printTitle("Adjacency List");
			printAdjacencyList(graph);

			/*
			 * print graph characteristics
			 */
			System.out.println(characteristics);

			/*
			 * social optimum
			 */
			printTitle("social optimum");
			System.out.println(cplex.solve());

			cplex.add(beta);

			switch (String.valueOf(cplex.solve())) {
			case "true":
				System.out.println("obj: " + cplex.getObjValue());
				System.out.println("f1: " + cplex.getValue(f1));
				System.out.println("f2: " + cplex.getValue(f2));
				System.out.println("f3: " + cplex.getValue(f3));
				System.out.println("f4: " + cplex.getValue(f4));
				System.out.println("f5: " + cplex.getValue(f5));
				System.out.println("f6: " + cplex.getValue(f6));
				System.out.println("f7: " + cplex.getValue(f7));
				System.out.println("f8: " + cplex.getValue(f8));
				break;

			default:

				throw new IllegalStateException("Problem not solved.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
