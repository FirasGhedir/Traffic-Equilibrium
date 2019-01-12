package heuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import graphModel.Graph;
import graphModel.Graphs;

public interface GraphPath<V,E> {
	/**
     * Returns the graph over which this path is defined. The path may also be valid with respect to
     * other graphs.
     *
     * @return the containing graph
     */
    Graph<V, E> getGraph();

    /**
     * Returns the start vertex in the path.
     *
     * @return the start vertex
     */
    V getStartVertex();

    /**
     * Returns the end vertex in the path.
     *
     * @return the end vertex
     */
    V getEndVertex();

    /**
     * Returns the edges making up the path. The first edge in this path is incident to the start
     * vertex. The last edge is incident to the end vertex. The vertices along the path can be
     * obtained by traversing from the start vertex, finding its opposite across the first edge, and
     * then doing the same successively across subsequent edges; see {@link #getVertexList()}.
     *
     * <p>
     * Whether or not the returned edge list is modifiable depends on the path implementation.
     *
     * @return list of edges traversed by the path
     */
    default List<E> getEdgeList()
    {
        List<V> vertexList = this.getVertexList();
        if (vertexList.size() < 2)
            return Collections.emptyList();

        Graph<V, E> g = this.getGraph();
        List<E> edgeList = new ArrayList<>();
        Iterator<V> vertexIterator = vertexList.iterator();
        V u = vertexIterator.next();
        while (vertexIterator.hasNext()) {
            V v = vertexIterator.next();
            edgeList.add(g.getEdge(u, v));
            u = v;
        }
        return edgeList;
    }

    /**
     * Returns the path as a sequence of vertices.
     *
     * @return path, denoted by a list of vertices
     */
    default List<V> getVertexList()
    {
        List<E> edgeList = this.getEdgeList();

        if (edgeList.isEmpty()) {
            V startVertex = getStartVertex();
            if (startVertex != null && startVertex.equals(getEndVertex())) {
                return Collections.singletonList(startVertex);
            } else {
                return Collections.emptyList();
            }
        }

        Graph<V, E> g = this.getGraph();
        List<V> list = new ArrayList<>();
        V v = this.getStartVertex();
        list.add(v);
        for (E e : edgeList) {
            v = Graphs.getOppositeVertex(g, e, v);
            list.add(v);
        }
        return list;
    }

    /**
     * Returns the weight assigned to the path. Typically, this will be the sum of the weights of
     * the edge list entries (as defined by the containing graph), but some path implementations may
     * use other definitions.
     *
     * @return the weight of the path
     */
    double getWeight();

    /**
     * Returns the length of the path, measured in the number of edges.
     * 
     * @return the length of the path, measured in the number of edges
     */
    default int getLength()
    {
        return getEdgeList().size();
    }
}
