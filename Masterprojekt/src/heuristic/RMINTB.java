package heuristic;

import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;

public class RMINTB {
	
	static IloCplex cplex;

	public RMINTB() throws IloException{
		this.cplex = new IloCplex();
		
	}
	
	public void solve(Graphs g) {
		
		//initialise boolean 
		
		
		
	}
	

}
