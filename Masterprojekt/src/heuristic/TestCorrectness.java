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

	
	public TestCorrectness() {
		
	}
	
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
		

		for(int i = 0 ; i < g.getEdges().size() ; i++) {
			System.out.println(g.getEdges().get(i).getFrom().getId() + " to " + g.getEdges().get(i).getTo().getId() + " " + g.getEdges().get(i).getL());
		}

		List<Edge> KP = BellmanFordShortestPath.findPathBetween(g, s,t).getEdgeList();

		int countLP = 0;
		int countKP = 0;
	
		for (int i = 0; i < KP.size(); i++) {
            countKP += KP.get(i).getL();
		}
		Graphs y = negativgraph(newgraph(g));

		for(int i = 0 ; i < y.getEdges().size() ; i++) {
			System.out.println(y.getEdges().get(i).getFrom().getId() + " to " + y.getEdges().get(i).getTo().getId() + " " + g.getEdges().get(i).getL());
		}
		
		List<Edge> LP = BellmanFordShortestPath.findPathBetween(y, s,t).getEdgeList();
		for (int i = 0; i < LP.size(); i++) {
            countLP += (-1)* LP.get(i).getL();
		}
		
		
		System.out.println("KP : " + KP.size() + " LP : " + LP.size() );
        System.out.println(countLP + " " + countKP);
        y = negativgraph(newgraph(g));
		if(countKP < countLP) return false;
        return true;
	}

}
