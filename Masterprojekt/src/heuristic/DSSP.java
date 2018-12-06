package heuristic;

import graphModel.Graph;
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
 *          A heuristic for solving the Dynamic Slope Scaling Procedure (DSSP)
 */
public class DSSP {

	Graphs graph = new Graphs();
	int[] rho; // node potential vector

	/**
	 * Constructor
	 */
	public DSSP() {
	}

	/**
	 * Constructor with parameter Graph
	 * 
	 * @param g
	 */
	public DSSP(Graphs g) {
		// set length of node potential vector
		rho = new int[g.getAdjacencyMatrix().length];
		// fill the vector out of the adjacency matrix
		rho = g.getNodePotentialVector(g.getAdjacencyMatrix());
	}

	/**
	 * Linear problem of the DSSP
	 */
	public void dssLP() {
		/*
		 * ToDo
		 */
	}

	/**
	 * Main algorithm of the DSSP
	 */
	public void dssp() {
		/*
		 * ToDo
		 */
	}
}
