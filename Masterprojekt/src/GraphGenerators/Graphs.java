package GraphGenerators;

import java.util.ArrayList;
import java.util.Set;

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 */
public class Graphs implements Graph<Vertex, Edge> {

	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<Edge> edges = new ArrayList<Edge>();

	/**
	 * Empty constructor
	 */
	public Graphs() {

	}

	@Override
	public Edge getEdge(Vertex sourceVertex, Vertex targetVertex) {
		Edge x = new Edge(sourceVertex, targetVertex, 0);
		if (edges.contains(x)) {
			return x;
		}
		return null;
	}

	@Override
	public Edge addEdge(Vertex sourceVertex, Vertex targetVertex) {
		edges.add(new Edge(sourceVertex, targetVertex, 0));
		return null;
	}

	@Override
	public boolean addEdge(Vertex sourceVertex, Vertex targetVertex, Edge e) {
		edges.add(new Edge(sourceVertex, targetVertex, 0));
		return false;
	}

	@Override
	public boolean addVertex(Vertex v) {
		vertices.add(v);
		return true;
	}

	@Override
	public boolean containsEdge(Edge e) {
		return edges.contains(e);
	}

	@Override
	public boolean containsVertex(Vertex v) {
		return vertices.contains(v);
	}

	@Override
	public int degreeOf(Vertex vertex) {
		int counter = 0;
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getFrom().equals(vertex)) {
				counter++;
			}

		}

		return counter;
	}

	@Override
	public boolean removeEdge(Edge e) {
		edges.remove(e);
		return true;
	}

	@Override
	public boolean removeVertex(Vertex v) {
		vertices.remove(v);
		return true;
	}

	@Override
	public double getEdgeWeight(Edge e) {
		return e.getWeight();
	}

	@Override
	public void setEdgeWeight(Edge e, double weight) {
		e.setWeight(weight);

	}

	@Override
	public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
		Edge e = new Edge(sourceVertex, targetVertex, 0);
		return edges.contains(e);
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public Vertex addVertex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Vertex> vertexSet() {
		// TODO Auto-generated method stub
		return null;
	}

}
