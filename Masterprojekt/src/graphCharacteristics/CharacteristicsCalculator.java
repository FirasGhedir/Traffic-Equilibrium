package graphCharacteristics;

import graphModel.Graphs;

/**
 * Universit�t Ulm
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
 *          This class calculates different characteristics of graph theory
 *          including:
 * 
 *          - max/average/min node degree
 * 
 * 
 *          - average distance
 *          (https://en.wikipedia.org/wiki/Distance_(graph_theory))
 *
 *
 *          - average eccentricity
 *          (https://en.wikipedia.org/wiki/Distance_(graph_theory))
 *
 *
 *          - Radius (https://en.wikipedia.org/wiki/Distance_(graph_theory))
 *
 *
 *          - Diameter (https://en.wikipedia.org/wiki/Distance_(graph_theory))
 *
 *
 *          - min separating set
 *          (https://en.wikipedia.org/wiki/Vertex_separator)
 *
 *
 *          - min cut (https://en.wikipedia.org/wiki/Minimum_cut)
 *
 *
 *          - degeneracy
 *          (https://en.wikipedia.org/wiki/Degeneracy_(graph_theory))
 *
 *
 *          - arboricity (https://en.wikipedia.org/wiki/Arboricity)
 *
 *
 */
public class CharacteristicsCalculator {

	// --- min/avg/max vertex degree
	MaxMinAvgVertexDegree m;
	int maxVertexDegree;
	int minVertexDegree;
	double avgVertexDegree;
	// --- eccentricity
	Eccentricity e;
	private double avgEccentricity; // the average vertex eccentricity of the graph
	private int[] eccentricities; // vertex vector with its eccentricity values
	int[] ecc;
	// --- diameter
	Diameter d;
	private int diameter; // the maximum eccentricity of any vertex in the graph
	// --- radius
	Radius r;
	private int radius; // the minimum eccentricity of any vertex in the graph

	/**
	 * Constructor
	 * 
	 * @param graph the given graph
	 * @throws InterruptedException if an error occures
	 */
	public CharacteristicsCalculator(Graphs graph) throws InterruptedException {

		// --- max/avg/min vertex degree
		this.m = new MaxMinAvgVertexDegree(graph);
		this.maxVertexDegree = m.getMaxVertexDegree();
		this.minVertexDegree = m.getMinVertexDegree();
		this.avgVertexDegree = m.getAvgVertexDegree();

		// --- eccentricity
		this.e = new Eccentricity(graph);
		this.eccentricities = e.getEccentricities();
		this.avgEccentricity = e.getAvgEccentricity();

		// --- Diameter
		this.d = new Diameter(graph);
		this.diameter = d.getDiameter();

		// --- Radius
		this.r = new Radius(graph);
		this.radius = r.getRadius();
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
	 * Get the eccentricities for each vertex in this graph.
	 * 
	 * --------------------------------------------
	 * 
	 * @return the eccentricities for each vertex
	 */
	public int[] getEccentricities() {
		return this.eccentricities;
	}

	/**
	 * Sets the eccentricities for each vertex in this graph.
	 * 
	 * @param eccentricities the current eccentricity vector
	 */
	public void setEccentricities(int[] eccentricities) {
		this.eccentricities = eccentricities;
	}

	/**
	 * Get the average eccentricities in the given graph.
	 * 
	 * --------------------------------------------
	 * 
	 * @return the average eccentricity
	 */
	public double getAvgEccentricity() {
		return this.avgEccentricity;
	}

	/**
	 * Gets the diameter
	 * 
	 * @return the diameter
	 */
	public int getDiameter() {
		return this.diameter;
	}

	/**
	 * Gets the diameter
	 * 
	 * @return the diameter
	 */
	public int getRadius() {
		return this.radius;
	}
}
