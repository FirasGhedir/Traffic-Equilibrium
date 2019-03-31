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
	
	public Instancegenerator() {
	this.rnd = new Random();
	}
	
	public void generategridgraph(int min, int max) {
		int n = min + rnd.nextInt(max - min + 1);
		double x = Math.sqrt(n);
		double y = Math.sqrt(Math.sqrt(n));

		int zeilen =(int) (y + rnd.nextDouble()*(x-y));
		System.out.println("zeilen : " + zeilen);

		int spalten = (int) (n/zeilen);
		System.out.println("spalten : " + spalten);

		Map<String, Vertex> map = new TreeMap<>();
		g = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(zeilen, spalten); // do not change !!
		test.generateGraph(g, map);
		tnt = new tntpBuilder(g,null);

		
	}
	
	public void generatepoisson(int min, int max , double rnd) {
		
	}
	
	public void generateheavytail(int min, int max) {
		
		
	}
	
	
	
	public static void main(String[] args) {
		Instancegenerator test = new Instancegenerator();
		test.generategridgraph(200, 400);
		
	}
	
}
