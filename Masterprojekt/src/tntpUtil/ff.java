package tntpUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GeneratorPoisson;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;

public class ff {

	public static void main(String[] args) {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs g = new Graphs();
		GeneratorPoisson test = new GeneratorPoisson(5, 20, 0.1);
		test.generateGraph(g, map);
		System.out.println(g.getVertices().size() + "  " + g.getEdges().size());
		g.generateEdgesFunctions();
		g.setPlayer(test.getPlayers());
		for (int i = 0; i < g.getPlayers().size(); i++) {
			System.out.println(g.getPlayers().get(i).getId() + " Demand : " + g.getPlayers().get(i).getDemand()
					+ " from : " + g.getPlayers().get(i).getSource().getId() + " to : "
					+ g.getPlayers().get(i).getSink().getId());
		}
		List<Edge> KP = BellmanFordShortestPath.findPathBetween(g, g.getPlayers().get(0).getSource(), g.getPlayers().get(0).getSink()).getEdgeList();
		System.out.println(KP.size());
        
		
	}
}