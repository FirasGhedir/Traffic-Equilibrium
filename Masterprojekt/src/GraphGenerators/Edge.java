package GraphGenerators;

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 */
public class Edge {

	Vertex from;
	Vertex to;
	double weight;

	/**
	 * Constructor to create a new edge
	 * 
	 * @param from   where the edge of the vertex starts
	 * @param to     where the edge of the vertex ends
	 * @param weight the value of the edge weight
	 */
	public Edge(Vertex from, Vertex to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	/**
	 * Getter method for the 'from' value
	 * 
	 * @return the beginning node of the vertex
	 */
	public Vertex getFrom() {
		return from;
	}

	/**
	 * Setter method for the 'from' value
	 * 
	 * @param from, the beginning node of the vertex
	 */
	public void setFrom(Vertex from) {
		this.from = from;
	}

	/**
	 * Getter method for the 'to' value
	 * 
	 * @return the node at the end of the vertex
	 */
	public Vertex getTo() {
		return to;
	}

	/**
	 * Setter method for the 'to' value
	 * 
	 * @param to, the node at the end of the vertex
	 */
	public void setTo(Vertex to) {
		this.to = to;
	}

	/**
	 * Getter method for the 'weight' value
	 * 
	 * @return the edge weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Setter method for the 'weight' value
	 * 
	 * @param weight, the edge weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
