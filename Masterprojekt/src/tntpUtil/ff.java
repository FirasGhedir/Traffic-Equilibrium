package tntpUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GeneratorPoisson;
import graphGenerator.HeavyTail;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;

public class ff {

	public static void main(String[] args) {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs g = new Graphs();
		HeavyTail test = new HeavyTail();
		test.generateGraph(g, map);
		System.out.println(g.getVertices().size() + "  " + g.getEdges().size());
		g.generateEdgesFunctions();
		g.generatePlayers();
		for (int i = 0; i < g.getPlayers().size(); i++) {
			System.out.println(g.getPlayers().get(i).getId() + " Demand : " + g.getPlayers().get(i).getDemand()
					+ " from : " + g.getPlayers().get(i).getSource().getId() + " to : "
					+ g.getPlayers().get(i).getSink().getId());
		}
		List<Edge> KP = BellmanFordShortestPath.findPathBetween(g, g.getPlayers().get(1).getSource(), g.getPlayers().get(1).getSink()).getEdgeList();
		System.out.println(KP.size());
        
		
	}
}