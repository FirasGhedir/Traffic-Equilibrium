package util;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.Vertex;
import util.ShortestPathAlgorithm.SingleSourcePaths;

@SuppressWarnings("hiding")
public class ListSingleSourcePathsImpl<Vertex, Edge> implements SingleSourcePaths<Vertex, Edge>, Serializable {
	private static final long serialVersionUID = -60070018446561686L;

	/**
	 * The graph
	 */
	protected Graph<Vertex, Edge> graph;

	/**
	 * The source vertex of all paths
	 */
	protected Vertex source;

	/**
	 * One path per vertex
	 */
	protected Map<Vertex, GraphPath<Vertex, Edge>> paths;

	/**
	 * Construct a new instance.
	 * 
	 * @param graph
	 *            the graph
	 * @param source
	 *            the source vertex
	 * @param paths
	 *            one path per target vertex
	 */
	public ListSingleSourcePathsImpl(Graph<Vertex, Edge> graph, Vertex source,
			Map<Vertex, GraphPath<Vertex, Edge>> paths) {
		this.graph = Objects.requireNonNull(graph, "Graph is null");
		this.source = Objects.requireNonNull(source, "Source vertex is null");
		this.paths = Objects.requireNonNull(paths, "Paths are null");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Graph<Vertex, Edge> getGraph() {
		return graph;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vertex getSourceVertex() {
		return source;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWeight(Vertex targetVertex) {
		GraphPath<Vertex, Edge> p = paths.get(targetVertex);
		if (p == null) {
			if (source.equals(targetVertex)) {
				return 0d;
			} else {
				return Double.POSITIVE_INFINITY;
			}
		} else {
			return p.getWeight();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GraphPath<Vertex, Edge> getPath(Vertex targetVertex) {
		GraphPath<Vertex, Edge> p = paths.get(targetVertex);
		if (p == null) {
			if (source.equals(targetVertex)) {
				return GraphWalk.singletonWalk(graph, source, 0d);
			} else {
				return null;
			}
		} else {
			return p;
		}
	}

}
