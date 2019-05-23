package tntpUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphGenerator.GeneratorPoisson;
import graphGenerator.GridGraphGenerator;
import graphGenerator.HeavyTail;
import graphModel.Graphs;
import graphModel.Vertex;
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
		String path = "./Masterprojekt/files/Gridinstances/" + 50 + "-" + 100 + "/" + id;
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

	public void generatepoisson(int min, int max, int id) {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs g = new Graphs();
		GeneratorPoisson test = new GeneratorPoisson(1, 99, 0.1);
		test.generateGraph(g, map);
		System.out.println(g.getVertices().size() + "  " + g.getEdges().size());
		g.generateEdgesFunctions();
		g.setPlayer(test.getPlayers());
		System.out.println(g.getPlayers().get(0).getSource().getId() + " buuurn "  + g.getPlayers().get(0).getSink().getId());
		String path = "./Masterprojekt/files/Poissoninstances/" + min + "-" + max + "/" + String.valueOf(id);
		buildJSON(path, g);

	}

	public void generateheavytail(int id) {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs g = new Graphs();
		HeavyTail test = new HeavyTail();
		test.generateGraph(g, map);
		g.generateEdgesFunctions();
		g.generatePlayers();
		System.out.println("done");
		String path = "./Masterprojekt/files/HeavyTail/" + 50 + "-" + 100 + "/" + String.valueOf(id);
		buildJSON(path, g);
	}

	public static void main(String[] args) throws IloException {

		Instancegenerator tests = new Instancegenerator();
		for (int i = 250; i < 251; i++) {

			tests.generategridgraph(5,10,Integer.toString(i));

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
