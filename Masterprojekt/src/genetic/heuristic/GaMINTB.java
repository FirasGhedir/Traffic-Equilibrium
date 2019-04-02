package genetic.heuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


import graphModel.Graphs;

import ilog.concert.IloException;

public class GaMINTB {

	private List<Chromosom> bestsolutions;
	Graphs graph;
	Population firas;

	public GaMINTB(Graphs graph,int p) {

		bestsolutions = new ArrayList<>();
		this.graph = graph;
		firas = new Population(p);
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

	

		final long time = System.currentTimeMillis();
       
		firas.generatechromosomes(graph);
		for (int i = 0; i < T; i++) {
			firas.run(this.getBestsolutions(), graph, firas);

		}

		Optional<Chromosom> alpha = this.getBestsolutions().stream()
				.min(Comparator.comparingInt(Chromosom::getEfficiency));
		this.savebestsolution(graph, alpha.get());

		alpha.get().calculateE();

		System.err.println(
				"##################################################### termination ########################################");
		System.out.println("Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : "
				+ alpha.get().getEfficiency() + " || Feasibility : " + alpha.get().isFeasible());

		System.out.printf("\n==========\nThe GAMINTB heuristic took %dms for calculation.\n",
				System.currentTimeMillis() - time);

           
	}
	
	
}
