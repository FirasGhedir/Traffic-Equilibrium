package GraphGenerators;

import java.util.Set;

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
public interface Graph<V, E> {

	/**
	 * 
	 * @param sourceVertex
	 * @param targetVertex
	 * @return
	 */
	E getEdge(V sourceVertex, V targetVertex);

	/**
	 * 
	 * @param sourceVertex
	 * @param targetVertex
	 * @return
	 */
	E addEdge(V sourceVertex, V targetVertex);

	/**
	 * 
	 * @param sourceVertex
	 * @param targetVertex
	 * @param e
	 * @return
	 */
	boolean addEdge(V sourceVertex, V targetVertex, E e);

	/**
	 * 
	 * @param v
	 * @return
	 */
	boolean addVertex(V v);

	/**
	 * 
	 * @param sourceVertex
	 * @param targetVertex
	 * @return
	 */
	boolean containsEdge(V sourceVertex, V targetVertex);

	/**
	 * 
	 * @param e
	 * @return
	 */
	boolean containsEdge(E e);

	/**
	 * 
	 * @param v
	 * @return
	 */
	boolean containsVertex(V v);

	/**
	 * 
	 * @param vertex
	 * @return
	 */
	int degreeOf(V vertex);

	/**
	 * 
	 * @param e
	 * @return
	 */
	boolean removeEdge(E e);

	/**
	 * 
	 * @param v
	 * @return
	 */
	boolean removeVertex(V v);

	/**
	 * 
	 */
	double DEFAULT_EDGE_WEIGHT = 1.0;

	/**
	 * 
	 * @param e
	 * @return
	 */
	double getEdgeWeight(E e);

	/**
	 * 
	 * @param e
	 * @param weight
	 */
	void setEdgeWeight(E e, double weight);

	/**
	 * 
	 * @param sourceVertex
	 * @param targetVertex
	 * @param weight
	 */
	default void setEdgeWeight(V sourceVertex, V targetVertex, double weight) {
		this.setEdgeWeight(this.getEdge(sourceVertex, targetVertex), weight);
	}

	/**
	 * Creates a new vertex in this graph and returns it.
	 * 
	 * @return a newly created vertex if added to the graph.
	 */
	V addVertex();

	/**
	 * Getter method for the graph type.
	 * 
	 * @return the graph type
	 */
	GraphType getType();

	/**
	 * setter method for the vertex
	 */
	 Set<V> vertexSet();

}