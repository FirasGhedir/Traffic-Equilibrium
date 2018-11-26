package GraphVisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          Graphs get visualized
 */
public class GraphVisualizer {

	static int edgeCount_Directed = 0; // This works with the inner MyEdge class

	/**
	 * Universität Ulm
	 * 
	 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
	 * 
	 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
	 * @author Julian Bestler (julian.bestler@uni-ulm.de)
	 * 
	 * @version 1.0
	 * 
	 *          _____________________________________________
	 * 
	 *          Class MyNode
	 */
	class MyNode {

		// static int edgeCount = 0; // This works with the inner MyEdge class

		String id;

		public MyNode(String id) {
			this.id = id;
		}

		public String toString() {
			return "V" + id;
		}

		public String Node_Property() {
			String node_prop = id;
			return (node_prop);
		}
	}

	/**
	 * Universität Ulm
	 * 
	 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
	 * 
	 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
	 * @author Julian Bestler (julian.bestler@uni-ulm.de)
	 * 
	 * @version 1.0
	 * 
	 *          _____________________________________________
	 * 
	 *          Class MyLink
	 */
	class MyLink {

		double weight;
		String Label;
		int id;

		public MyLink(double weight, String Label) {
			this.id = edgeCount_Directed++;
			this.weight = weight;
			this.Label = Label;
		}

		public String toString() {
			return "E" + id;
		}

		public String Link_Property() {
			String Link_prop = Label;
			return (Link_prop);
		}

		public String Link_Property_wt() {
			String Link_prop_wt = "" + weight;
			return (Link_prop_wt);
		}
	}

	/**
	 * used to construct graph and call graph algorithm used in JUNG
	 */
	public void Visualize_Directed_Graph(LinkedList<String> Distinct_nodes, LinkedList<String> source_vertex,
			LinkedList<String> target_vertex, LinkedList<Double> Edge_Weight, LinkedList<String> Edge_Label) {

		// CREATING weighted directed graph
		Graph<MyNode, MyLink> g = new DirectedSparseGraph<GraphVisualizer.MyNode, GraphVisualizer.MyLink>();

		// create node objects
		Hashtable<String, MyNode> Graph_Nodes = new Hashtable<String, GraphVisualizer.MyNode>();
		LinkedList<MyNode> Source_Node = new LinkedList<GraphVisualizer.MyNode>();
		LinkedList<MyNode> Target_Node = new LinkedList<GraphVisualizer.MyNode>();
		LinkedList<MyNode> Graph_Nodes_Only = new LinkedList<GraphVisualizer.MyNode>();
		// l LinkedList<MyLink> Graph_Links = new LinkedList<Graph_Algos.MyLink>();

		// create graph nodes
		for (int i = 0; i < Distinct_nodes.size(); i++) {
			String node_name = Distinct_nodes.get(i);
			MyNode data = new MyNode(node_name);
			Graph_Nodes.put(node_name, data);
			Graph_Nodes_Only.add(data);
		}

		// Now convert all source and target nodes into objects
		for (int t = 0; t < source_vertex.size(); t++) {
			Source_Node.add(Graph_Nodes.get(source_vertex.get(t)));
			Target_Node.add(Graph_Nodes.get(target_vertex.get(t)));
		}

		// Now add nodes and edges to the graph
		for (int i = 0; i < Edge_Weight.size(); i++) {
			g.addEdge(new MyLink(Edge_Weight.get(i), Edge_Label.get(i)), Source_Node.get(i), Target_Node.get(i),
					EdgeType.DIRECTED);
		}

		// -------------
		ISOMLayout<MyNode, MyLink> layout1 = new ISOMLayout<MyNode, MyLink>(g);
		layout1.setSize(new Dimension(600, 600));
		BasicVisualizationServer<MyNode, MyLink> viz = new BasicVisualizationServer<MyNode, MyLink>(layout1);
		viz.setPreferredSize(new Dimension(600, 600));
		Transformer<MyNode, String> vertexLabelTransformer = new Transformer<MyNode, String>() {

			public String transform(MyNode vertex) {
				return (String) vertex.Node_Property();
			}

		};

		Transformer<MyLink, String> edgeLabelTransformer = new Transformer<MyLink, String>() {

			public String transform(MyLink edge) {
				// return "[ " + edge.Link_Property() + " ]: Wt = " + edge.Link_Property_wt();
				return "costs = " + edge.Link_Property_wt();
			}

		};

		viz.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
		viz.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);

		JFrame frame = new JFrame(" + + + graph visualizer + + + ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viz);
		frame.pack();
		frame.setVisible(true);
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
	}

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// let the nodes of graph are: {A, B, C, D, E, F, G}
		// Directed edges are: {AB=0.7, BC=0.9, CD=0.57, DB=1.0, CA=1.3, AD=0.3}
		GraphVisualizer GA1 = new GraphVisualizer();
		LinkedList<String> Distinct_Vertex = new LinkedList<String>();// used to enter vertexes
		LinkedList<String> Source_Vertex = new LinkedList<String>();
		LinkedList<String> Target_Vertex = new LinkedList<String>();
		LinkedList<Double> Edge_Weight = new LinkedList<Double>();// used to enter edge weight
		LinkedList<String> Edge_Label = new LinkedList<String>(); // used to enter edge levels

		// add the distinct vertexes
		Distinct_Vertex.add("A");
		Distinct_Vertex.add("B");
		Distinct_Vertex.add("C");
		Distinct_Vertex.add("D");

		Source_Vertex.add("A");
		Target_Vertex.add("B");
		Edge_Weight.add(0.7);
		Edge_Label.add("AB");

		Source_Vertex.add("B");
		Target_Vertex.add("C");
		Edge_Weight.add(0.9);
		Edge_Label.add("BC");

		Source_Vertex.add("C");
		Target_Vertex.add("D");
		Edge_Weight.add(0.57);
		Edge_Label.add("CD");

		Source_Vertex.add("A");
		Target_Vertex.add("D");
		Edge_Weight.add(0.3);
		Edge_Label.add("AD");

		GA1.Visualize_Directed_Graph(Distinct_Vertex, Source_Vertex, Target_Vertex, Edge_Weight, Edge_Label);

	}

}
