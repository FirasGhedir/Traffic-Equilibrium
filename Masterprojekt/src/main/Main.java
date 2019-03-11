package main;

import bai_A.Mintb_FC;
import genetic.heuristic.GaMINTB;
import graphModel.Graphs;
import heuristic.RMINTB;
import heuristic.SocialOptimum;
import ilog.concert.IloException;
import nickerl.Nickerl;

public class Main{
	
	public static void Mintb(Graphs graph) {
		Mintb_FC solver = new Mintb_FC();
		try {
			solver.run(graph);
		} catch (IloException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Rmintb(Graphs graph) {
		RMINTB solver;
		try {
			solver = new RMINTB(graph);
			solver.run();

		} catch (IloException e) {
			e.printStackTrace();
		}
	}
	
	public static void Genetic(Graphs graph) {
		
		GaMINTB solver = new GaMINTB(graph);
		try {
			solver.run();
		} catch (IloException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Nickerl(Graphs graph) {
		
		try {
			Nickerl solver = new Nickerl(graph);
			solver.run();
		} catch (IloException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Graphs getGraph(int i) {
		//ToDo : Julian Bestler
		return null;
	}
	
	public static void main(String[] args) throws IloException {
	   Graphs graph = null;
	  
	   switch(args[0]) {
	   case "1" : graph = getGraph(1);
	   case "2" : graph = getGraph(2);
	   case "3" : graph = getGraph(3);
	   
	   }
	   
		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		systemOptimalFlow.solveDSSP(graph);

	   
	   switch(args[1]) {
	   case "1" : Mintb(graph);
	   case "2" : Rmintb(graph);
	   case "3" : Genetic(graph);
	   case "4" : Nickerl(graph);
	   }
		
		
	}
	
}