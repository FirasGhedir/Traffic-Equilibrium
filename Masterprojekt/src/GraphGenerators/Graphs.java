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
 * 
 *          _____________________________________________
 * 
 *          A collection of utilities to assist with graph manipulation.
 */
public class Graphs implements Graph<Vertex, Edge> {

	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<Edge> edges = new ArrayList<Edge>();

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
		return null;
	}

	@Override
	public GraphType getType() {
		return null;
	}

	@Override
	public Set<Vertex> vertexSet() {
		return null;
	}

	public ArrayList<Vertex> getoutNeighbors(Vertex v, int[][] adjazenz) {
		ArrayList<Vertex> tmp = new ArrayList<>();
		for (int i = 0; i < adjazenz[v.getId()].length; i++) {
			if (adjazenz[v.getId()][i] == 1) {
				tmp.add(this.vertices.get(i));
			}

		}

		return tmp;

	}

	
	public ArrayList<Vertex> getinNeighbors(Vertex v,int[][] adjazenz){
		ArrayList<Vertex> tmp = new ArrayList<>();
        for(int i = 0 ; i < adjazenz.length ; i++) {
        	if(i == v.getId()) continue ;
        		if(adjazenz[i][v.getId()]==1) tmp.add(this.vertices.get(i));
        		        		       	       	
        }
		
		return tmp;
	}
	public int[][] adjazenzmatrix() {
		int[][] adj = new int[vertices.size()][vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				adj[i][j] = 0;
				adj[j][i] = 0;
			}
		}

		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < edges.size(); j++) {
				if (edges.get(j).getFrom().equals(vertices.get(i))) {
					adj[i][edges.get(j).getTo().getId()] = 1;
				}

			}
		}
		return adj;

	}

}