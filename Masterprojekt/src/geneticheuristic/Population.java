package geneticheuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.SocialOptimum;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;
import player.Player;

public class Population {

	int size;
	List<Chromosom> y;
	List<Chromosom> afterranking;
	List<Chromosom> parents;
	Random r = new Random();

	public Population(int size) {
		this.size = size;
		y = new ArrayList<>();
		afterranking = new ArrayList<>();
		parents = new ArrayList<>();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Chromosom> getY() {
		return y;
	}

	public void setY(List<Chromosom> y) {
		this.y = y;
	}

	public void generatechromosomes(Graphs g) {

		for (int i = 0; i < getSize(); i++) {
			y.add(new Chromosom(g.getEdges().size()));
			for (int j = 0; j < g.getEdges().size(); j++) {

				double randomValue = 0 + (1 - 0) * r.nextDouble();
				int x = (int) (randomValue + 0.5);
				if (x == 0) {
					y.get(i).vector[j] = false;
				}

				else {

					y.get(i).vector[j] = true;

				}
			}

		}

	}

	public List<Chromosom> getAfterranking() {
		return afterranking;
	}

	public void setAfterranking(List<Chromosom> afterranking) {
		this.afterranking = afterranking;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public boolean evaluation(Graphs g, Chromosom xx) throws IloException {

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
			if (xx.getVector()[i] == true) {
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
			cplex.clearModel();
			return true;
		} else {
			cplex.clearModel();
			return false;
		}
	}

	public void savefetnicitiy() {
		for (int i = 0; i < getY().size(); i++) {
			getY().get(i).efficientycalculate();
		}
	}

	public void saverank() {

		List<Chromosom> solvable = new ArrayList<>();
		List<Chromosom> notsolvable = new ArrayList<>();

		for (int i = 0; i < getY().size(); i++) {
			getY().get(i).efficientycalculate();
			if (getY().get(i).isFeasible()) {
				solvable.add(getY().get(i));
			} else {
				notsolvable.add(getY().get(i));
			}
		}
		solvable.sort(Comparator.comparing(Chromosom::getEfficiency));
		notsolvable.sort(Comparator.comparing(Chromosom::getEfficiency));

		setAfterranking(solvable);
		getAfterranking().addAll(notsolvable);

		for (int i = 0; i < getAfterranking().size(); i++) {
			getAfterranking().get(i).setRank(i);
		}

	}

	public void saveprobability() {

		for (int i = 0; i < getAfterranking().size(); i++) {
			getAfterranking().get(i).setProbability(getAfterranking().get(i).probabilitycalculate(getSize()));
		}

	}
	
	public void saveminmax() {
		
		for(int i=0; i<getY().size() ; i++) {
			
			
		}
		
	}

	public static void main(String[] args) throws IloException {

		Population firas = new Population(10);
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(graph, map);
		firas.generatechromosomes(graph);

		Player player1 = new Player(1, graph.getVertices().get(4), graph.getVertices().get(15), 10);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 5);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		graph.setPlayer(x);
		graph.generateEdgesFunctions();

		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		// Store current System.out before assigning a new value

		System.out.println(systemOptimalFlow);

		for (int i = 0; i < firas.getY().size(); i++) {
			firas.getY().get(i).setFeasible(firas.evaluation(graph, firas.getY().get(i)));
		}
		
	
		firas.savefetnicitiy();

		firas.saverank();

		firas.saveprobability();
		
		firas.setY(firas.getAfterranking());
		
		firas.saveminmax();

		double rate = firas.getR().nextDouble();

		double nb = (rate * firas.getSize()) / 2;

		int finalrate = (int) nb;

		boolean odd = (finalrate & 1) != 0;

		if (odd) {
			finalrate++;
		}
		
     
        
        
		

	}

}
