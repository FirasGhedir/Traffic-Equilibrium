package graphModel;

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
 * 
 *          _____________________________________________
 * 
 *          The Graph interface is at the same time the root interface. A graph
 *          object G(V,E) contains a set V of vertices and a set E of edges.
 *          Each edge e=(v1,v2) in E connects vertex v1 to vertex v2
 */
public interface Graph<V, E> {

	double DEFAULT_EDGE_WEIGHT = 1.0; // the default weight for an edge

	/**
	 * Returns an edge connecting source vertex to target vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param sourceVertex is the source vertex of the edge
	 * @param targetVertex is the target vertex of the edge
	 * 
	 * 
	 * @return an edge connecting source vertex to target vertex
	 */
	E getEdge(V sourceVertex, V targetVertex);

	/**
	 * Returns the created edge if added to the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param sourceVertex the source vertex of the edge
	 * @param targetVertex the target vertex of the edge
	 * 
	 * @return the created edge if added to the graph
	 */
	E addEdge(V sourceVertex, V targetVertex);

	/**
	 * Adds the specified edge to the given graph, going from the source vertex to
	 * the target vertex.
	 * 
	 * --------------------------------------------
	 * 
	 * @param sourceVertex the source vertex of the edge
	 * @param targetVertex the target vertex of the edge
	 * @param e            the edge to be added to this graph
	 * 
	 * @return true, if the given graph does not contain the specified edge
	 */
	boolean addEdge(V sourceVertex, V targetVertex, E e);

	/**
	 * Creates a new vertex in this graph and returns it.
	 * 
	 * --------------------------------------------
	 * 
	 * @return the created vertex if added to the graph.
	 */
	V addVertex();

	/**
	 * Adds the specified vertex v to the given graph, if this graph doesn't contain
	 * a vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param v the vertex to be added to this graph.
	 * 
	 * @return the created vertex if added to the graph
	 */
	boolean addVertex(V v);

	/**
	 * Checks, if the given graph contains an edge going from the source vertex to
	 * the target vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param sourceVertex the source vertex of the edge
	 * @param targetVertex the target vertex of the edge
	 * 
	 * @return true, if the given graph contains the specified edge.
	 */
	boolean containsEdge(V sourceVertex, V targetVertex);

	/**
	 * Checks, if the given graph contains the specified edge.
	 * 
	 * --------------------------------------------
	 * 
	 * @param e the edge whose presence in this graph is to be tested
	 * 
	 * @return true, if the given graph contains the specified edge.
	 */
	boolean containsEdge(E e);

	/**
	 * Checks, if the given graph contains the specified vertex
	 * 
	 * --------------------------------------------
	 * 
	 * @param v the vertex in this graph
	 * 
	 * @return true, if the given graph contains the specified vertex
	 */
	boolean containsVertex(V v);

	/**
	 * Returns the vertex degree
	 * 
	 * --------------------------------------------
	 * 
	 * @param vertex the vertex whose degree has to get calculated
	 * 
	 * @return the degree of the specified vertex
	 */
	int degreeOf(V vertex);

	/**
	 * Removes the specified edge from the given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param e the edge to be removed from the given graph
	 * 
	 * @return true, if the given graph contains the specified edge
	 */
	boolean removeEdge(E e);

	/**
	 * Removes the specified vertex from the given graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param v the vertex to be removed from the given graph
	 * 
	 * @return true, if the given graph contains the specified vertex
	 */
	boolean removeVertex(V v);

	/**
	 * Returns the edge weight
	 * 
	 * --------------------------------------------
	 * 
	 * @param e the edge
	 * @return the egde weight
	 */
	double getEdgeWeight(E e);

	/**
	 * Sets the edge weight
	 * 
	 * --------------------------------------------
	 * 
	 * @param e      the edge
	 * @param weight the weight of the edge
	 */
	void setEdgeWeight(E e, double weight);

	/**
	 * Sets a weight to an edge
	 * 
	 * --------------------------------------------
	 * 
	 * @param sourceVertex the source vertex of the edge
	 * @param targetVertex the target vertex of the edge
	 * @param weight       the weight of the edge
	 */
	default void setEdgeWeight(V sourceVertex, V targetVertex, double weight) {
		this.setEdgeWeight(this.getEdge(sourceVertex, targetVertex), weight);
	}

	/**
	 * Returns the graph type
	 * 
	 * --------------------------------------------
	 * 
	 * @return the graph type
	 */
	GraphType getType();

	/**
	 * A collection that contains no duplicate elements. More formally, sets contain
	 * no pair of elements e1 and e2 such that e1.equals(e2), and at most one null
	 * element.
	 * 
	 * --------------------------------------------
	 * 
	 * @return a set of vertices
	 */
	Set<V> vertexSet();

	/**
	 * Returns the source vertex of an edge. For an undirected graph, source and
	 * target are distinguishable designations (but without any mathematical
	 * meaning).
	 * 
	 * --------------------------------------------
	 * 
	 * @param e edge of interest
	 *
	 * @return source vertex
	 */
	V getEdgeSource(E e);

	/**
	 * Returns the target vertex of an edge. For an undirected graph, source and
	 * target are distinguishable designations (but without any mathematical
	 * meaning).
	 * 
	 * --------------------------------------------
	 * 
	 * @param e edge of interest
	 *
	 * @return target vertex
	 */
	V getEdgeTarget(E e);
	
    Set<E> edgeSet();


}