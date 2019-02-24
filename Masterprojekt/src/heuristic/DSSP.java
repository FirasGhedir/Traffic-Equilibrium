package heuristic;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;
import ilog.cplex.IloCplexModeler; 

/**
 * University of Ulm
 * 
 * Project Algorithm Engineering-Project --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          A heuristic for solving the Dynamic Slope Scaling Procedure (DSSP)
 */
public class DSSP {

	private Graphs G;
	static ArrayList<IloNumExpr> s1 = new ArrayList<>();
	static ArrayList<IloNumExpr> s11 = new ArrayList<>();
	static ArrayList<IloNumExpr> s12 = new ArrayList<>();
	static ArrayList<IloAddable> s2 = new ArrayList<>();
	static IloCplex cplex;
	IloCplexModeler m1;

	private String DSSPResultSet;
	ByteArrayOutputStream stream = new ByteArrayOutputStream();
	String cplexSolverOutputStream;

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @throws IloException
	 *             if a CPLEX error occures
	 */
	public DSSP(Graphs graph) throws IloException {

		this.setGraph(graph);

		this.cplexSolverOutputStream = "";
		cplex = new IloCplex();
		
		m1 = new IloCplexModeler();

		// cplex.setOut(stream);

		solveDSSP(this.getGraph());
	}

	/**
	 * The main algorithm of solving the DSSP-LP
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph
	 *            the given graph
	 * @throws IloException
	 *             if a CPLEX error occurs
	 */
	public void solveDSSP(Graphs g) throws IloException {

		// --- Initializing ro ---
		for (int i = 0; i < g.getVertices().size(); i++) {
			for (int j = 0; j < g.getPlayers().size(); j++) {

				g.getVertices().get(i).getRo().add(j,
						m1.numVar(-Double.MAX_VALUE, Double.MAX_VALUE, "Ro of Player " + j + " in Vertex " + i));

			}

		}

		// --- initialising beta ---
		for (int i = 0; i < g.getEdges().size(); i++) {
			g.getEdges().get(i).setBeta(m1.numVar(0, Double.MAX_VALUE, "beta in the edge number : " + i));

		}

		// --- C*beta ---
		for (int i = 0; i < g.getEdges().size(); i++) {
			IloNumExpr tmp = m1.prod(g.getEdges().get(i).getC(), g.getEdges().get(i).getBeta());
			s1.add(tmp);
		}

		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		for (int i = 0; i < g.getEdges().size(); i++) {

			IloNumExpr tmp = m1.prod(g.getEdges().get(i).getSum(),
					m1.sum(m1.constant(g.getEdges().get(i).getCostB()),
							m1.prod(cplex.constant(g.getEdges().get(i).getCostA()), g.getEdges().get(i).getSum()),
							g.getEdges().get(i).getBeta()));
			s11.add(tmp);
		}

		IloNumExpr[] planet1 = s11.toArray(new IloNumExpr[s11.size()]);
		IloNumExpr x = m1.sum(planet1);

		for (int i = 0; i < g.getPlayers().size(); i++) {
			IloNumExpr tmp = m1.prod(m1.constant(g.getPlayers().get(i).getDemand()),
					m1.sum(g.getPlayers().get(i).getSource().getRo().get(i),
							m1.prod(-1, g.getPlayers().get(i).getSink().getRo().get(i))));
			s12.add(tmp);

		}
		IloNumExpr[] planet2 = s12.toArray(new IloNumExpr[s12.size()]);
		IloNumExpr y = m1.sum(planet2);

		IloAddable amg = m1.addEq(0, cplex.sum(x, cplex.prod(-1, y)));

		for (int i = 0; i < g.getPlayers().size(); i++) {

			for (int j = 0; j < g.getEdges().size(); j++) {

				IloNumExpr tmp = m1.sum(g.getEdges().get(j).getTo().getRo().get(i),
						m1.prod(-1, g.getEdges().get(j).getFrom().getRo().get(i)));
				IloNumExpr tmp1 = m1.sum(tmp, g.getEdges().get(j).getResult());
				IloAddable tmp2 = m1.addGe(cplex.sum(tmp1, g.getEdges().get(j).getBeta()), 0);
				s2.add(tmp2);
			}

		}
		IloAddable[] planet3 = s2.toArray(new IloAddable[s2.size()]);

		cplex.addMinimize(m1.sum(planet));
		cplex.add(planet3);
		cplex.add(amg);

		switch (String.valueOf(cplex.solve())) {
		case "true":

			System.out.println(true);

			this.setDSSPResultSet(getDSSPResultSet() + "obj: " + cplex.getObjValue() + "\n");

			for (int i = 0; i < g.getEdges().size(); i++) {

				this.setDSSPResultSet(getDSSPResultSet() + "   in the  Edge " + i + " beta would be : "
						+ cplex.getValue(g.getEdges().get(i).getBeta()) + "\n");

			}

			savevalues(cplex, g);
			cplex.clearModel();
			break;

		default:
			System.out.println(cplex.toString());

			throw new IllegalStateException("Problem not solved.");
		}
	}

	private void savevalues(IloCplex cplex2, Graphs g) throws IloException {

		for (int i = 0; i < g.getEdges().size(); i++) {
			g.getEdges().get(i).setBetta(cplex2.getValue(g.getEdges().get(i).getBeta()));
			if (g.getEdges().get(i).getBetta() > 0) {
				g.getEdges().get(i).getIlist().add(g.getEdges().get(i).getBetta());
			}
			g.getEdges().get(i).calculateL();

		}

		g.fillbeta();

	}

	/**
	 * Getter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the given graph
	 */
	public Graphs getGraph() {
		return this.G;
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
		this.G = g;
	}

	/**
	 * Gets the results of solving the DSSP-LP as a String
	 * 
	 * --------------------------------------------
	 * 
	 * @return the results of solving the DSSP-LP as a String
	 */
	public String getDSSPResultSet() {
		return this.DSSPResultSet;
	}

	/**
	 * Sets the results of solving the DSSP-LP
	 * 
	 * --------------------------------------------
	 * 
	 * @param dSSPResultSet
	 *            the results of solving the DSSP-LP
	 */
	public void setDSSPResultSet(String dSSPResultSet) {
		this.DSSPResultSet = dSSPResultSet;
	}

	/**
	 * The toString() method returns the string representation of the object
	 * CharacteristicsCalculation.
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
		return (cplexSolverOutputStream + "\n" + dashedLimiter + "\n\n" + this.getDSSPResultSet());
	}
}
