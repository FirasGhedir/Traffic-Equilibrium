package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Function;

import graphModel.Graph;
import graphModel.Graphs;

public class GraphWalk<Vertex,Edge> implements GraphPath<Vertex,Edge>,Serializable {
   
	private static final long serialVersionUID = 7663410644865380676L;
    protected Graph<Vertex, Edge> graph;

    protected List<Vertex> vertexList;
    protected List<Edge> edgeList;

    protected Vertex startVertex;

    protected Vertex endVertex;

    protected double weight;

    /**
     * Creates a walk defined by a sequence of edges. A walk defined by its edges can be specified
     * for non-simple graphs. Edge repetition is permitted, the start and end point points ($v_0$
     * and $v_k$) can be different.
     *
     * @param graph the graph
     * @param startVertex the starting vertex
     * @param endVertex the last vertex of the path
     * @param edgeList the list of edges of the path
     * @param weight the total weight of the path
     */
    public GraphWalk(Graph<Vertex, Edge> graph, Vertex startVertex, Vertex endVertex, List<Edge> edgeList, double weight)
    {
        this(graph, startVertex, endVertex, null, edgeList, weight);
    }

    /**
     * Creates a walk defined by a sequence of vertices. Note that the input graph must be simple,
     * otherwise the vertex sequence does not necessarily define a unique path. Furthermore, all
     * vertices must be pairwise adjacent.
     * 
     * @param graph the graph
     * @param vertexList the list of vertices of the path
     * @param weight the total weight of the path
     */
    public GraphWalk(Graph<Vertex, Edge> graph, List<Vertex> vertexList, double weight)
    {
        this(
            graph, (vertexList.isEmpty() ? null : vertexList.get(0)),
            (vertexList.isEmpty() ? null : vertexList.get(vertexList.size() - 1)), vertexList, null,
            weight);
    }

    /**
     * Creates a walk defined by both a sequence of edges and a sequence of vertices. Note that both
     * the sequence of edges and the sequence of vertices must describe the same path! This is not
     * verified during the construction of the walk. This constructor makes it possible to store
     * both a vertex and an edge view of the same walk, thereby saving computational overhead when
     * switching from one to the other.
     *
     * @param graph the graph
     * @param startVertex the starting vertex
     * @param endVertex the last vertex of the path
     * @param vertexList the list of vertices of the path
     * @param edgeList the list of edges of the path
     * @param weight the total weight of the path
     */
    public GraphWalk(
        Graph<Vertex, Edge>graph, Vertex startVertex, Vertex endVertex, List<Vertex> vertexList, List<Edge> edgeList,
        double weight)
    {
        // Some necessary but not sufficient conditions for valid paths
        if (vertexList == null && edgeList == null)
            throw new IllegalArgumentException("Vertex list and edge list cannot both be null!");
        if (startVertex != null && vertexList != null && edgeList != null
            && edgeList.size() + 1 != vertexList.size())
            throw new IllegalArgumentException(
                "VertexList and edgeList do not correspond to the same path (cardinality of vertexList +1 must equal the cardinality of the edgeList)");
        if (startVertex == null ^ endVertex == null)
            throw new IllegalArgumentException(
                "Either the start and end vertices must both be null, or they must both be not null (one of them is null)");

        this.graph = Objects.requireNonNull(graph);
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.vertexList = vertexList;
        this.edgeList = edgeList;
        this.weight = weight;
    }

    @Override
    public Graph<Vertex, Edge> getGraph()
    {
        return graph;
    }

    @Override
    public Vertex getStartVertex()
    {
        return startVertex;
    }

    @Override
    public Vertex getEndVertex()
    {
        return endVertex;
    }

    @Override
    public List<Edge> getEdgeList()
    {
        return (edgeList != null ? edgeList : GraphPath.super.getEdgeList());
    }

    @Override
    public List<Vertex> getVertexList()
    {
        return (vertexList != null ? vertexList : GraphPath.super.getVertexList());
    }

    @Override
    public double getWeight()
    {
        return weight;
    }

