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
 *          This class the minimum, maximum and average degree can be calculated
 */
public class MaxMinAvgNodeDegree {

	Graphs G = new Graphs();
	int maxNodeDegree;
	int minNodeDegree;
	double avgNodeDegree;

	/**
	 * Constructor 1
	 */
	public MaxMinAvgNodeDegree() {

	}

	/**
	 * Constructor 2
	 * 
	 * @param g is a given graph
	 */
	public MaxMinAvgNodeDegree(Graphs g) {
		this.G.setEdges(g.getEdges());
		this.G.setVertices(g.getVertices());
		this.maxNodeDegree = getMaxNodeDegree();
		this.minNodeDegree = getMinNodeDegree();
		this.avgNodeDegree = getAvgNodeDegree();
	}

	/**
	 * Method, which returns the integer value of the maximum node degree of a given
	 * graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the maximum node degree
	 */
	public int getMaxNodeDegree() {

		return Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).max().getAsInt();
	}

	/**
	 * Method, which returns the integer value of the minimum node degree of a given
	 * graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the minimum node degree
	 */
	public int getMinNodeDegree() {

		return Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).min().getAsInt();
	}

	/**
	 * Method, which returns the value of the average node degree of a given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the average node degree
	 */
	public double getAvgNodeDegree() {

		return Arrays.stream(this.G.getNodePotentialVector(this.G.getAdjacencyMatrix())).average().getAsDouble();
	}

}
