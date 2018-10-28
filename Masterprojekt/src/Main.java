import java.util.Map;
import java.util.TreeMap;

import GraphGenerators.Graph;
import GraphGenerators.GridGraphGenerator;

public class Main {
	
	public static <V, E> void main(String[] args) {
		
		GridGraphGenerator<V,E> test = new GridGraphGenerator<>(5,5);
		Map<String,V> map= new TreeMap<>();
		Graph<V,E> graph = null;
		
		test.generateGraph(graph,map);

	}

}
