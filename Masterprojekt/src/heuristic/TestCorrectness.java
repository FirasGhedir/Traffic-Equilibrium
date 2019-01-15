package heuristic;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;

public class TestCorrectness {

	private Graphs graph;
	private Graphs graph1;

	public Graphs newgraph(Graphs g) {
		graph = new Graphs();
		ArrayList<Edge> edges = new ArrayList<>();
		ArrayList<Vertex> vertices = new ArrayList<>();

		for (int i = 0; i < g.getEdges().size(); i++) {

			if (g.getEdges().get(i).getL() > 0)
				edges.add(g.getEdges().get(i));
		}

		for (int i = 0; i < edges.size(); i++) {
			for (int j = 0; j < g.getVertices().size(); j++) {
				if (edges.get(i).getFrom().equals(g.getVertices().get(j))
						|| edges.get(i).getTo().equals(g.getVertices().get(j)))
					vertices.add(g.getVertices().get(i));

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
		graph1 = new Graphs();
		graph1 = g;
		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 0; i < g.getEdges().size(); i++) {

			Edge e = g.getEdges().get(i);
			e.setL(-1 * g.getEdges().get(i).getL());
			edges.add(e);
		}

		graph1.setEdges(edges);
		return graph1;

	}

	public boolean test(Graphs g, Vertex s, Vertex t) {
		Graphs x = negativgraph(newgraph(g));
		BellmanFordShortestPath algo = new BellmanFordShortestPath(x);

		BellmanFordShortestPath algo1 = new BellmanFordShortestPath(g);

		List<Edge> LP = algo.findPathBetween(x, g.getVertices().get(0), g.getVertices().get(9)).getEdgeList();
		List<Edge> KP = algo.findPathBetween(g, g.getVertices().get(0), g.getVertices().get(9)).getEdgeList();

		int countLP = 0;
		int countKP = 0;
		for (int i = 0; i < LP.size(); i++) {
              countLP += LP.get(i).getL();
		}

		for (int i = 0; i < KP.size(); i++) {
             countKP += KP.get(i).getL();
		}

		if(countKP < countLP) return false;
        return true;
	}

}
