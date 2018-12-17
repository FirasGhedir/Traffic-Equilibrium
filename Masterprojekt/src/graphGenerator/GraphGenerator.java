package graphGenerator;

import java.util.*;

import graphModel.Graph;

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
 *          An interface for generating new graph structure with V (the graph
 *          vertex type), E (the graph edge type) and T ( type for returning
 *          implementation-specific mappings)
 */
public interface GraphGenerator<V, E, T> {

	/**
	 * Generates a graph structure
	 * 
	 * --------------------------------------------
	 * 
	 * @param target    receives the generated edges and vertices
	 * @param resultMap receives implementation-specific mappings
	 */
	void generateGraph(Graph<V, E> target, Map<String, T> resultMap);

	/**
	 * Generates a graph structure
	 * 
	 * --------------------------------------------
	 * 
	 * @param target receives the generated edges and vertices
	 */
	default void generateGraph(Graph<V, E> target) {
		generateGraph(target, null);
	}
}