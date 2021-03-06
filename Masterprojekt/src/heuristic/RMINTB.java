package heuristic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import bai_A.Mintb_FC;
import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;
import ilog.cplex.IloCplex.UnknownObjectException;

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
 *          Solver for the Restricted Minimum Tollbooth Problem (RMINTB)
 */
public class RMINTB {

	Graphs g;
	IloCplex cplex;
	List<Integer> tolls;
	List<Integer> tmptoll;
	List<Integer> bestsolution;
	List<Integer> omega;
	int l = 0;
	int count = 0;
	long timer;
	long start;
	long end;
	SocialOptimum social;

	public RMINTB(Graphs graph) throws IloException {
		this.g = graph;
		cplex = new IloCplex();
		tolls = new ArrayList<>();
		bestsolution = new ArrayList<>();
		omega = new ArrayList<>();
		tmptoll = new ArrayList<>();
		social = new SocialOptimum(graph);
	}

	public void solve() throws IloException {
		start = System.currentTimeMillis();
		social.solveDSSP(g);
		Mintb_FC dssp = new Mintb_FC(g);
		dssp.run(g);
		l = 1;
		step2();
	}

	private void step2() throws IloException {
		if (l > 1) {
			omega.clear();
			for (int i = 0; i < bestsolution.size(); i++) {
				if (bestsolution.get(i) > 0) {
					omega.add(i);
				}

			}
		} else {
			for (int i = 0; i < this.g.getEdges().size(); i++) {
				if (this.g.getEdges().get(i).getBetta() > 0) {
					tolls.add(i, 1);
					omega.add(i);
				} else {
					tolls.add(i, 0);
				}

			}
		}
		count = 0;
		step3();
	}

	private void step3() throws IloException {

		tmptoll = new ArrayList<>(tolls);
		System.out.println(l  + " sss");
		tmptoll.set(omega.get(count), 0);

		if (checkfeasibility(tmptoll) == true) {
			bestsolution = new ArrayList<>(tmptoll);
			tolls = new ArrayList<>(tmptoll);
			l++;
			step2();
		} else {
			bestsolution = new ArrayList<>(tolls);
			step4();

		}

	}

	private void step4() throws IloException {
		if (count == omega.size() - 1) {
			end();
		} else {
			count++;
			step3();
		}

	}

	private void end() {
		end = System.currentTimeMillis();
		resultSet();

	}

	private boolean checkfeasibility(List<Integer> tolls) throws IloException {

		ArrayList<IloNumExpr> s11 = new ArrayList<>();
		ArrayList<IloNumExpr> s12 = new ArrayList<>();
		ArrayList<IloAddable> s2 = new ArrayList<>();

		IloCplex cplex = new IloCplex();

		// --- initialising ro ---
		for (int i = 0; i < g.getVertices().size(); i++) {
			for (int j = 0; j < g.getPlayers().size(); j++) {

				g.getVertices().get(i).getRo().add(j,
						cplex.numVar(-Double.MAX_VALUE, Double.MAX_VALUE, "Ro of Player " + j + " in Vertex " + i));

			}

		}

		// --- initialising beta ---
		for (int i = 0; i < g.getEdges().size(); i++) {
			if (tolls.get(i) == 1) {
				g.getEdges().get(i).setBeta(cplex.numVar(0, Double.MAX_VALUE, "beta in the edge number : " + i));
			} else {
				g.getEdges().get(i).setBeta(cplex.numVar(0, 0, "beta in the edge number : " + i));
			}

		}

		for (int i = 0; i < g.getEdges().size(); i++) {

			IloNumExpr tmp = cplex.prod(g.getEdges().get(i).getSum(),
					cplex.sum(cplex.constant(g.getEdges().get(i).getCostB()),
							cplex.prod(cplex.constant(g.getEdges().get(i).getCostA()), g.getEdges().get(i).getSum()),
							g.getEdges().get(i).getBeta()));
			s11.add(tmp);
		}

		IloNumExpr[] planet1 = s11.toArray(new IloNumExpr[s11.size()]);
		IloNumExpr x = cplex.sum(planet1);

		for (int i = 0; i < g.getPlayers().size(); i++) {
			IloNumExpr tmp = cplex.prod(cplex.constant(g.getPlayers().get(i).getDemand()),
					cplex.sum(g.getPlayers().get(i).getSource().getRo().get(i),
							cplex.prod(-1, g.getPlayers().get(i).getSink().getRo().get(i))));
			s12.add(tmp);

		}
		IloNumExpr[] planet2 = s12.toArray(new IloNumExpr[s12.size()]);
		IloNumExpr y = cplex.sum(planet2);

		IloAddable amg = cplex.addEq(0, cplex.sum(x, cplex.prod(-1, y)));

		for (int i = 0; i < g.getPlayers().size(); i++) {

			for (int j = 0; j < g.getEdges().size(); j++) {

				IloNumExpr tmp = cplex.sum(g.getEdges().get(j).getTo().getRo().get(i),
						cplex.prod(-1, g.getEdges().get(j).getFrom().getRo().get(i)));
				IloNumExpr tmp1 = cplex.sum(tmp, g.getEdges().get(j).getResult());
				IloAddable tmp2 = cplex.addGe(cplex.sum(tmp1, g.getEdges().get(j).getBeta()), 0);
				s2.add(tmp2);
			}

		}
		IloAddable[] planet3 = s2.toArray(new IloAddable[s2.size()]);

		cplex.add(planet3);
		cplex.add(amg);
		cplex.minimize();

		if (cplex.solve()) {
			for (int i = 0; i < g.getEdges().size(); i++) {
				g.getEdges().get(i).setBetta(cplex.getValue(g.getEdges().get(i).getBeta()));
			}
			cplex.clearModel();
			return true;
		} else {
			cplex.clearModel();
			return false;

		}
	}

