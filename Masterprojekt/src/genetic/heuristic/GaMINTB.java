package genetic.heuristic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


import graphModel.Graphs;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;

public class GaMINTB {

	private List<Chromosom> bestsolutions;
	Graphs graph;
	Population firas;
	SocialOptimum social;
	long timer;
	long start;
	long end;
	boolean flag;


	public GaMINTB(Graphs graph,int p) throws IloException {

		bestsolutions = new ArrayList<>();
		this.graph = graph;
		firas = new Population(p);
		social = new SocialOptimum(graph);
		social.solveDSSP(graph);
		start = System.currentTimeMillis();

	}

	public List<Chromosom> getBestsolutions() {
		return bestsolutions;
	}

	public void setBestsolutions(List<Chromosom> bestsolutions) {
		this.bestsolutions = bestsolutions;
	}

	public void savebestsolution(Graphs g, Chromosom xx) {

		for (int i = 0; i < xx.getBeta().size(); i++) {
			g.getEdges().get(i).setBetta(xx.getBeta().get(i));
			g.getEdges().get(i).calculateL();
		}
	}

	public void run(int T) throws IloException {

	
       
		firas.generatechromosomes(graph);
		for (int i = 0; i < T; i++) {
			firas.run(this.getBestsolutions(), graph, firas);

		}

		Optional<Chromosom> alpha = this.getBestsolutions().stream()
				.min(Comparator.comparingInt(Chromosom::getEfficiency));
		this.savebestsolution(graph, alpha.get());

		alpha.get().calculateE();
		

        	
		end =  System.currentTimeMillis();
		timer = end - start;
		resultSet();
		System.err.println(firas.evaluation(graph, alpha.get()) + " sidi sidi ");
	}
	
	public void resultSet() {

		/*
		 * write to file
		 */

		String path_ResultSet = graph.getPath();

		try (PrintWriter buildFlowWriter = new PrintWriter(path_ResultSet + ".GAMINTB" + ".txt", "UTF-8")) {

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
			for (int i = 0; i < graph.getEdges().size(); i++) {

				// from
				String from = null;
				from = String.valueOf(graph.getEdges().get(i).getFrom().getId());

				// to
				String to = null;
				to = String.valueOf( graph.getEdges().get(i).getTo().getId());

				// beta
				String beta = null;
				beta = String.valueOf(graph.getEdges().get(i).getBetta());

				// commodities
				String commodities = " Commodities: ";

				for (int j = 0; j < graph.getPlayers().size(); j++) {
					commodities +=  graph.getPlayers().get(j).getId() + " "
							+ graph.getEdges().get(i).getValues().get(j) + " ";
				}

				/*
				 * print line
				 */
				String line = from + " " + to + " " + beta + " " + commodities;

				buildFlowWriter.printf("%s\n", line);

			}
 
			String  feasiblity = "Feasibility: " +  flag ; 
			buildFlowWriter.printf("%s\n", feasiblity);

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

			for (int i = 0; i < graph.getPlayers().size(); i++) {
				TestCorrectness t = new TestCorrectness();
				t.test(graph, graph.getPlayers().get(i));
				testCorrectness += t.getResult();
			}

			// number of tollbooths (out of beta)
			String numberOfTollbooths = null;
			int count = 0;
			for (int i = 0; i < graph.getEdges().size(); i++) {
				if (graph.getEdges().get(i).getBetta() > 0) {
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
            System.out.println(graph.getPath());
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
