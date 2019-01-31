package heuristic;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import graphModel.Edge;
import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;

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
 *          Solver for the Restricted Minimum Tollbooth Problem (RMINTB)
 */
public class RMINTB {

	Graphs g;
	int i = 0, l = 0;
	static IloCplex cplex;
	static ArrayList<IloAddable> s2 = new ArrayList<>();
	static ArrayList<IloNumExpr> s11 = new ArrayList<>();
	static ArrayList<IloNumExpr> s12 = new ArrayList<>();
	static ArrayList<IloNumExpr> s13 = new ArrayList<>();
	static ArrayList<Edge> omega = new ArrayList<>();
	private String rmintbResultSet;
	ByteArrayOutputStream stream = new ByteArrayOutputStream();
	String cplexSolverOutputStream;
	ArrayList<Double> tempo = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @throws IloException
	 *             if a CPLEX error occures
	 */
	public RMINTB(Graphs g) throws IloException {

		this.setGraph(g);
		this.setRMINTBResultSet("");

		cplex = new IloCplex();
		cplex.setOut(stream);

	}

	/**
	 * The main algorithm of solving the RMINTB
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph
	 *            the given graph
	 * @throws IloException
	 *             if a CPLEX error occures
	 */
	public boolean solveRMINTB(Graphs g) throws IloException {

		// IloNumVar[] planet = new IloNumVar[g.getEdges().size()];

		// // --- initialising beta ---
		// for (int i = 0; i < g.getEdges().size(); i++) {
		// g.getEdges().get(i).setBeta(cplex.numVar(0, Double.MAX_VALUE, "beta in the
		// edge number : " + i));
		//
		// }
		//
		// // initialise boolean
		// for (int i = 0; i < g.getEdges().size(); i++) {
		//
		// g.getEdges().get(i)
		// .setY(cplex.numVar(0, 1, IloNumVarType.Bool, "is There a Toll station in the
		// edge number : " + i));
		//
		// planet[i] = g.getEdges().get(i).getY();
		//
		// }

		for (int i = 0; i < g.getPlayers().size(); i++) {

			for (int j = 0; j < g.getEdges().size(); j++) {

				IloNumExpr tmp = cplex.sum(g.getEdges().get(j).getTo().getRo().get(i),
						cplex.prod(-1, g.getEdges().get(j).getFrom().getRo().get(i)));

				IloNumExpr tmp1 = cplex.sum(tmp, g.getEdges().get(j).getResult());
				IloAddable tmp2 = cplex.addGe(cplex.sum(tmp1, g.getEdges().get(j).getBetta()), 0);
				s2.add(tmp2);
			}

		}

		IloAddable[] planet1 = s2.toArray(new IloAddable[s2.size()]);

		for (int i = 0; i < g.getEdges().size(); i++) {

			IloNumExpr tmp = cplex.prod(g.getEdges().get(i).getSum(),
					cplex.sum(cplex.constant(g.getEdges().get(i).getCostB()),
							cplex.prod(cplex.constant(g.getEdges().get(i).getCostA()), g.getEdges().get(i).getSum()),
							cplex.constant(g.getEdges().get(i).getBetta())));
			s11.add(tmp);
		}

		IloNumExpr[] planet2 = s11.toArray(new IloNumExpr[s11.size()]);
		IloNumExpr x = cplex.sum(planet2);

		for (int i = 0; i < g.getPlayers().size(); i++) {
			IloNumExpr tmp = cplex.prod(cplex.constant(g.getPlayers().get(i).getDemand()),
					cplex.sum(g.getPlayers().get(i).getSource().getRo().get(i),
							cplex.prod(-1, g.getPlayers().get(i).getSink().getRo().get(i))));
			s12.add(tmp);

		}
		IloNumExpr[] planet3 = s12.toArray(new IloNumExpr[s12.size()]);
		IloNumExpr y = cplex.sum(planet3);

		IloAddable amg = cplex.addEq(0, cplex.sum(x, cplex.prod(-1, y)));

		int m = 100;

		for (int i = 0; i < g.getEdges().size(); i++) {
			IloNumExpr tmp = null;
			if(g.getEdges().get(i).getBetta()>0) {
			 tmp = cplex.addGe(
					cplex.sum(cplex.constant(m), -1 * g.getEdges().get(i).getBetta()), 0);}
			else {
				cplex.sum(cplex.constant(0),g.getEdges().get(i).getBetta());
			}
			s13.add(tmp);
		}
		IloAddable[] planet4 = s13.toArray(new IloAddable[s13.size()]);

		cplex.add(planet1);
		cplex.add(planet4);
		cplex.add(amg);
		cplex.minimize();

		switch (String.valueOf(cplex.solve())) {
		case "true":

			this.setRMINTBResultSet(getRMINTBResultSet() + "obj: " + cplex.getObjValue() + "\n");
			return true;

		default:
			return false;
		// throw new IllegalStateException("Problem not solved.");
		}

	}

