package graphAnalysis;

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
 *          This class contains tools to analyse graph data
 */
public class GraphAnalysis {

	Graphs G = new Graphs();
	int maxNodeDegree;

	/**
	 * Constructor 1
	 */
	public GraphAnalysis() {

	}

	/**
	 * Constructor 2
	 * 
	 * @param g is a given graph
	 */
	public GraphAnalysis(Graphs g) {
		this.G.setEdges(g.getEdges());
		this.G.setVertices(g.getVertices());
		this.maxNodeDegree = getMaxNodeDegree();
	}

	/**
	 * Creates the node potential vector for a given graph
	 * --------------------------------------------
	 * 
	 * @param adjacencymatrix the given adjacency matrix
	 * 
	 * @return the node potential vector out of the adjacency matrix
	 */
	public int getMaxNodeDegree() {

		int[] nodePotentialVector = new int[G.getAdjacencyMatrix().length];

		int index = 0;
		for (int i : nodePotentialVector) {
			int tmp = 0;
			for (int j = 0; j < G.getAdjacencyMatrix().length; j++) {
				tmp += G.getAdjacencyMatrix()[j][i] + G.getAdjacencyMatrix()[i][j];
			}
			nodePotentialVector[index] = tmp;
			++index;
		}

		return Arrays.stream(nodePotentialVector).max().getAsInt();
	}

}
