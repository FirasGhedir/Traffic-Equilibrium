package genetic.heuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;
import heuristic.SocialOptimum;
import ilog.concert.IloException;
import player.Player;


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

	public static void main(String[] args) throws IloException {

		GaMINTB start = new GaMINTB();

		Population firas = new Population(10);
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(2, 2); // do not change !!
		test.generateGraph(graph, map);

		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(3), 7);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(3), 8);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		graph.setPlayer(x);
		graph.generateEdgesFunctions();

		BellmanFordShortestPath algo = new BellmanFordShortestPath(graph);
		
		List<Vertex> tmp = algo.findPathBetween(graph,graph.getVertices().get(0),graph.getVertices().get(3)).getVertexList();		

		
		for(int i = 0 ; i < tmp.size() ; i++) {
			System.out.println(tmp.get(i).getId());
		}
		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		// Store current System.out before assigning a new value

		System.out.println(systemOptimalFlow);

		firas.generatechromosomes(graph);
	/*	for (int i = 0; i < 100; i++) {
			firas.run(start.getBestsolutions(), graph, firas);
	

		}
		Optional<Chromosom> alpha = start.getBestsolutions().stream().min(Comparator.comparingInt(Chromosom::getEfficiency));

		System.err.println("##################################################### termination ########################################");
		System.out.println("Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : " +alpha.get().getEfficiency() + " || Feasibility  : " + alpha.get().isFeasible());
		*/
	}

}
