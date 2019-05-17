package graphCharacteristics;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;

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
 *          This contains an algorithm to calculate Degeneracy,.
 * 
 * 
 *          The degeneracy of a graph is the smallest value of k for which it is
 *          k-degenerate. In graph theory, a k-degenerate graph is an undirected
 *          graph in which every subgraph has a vertex of degree at most k: that
 *          is, some vertex in the subgraph touches k or fewer of the subgraph's
 *          edges.
 * 
 */
public class Degeneracy {

	Graphs graph;
	int degeneracy;
	private Map<Vertex, Integer> scores;

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph
	 * @throws InterruptedException
	 */
	public Degeneracy(Graphs graph) throws InterruptedException {
		setG(graph);
		calculateDegeneracy();
	}

	/**
	 * Calculates the degeneracy of the graph.
	 */
	@SuppressWarnings("unchecked")
	private void calculateDegeneracy() {

		setScores(new HashMap<>());
		setDegeneracy(0);

		/*
		 * Initialize buckets
		 */
		int n = graph.getVertices().size();
		int maxDegree = n - 1;
		Set<Vertex>[] buckets = (Set<Vertex>[]) Array.newInstance(Set.class, maxDegree + 1);
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new HashSet<>();
		}

		int minDegree = n;
		Map<Vertex, Integer> degrees = new HashMap<>();
		for (Vertex v : graph.getVertices()) {
			int d = graph.degreeOf(v);
			buckets[d].add(v);
			degrees.put(v, d);
			minDegree = Math.min(minDegree, d);
		}

		/*
		 * Extract from buckets
		 */
		while (minDegree < n) {

			Set<Vertex> b = buckets[minDegree];

			if (b.isEmpty()) {
				minDegree++;
				continue;
			}

			Vertex v = b.iterator().next();
			b.remove(v);
			scores.put(v, minDegree);

			setDegeneracy(Math.max(getDegeneracy(), minDegree));

//			for (Edge e : graph.getEdges()) {
//				Vertex u = Graphs.getOppositeVertex(graph, e, v);
//				int uDegree = degrees.get(u);
//				if (uDegree > minDegree && !scores.containsKey(u)) {
//					buckets[uDegree].remove(u);
//					uDegree--;
//					degrees.put(u, uDegree);
//					buckets[uDegree].add(u);
//					minDegree = Math.min(minDegree, uDegree);
//				}
//			}
		}
	}

	/**
	 * Getter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the given graph
	 */
	public Graphs getG() {
		return this.graph;
	}

	/**
	 * Getter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param g the given graph
	 */
	public void setG(Graphs g) {
		this.graph = g;
	}

	/**
	 * Setter method for degeneracy
	 * 
	 * --------------------------------------------
	 * 
	 * @param degeneracy
	 */
	public void setDegeneracy(int degeneracy) {
		this.degeneracy = degeneracy;
	}

	/**
	 * Getter method for degeneracy
	 * 
	 * --------------------------------------------
	 * 
	 * @return the degeneracy
	 */
	public int getDegeneracy() {
		return this.degeneracy;
	}

	/**
	 * Setter method for scores
	 * 
	 * --------------------------------------------
	 * 
	 * @param scores the given scores
	 */
	public void setScores(Map<Vertex, Integer> scores) {
		this.scores = scores;
	}

	/**
	 * Setter method for scores
	 * 
	 * --------------------------------------------
	 * 
	 * @return the scores map
	 */
	public Map<Vertex, Integer> getScores() {
		return this.scores;
	}
}
