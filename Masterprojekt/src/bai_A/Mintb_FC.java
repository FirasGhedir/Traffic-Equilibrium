package bai_A;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import graphModel.Graphs;
import heuristic.DSSP;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;

public class Mintb_FC {
	int l = 1;
	DSSP x;
	final static double default_epsilon = 0.1;
	SocialOptimum social;
	Graphs g;
	long timer;
	long start;
	long end;

	public Mintb_FC(Graphs g) throws IloException {
		this.g=g;
		start = System.currentTimeMillis();
		social = new SocialOptimum(g);
		social.solveDSSP(g);
		run(g);
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
		end =  System.currentTimeMillis();
		timer = end - start;
		resultSet();
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
	
	public void resultSet() {

		/*
		 * write to file
		 */

		String path_ResultSet = g.getPath();

		try (PrintWriter buildFlowWriter = new PrintWriter(path_ResultSet + ".MINTB" + ".txt", "UTF-8")) {

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
			for (int i = 0; i < g.getEdges().size(); i++) {

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
			for (int i = 0; i < g.getEdges().size(); i++) {
				if (g.getEdges().get(i).getBetta() > 0) {
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
            System.out.println(g.getPath());
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
