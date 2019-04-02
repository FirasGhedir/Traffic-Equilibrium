package main;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bai_A.Mintb_FC;
import genetic.heuristic.GaMINTB;
import graphModel.Graphs;
import heuristic.RMINTB;
import heuristic.SocialOptimum;
import ilog.concert.IloException;
import nickerl.Nickerl;

public class Main {

	public static void Mintb(Graphs graph) throws IloException {
		SocialOptimum social = new SocialOptimum(graph);
		social.solveDSSP(graph);
		Mintb_FC solver = new Mintb_FC();
		try {
			solver.run(graph);
		} catch (IloException e) {
			e.printStackTrace();
		}

	}

	public static void Rmintb(Graphs graph) throws IloException {
		SocialOptimum social = new SocialOptimum(graph);
		social.solveDSSP(graph);
		RMINTB solver;
		try {
			solver = new RMINTB(graph);
			solver.solve();

		} catch (IloException e) {
			e.printStackTrace();
		}
	}

	public static void Genetic(Graphs graph, int p, int T) throws IloException {
		SocialOptimum social = new SocialOptimum(graph);
		social.solveDSSP(graph);

		GaMINTB solver = new GaMINTB(graph, p);
		try {
			solver.run(T);
		} catch (IloException e) {
			e.printStackTrace();
		}

	}

	public static void Nickerl(Graphs graph) throws IloException {
		SocialOptimum social = new SocialOptimum(graph);
		social.solveDSSP(graph);
		try {
			Nickerl solver = new Nickerl(graph);
			solver.run();
		} catch (IloException e) {
			e.printStackTrace();
		}

	}

	public static Graphs getGraph(int i, String x, String id) {
		String path = "";
		String graphtype;
		if (i == 1) {
			graphtype = "Gridinstances";
		} else if (i == 2) {
			graphtype = "Poissoninstances";
		} else {
			graphtype = "Heavytailinstances";
		}
		switch (x) {
		case "1":
			path = "./Masterprojekt/files/" + graphtype + "/" + "100-400" + "/" + id;
			break;

		case "2":
			path = "./Masterprojekt/files/" + graphtype + "/" + "400-700" + "/" + id;
			break;

		case "3":
			path = "./Masterprojekt/files/" + graphtype + "/" + "700-1000" + "/" + id;

			break;

		}
		System.out.println(path);

		File file = new File(path + ".JSON");

		Graphs graph = new Graphs(); // new instance for JSON data

		// check, if file is valide
		String fileName = file.getName().toUpperCase();
		boolean extension = fileName.endsWith(".JSON");
		System.out.println(fileName);

		switch (String.valueOf(extension)) {
		case "true":
			final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to
			// reuse!
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				graph = mapper.readValue(new File(path), Graphs.class); // reads object instance of
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
		matching(graph);
		return graph;
	}

	public static void main(String[] args) throws IloException {
		Graphs graph = new Graphs();
//		 switch (args[0]) {
//		 case "1":
//		 graph = getGraph(1, args[1], args[2]);
//		 break;
//		 case "2":
//		 graph = getGraph(2, args[1], args[2]);
//		 break;
//		 case "3":
//		 graph = getGraph(3, args[1], args[2]);
//		break;
//		 }
//		
//		 switch (args[3]) {
//		 case "1":
//		 Mintb(graph);
//		 break;
//		 case "2":
//		 Rmintb(graph);
//		 break;
//		 case "3":
//		 Genetic(graph, Integer.valueOf(args[4]), Integer.valueOf(args[5]));
//		 break;
//		 case "4":
//		 Nickerl(graph);
//		 break;
//		 }

		 graph = getGraph(1,"1","1");
		 System.out.println(graph.getVertices().size());


	}

	public static void matching(Graphs graph) {
		for (int i = 0; i < graph.getVertices().size(); i++) {
			for (int j = 0; j < graph.getPlayers().size(); j++) {

				if (graph.getVertices().get(i).equals(graph.getPlayers().get(j).getSink())) {
					graph.getVertices().set(i, graph.getPlayers().get(j).getSink());
				}
				if (graph.getVertices().get(i).equals(graph.getPlayers().get(j).getSource())) {
					graph.getVertices().set(i, graph.getPlayers().get(j).getSource());
				}

			}

		}

		for (int i = 0; i < graph.getEdges().size(); i++) {
			for (int j = 0; j < graph.getVertices().size(); j++) {
				if (graph.getEdges().get(i).getTo().equals(graph.getVertices().get(j))) {
					graph.getEdges().get(i).setTo(graph.getVertices().get(j));
				}

				if (graph.getEdges().get(i).getFrom().equals(graph.getVertices().get(j))) {
					graph.getEdges().get(i).setFrom(graph.getVertices().get(j));
				}
			}

		}

	}


}