    /**
     * Updates the weight of this walk
     * 
     * @param weight weight of the walk
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    @Override
    public int getLength()
    {
        if (edgeList != null)
            return edgeList.size();
        else if (vertexList != null && !vertexList.isEmpty())
            return vertexList.size() - 1;
        else
            return 0;
    }

    @Override
    public String toString()
    {
        if (vertexList != null)
            return vertexList.toString();
        else
            return edgeList.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof GraphWalk))
            return false;
        else if (this == o)
            return true;
        @SuppressWarnings("unchecked") GraphWalk<Vertex, Edge> other = (GraphWalk<Vertex, Edge>) o;
        if (this.isEmpty() && other.isEmpty())
            return true;
        if (!this.startVertex.equals(other.getStartVertex())
            || !this.endVertex.equals(other.getEndVertex()))
            return false;
        // If this path is expressed as a vertex list, we may get away by comparing the other path's
        // vertex list
        // This only works if its vertexList identifies a unique path in the graph
        if (this.edgeList == null && !other.getGraph().getType().isAllowingMultipleEdges())
            return this.vertexList.equals(other.getVertexList());
        else // Unlucky, we need to compare the edge lists,
            return this.getEdgeList().equals(other.getEdgeList());
    }

    @Override
    public int hashCode()
    {
        int hashCode = 1;
        if (isEmpty())
            return hashCode;

        hashCode = 31 * hashCode + startVertex.hashCode();
        hashCode = 31 * hashCode + endVertex.hashCode();

        if (edgeList != null)
            return 31 * hashCode + edgeList.hashCode();
        else
            return 31 * hashCode + vertexList.hashCode();
    }

    /**
     * Reverses the direction of the walk. In case of directed/mixed graphs, the arc directions will
     * be reversed. An exception is thrown if reversing an arc $(u,v)$ is impossible because arc
     * $(v,u)$ is not present in the graph. The weight of the resulting walk equals the sum of edge
     * weights in the walk.
     * 
     * @throws InvalidGraphWalkException if the path is invalid
     * @return a reversed GraphWalk
     */
    public GraphWalk<Vertex, Edge> reverse()
    {
        return this.reverse(null);
    }

    /**
     * Reverses the direction of the walk. In case of directed/mixed graphs, the arc directions will
     * be reversed. An exception is thrown if reversing an arc $(u,v)$ is impossible because arc
     * $(v,u)$ is not present in the graph.
     * 
     * @param walkWeightCalculator Function used to calculate the weight of the reversed GraphWalk
     * @throws InvalidGraphWalkException if the path is invalid
     * @return a reversed GraphWalk
     */
    public GraphWalk<Vertex, Edge> reverse(Function<GraphWalk<Vertex, Edge>, Double> walkWeightCalculator)
    {
        List<Vertex> revVertexList = null;
        List<Edge> revEdgeList = null;
        double revWeight = 0;

        if (vertexList != null) {
            revVertexList = new ArrayList<>(this.vertexList);
            Collections.reverse(revVertexList);
            if (graph.getType().isUndirected())
                revWeight = this.weight;

            // Check validity of the path. If the path is invalid, then calculating its weight may
            // result in an undefined exception.
            // If an edgeList is provided, then this check can be postponed to the construction of
            // the reversed edge list
            if (!graph.getType().isUndirected() && edgeList == null) {
                for (int i = 0; i < revVertexList.size() - 1; i++) {
                    Vertex u = revVertexList.get(i);
                    Vertex v = revVertexList.get(i + 1);
                    Edge edge = graph.getEdge(u, v);
                    if (edge == null)
                        throw new InvalidGraphWalkException(
                            "this walk cannot be reversed. The graph does not contain a reverse arc for arc "
                                + graph.getEdge(v, u));
                    else
                        revWeight += graph.getEdgeWeight(edge);
                }
            }
        }

        if (edgeList != null) {
            revEdgeList = new ArrayList<>(this.edgeList.size());

            if (graph.getType().isUndirected()) {
                revEdgeList.addAll(this.edgeList);
                Collections.reverse(revEdgeList);
                revWeight = this.weight;
            } else {
                ListIterator<Edge> listIterator = this.edgeList.listIterator(edgeList.size());
                while (listIterator.hasPrevious()) {
                    Edge e = listIterator.previous();
                    Vertex u = graph.getEdgeSource(e);
                    Vertex v = graph.getEdgeTarget(e);
                    Edge revEdge = graph.getEdge(v, u);
                    if (revEdge == null)
                        throw new InvalidGraphWalkException(
                            "this walk cannot be reversed. The graph does not contain a reverse arc for arc "
                                + e);
                    revEdgeList.add(revEdge);
                    revWeight += graph.getEdgeWeight(revEdge);
                }
            }
        }
        // Update weight of reversed walk
        GraphWalk<Vertex, Edge> gw = new GraphWalk<>(
            this.graph, this.endVertex, this.startVertex, revVertexList, revEdgeList, 0);
        if (walkWeightCalculator == null)
            gw.weight = revWeight;
        else
            gw.weight = walkWeightCalculator.apply(gw);
        return gw;
    }

