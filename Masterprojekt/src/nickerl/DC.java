package nickerl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphGenerator.GridGraphGenerator;
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
	List<Edge> tmpedges;
	Random r = new Random();

	public DC() {
		try {
			this.cplex = new IloCplex();
			this.tmpedges = new ArrayList<>();
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Graphs newgraph(Graphs g, int k) {
		Graphs graph = new Graphs();
		ArrayList<Edge> edges = new ArrayList<>();
		ArrayList<Vertex> vertices = new ArrayList<>();

		for (int i = 0; i < g.getEdges().size(); i++) {

			if (g.getEdges().get(i).getValues().get(k) > 0.1)
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

	public void initialize(Graphs g) {

		for (int i = 0; i < g.getPlayers().size(); i++) {
			for (int j = 0; j < g.getEdges().size(); j++) {
				g.getPlayers().get(i).getValues().add(g.getEdges().get(j).getValues().get(i));

			}
		}

	}

	private static void buildJSON(Object obj) {

		final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to reuse!

		// configure JSON
		File file = new File("./Masterprojekt/files/graphData.json");
		try {
			mapper.writeValue(file, obj); // writes JSON serialization of object instance
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Graphs createObjectInstanceFromJSON(File file) {

		Graphs graph = new Graphs(); // new instance for JSON data

		// check, if file is valide
		String fileName = file.getName().toUpperCase();
		boolean extension = fileName.endsWith(".JSON");

		switch (String.valueOf(extension)) {
		case "true":
			final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			try {
				graph = mapper.readValue(new File("./Masterprojekt/files/graphData.json"), Graphs.class); // reads
																											// object
																											// instance
																											// of
				// JSON serialization
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return graph;
		default:
			System.err.println("The given file is not valid to create an object instance out of it...");
			break;
		}
		return graph;
	}

	public void run(Graphs graph) throws IloException {

		first = new SocialOptimum(graph);

		for (int i = 0; i < graph.getEdges().size(); i++) {
			graph.getEdges().get(i)
					.setY(cplex.numVar(0, 0, IloNumVarType.Bool, "is There a Toll station in the edge number  : " + i));
		}

		buildJSON(graph);

		for (int i = 0; i < graph.getPlayers().size(); i++) {
			Graphs x = createObjectInstanceFromJSON(new File("./graphData.json"));

			while (graph.getPlayers().get(i).getDemand() > 0) {
				x = newgraph(x, i);

				List<Edge> KP = BellmanFordShortestPath
						.findPathBetween(x, graph.getPlayers().get(i).getSource(), graph.getPlayers().get(i).getSink())
						.getEdgeList();

				graph.getPlayers().get(i).setP(KP);
				Optional<Edge> fmax = KP.stream().max(Comparator.comparingDouble(Edge::getSum));

				double f = fmax.get().getSum();

				Player player = new Player(graph.getPlayers().get(i).getId() + graph.getPlayers().size() - 1,

						graph.getPlayers().get(i).getSource(), graph.getPlayers().get(i).getSink(), f);
				player.setP(KP);
				player.setValues(new ArrayList<>(graph.getPlayers().get(i).getValues()));

				for (int j = 0; j < graph.getEdges().size(); j++) {

					if (KP.contains(graph.getEdges().get(j))) {
						graph.getEdges().get(j).getValues().add(f);
						graph.getEdges().get(j).getValues().set(i, graph.getEdges().get(j).getValues().get(i) - f);
					} else {
						graph.getEdges().get(j).getValues().add(0.0);

					}

				}

				graph.getPlayers().get(i).setDemand(graph.getPlayers().get(i).getDemand() - f);
				graph.getP().add(player);

			}
		}
		buildJSON(graph);

		if (graph.getVertices().size() > 2) {
			double randomValue = graph.getVertices().size() * r.nextDouble();
			Graphs g1 = new Graphs();
			Graphs g2 = new Graphs();
			for (int j = 0; j < randomValue; j++) {

				g1.getVertices().add(graph.getVertices().get(j));
			}

			for (int j = graph.getVertices().size(); j > randomValue; j++) {
				g2.getVertices().add(graph.getVertices().get(j));
			}

			for (int j = 0; j < graph.getEdges().size(); j++) {
				if (g1.containsVertex(graph.getEdges().get(j).getFrom())
						&& g1.containsVertex(graph.getEdges().get(j).getTo())) {
					g1.getEdges().add(graph.getEdges().get(j));
				} else if (g2.containsVertex(graph.getEdges().get(j).getFrom())
						&& g2.containsVertex(graph.getEdges().get(j).getTo())) {
					g2.getEdges().add(graph.getEdges().get(j));

				}

				else {
					tmpedges.add(graph.getEdges().get(j));
				}

			}

		} else
			return;
		
		while(graph.getP().size() !=0) {
			
			
		}
		
	}

	/**
	 * 
	 * @param graph
	 * @return
	 */
	public static Graphs cloneGraphinstance(Graphs graph) {
		// create new graph clone
		Graphs graphCopy = null;
		try {
			graphCopy = (Graphs) graph.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return graphCopy;

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// --- Graph parameter ---
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(graph, map);

		// --- player ---
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 10);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 5);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		graph.setPlayer(x);
		graph.generateEdgesFunctions();// edge functions are totally randomized
		Graphs graphCopy = cloneGraphinstance(graph);
		graph.getPlayers().get(0).setDemand(44);
		System.out.println(graphCopy.getPlayers().get(0).getDemand());
		/*
		 * Verify JDK's rules
		 */
		// Must be true and objects must have different memory addresses
		System.out.println(graph = graphCopy);
	}
}
