package heuristic;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;

public class TestCorrectness {

	private Graphs graph;

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
	
	

}
