package graphModel;

import ilog.concert.IloNumVar;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
 *          In the Edge class new Edge objects get built with start vertices,
 *          end vertices and edge weights.
 */
public class Edge {

	public Vertex from; // Start vertex
	public Vertex to; // End vertex
	double weight; // edge weight
	double costA;
	double costB; // cost
	double c; // C
	@JsonIgnore
	IloNumVar beta;
	double Result;
	@JsonIgnore
	IloNumVar y;
	double l; // the real weight after finishing the algorithm
	@JsonIgnore
	ArrayList<IloNumVar> players = new ArrayList<IloNumVar>();
	ArrayList<Double> values = new ArrayList<>();
	ArrayList<Double> ilist = new ArrayList<>();
	double sum;
    double betta;
	/**
	 * Constructor to create a new edge
	 * 
	 * --------------------------------------------
	 * 
	 * @param from   where the edge of the vertex starts
	 * @param to     where the edge of the vertex ends
	 * @param weight the value of the edge weight
	 */
	
	public Edge(Vertex from, Vertex to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.l = weight;
	}
	
	public Edge() {
		
	}
	
	

	/**
	 * Getter method for the 'from' value
	 * 
	 * @return the beginning node of the vertex
	 */
	public Vertex getFrom() {
		return this.from;
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
		return this.to;
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
		return this.weight;
	}

	/**
	 * Setter method for the 'weight' value
	 * 
	 * @param weight, the edge weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public ArrayList<IloNumVar> getPlayers() {
		return this.players;
	}

	public void setPlayers(ArrayList<IloNumVar> players) {
		this.players = players;
	}

	public double getCostA() {
		return this.costA;
	}

	public void setCostA(double d) {
		this.costA = d;
	}

	public double getCostB() {
		return this.costB;
	}

	public void setCostB(double d) {
		this.costB = d;
	}

	public IloNumVar[] convertarray() {
		return players.toArray(new IloNumVar[players.size()]);
	}

	public double getC() {
		return this.c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public ArrayList<Double> getValues() {
		return this.values;
	}

	public void setValues(ArrayList<Double> values) {
		this.values = values;
	}

	public double getSum() {
		return this.sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public IloNumVar getBeta() {
		return this.beta;
	}

	public void setBeta(IloNumVar beta) {
		this.beta = beta;
	}

	public double getResult() {
		this.Result = this.getSum() * this.getCostA() + this.getCostB();
		return this.Result;
	}

	public void setResult(double result) {
		this.Result = result;
	}

	public IloNumVar getY() {
		return y;
	}

	public void setY(IloNumVar y) {
		this.y = y;
	}

	public double getL() {
		return l;
	}

	public void setL(double l) {
		this.l = l;
	}
	
	public void calculateL() {
		
		double x = getCostA()*getSum() + getCostB() + getBetta() ;
		setL(x);
	}

	public double getBetta() {
		return betta;
	}

	public void setBetta(double betta) {
		this.betta = betta;
	}



	public ArrayList<Double> getIlist() {
		return ilist;
	}



	public void setIlist(ArrayList<Double> ilist) {
		this.ilist = ilist;
	}
	
	
	

}
