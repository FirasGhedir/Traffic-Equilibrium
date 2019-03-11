package bai_A;

import java.util.ArrayList;

import graphModel.Graphs;
import heuristic.DSSP;
import ilog.concert.IloException;

public class Mintb_FC {
	int l = 1;
	DSSP x;
	final static double default_epsilon = 0.1;

	public Mintb_FC() {
		
	}
	
	public  void run(Graphs g) throws IloException {
        ArrayList<Double> xx = new ArrayList<>();
        ArrayList<Double> yy = new ArrayList<>();
		x = new DSSP(g);
		x.solveDSSP(g);
        
		while (true) {
			System.out.println(l);
			update(g);
			xx = g.getBeta();
			x.solveDSSP(g);;
			yy = g.getBeta();
			if(check(xx,yy) == true) break;
			l++;

		}
	}

	private boolean check(ArrayList<Double> x , ArrayList<Double> y) {
        
		double sum =0;
		for(int i = 0 ; i < x.size() ; i++) {
			
			sum +=  (y.get(i)-x.get(i))*(y.get(i)-x.get(i));
		}
		
		sum = Math.sqrt(sum);
		if(sum < default_epsilon) return true;
		
		return false;
	}

	private void update(Graphs g) {
	
		for (int i = 0; i < g.getEdges().size(); i++) {
			if (g.getEdges().get(i).getBetta() > 0) {
				g.getEdges().get(i).setC(1 / g.getEdges().get(i).getBetta());
			} else if (g.getEdges().get(i).getBetta() == 0 && g.getEdges().get(i).getIlist().size() == 0) {

				g.getEdges().get(i).setC(g.getEdges().get(i).getC());

			}

			else if (g.getEdges().get(i).getBetta() == 0 && g.getEdges().get(i).getIlist().size() != 0) {

				g.getEdges().get(i).setC(g.getEdges().get(i).getIlist().get(g.getEdges().get(i).getIlist().size() - 1));
			}

		}
		
	}

}
