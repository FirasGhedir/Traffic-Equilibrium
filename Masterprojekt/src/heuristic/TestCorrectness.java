package heuristic;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import player.Player;
import util.BellmanFordShortestPath;

public class TestCorrectness {

	private Graphs graph;
	private Graphs graph1;
	final static double default_epsilon = 1;
	private String result;

	public TestCorrectness() {

	}

	public Graphs newgraph(Graphs g,Player p) {
		graph = new Graphs(g);
		ArrayList<Edge> edges = new ArrayList<>();
		ArrayList<Vertex> vertices = new ArrayList<>();

		for (int i = 0; i < g.getEdges().size(); i++) {

			if (g.getEdges().get(i).getValues().get(p.getId()) > 0)
				edges.add(g.getEdges().get(i));
		}

		for (int i = 0; i < edges.size(); i++) {
			for (int j = 0; j < g.getVertices().size(); j++) {
				if (edges.get(i).getFrom().equals(g.getVertices().get(j))
						|| edges.get(i).getTo().equals(g.getVertices().get(j)))
					vertices.add(g.getVertices().get(j));

			}
		}

		LinkedHashSet<Vertex> set = new LinkedHashSet<>();

		set.addAll(vertices);

		vertices.clear();

		vertices.addAll(set);

		graph.setEdges(edges);
		graph.setVertices(vertices);
		return graph;

	}

	public Graphs negativgraph(Graphs g) {
		graph1 = new Graphs(g);

				
		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 0; i < g.getEdges().size(); i++) {

			Edge e = g.getEdges().get(i);
			e.setL(-1 * g.getEdges().get(i).getL());
			edges.add(e);
		}

		graph1.setEdges(edges);
		return graph1;

	}

	public boolean test(Graphs g, Player player) {
	    Vertex s = player.getSource();
	    Vertex t = player.getSink();
		List<Edge> KP = BellmanFordShortestPath.findPathBetween(g, s, t).getEdgeList();

		double countLP = 0;
		double countKP = 0;

		for (int i = 0; i < KP.size(); i++) {
			countKP += KP.get(i).getL();
		}
		Graphs y = negativgraph(newgraph(g,player));
		
		List<Edge> LP = BellmanFordShortestPath.findPathBetween(y, s, t).getEdgeList();
		for (int i = 0; i < LP.size(); i++) {
			countLP += (-1) * LP.get(i).getL();
		}
		
		for(int i = 0 ; i < y.getEdges().size() ; i++) {
			y.getEdges().get(i).setL((-1)*y.getEdges().get(i).getL());
		}
	    setResult(" " +  (!(countKP + default_epsilon < countLP)) + " Commodity ID : "+ player.getId()+ " KP : " +countKP + " LP : " + countLP + "\n");
		
		if (countKP + default_epsilon < countLP) return false;
		return true;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
