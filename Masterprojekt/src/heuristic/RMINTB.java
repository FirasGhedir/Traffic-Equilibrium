package heuristic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;
import player.Player;


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
	IloCplex cplex;
	List<Integer> tolls;
	List<Integer> tmptoll;
	List<Integer> bestsolution;
	List<Integer> omega;
	int l = 0 ; 
	int count = 0;
	
    public RMINTB(Graphs graph) throws IloException {
    	this.g = graph;
    	cplex = new IloCplex();
    	tolls = new ArrayList<>();
    	bestsolution = new ArrayList<>();
        omega = new ArrayList<>();
        tmptoll = new ArrayList<>();
    }
    
    public void solve() throws IloException {
    	DSSP dssp = new DSSP(g);
    	dssp.solveDSSP(g);
    	l=1;
   
    	step2();  	
      }

	private void step2() throws IloException {
	 	if(l>1) {
	 		omega.clear();
	 		for(int i = 0 ; i < bestsolution.size()  ; i++) {
	    		if(bestsolution.get(i)>0) {
	    			omega.add(i);
	    		}
	    		    	
	    	}
	 	}
	 	else {
		for(int i = 0 ; i < this.g.getEdges().size() ; i++) {
    		if(this.g.getEdges().get(i).getBetta()>0) {
    			tolls.add(i,1);
    			omega.add(i);
    		}
    		else {
    			tolls.add(i,0);
    		}
    	
    	}
	 }
	 	count=0;
	 	step3();
	}

	private void step3() throws IloException {
	 
	 tmptoll = new ArrayList<>(tolls);
	 tmptoll.set(omega.get(count), 0);
		
     if(checkfeasibility(tmptoll) == true) {
    	 bestsolution = new ArrayList<>(tmptoll);
    	 tolls = new ArrayList<>(tmptoll);
    	 l++;
    	 step2();
     }
     else {
    	 bestsolution = new ArrayList<>(tolls);
    	 step4();
    	 
     }
		
	}
	
	private void step4() throws IloException {
		if(count == omega.size()-1 ) {
		   end();
		}
		else {
			count++;
			step3();
		}
		
	}

	private void end() {
       for(int i = 0 ; i < bestsolution.size() ; i++) {
    	   System.out.println("In The Edger number : " + i + " y = " + bestsolution.get(i));
       }
		
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
			
			cplex.clearModel();
			return true;
		} else {
			cplex.clearModel();
			return false;
		} 
	}

	public static void main(String[] args) throws IloException {
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		
//		@SuppressWarnings("rawtypes")
//		BarabasiAlbertGraphGenerator test = new BarabasiAlbertGraphGenerator(10,3,100);
//		test.generateGraph(graph,map);


		GridGraphGenerator test = new GridGraphGenerator(6,6); // do not change !!
		test.generateGraph(graph, map);
		
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 4);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 4);
		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);
       graph.setPlayer(x);
       graph.generateEdgesFunctions();
        SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		systemOptimalFlow.solveDSSP(graph);

		RMINTB solver = new RMINTB(graph);
		solver.solve();
		TestCorrectness correct = new TestCorrectness();
		System.out.println("getPlayers(0): "
				+ correct.test(graph, graph.getPlayers().get(0).getSource(), graph.getPlayers().get(0).getSink()));
	}
}