    /**
     * Concatenates the specified GraphWalk to the end of this GraphWalk. This action can only be
     * performed if the end vertex of this GraphWalk is the same as the start vertex of the
     * extending GraphWalk
     * 
     * @param extension GraphPath used for the concatenation.
     * @param walkWeightCalculator Function used to calculate the weight of the GraphWalk obtained
     *        after the concatenation.
     * @return a GraphWalk that represents the concatenation of this object's walk followed by the
     *         walk specified in the extension argument.
     */
    public GraphWalk<Vertex, Edge> concat(
        GraphWalk<Vertex, Edge> extension, Function<GraphWalk<Vertex, Edge>, Double> walkWeightCalculator)
    {
        if (this.isEmpty())
            throw new IllegalArgumentException("An empty path cannot be extended");
        if (!this.endVertex.equals(extension.getStartVertex()))
            throw new IllegalArgumentException(
                "This path can only be extended by another path if the end vertex of the orginal path and the start vertex of the extension are equal.");

        List<Vertex> concatVertexList = null;
        List<Edge> concatEdgeList = null;

        if (vertexList != null) {
            concatVertexList = new ArrayList<>(this.vertexList);
            List<Vertex> vertexListExtension = extension.getVertexList();
            concatVertexList.addAll(vertexListExtension.subList(1, vertexListExtension.size()));
        }

        if (edgeList != null) {
            concatEdgeList = new ArrayList<>(this.edgeList);
            concatEdgeList.addAll(extension.getEdgeList());
        }

        GraphWalk<Vertex, Edge> gw = new GraphWalk<>(
            this.graph, startVertex, extension.getEndVertex(), concatVertexList, concatEdgeList, 0);
        gw.setWeight(walkWeightCalculator.apply(gw));
        return gw;
    }

    /**
     * Returns true if the path is an empty path, that is, a path with startVertex=endVertex=null
     * and with an empty vertex and edge list.
     * 
     * @return Returns true if the path is an empty path.
     */
    public boolean isEmpty()
    {
        return startVertex == null;
    }