	/**
	 * ResultSet for RMINTB
	 * 
	 * @throws IloException
	 * @throws UnknownObjectException
	 * 
	 */
	public void resultSet() {

		/*
		 * write to file
		 */

		String path_ResultSet = g.getPath();

		try (PrintWriter buildFlowWriter = new PrintWriter(path_ResultSet + ".RMINTB" + ".txt", "UTF-8")) {

			/*
			 * lines:
			 * 
			 * -------
			 * 
			 * 
			 * - from_to_beta_f^(k_1)*_f^(k_2)*_..._f^(k_m)* -
			 * from_to_beta_f^(k_1)*_f^(k_2)*_..._f^(k_m)* - ...
			 * 
			 */
			for (int i = 0; i < bestsolution.size(); i++) {

				// from
				String from = null;
				from = String.valueOf(g.getEdges().get(i).getFrom().getId());

				// to
				String to = null;
				to = String.valueOf( g.getEdges().get(i).getTo().getId());

				// beta
				String beta = null;
				beta = String.valueOf(g.getEdges().get(i).getBetta());

				// commodities
				String commodities = " Commodities: ";

				for (int j = 0; j < g.getPlayers().size(); j++) {
					commodities +=  g.getPlayers().get(j).getId() + " "
							+ g.getEdges().get(i).getValues().get(j) + " ";
				}

				/*
				 * print line
				 */
				String line = from + " " + to + " " + beta + " " + commodities;

				buildFlowWriter.printf("%s\n", line);

			}

			/*
			 * rest:
			 * 
			 * -------
			 * 
			 * 
			 * time testcorrectness number tollbooths social optimum
			 * 
			 */

			// time
			String time = String.valueOf(timer);
			time = " Execution Time:  " + String.valueOf(time) + " ms";

			// TestCorrectness
			String testCorrectness = "";

			for (int i = 0; i < g.getPlayers().size(); i++) {
				TestCorrectness t = new TestCorrectness();
				t.test(g, g.getPlayers().get(i));
				testCorrectness += t.getResult();
			}

			// number of tollbooths (out of beta)
			String numberOfTollbooths = null;
			int count = 0;
			for (int i = 0; i < bestsolution.size(); i++) {
				if (bestsolution.get(i) > 0) {
					count++;
				}
			}
			numberOfTollbooths = "Number of Toll Stations: " + String.valueOf(count);

			// social optimum
			String socialOptimum = this.social.getCplexobj();

			/*
			 * print rest
			 */
			String rest = time + "\n" + testCorrectness + "\n" + numberOfTollbooths + "\n" + socialOptimum;

			buildFlowWriter.printf("");
			buildFlowWriter.printf("%s\n", rest);

			// flush and close
			buildFlowWriter.flush();
			buildFlowWriter.close();

		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
