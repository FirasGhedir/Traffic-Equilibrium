package GraphGenerators;

import java.util.*;

public class RandomGraphGenerator<V, E> implements GraphGenerator<V, E, V> {

	private final int n; // the number of nodes
	private final Random rng; // the random number generator
	private static final boolean DEFAULT_ALLOW_LOOPS = false;
	private final double p; // the probability of edges
	private final boolean createLoops;

	/**
	 * Constructor to create a random graph generator without self-loops.
	 * 
	 * @param n
	 *            the number of nodes
	 * @param p
	 *            the edge probability
	 */
	public RandomGraphGenerator(int n, double p) {
		this(n, p, new Random(), DEFAULT_ALLOW_LOOPS);
	}

	/**
	 * Constructor to create a random graph generator without self-loops.
	 * 
	 * @param n
	 *            the number of nodes
	 * @param p
	 *            the edge probability
	 * @param seed
	 *            seed for the random number generator
	 */
	public RandomGraphGenerator(int n, double p, long seed) {
		this(n, p, new Random(seed), DEFAULT_ALLOW_LOOPS);
	}

	/**
	 * Constructor to create a random graph generator without self-loops.
	 * 
	 * @param n
	 *            the number of nodes
	 * @param p
	 *            the edge probability
	 * @param seed
	 *            seed for the random number generator
	 * @param createLoops
	 *            whether the generated graph may create loops
	 */
	public RandomGraphGenerator(int n, double p, long seed, boolean createLoops) {
		this(n, p, new Random(seed), createLoops);
	}

	/**
	 * Constructor to create a random graph generator without self-loops.
	 * 
	 * @param n
	 *            the number of nodes
	 * @param p
	 *            the edge probability
	 * @param rng
	 *            the random number generator to use
	 * @param createLoops
	 *            whether the generated graph may create loops
	 */
	public RandomGraphGenerator(int n, double p, Random rng, boolean createLoops) {
		if (n < 0) {
			throw new IllegalArgumentException("number of vertices must be non-negative");
		}
		this.n = n;
		if (p < 0.0 || p > 1.0) {
			throw new IllegalArgumentException("not valid probability of edge existence");
		}
		this.p = p;
		this.rng = Objects.requireNonNull(rng);
		this.createLoops = createLoops;
	}

	/**
	 * Generates a random graph based on the $G(n, p)$ model.
	 * 
	 * @param target
	 *            the target graph
	 * @param resultMap
	 *            not used by this generator, can be null
	 */
	@Override
	public void generateGraph(Graph<V, E> target, Map<String, V> resultMap) {
		// special case
		if (n == 0) {
			return;
		}

		// check whether to also create loops
		// if (createLoops && !target.getType().size()) {
		// throw new IllegalArgumentException("Provided graph does not support
		// self-loops");
		// }

		// create vertices
		int previousVertexSetSize = target.vertexSet().size();
		Map<Integer, V> vertices = new HashMap<>(n);
		for (int i = 0; i < n; i++) {
			vertices.put(i, target.addVertex());
		}

		if (target.vertexSet().size() != previousVertexSetSize + n) {
			throw new IllegalArgumentException("Vertex factory did not produce " + n + " distinct vertices.");
		}

		// check if graph is directed
		boolean isDirected = target.getType().isDirected();

		// create edges
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {

				if (i == j) {
					if (!createLoops) {
						// no self-loops
						continue;
					}
				}

				V s = null;
				V t = null;

				// s->t
				if (rng.nextDouble() < p) {
					s = vertices.get(i);
					t = vertices.get(j);
					target.addEdge(s, t);
				}

				if (isDirected) {
					// t->s
					if (rng.nextDouble() < p) {
						if (s == null) {
							s = vertices.get(i);
							t = vertices.get(j);
						}
						target.addEdge(t, s);
					}
				}
			}
		}

	}

}
