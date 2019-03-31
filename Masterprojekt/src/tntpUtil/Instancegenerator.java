package tntpUtil;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;

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
		GridGraphGenerator test = new GridGraphGenerator(zeilen, spalten); // do not change !!
		test.generateGraph(g, map);

		String path = min + "-" + max;
		g.generateEdgesFunctions();
		g.generatePlayers();

		if (debug) {
			System.out.println("----------------\nInstancegenerator");
			System.out.println("number vertices:     " + path);
			System.out.println("number players:      " + Integer.toString(g.getVertices().size()));
			System.out.println("number commodoties:  " + Integer.toString(g.getPlayers().size()));
			System.out.println("id                   " + id);
			System.out.println("----------------");
		}

		tnt = new tntpBuilder(g, "Gridinstances", path, Integer.toString(g.getVertices().size()),
				Integer.toString(g.getPlayers().size()), id);

	}

	public void generatepoisson(int min, int max, double rnd) {

	}

	public void generateheavytail(int min, int max) {

	}

	public static void main(String[] args) {

		Instancegenerator test = new Instancegenerator();
		for (int i = 0; i < 1; i++) {

			test.generategridgraph(100, 400, Integer.toString(i));

		}

		Map<String, Vertex> map = new TreeMap<>();
		Graphs gg = new Graphs();
		GridGraphGenerator tests = new GridGraphGenerator(5, 5); // do not change !!
		tests.generateGraph(gg, map);

//		ss tnt = new ss(gg, null);
	}
}
