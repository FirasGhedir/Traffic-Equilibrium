package graphModel;

import ilog.concert.IloNumVar;

import java.util.ArrayList;

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
	int costA, costB; // cost
	double c; // C
	IloNumVar beta;
	double Result;
	IloNumVar y;

	ArrayList<IloNumVar> players = new ArrayList<IloNumVar>();
	ArrayList<Double> values = new ArrayList<>();
	double sum;

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
		this.weight = weight;
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

	public int getCostA() {
		return this.costA;
	}

	public void setCostA(int costA) {
		this.costA = costA;
	}

	public int getCostB() {
		return this.costB;
	}

	public void setCostB(int costB) {
		this.costB = costB;
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
	
	

}
