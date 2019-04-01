package tntpUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import graphGenerator.BarabasiAlbertGraphGenerator;
import graphGenerator.GridGraphGenerator;
import graphGenerator.HeavyTailGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.RMINTB;
import heuristic.SocialOptimum;
import ilog.concert.IloException;
import nickerl.Nickerl;
import player.Player;

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

//		if (debug) {
//			System.out.println("----------------\nInstancegenerator");
//			System.out.println("number vertices:     " + path);
//			System.out.println("number players:      " + Integer.toString(g.getVertices().size()));
//			System.out.println("number commodoties:  " + Integer.toString(g.getPlayers().size()));
//			System.out.println("id                   " + id);
//			System.out.println("----------------");
//		}

		tnt = new tntpBuilder(g, "Gridinstances", path, Integer.toString(g.getVertices().size()),
				Integer.toString(g.getPlayers().size()), id);

	}

	public void generatepoisson(int min, int max, double rnd) {

		
		
	}

	public void generateheavytail(int min, int max) {

		
		
		
	}

	public static void main(String[] args) throws IloException {

	//	Instancegenerator test = new Instancegenerator();
//		for (int i = 0; i < 15; i++) {
//
//			test.generategridgraph(700, 1000, Integer.toString(i));
//
//		}

		Map<String, Vertex> map = new TreeMap<>();
		Graphs gg = new Graphs();
		BarabasiAlbertGraphGenerator tests = new BarabasiAlbertGraphGenerator(100, 3,200); // do not change !!
	//	GridGraphGenerator tests = new GridGraphGenerator(4	, 4); // do not change !!

		tests.generateGraph(gg, map);
		System.out.println(gg.getVertices().size());
		System.out.println(gg.getEdges().size());

		Player player1 = new Player(0, gg.getVertices().get(0), gg.getVertices().get(15), 20);
		Player player2 = new Player(1, gg.getVertices().get(1), gg.getVertices().get(14), 10);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		gg.setPlayer(x);
		gg.generateEdgesFunctions();
		//gg.generatePlayers();
		SocialOptimum social = new SocialOptimum(gg);
		social.solveDSSP(gg);
		RMINTB n = new RMINTB(gg);
		n.solve();

//		ss tnt = new ss(gg, null);
	}
}
