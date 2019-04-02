package tntpUtil;

import java.io.File;
import java.io.IOException;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphGenerator.GridGraphGenerator;
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
		GridGraphGenerator test = new GridGraphGenerator(zeilen, spalten); // do not change !!
		test.generateGraph(g, map);
		g.generateEdgesFunctions();
		g.generatePlayers();
	//	String path ="./Masterprojekt/files/Gridinstances/" +  min + "-" + max + "/" + g.getVertices().size()+"."+g.getPlayers().size()+"."+id;
		String path ="./Masterprojekt/files/Gridinstances/" +  min + "-" + max + "/" +id;
		buildJSON(path,g);

//		if (debug) {
//			System.out.println("----------------\nInstancegenerator");
//			System.out.println("number vertices:     " + path);
//			System.out.println("number players:      " + Integer.toString(g.getVertices().size()));
//			System.out.println("number commodoties:  " + Integer.toString(g.getPlayers().size()));
//			System.out.println("id                   " + id);
//			System.out.println("----------------");
//		}
 
		
		
		

	}

	public void generatepoisson(int min, int max, double rnd) {

		
		
	}

	public void generateheavytail(int min, int max) {

		
		
		
	}

	public static void main(String[] args) throws IloException {

		Instancegenerator test = new Instancegenerator();
		for (int i = 0; i < 50; i++) {

			test.generategridgraph(100, 400, Integer.toString(i));

		}

//		Map<String, Vertex> map = new TreeMap<>();
//		Graphs gg = new Graphs();
//	//	BarabasiAlbertGraphGenerator tests = new BarabasiAlbertGraphGenerator(100, 3,200); // do not change !!
//	//	GridGraphGenerator tests = new GridGraphGenerator(4	, 4); // do not change !!
//
//		GnpRandomGraphGenerator tests = new GnpRandomGraphGenerator(10,0.9);
//		
//		
//		
//		tests.generateGraph(gg, map);
//		
//		System.out.println(gg.getVertices().size());
//		System.out.println(gg.getEdges().size());
//
//		for(int i = 0 ; i < gg.getEdges().size() ; i++) {
//			System.out.println(gg.getEdges().get(i).getFrom().getId() + " => " + gg.getEdges().get(i).getTo().getId());
//		}
//		
//		Player player1 = new Player(0, gg.getVertices().get(0), gg.getVertices().get(9), 20);
//		Player player2 = new Player(1, gg.getVertices().get(1), gg.getVertices().get(9), 10);
//
//		ArrayList<Player> x = new ArrayList<>();
//		x.add(0, player1);
//		x.add(1, player2);
////
//	//	gg.setPlayer(x);
//		gg.generateEdgesFunctions();
//		gg.generatecomodity();
//		System.out.println(gg.getPlayers().size());
//		SocialOptimum social = new SocialOptimum(gg);
//		social.solveDSSP(gg);
//		RMINTB n = new RMINTB(gg);
//		n.solve();
//
////		ss tnt = new ss(gg, null);
	}
	

	
	private static void buildJSON(String path,Object obj) {

		final ObjectMapper mapper = new ObjectMapper(); // can use static singleton, inject: just make sure to reuse!
	//	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// configure JSON
		//File file = new File("./Masterprojekt/files/graphData.json");
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
