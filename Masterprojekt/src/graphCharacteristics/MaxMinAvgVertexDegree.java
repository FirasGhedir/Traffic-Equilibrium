package graphCharacteristics;

import java.util.Arrays;

import graphModel.Graphs;

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
 *          This class the minimum, maximum and average degree can be
 *          calculated.
 * 
 *          The degree of a graph vertex v of a graph G is the number of graph
 *          edges which touch v. The vertex degrees are illustrated above for a
 *          random graph.
 * 
 *          Directed graphs have two types of degrees, known as the indegree
 *          d_in and the outdegree d_out with d_total = d_in + d_out
 */
public class MaxMinAvgVertexDegree {

	Graphs G = new Graphs();
	int maxVertexDegree;
	int minVertexDegree;
	double avgVertexDegree;

	/**
	 * Constructor
	 * 
	 * @param g is a given graph
	 * @throws InterruptedException
	 */
	public MaxMinAvgVertexDegree(Graphs g) throws InterruptedException {
		this.G.setEdges(g.getEdges());
		this.G.setVertices(g.getVertices());
		calcMaxMinAvgVertexDegree();
	}

	/**
	 * calculates the radius for the given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @throws InterruptedException if an error occures
	 */
	public void calcMaxMinAvgVertexDegree() throws InterruptedException {
		this.maxVertexDegree = Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).max()
				.getAsInt();
		this.minVertexDegree = Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).min()
				.getAsInt();
		this.avgVertexDegree = Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).average()
				.orElse(Double.NaN);
	}

	/**
	 * Method, which returns the integer value of the maximum vertex degree of a
	 * given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the maximum vertex degree
	 */
	public int getMaxVertexDegree() {
		return this.maxVertexDegree;
	}

	/**
	 * Method, which returns the integer value of the minimum vertex degree of a
	 * given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the minimum vertex degree
	 */
	public int getMinVertexDegree() {
		return this.minVertexDegree;
	}

	/**
	 * Method, which returns the value of the average vertex degree of a given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the average vertex degree
	 */
	public double getAvgVertexDegree() {
		return this.avgVertexDegree;
	}

	/**
	 * Sets the current graph
	 * 
	 * @param g the given graph
	 */
	public void setG(Graphs g) {
		this.G = g;
	}
}
