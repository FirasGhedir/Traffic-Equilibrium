package nickerl;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bai_A.Mintb_FC;
import graphModel.Graphs;
import heuristic.RMINTB;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
import graphModel.*;
import graphGenerator.*;
import java.util.*;

public class Main {

	static Scanner scan;
	static Graphs g;

	public static void matching(Graphs graph) {

		for (int i = 0; i < g.getPlayers().size(); i++) {
			for (int j = 0; j < g.getPlayers().size(); j++) {
				if (g.getPlayers().get(i).getSink().equals(g.getPlayers().get(j).getSink())) {
					g.getPlayers().get(j).setSink(g.getPlayers().get(i).getSink());
				} else if (g.getPlayers().get(i).getSource().equals(g.getPlayers().get(j).getSource())) {
					g.getPlayers().get(j).setSink(g.getPlayers().get(i).getSink());

				} else if (g.getPlayers().get(i).getSink().equals(g.getPlayers().get(j).getSource())) {
					g.getPlayers().get(j).setSource(g.getPlayers().get(i).getSink());

				}

				else if (g.getPlayers().get(i).getSource().equals(g.getPlayers().get(j).getSink())) {
					g.getPlayers().get(j).setSink(g.getPlayers().get(i).getSource());

				}

			}
		}

		for (int i = 0; i < graph.getVertices().size(); i++) {
			for (int j = 0; j < graph.getPlayers().size(); j++) {

				if (graph.getVertices().get(i).equals(graph.getPlayers().get(j).getSink())) {
					graph.getVertices().set(i, graph.getPlayers().get(j).getSink());
					graph.getVertices().get(i).setRo(graph.getPlayers().get(j).getSink().getRo());
				}
				if (graph.getVertices().get(i).getId() == graph.getPlayers().get(j).getSource().getId()) {

					graph.getVertices().set(i, graph.getPlayers().get(j).getSource());
					graph.getVertices().get(i).setRo(graph.getPlayers().get(j).getSource().getRo());

				}

			}

		}

		for (int i = 0; i < graph.getEdges().size(); i++) {
			for (int j = 0; j < graph.getVertices().size(); j++) {
				if (graph.getEdges().get(i).getTo().equals(graph.getVertices().get(j))) {
					graph.getEdges().get(i).setTo(graph.getVertices().get(j));
					graph.getEdges().get(i).getTo().setRo(graph.getVertices().get(j).getRo());
				}

				if (graph.getEdges().get(i).getFrom().equals(graph.getVertices().get(j))) {
					graph.getEdges().get(i).setFrom(graph.getVertices().get(j));
					graph.getEdges().get(i).getFrom().setRo(graph.getVertices().get(j).getRo());

				}

			}
		}

	}

	public static void main(String[] args) throws IloException {

		for (int i = 100; i <250; i++) {
			g = getGraph(i);
			matching(g);
			Mintb_FC solver = new Mintb_FC(g);
		    solver.run(g);
			//RMINTB solver = new RMINTB(g);
			//solver.solve();
		}
	}

	public static Graphs getGraph(int i) {

		String path = "./Masterprojekt/files/" + "Gridinstances" + "/" + "50-100" + "/" + Integer.toString(i);

		File file = new File(path + ".JSON");

		Graphs graph = new Graphs();// new instance for JSON data
		System.out.println(path);
        graph.setPath(path);
        System.err.println(graph.getPath());
        
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
			graph.setPath(path);
			System.out.println(graph.getPath() + " see");
			return graph;
		default:
			System.err.println("The given file is not valid to create an object instance out of it...");
			break;
		}
		// matching(graph);
		return graph;
	}
}
