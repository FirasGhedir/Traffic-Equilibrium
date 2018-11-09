package GraphGenerators;

import java.util.*;

/**
 * Universit�t Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 */
public interface GraphGenerator<V, E, T> {
	/*
	 *
	 * @param <V> the graph vertex type
	 * 
	 * @param <E> the graph edge type
	 * 
	 * @param <T> type for returning implementation-specific mappings
	 *
	 */

	/**
	 * Generates a graph structure
	 * 
	 * @param target
	 *            receives the generated edges and vertices
	 * @param resultMap
	 *            receives implementation-specific mappings
	 */
	void generateGraph(Graph<V, E> target, Map<String, T> resultMap);

	/**
	 * 
	 * @param target
	 *            receives the generated edges and vertices
	 */
	default void generateGraph(Graph<V, E> target) {
		generateGraph(target, null);
	}

}