package genetic.heuristic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import bai_A.Mintb_FC;
import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.RMINTB;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
import player.Player;

final class DiscardOutputStream extends java.io.OutputStream {

	@Override
	public void write(int b) throws IOException {
		// noop
	}

}

public class GaMINTB {

	private List<Chromosom> bestsolutions;

	public GaMINTB() {

		bestsolutions = new ArrayList<>();
	}

	public List<Chromosom> getBestsolutions() {
		return bestsolutions;
	}

	public void setBestsolutions(List<Chromosom> bestsolutions) {
		this.bestsolutions = bestsolutions;
	}

	public void savebestsolution(Graphs g, Chromosom xx) {

		for (int i = 0; i < xx.getBeta().size(); i++) {
			g.getEdges().get(i).setBetta(xx.getBeta().get(i));
			g.getEdges().get(i).calculateL();
		}
	}

	public static void main(String[] args) throws IloException {

		// System.setOut(new PrintStream(new DiscardOutputStream()));

		GaMINTB start = new GaMINTB();

		Population firas = new Population(40);
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(3, 3); // do not change !!
		test.generateGraph(graph, map);

		Player player1 = new Player(0, graph.getVertices().get(0), graph.getVertices().get(8), 7);
		Player player2 = new Player(1, graph.getVertices().get(1), graph.getVertices().get(8), 4);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);
		// graph.setPlayer(x);
		graph.generatePlayers();
		graph.generateEdgesFunctions();
		System.out.println("the number of edges " + graph.getEdges().size());

		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);

		// SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		// Store current System.out before assigning a new value

		System.out.println(systemOptimalFlow);

//		firas.generatechromosomes(graph);
//		for (int i = 0; i < 20; i++) {
//			firas.run(start.getBestsolutions(), graph, firas);
//
//		}
//
//		Optional<Chromosom> alpha = start.getBestsolutions().stream()
//				.min(Comparator.comparingInt(Chromosom::getEfficiency));
//		start.savebestsolution(graph, alpha.get());
//		for (int i = 0; i < alpha.get().getBeta().size(); i++) {
//			if (alpha.get().getBeta().get(i) < 0.00001) {
//				alpha.get().getBeta().set(i, 0.0);
//			}
//		}
//		alpha.get().calculateE();
//		for (int i = 0; i < alpha.get().getBeta().size(); i++) {
//			if (alpha.get().getBeta().get(i) == 0) {
//				alpha.get().vector[i] = false;
//			}
//		}
//		alpha.get().setFeasible(firas.evaluation(graph, alpha.get()));
//		System.err.println(
//				"##################################################### termination ########################################");
//		System.out.println("Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : "
//				+ alpha.get().getEfficiency() + " || Feasibility : " + alpha.get().isFeasible());

//		Mintb_FC pp = new Mintb_FC();
//		pp.run(graph);
		
		RMINTB aa = new RMINTB(graph);
		aa.run();

		
		TestCorrectness correct = new TestCorrectness();
		TestCorrectness correct1 = new TestCorrectness();

		Graphs g1 = new Graphs(graph);
		Graphs g2 = new Graphs(graph);

		System.out.println("getPlayers(0): "
				+ correct1.test(g1, g1.getPlayers().get(0).getSource(), g1.getPlayers().get(0).getSink()));
		System.out.println("getPlayers(1): "
				+ correct.test(g2, g2.getPlayers().get(1).getSource(), g2.getPlayers().get(1).getSink()));

	}

}
