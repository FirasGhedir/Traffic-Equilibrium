package heuristic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

import graphModel.Graph;
import graphModel.Graphs;
import heuristic.ShortestPathAlgorithm.SingleSourcePaths;

public class DijkstraClosestFirstIterator<V, E> implements Iterator<V> {

	private final Graph<V, E> graph;
	private final V source;
	private final double radius;
	private final Map<V, AddressableHeap.Handle<Double, Pair<V, E>>> seen;
	private AddressableHeap<Double, Pair<V, E>> heap;

	/**
	 * 
	 * @param graph
	 * @param source
	 */
	public DijkstraClosestFirstIterator(Graph<V, E> graph, V source) {
		this(graph, source, Double.POSITIVE_INFINITY, PairingHeap::new);
	}

	/**
	 * 
	 * @param graph
	 * @param source
	 * @param radius
	 */
	public DijkstraClosestFirstIterator(Graph<V, E> graph, V source, double radius) {
		this(graph, source, radius, PairingHeap::new);
	}

	/**
	 * 
	 * @param graph
	 * @param source
	 * @param heapSupplier
	 */
	public DijkstraClosestFirstIterator(Graph<V, E> graph, V source,
			Supplier<AddressableHeap<Double, Pair<V, E>>> heapSupplier) {
		this(graph, source, Double.POSITIVE_INFINITY, heapSupplier);
	}

	/**
	 * 
	 * @param graph
	 * @param source
	 * @param radius
	 * @param heapSupplier
	 */
	public DijkstraClosestFirstIterator(Graph<V, E> graph, V source, double radius,
			Supplier<AddressableHeap<Double, Pair<V, E>>> heapSupplier) {

		this.graph = Objects.requireNonNull(graph, "Graph cannot be null");
		this.source = Objects.requireNonNull(source, "Source vertex cannot be null");
		Objects.requireNonNull(heapSupplier, "Heap supplier cannot be null");
		if (radius < 0.0) {
			throw new IllegalArgumentException("Radius must be non-negative");
		}
		this.radius = radius;
		this.seen = new HashMap<>();
		this.heap = heapSupplier.get();
		updateDistance(source, null, 0d);
	}

	@Override
	public boolean hasNext() {
		if (heap.isEmpty()) {
			return false;
		}
		AddressableHeap.Handle<Double, Pair<V, E>> vNode = heap.findMin();
		double vDistance = vNode.getKey();
		if (radius < vDistance) {
			heap.clear();
			return false;
		}
		return true;
	}

	@Override
	public V next() {

		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		AddressableHeap.Handle<Double, Pair<V, E>> vNode = heap.deleteMin();
		V v = vNode.getValue().getFirst();
		double vDistance = vNode.getKey();

		for (E e : graph.outgoingEdgesOf(v)) {
			V u = Graphs.getOppositeVertex(graph, e, v);
			double eWeight = graph.getEdgeWeight(e);
			if (eWeight < 0.0) {
				throw new IllegalArgumentException("Negative edge weight not allowed");
			}
			updateDistance(u, e, vDistance + eWeight);
		}

		return v;
	}

	public SingleSourcePaths<V, E> getPaths() {

		return new TreeSingleSourcePathsImpl<>(graph, source, getDistanceAndPredecessorMap());
	}

	public Map<V, Pair<Double, E>> getDistanceAndPredecessorMap() {

		Map<V, Pair<Double, E>> distanceAndPredecessorMap = new HashMap<>();

		for (AddressableHeap.Handle<Double, Pair<V, E>> vNode : seen.values()) {
			double vDistance = vNode.getKey();
			if (radius < vDistance) {
				continue;
			}
			V v = vNode.getValue().getFirst();
			distanceAndPredecessorMap.put(v, Pair.of(vDistance, vNode.getValue().getSecond()));
		}

		return distanceAndPredecessorMap;
	}

	private void updateDistance(V v, E e, double distance) {

		AddressableHeap.Handle<Double, Pair<V, E>> node = seen.get(v);
		if (node == null) {
			node = heap.insert(distance, Pair.of(v, e));
			seen.put(v, node);
		} else if (distance < node.getKey()) {
			node.decreaseKey(distance);
			node.setValue(Pair.of(node.getValue().getFirst(), e));
		}
	}
}
