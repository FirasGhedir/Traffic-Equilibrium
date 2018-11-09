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
public class Vertex {

	int id;

	/**
	 * Constructor, creates a new vertex
	 * 
	 * @param id, the identifier number of the vertex
	 */
	public Vertex(int id) {
		this.id = id;
	}

	/**
	 * Getter method for the id
	 * 
	 * @return the id of the vertex
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for the id
	 * 
	 * @param id, the identifier number of the vertex
	 */
	public void setId(int id) {
		this.id = id;
	}

}