	public void run() throws IloException {
       
		step1();
		

	}

	public void step1() throws IloException {
		DSSP step1 = new DSSP(this.g);
		step1.solveDSSP(g);
		l = 1;
        step2(i,true);
	}

	public void step2(int s,boolean xx) throws IloException {
       if(xx) {
		for (int i = 0; i < g.getEdges().size(); i++) {
			if (g.getEdges().get(i).getBetta() > 0) {
				omega.add(g.getEdges().get(i));
			}

		}
       }

		for (int i = 0; i < g.getEdges().size(); i++) {
			tempo.add(new Double(g.getEdges().get(i).getBetta()));
		}
		omega.get(s).setBetta(0);
		omega.remove(s);
		step3(g);
		

	}

	private void step3(Graphs g2) throws IloException {

		if (solveRMINTB(g2) == true) {
			System.out.println("Danielooooo");
			int countnew = 0;
			int countold = 0;
			for (int i = 0; i < g2.getEdges().size(); i++) {
				if (omega.contains(g2.getEdges().get(i)))
					countnew++;
				if (g2.getEdges().get(i).getBetta() > 0)
					countold++;
			}

			if (countnew < countold)
				step2(i,false);
		} else
			step4();
	}

	private void step4() throws IloException {
		if (omega.size() == 0)
			end();
		else {
			i++;
			step3(g);
		}

	}

	private void end() {
 
		for (int i = 0; i < g.getEdges().size(); i++) {
			System.out.println("In edge number :" + i + " beta would be " + g.getEdges().get(i).getBetta());
		}

		
		
	}

	/**
	 * Getter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the given graph
	 */
	public Graphs getGraph() {
		return this.g;
	}

	/**
	 * Setter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param g
	 *            the given graph
	 */
	public void setGraph(Graphs g) {
		this.g = g;
	}

	/**
	 * Gets the results of solving the DSSP-LP as a String
	 * 
	 * --------------------------------------------
	 * 
	 * @return the results of solving the DSSP-LP as a String
	 */
	public String getRMINTBResultSet() {
		return this.rmintbResultSet;
	}

	/**
	 * Sets the results of solving the DSSP-LP
	 * 
	 * --------------------------------------------
	 * 
	 * @param dSSPResultSet
	 *            the results of solving the DSSP-LP
	 */
	public void setRMINTBResultSet(String rmintbResultSet) {
		this.rmintbResultSet = rmintbResultSet;
	}

	/**
	 * The toString() method returns the string representation of the object RMINTB
	 */
	@Override
	public String toString() {

		this.cplexSolverOutputStream = new String(stream.toByteArray());

		/*
		 * print title
		 */
		String leftAlignFormat = "|| %-10s %-70s  ||%n";
		String limiter = "++========================================================================================++";
		String dashedLimiter = "++----------------------------------------------------------------------------------------++";
		System.out.format("%n");
		System.out.format(limiter + "%n");
		System.out.format(leftAlignFormat, "\t", "");
		System.out.format(leftAlignFormat, "\t",
				this.getClass().getSimpleName() + " (" + this.getClass().getName() + ")");
		System.out.format(leftAlignFormat, "\t", "");
		System.out.format(limiter + "%n%n");

		/*
		 * return the string representation of the object
		 */
		return (cplexSolverOutputStream + "\n" + dashedLimiter + "\n\n" + this.getRMINTBResultSet());
	}

}
