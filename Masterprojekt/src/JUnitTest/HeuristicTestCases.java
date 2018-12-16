package JUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;
import player.Player;

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
 *          JUnit test cases for testing heuristics
 */
class HeuristicTestCases {

	@Test
	public void testSocialOptimum() throws IloException {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2);
		test.generateGraph(graph, map);

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
		
		IloAddable[] beta = {x1, x2, x3, x4, x5, x6, x7, x8};
		cplex.add(beta);

		double expected = 0;
		double actual = 0;
		double delta = 0.0005;

		switch (String.valueOf(cplex.solve())) {
		case "true":
			// --- f1
			expected = 2.357;
			actual = cplex.getValue(f1);
			assertEquals(expected, actual, delta);

			// --- f2
			expected = 0;
			actual = cplex.getValue(f2);
			assertEquals(expected, actual, delta);

			// --- f3
			expected = 2.357;
			actual = cplex.getValue(f3);
			assertEquals(expected, actual, delta);

			// --- f4
			expected = 4;
			actual = cplex.getValue(f4);
			assertEquals(expected, actual, delta);

			// --- f5
			expected = 2.6428;
			actual = cplex.getValue(f5);
			assertEquals(expected, actual, delta);

			// --- f6
			expected = 0;
			actual = cplex.getValue(f6);
			assertEquals(expected, actual, delta);

			// --- f7
			expected = 2.6428;
			actual = cplex.getValue(f7);
			assertEquals(expected, actual, delta);

			// --- f8
			expected = 0;
			actual = cplex.getValue(f8);
			assertEquals(expected, actual, delta);
			break;

		default:

			throw new IllegalStateException("Problem not solved.");
		}
	}
}
