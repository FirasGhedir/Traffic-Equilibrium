package genetic.heuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import graphGenerator.GnpRandomGraphGenerator;
import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
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
	
	public  void savebestsolution(Graphs g,Chromosom xx) {
		
		for(int i = 0 ; i < xx.getBeta().size() ; i++) {
			 g.getEdges().get(i).setBetta(xx.getBeta().get(i));
			 g.getEdges().get(i).calculateL();
		}
	}

	public static void main(String[] args) throws IloException {

		GaMINTB start = new GaMINTB();

		

		Population firas = new Population(100);
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(graph, map);

		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 4);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 4);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		graph.setPlayer(x);
		graph.generateEdgesFunctions();
        System.out.println("the number of edges " + graph.getEdges().size() );
			
		
		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		// Store current System.out before assigning a new value

		System.out.println(systemOptimalFlow);

		firas.generatechromosomes(graph);
		for (int i = 0; i < 100; i++) {
			firas.run(start.getBestsolutions(), graph, firas);
	

		}
		Optional<Chromosom> alpha = start.getBestsolutions().stream().min(Comparator.comparingInt(Chromosom::getEfficiency));

		System.err.println("##################################################### termination ########################################");
		System.out.println("Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : " +alpha.get().getEfficiency() + " || Feasibility  : " + alpha.get().isFeasible());
		
		start.savebestsolution(graph,alpha.get());
		TestCorrectness correct = new TestCorrectness();
		System.out.println(correct.test(graph, player1.getSource(), player1.getSink()));
	//	System.out.println(correct.test(graph, player2.getSource(), player2.getSink()));

		
	}

}
