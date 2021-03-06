package graphModel;

import java.util.ArrayList;

import ilog.concert.IloNumVar;



/**
 * Universitšt Ulm
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
 *          In the Vertex class new Vertex objects get built with unique
 *          identification numbers for each object.
 */
public class Vertex {

	int id; // vertex identification number
    double deg;
	ArrayList<IloNumVar> ro = new ArrayList<>();

	/**
	 * Constructor, creates a new vertex
	 * 
	 * @param id, the identifier number of the vertex
	 */
	public Vertex(int id) {
		this.id = id;
	}

	public Vertex() {
	}

	/**
	 * Getter method for the id
	 * 
	 * @return the id of the vertex
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setter method for the id
	 * 
	 * @param id, the identifier number of the vertex
	 */
	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<IloNumVar> getRo() {
		return this.ro;
	}

	public void setRo(ArrayList<IloNumVar> ro) {
		this.ro = ro;
	}
	
	public boolean equals(Vertex v) {
		return  this.id == v.getId();
	}
 
	public double getDeg() {
		return deg;
	}
	
	public void setDeg(double deg) {
		this.deg = deg;
	}
}