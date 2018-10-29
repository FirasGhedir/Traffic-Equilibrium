import java.util.Map;
import java.util.TreeMap;

import GraphGenerators.Graphs;
import GraphGenerators.GridGraphGenerator;
import GraphGenerators.Vertex;



public class Main {
	
	public static  void main(String[] args) {
		
		GridGraphGenerator test = new GridGraphGenerator(3,2);
		Map<String,Vertex> map= new TreeMap<>();
		Graphs graph=new Graphs();
		test.generateGraph(graph,map);
		System.out.println(graph.getEdges().size());
		System.out.println(graph.getVertices().size());
		

	}

}
