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
	int maxvertexDegree;
	int minvertexDegree;
	double avgvertexDegree;

	/**
	 * Constructor 1
	 */
	public MaxMinAvgVertexDegree() {

	}

	/**
	 * Constructor 2
	 * 
	 * @param g is a given graph
	 */
	public MaxMinAvgVertexDegree(Graphs g) {
		this.G.setEdges(g.getEdges());
		this.G.setVertices(g.getVertices());
		this.maxvertexDegree = getMaxVertexDegree();
		this.minvertexDegree = getMinVertexDegree();
		this.avgvertexDegree = getAvgVertexDegree();
	}

	/**
	 * Method, which returns the integer value of the maximum vertex degree of a given
	 * graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the maximum vertex degree
	 */
	public int getMaxVertexDegree() {

		return Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).max().getAsInt();
	}

	/**
	 * Method, which returns the integer value of the minimum vertex degree of a given
	 * graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the minimum vertex degree
	 */
	public int getMinVertexDegree() {

		return Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).min().getAsInt();
	}

	/**
	 * Method, which returns the value of the average vertex degree of a given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the average vertex degree
	 */
	public double getAvgVertexDegree() {

		return Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).average().orElse(Double.NaN);
	}

}
