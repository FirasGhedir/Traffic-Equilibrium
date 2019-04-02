package heuristic;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
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
 *          This class contains everything for solving the system optimal flow
 *          (social optimum)
 */
public class SocialOptimum {

	private Graphs G;
	static ArrayList<IloNumExpr> s1 = new ArrayList<>();
	static ArrayList<IloAddable> s2 = new ArrayList<>();
	static IloCplex cplex;
	private String socialOptimumResultSet;
	ByteArrayOutputStream stream = new ByteArrayOutputStream();
	String cplexSolverOutputStream;

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @throws IloException if a CPLEX error occures
	 */
	public SocialOptimum(Graphs graph) throws IloException {

		this.setGraph(graph);
		this.setSocialOptimumResultSet("");

		this.cplexSolverOutputStream = "";
		cplex = new IloCplex();
		cplex.setOut(stream); // store the cplex solver Outputstream in Bytestream

		//solveDSSP(this.getGraph());
	}

	/**
	 * The main algorithm of solving the system optimal flow
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph the given graph
	 * @throws IloException if a CPLEX error occures
	 */
	public void solveDSSP(Graphs graph) throws IloException {

		for (int i = 0; i < graph.getEdges().size(); i++) {
			for (int j = 0; j < graph.getPlayers().size(); j++) {
				graph.getEdges().get(i).getPlayers()
						.add(cplex.numVar(0, Double.MAX_VALUE, "PlayerNr : " + j + " in Edge : " + i));
			}
		}

		for (int i = 0; i < graph.getEdges().size(); i++) {
			IloNumExpr tmp = cplex.sum(graph.getEdges().get(i).convertarray());
			IloNumExpr tmp1 = cplex.sum(cplex.constant(graph.getEdges().get(i).getCostB()),
					cplex.prod(cplex.constant(graph.getEdges().get(i).getCostA()), tmp));
			IloNumExpr tmp2 = cplex.prod(tmp, tmp1);
			s1.add(tmp2);

		}

		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		ArrayList<IloNumVar> out = new ArrayList<>();
		ArrayList<IloNumVar> in = new ArrayList<>();

		int count = 0;
		for (int i = 0; i < graph.getPlayers().size(); i++) {
			for (int j = 0; j < graph.getVertices().size(); j++) {
				in.clear();
				out.clear();
				for (int k = 0; k < graph.getEdges().size(); k++) {
					if (graph.getEdges().get(k).getFrom().equals(graph.getVertices().get(j))) {
						out.add(graph.getEdges().get(k).getPlayers().get(i));

					}

					if (graph.getEdges().get(k).getTo().equals(graph.getVertices().get(j))) {
						in.add(graph.getEdges().get(k).getPlayers().get(i));

					}

				}

				IloNumVar[] x1 = in.toArray(new IloNumVar[in.size()]);
				IloNumVar[] y1 = out.toArray(new IloNumVar[out.size()]);

				IloNumExpr samara = cplex.sum(cplex.sum(y1), cplex.prod(-1, cplex.sum(x1)));

				IloAddable amg;
				if (graph.getVertices().get(j).equals(graph.getPlayers().get(i).getSource())) {
					amg = cplex.addEq(graph.getPlayers().get(i).getDemand(), samara);
				}

				else if (graph.getVertices().get(j).equals(graph.getPlayers().get(i).getSink())) {
					amg = cplex.addEq(-graph.getPlayers().get(i).getDemand(), samara);

				}

				else {
					amg = cplex.addEq(0, samara);

				}

				s2.add(count, amg);
				count++;
			}

		}

		IloAddable[] beta = s2.toArray(new IloAddable[s2.size()]);

		cplex.addMinimize(cplex.sum(planet));
		cplex.add(beta);

		switch (String.valueOf(cplex.solve())) {
		case "true":

			this.setSocialOptimumResultSet(getSocialOptimumResultSet() + "obj: " + cplex.getObjValue() + "\n--\n");

			for (int i = 0; i < graph.getEdges().size(); i++) {
				for (int j = 0; j < graph.getEdges().get(i).getPlayers().size(); j++) {

//					setSocialOptimumResultSet(
//							getSocialOptimumResultSet() + graph.getEdges().get(i).getPlayers().get(j).toString() + " : "
//									+ cplex.getValue(graph.getEdges().get(i).getPlayers().get(j)) + "\n");
					System.out.println(cplex.getValue(graph.getEdges().get(i).getPlayers().get(j)));
              if(cplex.getValue(graph.getEdges().get(i).getPlayers().get(j))> 0.000001) {
					graph.getEdges().get(i).getValues()
							.add(cplex.getValue(graph.getEdges().get(i).getPlayers().get(j)));

				}
              else {
            	  graph.getEdges().get(i).getValues()
					.add(0.0);
              }
			}
			}
			break;

		default:

			throw new IllegalStateException("Problem not solved.");
		}

		for (int i = 0; i < graph.getEdges().size(); i++) {
			double c = 0;
			for (int j = 0; j < graph.getEdges().get(i).getValues().size(); j++) {
				c += graph.getEdges().get(i).getValues().get(j);
			}

			graph.getEdges().get(i).setSum(c);

		}

		List<Double> minimum = new ArrayList<>();
		for (int i = 0; i < graph.getEdges().size(); i++) {
			if (graph.getEdges().get(i).getSum() > 0) {
				graph.getEdges().get(i)
						.setC(1 / (graph.getEdges().get(i).getCostA() * graph.getEdges().get(i).getSum()));
				minimum.add(graph.getEdges().get(i).getCostA() * graph.getEdges().get(i).getSum());
			}
		}

		double minimito = Collections.min(minimum);

		for (int i = 0; i < graph.getEdges().size(); i++) {
			if (graph.getEdges().get(i).getC() == 0) {

				graph.getEdges().get(i).setC(1 / (2 * minimito));
			}
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
		return this.G;
	}

	/**
	 * Setter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param g the given graph
	 */
	public void setGraph(Graphs g) {
		this.G = g;
	}

	/**
	 * Gets the social optimum result set
	 * 
	 * --------------------------------------------
	 * 
	 * @return the results of solving the system optimal flow as a String
	 */
	public String getSocialOptimumResultSet() {
		return this.socialOptimumResultSet;
	}

	/**
	 * Sets the social optimum result set
	 * 
	 * --------------------------------------------
	 * 
	 * @param socialOptimumResultSet the results of solving the system optimal flow
	 */
	public void setSocialOptimumResultSet(String socialOptimumResultSet) {
		this.socialOptimumResultSet = socialOptimumResultSet;
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
		return (cplexSolverOutputStream + "\n" + dashedLimiter + "\n\n" + this.getSocialOptimumResultSet());
	}
}
