package geneticheuristic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.SocialOptimum;
import ilog.concert.IloException;
import player.Player;

public class GaMINTB {

    List<Chromosom> bestsolutions;

	public GaMINTB() {
            
		bestsolutions = new ArrayList<>();
	}



	
	public static void main(String[] args) throws IloException {
		
		GaMINTB start = new GaMINTB();

		Population firas = new Population(10);
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(graph, map);
		firas.generatechromosomes(graph);
		
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(3), 10);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 5);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		graph.setPlayer(x);
		graph.generateEdgesFunctions();
		
		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		// Store current System.out before assigning a new value

		System.out.println(systemOptimalFlow);

		
		firas.generatechromosomes(graph);

		for(int i=0; i < 30; i++) {
		firas.run(start.bestsolutions,graph,firas);
		
		}
		
     	
        Optional<Chromosom> alpha = start.bestsolutions.stream().min(Comparator.comparingInt(Chromosom::getEfficiency)) ;
             
        System.out.println(alpha.get().getEfficiency());

}
}
