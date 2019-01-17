package genetic.heuristic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;
import heuristic.DSSP;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
import player.Player;

public class main {


public static Graphs copy(Graphs original) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"))) {
        out.writeObject(original);
        return (Graphs) in.readObject();
    } catch (Exception e) {
        throw new RuntimeException(e); 
    }
}
	
	public static void main(String[] args) throws IloException, CloneNotSupportedException {

		Vertex a = new Vertex(1);
		Vertex b = new Vertex(2);
		Vertex c = new Vertex(3);
		Vertex d = new Vertex(4);
		Edge ab = new Edge(a, b, 1);
		Edge ac = new Edge(a, c, 2);
		Edge ad = new Edge(a, d, 5);
		Edge cb = new Edge(c, b, 3);
		Edge cd = new Edge(c, d, 1);
		Edge bd = new Edge(b, d, 3);
		ArrayList<Edge> edges = new ArrayList<>();
		ArrayList<Vertex> vertices = new ArrayList<>();
		edges.add(ab);
		edges.add(ac);
		edges.add(ad);
		edges.add(cb);
		edges.add(cd);
		edges.add(bd);
		vertices.add(a);
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);
		
		Graphs g = new Graphs();
		g.setEdges(edges);
		g.setVertices(vertices);
		
		List<Edge> KP = BellmanFordShortestPath.findPathBetween(g,a,d).getEdgeList();
		for(int i = 0 ; i < KP.size() ; i++) {
			System.out.println("from : " + KP.get(i).getFrom().getId() + " to : " + KP.get(i).getTo().getId() + " Weight : " +  KP.get(i).getL());
		}
        System.out.println(KP.size());
        Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(graph, map);

		// --- player ---
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 10);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 5);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		graph.setPlayer(x);
		graph.generateEdgesFunctions();// edge functions are totally randomized
		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);

    	DSSP dssp = new DSSP(graph);
      //  Graphs xx = copy(graph);
		TestCorrectness correct = new TestCorrectness();
		System.out.println(correct.test(graph, player1.getSource(), player1.getSink()));
		//System.out.println(correct.test(graph, player2.getSource(), player2.getSink()));

	}

}
