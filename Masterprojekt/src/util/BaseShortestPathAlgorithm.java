package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import graphModel.Graph;

public abstract class BaseShortestPathAlgorithm<Vertex,Edge>  implements ShortestPathAlgorithm<Vertex,Edge>{

	/**
     * Error message for reporting the existence of a negative-weight cycle.
     */
    protected static final String GRAPH_CONTAINS_A_NEGATIVE_WEIGHT_CYCLE =
        "Graph contains a negative-weight cycle";
    /**
     * Error message for reporting that a source vertex is missing.
     */
    protected static final String GRAPH_MUST_CONTAIN_THE_SOURCE_VERTEX =
        "Graph must contain the source vertex!";
    /**
     * Error message for reporting that a sink vertex is missing.
     */
    protected static final String GRAPH_MUST_CONTAIN_THE_SINK_VERTEX =
        "Graph must contain the sink vertex!";

    /**
     * The underlying graph.
     */
    protected final Graph<Vertex, Edge> graph;

    /**
     * Constructs a new instance of the algorithm for a given graph.
     * 
     * @param graph the graph
     */
    public BaseShortestPathAlgorithm(Graph<Vertex, Edge> graph)
    {
        this.graph = Objects.requireNonNull(graph, "Graph is null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SingleSourcePaths<Vertex, Edge> getPaths(Vertex source)
    {
        if (!graph.containsVertex(source)) {
            throw new IllegalArgumentException("graph must contain the source vertex");
        }

        Map<Vertex, GraphPath<Vertex, Edge>> paths = new HashMap<>();
        for (Vertex v : graph.vertexSet()) {
            paths.put(v, getPath(source, v));
        }
        return new ListSingleSourcePathsImpl<>(graph, source, paths);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPathWeight(Vertex source, Vertex sink)
    {
        GraphPath<Vertex, Edge> p = getPath(source, sink);
        if (p == null) {
            return Double.POSITIVE_INFINITY;
        } else {
            return p.getWeight();
        }
    }

    /**
     * Create an empty path. Returns null if the source vertex is different than the target vertex.
     * 
     * @param source the source vertex
     * @param sink the sink vertex
     * @return an empty path or null null if the source vertex is different than the target vertex
     */
    protected final GraphPath<Vertex, Edge> createEmptyPath(Vertex source, Vertex sink)
    {
        if (source.equals(sink)) {
            return GraphWalk.singletonWalk(graph, source, 0d);
        } else {
            return null;
        }
    }

}
