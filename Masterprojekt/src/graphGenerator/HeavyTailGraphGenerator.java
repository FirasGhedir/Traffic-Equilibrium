package graphGenerator;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.Vertex;

import java.util.*;

/**
 * University Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          In the class HeavyTailGraphGenerator you can create a scale-free
 *          network graph using a preferential attachment mechanism
 * 
 *          See more:
 *          https://en.wikipedia.org/wiki/Barab%C3%A1si%E2%80%93Albert_model
 */
public class HeavyTailGraphGenerator  implements GraphGenerator<Vertex, Edge, Vertex> {

	// === relevant parameters ===
	private Random random;
	private int numberInitialVertices;
	private int numberNewEdges;
	private int sumNodes;

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @param numberInitialVertices
	 * @param numberNewEdges
	 * @param sumNodes
	 * @param random
	 */
	public HeavyTailGraphGenerator(int numberInitialVertices, int numberNewEdges, int sumNodes, Random random) {

		if (isValid(numberInitialVertices, numberNewEdges, sumNodes, random)) {

			setNumberInitialVertices(numberInitialVertices);
			setNumberNewEdges(numberNewEdges);
			setSumNodes(sumNodes);
			setRandom(Objects.requireNonNull(random, "Cannot be null"));
		}
	}

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @param numberInitialVertices
	 * @param numberNewEdges
	 * @param sumNodes
	 */
	public HeavyTailGraphGenerator(int numberInitialVertices, int numberNewEdges, int sumNodes) {

		this(numberInitialVertices, numberNewEdges, sumNodes, new Random());
	}

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @param numberInitialVertices
	 * @param numberNewEdges
	 * @param sumNodes
	 * @param seed
	 */
	public HeavyTailGraphGenerator(int numberInitialVertices, int numberNewEdges, int sumNodes, long seed) {

		this(numberInitialVertices, numberNewEdges, sumNodes, new Random(seed));
	}

	/**
	 * Method which checks if input paramteres are valid
	 * 
	 * --------------------------------------------
	 * 
	 * @param numberInitialVertices
	 * @param numberNewEdges
	 * @param sumNodes
	 * @param random
	 * @return
	 */
	public boolean isValid(int numberInitialVertices, int numberNewEdges, int sumNodes, Random random) {

		switch (String.valueOf(numberInitialVertices < 1 || numberNewEdges <= 0
				|| numberInitialVertices < numberNewEdges || sumNodes < numberInitialVertices)) {
		case "true":

			if (numberInitialVertices < 1) {
				System.err.printf("invalid value; %d has to be < 1", numberInitialVertices);
				return false;
			}

			if (numberNewEdges <= 0) {
				System.err.printf("invalid value; %d has to be <= 0", numberNewEdges);
				return false;
			}

			if (numberInitialVertices < numberNewEdges) {
				System.err.printf("invalid value; %d has to be smaller than %d", numberInitialVertices, numberNewEdges);
				return false;
			}

			if (sumNodes < numberInitialVertices) {
				System.err.printf("invalid value; %d has to be smaller than %d", sumNodes, numberInitialVertices);
				return false;
			}

			break;

		}

		return true;

	}

	/**
	 * Method for creating Heavy Tail Graph instance
	 */
	@Override
	public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap) {

		Set<Vertex> oldNodes = new HashSet<>(target.vertexSet());
		Set<Vertex> newNodes = new HashSet<>();

		new CompleteGraphGenerator(numberInitialVertices).generateGraph(target, resultMap);
		target.vertexSet().stream().filter(v -> !oldNodes.contains(v)).forEach(newNodes::add);

		List<Vertex> nodes = new ArrayList<>(sumNodes * numberNewEdges);
		nodes.addAll(newNodes);

		for (int i = 0; i < numberInitialVertices - 2; i++) {
			nodes.addAll(newNodes);
		}

		for (int i = numberInitialVertices; i < sumNodes; i++) {
			//Vertex v = target.addVertex();
            Vertex v = new Vertex(i);
            target.addVertex(v);
			List<Vertex> newEndpoints = new ArrayList<>();
			int added = 0;
			while (added < numberNewEdges) {
				Vertex u = nodes.get(random.nextInt(nodes.size()));
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

	/**
	 * 
	 * @return
	 */
	public int getNumberInitialVertices() {
		return this.numberInitialVertices;
	}

	/**
	 * 
	 * @param numberInitialVertices
	 */
	public void setNumberInitialVertices(int numberInitialVertices) {
		this.numberInitialVertices = numberInitialVertices;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberNewEdges() {
		return this.numberNewEdges;
	}

	/**
	 * 
	 * @param numberNewEdges
	 */
	public void setNumberNewEdges(int numberNewEdges) {
		this.numberNewEdges = numberNewEdges;
	}

	/**
	 * 
	 * @return
	 */
	public int getSumNodes() {
		return this.sumNodes;
	}

	/**
	 * 
	 * @param sumNodes
	 */
	public void setSumNodes(int sumNodes) {
		this.sumNodes = sumNodes;
	}

	/**
	 * 
	 * @return
	 */
	public Random getRandom() {
		return this.random;
	}

	/**
	 * 
	 * @param random
	 */
	public void setRandom(Random random) {
		this.random = random;
	}
}
