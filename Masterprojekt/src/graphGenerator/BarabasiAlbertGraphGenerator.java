package graphGenerator;
import java.util.*;

import graphModel.Graph;

public class BarabasiAlbertGraphGenerator<Vertex,Edge> implements GraphGenerator<Vertex,Edge,Vertex> {

	 private final Random rng;
	    private final int m0;
	    private final int m;
	    private final int n;

	    /**
	     * Constructor
	     * 
	     * @param m0 number of initial nodes
	     * @param m number of edges of each new node added during the network growth
	     * @param n final number of nodes
	     * @throws IllegalArgumentException in case of invalid parameters
	     */
	    public BarabasiAlbertGraphGenerator(int m0, int m, int n)
	    {
	        this(m0, m, n, new Random());
	    }
	    
	    /**
	     * Constructor
	     * 
	     * @param m0 number of initial nodes
	     * @param m number of edges of each new node added during the network growth
	     * @param n final number of nodes
	     * @param seed seed for the random number generator
	     * @throws IllegalArgumentException in case of invalid parameters
	     */
	    public BarabasiAlbertGraphGenerator(int m0, int m, int n, long seed)
	    {
	        this(m0, m, n, new Random(seed));
	    }

	    /**
	     * Constructor
	     * 
	     * @param m0 number of initial nodes
	     * @param m number of edges of each new node added during the network growth
	     * @param n final number of nodes
	     * @param rng the random number generator to use
	     * @throws IllegalArgumentException in case of invalid parameters
	     */
	    public BarabasiAlbertGraphGenerator(int m0, int m, int n, Random rng)
	    {
	        if (m0 < 1) {
	            throw new IllegalArgumentException("invalid initial nodes (" + m0 + " < 1)");
	        }
	        this.m0 = m0;
	        if (m <= 0) {
	            throw new IllegalArgumentException("invalid edges per node (" + m + " <= 0");
	        }
	        if (m > m0) {
	            throw new IllegalArgumentException("invalid edges per node (" + m + " > " + m0 + ")");
	        }
	        this.m = m;
	        if (n < m0) {
	            throw new IllegalArgumentException(
	                "total number of nodes must be at least equal to the initial set");
	        }
	        this.n = n;
	        this.rng = Objects.requireNonNull(rng, "Random number generator cannot be null");
	    }

	    /**
	     * Generates an instance.
	     * 
	     * @param target the target graph
	     * @param resultMap not used by this generator, can be null
	     */
	    @Override
	    public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap)
	    {
	        /*
	         * Create complete graph with m0 nodes
	         */
	        Set<Vertex> oldNodes = new HashSet<>(target.vertexSet());
	        Set<Vertex> newNodes = new HashSet<>();
	        new CompleteGraphGenerator<Vertex, Edge>(m0).generateGraph(target, resultMap);
	        target.vertexSet().stream().filter(v -> !oldNodes.contains(v)).forEach(newNodes::add);

	        List<Vertex> nodes = new ArrayList<>(n * m);
	        nodes.addAll(newNodes);
	        /*
	         * Augment node list to have node multiplicity equal to min(1,m0-1).
	         */
	        for (int i = 0; i < m0 - 2; i++) {
	            nodes.addAll(newNodes);
	        }

	        /*
	         * Grow network with preferential attachment
	         */
	        for (int i = m0; i < n; i++) {
	            Vertex v = target.addVertex();

	            List<Vertex> newEndpoints = new ArrayList<>();
	            int added = 0;
	            while (added < m) {
	                Vertex u = nodes.get(rng.nextInt(nodes.size()));
	                if (!target.containsEdge(v, u)) {
	                    target.addEdge(v, u);
	                    added++;
	                    newEndpoints.add(v);
	                    if (i > 1) {
	                        newEndpoints.add(u);
	                    }
	                }
	            }
	            nodes.addAll(newEndpoints);
	        }

	    }
	
}
