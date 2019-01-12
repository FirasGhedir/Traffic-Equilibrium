package heuristic;



import graphModel.Graph;
import graphModel.Graphs;
import heuristic.ShortestPathAlgorithm.SingleSourcePaths;

import java.io.*;
import java.util.*;

public class TreeSingleSourcePathsImpl<Vertex,Edge> implements
SingleSourcePaths<Vertex, Edge>,
Serializable {

	 private static final long serialVersionUID = -5914007312734512847L;

	    /**
	     * The graph
	     */
	    protected Graph<Vertex, Edge> g;

	    /**
	     * The source vertex
	     */
	    protected Vertex source;

	    /**
	     * A map which keeps for each target vertex the predecessor edge and the total length of the
	     * shortest path.
	     */
	    protected Map<Vertex, Pair<Double, Edge>> map;

	    /**
	     * Construct a new instance.
	     * 
	     * @param g the graph
	     * @param source the source vertex
	     * @param distanceAndPredecessorMap a map which contains for each vertex the distance and the
	     *        last edge that was used to discover the vertex. The map does not need to contain any
	     *        entry for the source vertex. In case it does contain the predecessor at the source
	     *        vertex must be null.
	     */
	    public TreeSingleSourcePathsImpl(
	        Graph<Vertex, Edge> g, Vertex source, Map<Vertex, Pair<Double, Edge>> distanceAndPredecessorMap)
	    {
	        this.g = Objects.requireNonNull(g, "Graph is null");
	        this.source = Objects.requireNonNull(source, "Source vertex is null");
	        this.map = Objects
	            .requireNonNull(distanceAndPredecessorMap, "Distance and predecessor map is null");
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public Graph<Vertex, Edge> getGraph()
	    {
	        return g;
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public Vertex getSourceVertex()
	    {
	        return source;
	    }

	    /**
	     * Get the internal map used for representing the paths.
	     * 
	     * @return the internal distance and predecessor map used for representing the paths.
	     */
	    public Map<Vertex, Pair<Double, Edge>> getDistanceAndPredecessorMap()
	    {
	        return Collections.unmodifiableMap(map);
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public double getWeight(Vertex targetVertex)
	    {
	        Pair<Double, Edge> p = map.get(targetVertex);
	        if (p == null) {
	            if (source.equals(targetVertex)) {
	                return 0d;
	            } else {
	                return Double.POSITIVE_INFINITY;
	            }
	        } else {
	            return p.getFirst();
	        }
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Override
	    public GraphPath<Vertex, Edge> getPath(Vertex targetVertex)
	    {
	        if (source.equals(targetVertex)) {
	            return GraphWalk.singletonWalk(g, source, 0d);
	        }

	        LinkedList<Edge> edgeList = new LinkedList<>();

	        Vertex cur = targetVertex;
	        Pair<Double, Edge> p = map.get(cur);
	        if (p == null || p.getFirst().equals(Double.POSITIVE_INFINITY)) {
	            return null;
	        }

	        double weight = 0d;
	        while (p != null && !cur.equals(source)) {
	            Edge e =  p.getSecond();
	            if (e == null) {
	                break;
	            }
	            edgeList.addFirst(e);
	            weight += g.getEdgeWeight(e);
	            cur = Graphs.getOppositeVertex(g, e, cur);
	            p = map.get(cur);
	        }

	        return new GraphWalk<>(g, source, targetVertex, null, edgeList, weight);
	    }

	
}
