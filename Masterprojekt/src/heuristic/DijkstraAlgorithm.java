package heuristic;

import java.util.function.Supplier;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.Vertex;

public final class DijkstraAlgorithm extends BaseShortestPathAlgorithm<Vertex, Edge> {

	private final double radius;
	private final Supplier<AddressableHeap<Double, Pair<Vertex, Edge>>> heapSupplier;

	/**
	 * Constructs a new instance of the algorithm for a given graph. The constructed
	 * algorithm will use pairing heap as a default heap implementation.
	 *
	 * @param graph the graph
	 */
	public DijkstraAlgorithm(Graph<Vertex, Edge> graph) {
		this(graph, Double.POSITIVE_INFINITY, PairingHeap::new);
	}

	/**
	 * Constructs a new instance of the algorithm for a given graph. The constructed
	 * algorithm will use pairing heap as a default heap implementation.
	 *
	 * @param graph  the graph
	 * @param radius limit on path length, or Double.POSITIVE_INFINITY for unbounded
	 *               search
	 */
	public DijkstraAlgorithm(Graph<Vertex, Edge> graph, double radius) {
		this(graph, radius, PairingHeap::new);
	}

	/**
	 * Constructs a new instance of the algorithm for a given graph. The constructed
	 * algorithm will use the heap supplied by the {@code heapSupplier}
	 *
	 * @param graph        the graph
	 * @param heapSupplier supplier of the preferable heap implementation
	 */
	public DijkstraAlgorithm(Graph<Vertex, Edge> graph,
			Supplier<AddressableHeap<Double, Pair<Vertex, Edge>>> heapSupplier) {
		this(graph, Double.POSITIVE_INFINITY, heapSupplier);
	}

	/**
	 * Constructs a new instance of the algorithm for a given graph.
	 *
	 * @param graph        the graph
	 * @param radius       limit on path length, or Double.POSITIVE_INFINITY for
	 *                     unbounded search
	 * @param heapSupplier supplier of the preferable heap implementation
	 */
	public DijkstraAlgorithm(Graph<Vertex, Edge> graph, double radius,
			Supplier<AddressableHeap<Double, Pair<Vertex, Edge>>> heapSupplier) {
		super(graph);
		if (radius < 0.0) {
			throw new IllegalArgumentException("Radius must be non-negative");
		}
		this.heapSupplier = heapSupplier;
		this.radius = radius;
	}

	/**
	 * Find a path between two vertices. For a more advanced search (e.g. limited by
	 * radius or using another heap), use the constructor instead.
	 *
	 * @param graph  the graph to be searched
	 * @param source the vertex at which the path should start
	 * @param sink   the vertex at which the path should end
	 * @param        <V> the graph vertex type
	 * @param        <E> the graph edge type
	 * @return a shortest path, or null if no path exists
	 */
	public static <V, E> GraphPath<Vertex, Edge> findPathBetween(Graph<Vertex, Edge> graph, Vertex source,
			Vertex sink) {
		return new DijkstraAlgorithm(graph).getPath(source, sink);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GraphPath<Vertex, Edge> getPath(Vertex source, Vertex sink) {
		if (!graph.containsVertex(source)) {
			throw new IllegalArgumentException(GRAPH_MUST_CONTAIN_THE_SOURCE_VERTEX);
		}
		if (!graph.containsVertex(sink)) {
			throw new IllegalArgumentException(GRAPH_MUST_CONTAIN_THE_SINK_VERTEX);
		}
		if (source.equals(sink)) {
			return createEmptyPath(source, sink);
		}

		DijkstraClosestFirstIterator<Vertex, Edge> it = new DijkstraClosestFirstIterator<>(graph, source, radius,
				heapSupplier);

		while (it.hasNext()) {
			Vertex vertex = it.next();
			if (vertex.equals(sink)) {
				break;
			}
		}

		return it.getPaths().getPath(sink);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Note that in the case of Dijkstra's algorithm it is more efficient to compute
	 * all single-source shortest paths using this method than repeatedly invoking
	 * {@link #getPath(Object, Object)} for the same source but different sink
	 * vertex.
	 */
	@Override
	public SingleSourcePaths<Vertex, Edge> getPaths(Vertex source) {
		if (!graph.containsVertex(source)) {
			throw new IllegalArgumentException(GRAPH_MUST_CONTAIN_THE_SOURCE_VERTEX);
		}

		DijkstraClosestFirstIterator<Vertex, Edge> it = new DijkstraClosestFirstIterator<>(graph, source, radius,
				heapSupplier);

		while (it.hasNext()) {
			it.next();
		}

		return it.getPaths();
	}

}
