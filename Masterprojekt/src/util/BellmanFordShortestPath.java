package util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.Graphs;

@SuppressWarnings("hiding")
public class BellmanFordShortestPath<Vertex, Edge>
extends
BaseShortestPathAlgorithm<Vertex, Edge>
{
protected final Comparator<Double> comparator;
protected final int maxHops;

/**
 * Construct a new instance.
 *
 * @param graph the input graph
 */
public BellmanFordShortestPath(Graph<Vertex, Edge> graph)
{
    this(graph, ToleranceDoubleComparator.DEFAULT_EPSILON);
}

/**
 * Construct a new instance.
 *
 * @param graph the input graph
 * @param epsilon tolerance when comparing floating point values
 */
public BellmanFordShortestPath(Graph<Vertex, Edge> graph, double epsilon)
{
    this(graph, ToleranceDoubleComparator.DEFAULT_EPSILON, Integer.MAX_VALUE);
}

/**
 * Construct a new instance.
 *
 * @param graph the input graph
 * @param epsilon tolerance when comparing floating point values
 * @param maxHops execute the algorithm for at most this many iterations. If this is smaller
 *        than the number of vertices, then the negative cycle detection feature is disabled.
 * @throws IllegalArgumentException if the number of maxHops is not positive
 */
public BellmanFordShortestPath(Graph<Vertex, Edge> graph, double epsilon, int maxHops)
{
    super(graph);
    this.comparator = new ToleranceDoubleComparator(epsilon);
    if (maxHops < 1) {
        throw new IllegalArgumentException("Number of hops must be positive");
    }
    this.maxHops = maxHops;
}

/**
 * {@inheritDoc}
 * 
 * @throws NegativeCycleDetectedException in case a negative weight cycle is detected
 */
@Override
public GraphPath<Vertex, Edge> getPath(Vertex source, Vertex sink)
{
    if (!graph.containsVertex(sink)) {
        throw new IllegalArgumentException(GRAPH_MUST_CONTAIN_THE_SINK_VERTEX);
    }
    return getPaths(source).getPath(sink);
}

/**
 * {@inheritDoc}
 * 
 * @throws NegativeCycleDetectedException in case a negative weight cycle is detected
 */
@Override
@SuppressWarnings({ "unchecked", "rawtypes" })
public SingleSourcePaths<Vertex, Edge> getPaths(Vertex source)
{
    if (!graph.containsVertex(source)) {
        throw new IllegalArgumentException(GRAPH_MUST_CONTAIN_THE_SOURCE_VERTEX);
    }

    /*
     * Initialize distance and predecessor.
     */
    int n = graph.vertexSet().size();
    Map<Vertex, Double> distance = new HashMap<>();
    Map<Vertex, Edge> pred = new HashMap<>();
    for (Vertex v : graph.vertexSet()) {
        distance.put(v, Double.POSITIVE_INFINITY);
    }
    distance.put(source, 0d);

    /*
     * Maintain two sets of vertices whose edges need relaxation. The first set is the current
     * set of vertices while the second is the set for the subsequent iteration.
     */
    Set<Vertex>[] updated = (Set<Vertex>[]) Array.newInstance(Set.class, 2);
    updated[0] = new LinkedHashSet<>();
    updated[1] = new LinkedHashSet<>();
    int curUpdated = 0;
    updated[curUpdated].add(source);

    /*
     * Relax edges.
     */
    for (int i = 0; i < Math.min(n - 1, maxHops); i++) {
        Set<Vertex> curVertexSet = updated[curUpdated];
        Set<Vertex> nextVertexSet = updated[(curUpdated + 1) % 2];

        for (Vertex v : curVertexSet) {
            for (Edge e : graph.outgoingEdgesOf(v)) {
                Vertex u = Graphs.getOppositeVertex(graph, e, v);
                double newDist = distance.get(v) + graph.getEdgeWeight(e);
                if (comparator.compare(newDist, distance.get(u)) < 0) {
                    distance.put(u, newDist);
                    pred.put(u, e);
                    nextVertexSet.add(u);
                }
            }
        }

        // swap next with current
        curVertexSet.clear();
        curUpdated = (curUpdated + 1) % 2;

        // stop if no relaxation
        if (nextVertexSet.isEmpty()) {
            break;
        }
    }

    /*
     * Check for negative cycles. The user can disable this by providing a maxHops parameter
     * smaller than the number of vertices.
     */
    if (maxHops >= n) {
        for (Vertex v : updated[curUpdated]) {
            for (Edge e : graph.outgoingEdgesOf(v)) {
                Vertex u = Graphs.getOppositeVertex(graph, e, v);
                double newDist = distance.get(v) + graph.getEdgeWeight(e);
                if (comparator.compare(newDist, distance.get(u)) < 0) {
                    // record update for negative cycle computation
                    pred.put(u, e);
                    throw new NegativeCycleDetectedException(
                        GRAPH_CONTAINS_A_NEGATIVE_WEIGHT_CYCLE, computeNegativeCycle(e, pred));
                }
            }
        }
    }

    /*
     * Transform result
     */
    Map<Vertex, Pair<Double, Edge>> distanceAndPredecessorMap = new HashMap<>();
    for (Vertex v : graph.vertexSet()) {
        distanceAndPredecessorMap.put(v, new Pair(distance.get(v), pred.get(v)));
    }
    return new TreeSingleSourcePathsImpl<Vertex,Edge>(graph, source, distanceAndPredecessorMap);
}

/**
 * Find a path between two vertices.
 * 
 * @param graph the graph to be searched
 * @param source the vertex at which the path should start
 * @param sink the vertex at which the path should end
 * 
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 *
 * @return a shortest path, or null if no path exists
 */
public static <Vertex, Edge> GraphPath<Vertex, Edge> findPathBetween(Graph<Vertex, Edge> graph, Vertex source, Vertex sink)
{
    return new BellmanFordShortestPath<>(graph).getPath(source, sink);
}

/**
 * Computes a negative weight cycle assuming that the algorithm has already determined that it
 * exists.
 * 
 * @param edge an edge which we know that belongs to the negative weight cycle
 * @param pred the predecessor array
 * 
 * @return the negative weight cycle
 */
private GraphPath<Vertex, Edge> computeNegativeCycle(Edge edge, Map<Vertex, Edge> pred)
{
    // find a vertex of the cycle
    Set<Vertex> visited = new HashSet<>();
    Vertex start = graph.getEdgeTarget(edge);
    visited.add(start);
    Vertex cur = Graphs.getOppositeVertex(graph, edge, start);

    while (!visited.contains(cur)) {
        visited.add(cur);
        Edge e = pred.get(cur);
        cur = Graphs.getOppositeVertex(graph, e, cur);
    }

    // now build the actual cycle
    List<Edge> cycle = new ArrayList<>();
    double weight = 0d;
    start = cur;
    do {
        Edge e = pred.get(cur);
        cycle.add(e);
        weight += graph.getEdgeWeight(e);
        cur = Graphs.getOppositeVertex(graph, e, cur);
    } while (cur != start);
    Collections.reverse(cycle);

    return new GraphWalk<>(graph, start, start, cycle, weight);
}
}
