package tntpUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bai_A.Mintb_FC;
import graphGenerator.GnpRandomGraphGenerator;
import graphGenerator.GridGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;
import heuristic.RMINTB;
import ilog.concert.IloException;

public class Instancegenerator {

	Graphs g;
	tntpBuilder tnt;
	Random rnd;

	boolean debug = true;

	public Instancegenerator() {
		this.rnd = new Random();
	}

	public void generategridgraph(int min, int max, String id) {

		int n = min + rnd.nextInt(max - min + 1);
		double x = Math.sqrt(n);
		double y = Math.sqrt(Math.sqrt(n));

		int zeilen = (int) (y + rnd.nextDouble() * (x - y));
		System.out.println("zeilen : " + zeilen);

		int spalten = (int) (n / zeilen);
		System.out.println("spalten : " + spalten);

		Map<String, Vertex> map = new TreeMap<>();
		g = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(zeilen, spalten);
		// change !!
		test.generateGraph(g, map);
		g.generateEdgesFunctions();
		g.generatePlayers();
		// String path ="./Masterprojekt/files/Gridinstances/" + min + "-" + max + "/" +
		// g.getVertices().size()+"."+g.getPlayers().size()+"."+id;
		String path = "./Masterprojekt/files/Gridinstances/" + min + "-" + max + "/" + id;
		buildJSON(path, g);

		// if (debug) {
		// System.out.println("----------------\nInstancegenerator");
		// System.out.println("number vertices: " + path);
		// System.out.println("number players: " +
		// Integer.toString(g.getVertices().size()));
		// System.out.println("number commodoties: " +
		// Integer.toString(g.getPlayers().size()));
		// System.out.println("id " + id);
		// System.out.println("----------------");
		// }

	}

	public void generatepoisson(int min, int max, double rnd, int id) {

		Map<String, Vertex> map = new TreeMap<>();
		g = new Graphs();
		int randomInt = ThreadLocalRandom.current().nextInt(min, max);
		GnpRandomGraphGenerator test = new GnpRandomGraphGenerator(randomInt, rnd);
		test.generateGraph(g, map);
		g.generateEdgesFunctions();
		g.generatecomodity();
		for(int i = 0 ; i < g.getEdges().size() ; i++) {
			System.out.println(g.getEdges().get(i).getFrom().getId() + "  " + g.getEdges().get(i).getTo().getId());
		}
//		List<Edge> LP = BellmanFordShortestPath
//				.findPathBetween(g, g.getPlayers().get(0).getSource(), g.getPlayers().get(0).getSink()).getEdgeList();
		System.out.println(g.getPlayers().get(0).getSource().getId() + " buuurn "  + g.getPlayers().get(0).getSink().getId());
		String path = "./Masterprojekt/files/Poissoninstances/" + min + "-" + max + "/" + String.valueOf(id);
		buildJSON(path, g);

	}

	public void generateheavytail(int min, int max) {

	}

	public static void main(String[] args) throws IloException {

		Instancegenerator tests = new Instancegenerator();
		for (int i = 0; i < 1; i++) {

			tests.generatepoisson(50, 100, 0.3, i);

		}

	}

	private static void buildJSON(String path, Object obj) {

		final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to reuse!
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// configure JSON
		// File file = new File("./Masterprojekt/files/graphData.json");
		File file = new File(path);
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

}
