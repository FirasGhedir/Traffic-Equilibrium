package GraphGenerators;

import java.util.*;

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 */
public interface GraphGenerator<V, E, T> {

	void generateGraph(Graph<V, E> target, Map<String, T> resultMap);

	default void generateGraph(Graph<V, E> target) {
		generateGraph(target, null);
	}

}