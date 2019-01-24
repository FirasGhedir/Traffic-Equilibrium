package nickerl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import genetic.heuristic.Chromosom;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;
import heuristic.SocialOptimum;
import ilog.concert.IloException;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;
import player.Player;

public class DC {
	IloCplex cplex;
	SocialOptimum first;

	public DC() {
		try {
			this.cplex = new IloCplex();
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Graphs newgraph(Graphs g) {
		Graphs graph = new Graphs();
		ArrayList<Edge> edges = new ArrayList<>();
		ArrayList<Vertex> vertices = new ArrayList<>();

		for (int i = 0; i < g.getEdges().size(); i++) {

			if (g.getEdges().get(i).getSum() > 0.5)
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

	public void run(Graphs graph) throws IloException {

		first = new SocialOptimum(graph);
		for (int i = 0; i < graph.getEdges().size(); i++) {
			graph.getEdges().get(i)
					.setY(cplex.numVar(0, 0, IloNumVarType.Bool, "is There a Toll station in the edge number  : " + i));
		}

		for (int i = 0; i < graph.getPlayers().size(); i++) {

			List<Edge> KP = BellmanFordShortestPath
					.findPathBetween(graph, graph.getPlayers().get(i).getSource(), graph.getPlayers().get(i).getSink())
					.getEdgeList();

			graph.getPlayers().get(i).setP(KP);
			Optional<Edge> fmax = KP.stream().max(Comparator.comparingDouble(Edge::getSum));
			double f = fmax.get().getSum();
		//	Player player = new Player(i,graph.getPlayers().get(i).getSource(),graph.getPlayers().get(i).getSink(),f);
			graph.getPlayers().get(i).setDemand(f);
			 for(int j = 0 ; j < graph.getPlayers().get(i).getP().size() ; i++) {
				 graph.getPlayers().get(i).getP().get(j).getValues().set(i,f);  
			 }

		}
		
	

	}

}