    /**
     * Convenience method which verifies whether the given path is feasible wrt the input graph and
     * forms an actual path.
     * 
     * @throws InvalidGraphWalkException if the path is invalid
     */
    public void verify()
    {

        if (isEmpty()) // Empty path
            return;

        if (vertexList != null && !vertexList.isEmpty()) {
            if (!startVertex.equals(vertexList.get(0)))
                throw new InvalidGraphWalkException(
                    "The start vertex must be the first vertex in the vertex list");
            if (!endVertex.equals(vertexList.get(vertexList.size() - 1)))
                throw new InvalidGraphWalkException(
                    "The end vertex must be the last vertex in the vertex list");
            // All vertices and edges in the path must be contained in the graph
            if (!graph.vertexSet().containsAll(vertexList))
                throw new InvalidGraphWalkException(
                    "Not all vertices in the path are contained in the graph");

            if (edgeList == null) {
                // Verify sequence
                Iterator<Vertex> it = vertexList.iterator();
                Vertex u = it.next();
                while (it.hasNext()) {
                    Vertex v = it.next();
                    if (graph.getEdge(u, v) == null)
                        throw new InvalidGraphWalkException(
                            "The vertexList does not constitute to a feasible path. Edge (" + u
                                + "," + v + " does not exist in the graph.");
                    u = v;
                }
            }
        }

        if (edgeList != null && !edgeList.isEmpty()) {
            if (!Graphs.testIncidence(graph, edgeList.get(0), startVertex))
                throw new InvalidGraphWalkException(
                    "The first edge in the edge list must leave the start vertex");
            if (!graph.edgeSet().containsAll(edgeList))
                throw new InvalidGraphWalkException(
                    "Not all edges in the path are contained in the graph");

            if (vertexList == null) {
                Vertex u = startVertex;
                for (Edge edge : edgeList) {
                    if (!Graphs.testIncidence(graph, edge, u))
                        throw new InvalidGraphWalkException(
                            "The edgeList does not constitute to a feasible path. Conflicting edge: "
                                + edge);
                    u = Graphs.getOppositeVertex(graph, edge, u);
                }
                if (!u.equals(endVertex))
                    throw new InvalidGraphWalkException(
                        "The path defined by the edgeList does not end in the endVertex.");
            }
        }

        if (vertexList != null && edgeList != null) {
            // Verify that the path is an actual path in the graph
            if (edgeList.size() + 1 != vertexList.size())
                throw new InvalidGraphWalkException(
                    "VertexList and edgeList do not correspond to the same path (cardinality of vertexList +1 must equal the cardinality of the edgeList)");

            for (int i = 0; i < vertexList.size() - 1; i++) {
                Vertex u = vertexList.get(i);
                Vertex v = vertexList.get(i + 1);
                Edge edge = getEdgeList().get(i);

                if (graph.getType().isDirected()) { // Directed graph
                    if (!graph.getEdgeSource(edge).equals(u)
                        || !graph.getEdgeTarget(edge).equals(v))
                        throw new InvalidGraphWalkException(
                            "VertexList and edgeList do not form a feasible path");
                } else { // Undirected or mixed
                    if (!Graphs.testIncidence(graph, edge, u)
                        || !Graphs.getOppositeVertex(graph, edge, u).equals(v))
                        throw new InvalidGraphWalkException(
                            "VertexList and edgeList do not form a feasible path");
                }
            }
        }
    }

    /**
     * Convenience method which creates an empty walk.
     * 
     * @param graph input graph
     * @param <V> vertex type
     * @param <E> edge type
     * @return an empty walk
     */
    public static <Vertex, Edge> GraphWalk<Vertex, Edge> emptyWalk(Graph<Vertex, Edge> graph)
    {
        return new GraphWalk<>(
            graph, null, null, Collections.emptyList(), Collections.emptyList(), 0.0);
    }

    /**
     * Convenience method which creates a walk consisting of a single vertex with weight 0.0.
     * 
     * @param graph input graph
     * @param v single vertex
     * @param <V> vertex type
     * @param <E> edge type
     * @return an empty walk
     */
    public static <Vertex, Edge> GraphWalk<Vertex, Edge> singletonWalk(Graph<Vertex, Edge> graph, Vertex v)
    {
        return singletonWalk(graph, v, 0d);
    }

    /**
     * Convenience method which creates a walk consisting of a single vertex.
     * 
     * @param graph input graph
     * @param v single vertex
     * @param weight weight of the path
     * @param <V> vertex type
     * @param <E> edge type
     * @return an empty walk
     */
    public static <Vertex, Edge> GraphWalk<Vertex, Edge> singletonWalk(Graph<Vertex, Edge> graph, Vertex v, double weight)
    {
        return new GraphWalk<>(
            graph, v, v, Collections.singletonList(v), Collections.emptyList(), weight);
    }

}

/**
 * Exception thrown in the event that the path is invalid.
 */
class InvalidGraphWalkException
    extends
    RuntimeException
{
    private static final long serialVersionUID = 3811666107707436479L;

    public InvalidGraphWalkException(String message)
    {
        super(message);
    }

}
