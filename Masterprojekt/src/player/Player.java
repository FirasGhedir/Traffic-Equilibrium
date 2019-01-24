package player;

import java.util.ArrayList;
import java.util.List;

import graphModel.Edge;
import graphModel.Vertex;

/**
 * Universität Ulm
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
 *          In the Player class you can create new players and set source, sink,
 *          demand and ID
 */
public class Player {

	Vertex source;
	Vertex sink;
	double demand;
	int id;
    List<Edge> P;
    List<Double> values;
    
	public Player(int id, Vertex source, Vertex sink, double demand) {
		this.id = id;
		this.source = source;
		this.sink = sink;
		this.demand = demand;
		this.P = new ArrayList<>();
        this.values = new ArrayList<>();
	}

	public Player() {
		
	}
	public Vertex getSource() {
		return this.source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getSink() {
		return this.sink;
	}

	public void setSink(Vertex sink) {
		this.sink = sink;
	}

	public double getDemand() {
		return this.demand;
	}

	public void setDemand(double demand) {
		this.demand = demand;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Edge> getP() {
		return P;
	}

	public void setP(List<Edge> p) {
		P = p;
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}
	
	
	

}
