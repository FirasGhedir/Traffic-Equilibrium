package TestCaseSiouxFalls;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import bai_A.Mintb_FC;
import genetic.heuristic.Chromosom;
import genetic.heuristic.GaMINTB;
import genetic.heuristic.Population;
import graphGenerator.GridGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
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
 *          In the Main class you can create new graphs, test and work with
 *          them.
 */
public class SiouxFalls {

	static SocialOptimum systemOptimalFlow;
	static FileInputStream fstream;

	static ArrayList<Vertex> vertices = new ArrayList<>();
	static ArrayList<Edge> edges = new ArrayList<>();

	static Vertex v1;
	static Vertex v2;
	static Vertex v3;
	static Vertex v4;
	static Vertex v5;
	static Vertex v6;
	static Vertex v7;
	static Vertex v8;
	static Vertex v9;

	static Vertex v10;
	static Vertex v11;
	static Vertex v12;
	static Vertex v13;
	static Vertex v14;
	static Vertex v15;
	static Vertex v16;
	static Vertex v17;
	static Vertex v18;
	static Vertex v19;

	static Vertex v20;
	static Vertex v21;
	static Vertex v22;
	static Vertex v23;
	static Vertex v24;

	static Edge e1;
	static Edge e2;
	static Edge e3;
	static Edge e4;
	static Edge e5;
	static Edge e6;
	static Edge e7;
	static Edge e8;
	static Edge e9;

	static Edge e10;
	static Edge e11;
	static Edge e12;
	static Edge e13;
	static Edge e14;
	static Edge e15;
	static Edge e16;
	static Edge e17;
	static Edge e18;
	static Edge e19;

	static Edge e20;
	static Edge e21;
	static Edge e22;
	static Edge e23;
	static Edge e24;
	static Edge e25;
	static Edge e26;
	static Edge e27;
	static Edge e28;
	static Edge e29;

	static Edge e30;
	static Edge e31;
	static Edge e32;
	static Edge e33;
	static Edge e34;
	static Edge e35;
	static Edge e36;
	static Edge e37;
	static Edge e38;
	static Edge e39;

	static Edge e40;
	static Edge e41;
	static Edge e42;
	static Edge e43;
	static Edge e44;
	static Edge e45;
	static Edge e46;
	static Edge e47;
	static Edge e48;
	static Edge e49;

	static Edge e50;
	static Edge e51;
	static Edge e52;
	static Edge e53;
	static Edge e54;
	static Edge e55;
	static Edge e56;
	static Edge e57;
	static Edge e58;
	static Edge e59;

	static Edge e60;
	static Edge e61;
	static Edge e62;
	static Edge e63;
	static Edge e64;
	static Edge e65;
	static Edge e66;
	static Edge e67;
	static Edge e68;
	static Edge e69;

	static Edge e70;
	static Edge e71;
	static Edge e72;
	static Edge e73;
	static Edge e74;
	static Edge e75;
	static Edge e76;

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args             the command line arguments
	 * @param adjacency_matrix
	 * @throws IOException
	 * @throws IloException
	 */
	public static void main(String[] args) throws IOException, IloException {

		/*
		 * =============================================================
		 * ======================= ADD VERTICES ========================
		 * =============================================================
		 */

		try {
			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_node.tntp");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			int index = 0;
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("Node") != true) {
					index++;
				}
			}

			System.out.println("\n -> " + index + " vertices added....\n");

			int[] id = new int[index];

			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_node.tntp");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			strLine = null;
			// Read File Line By Line
			int index2 = 0;
			while ((strLine = br.readLine()) != null) {
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("Node") != true) {
					id[index2] = Integer.parseInt(splited[0]);
					index2++;
				}
			}

			v1 = new Vertex(id[0]);
			v2 = new Vertex(id[1]);
			v3 = new Vertex(id[2]);
			v4 = new Vertex(id[3]);
			v5 = new Vertex(id[4]);
			v6 = new Vertex(id[5]);
			v7 = new Vertex(id[6]);
			v8 = new Vertex(id[7]);
			v9 = new Vertex(id[8]);

			v10 = new Vertex(id[9]);
			v11 = new Vertex(id[10]);
			v12 = new Vertex(id[11]);
			v13 = new Vertex(id[12]);
			v14 = new Vertex(id[13]);
			v15 = new Vertex(id[14]);
			v16 = new Vertex(id[15]);
			v17 = new Vertex(id[16]);
			v18 = new Vertex(id[17]);
			v19 = new Vertex(id[18]);

			v20 = new Vertex(id[19]);
			v21 = new Vertex(id[20]);
			v22 = new Vertex(id[21]);
			v23 = new Vertex(id[22]);
			v24 = new Vertex(id[23]);

			vertices.add(v1);
			vertices.add(v2);
			vertices.add(v3);
			vertices.add(v4);
			vertices.add(v5);
			vertices.add(v6);
			vertices.add(v7);
			vertices.add(v8);
			vertices.add(v9);

			vertices.add(v10);
			vertices.add(v11);
			vertices.add(v12);
			vertices.add(v13);
			vertices.add(v14);
			vertices.add(v15);
			vertices.add(v16);
			vertices.add(v17);
			vertices.add(v18);
			vertices.add(v19);

			vertices.add(v20);
			vertices.add(v21);
			vertices.add(v22);
			vertices.add(v23);
			vertices.add(v24);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Vertex> vertices = new ArrayList<>();

		/*
		 * =============================================================
		 * ========================= ADD EDGES =========================
		 * =============================================================
		 */

		try {
			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_flow.tntp");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			int index = 0;
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("From") != true) {
					index++;
				}
			}

			System.out.println("\n -> " + index + " edges added....\n");

			double[] weight = new double[index];

			strLine = null;
			fstream = new FileInputStream("./src/TestCaseSiouxFalls/SiouxFalls_flow.tntp");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			// Read File Line By Line
			int index2 = 0;
			while ((strLine = br.readLine()) != null) {
				// split string and call your function
				String[] splited = strLine.split("\\s+");
				if (splited[0].endsWith("From") != true) {
					weight[index2] = Double.parseDouble(splited[2]);
					index2++;
				}
			}

			e1 = new Edge(v1, v2, weight[0]);
			e2 = new Edge(v1, v3, weight[1]);
			e3 = new Edge(v2, v1, weight[2]);
			e4 = new Edge(v2, v6, weight[3]);
			e5 = new Edge(v3, v1, weight[4]);
			e6 = new Edge(v3, v4, weight[5]);
			e7 = new Edge(v3, v12, weight[6]);
			e8 = new Edge(v4, v3, weight[7]);
			e9 = new Edge(v4, v5, weight[8]);

			e10 = new Edge(v4, v11, weight[9]);
			e11 = new Edge(v5, v4, weight[10]);
			e12 = new Edge(v5, v6, weight[11]);
			e13 = new Edge(v5, v9, weight[12]);
			e14 = new Edge(v6, v2, weight[13]);
			e15 = new Edge(v6, v5, weight[14]);
			e16 = new Edge(v6, v8, weight[15]);
			e17 = new Edge(v7, v6, weight[16]);
			e18 = new Edge(v7, v18, weight[17]);
			e19 = new Edge(v8, v6, weight[18]);

			e20 = new Edge(v8, v7, weight[19]);
			e21 = new Edge(v8, v9, weight[20]);
			e22 = new Edge(v8, v16, weight[21]);
			e23 = new Edge(v9, v5, weight[22]);
			e24 = new Edge(v9, v8, weight[23]);
			e25 = new Edge(v9, v10, weight[24]);
			e26 = new Edge(v10, v9, weight[25]);
			e27 = new Edge(v10, v11, weight[26]);
			e28 = new Edge(v10, v15, weight[27]);
			e29 = new Edge(v10, v16, weight[28]);

			e30 = new Edge(v10, v17, weight[29]);
			e31 = new Edge(v11, v4, weight[30]);
			e32 = new Edge(v11, v10, weight[31]);
			e33 = new Edge(v11, v12, weight[32]);
			e34 = new Edge(v11, v14, weight[33]);
			e35 = new Edge(v12, v3, weight[34]);
			e36 = new Edge(v12, v11, weight[35]);
			e37 = new Edge(v12, v13, weight[36]);
			e38 = new Edge(v13, v12, weight[37]);
			e39 = new Edge(v13, v24, weight[38]);

			e40 = new Edge(v14, v11, weight[39]);
			e41 = new Edge(v14, v15, weight[40]);
			e42 = new Edge(v14, v23, weight[41]);
			e43 = new Edge(v15, v10, weight[42]);
			e44 = new Edge(v15, v14, weight[43]);
			e45 = new Edge(v15, v19, weight[44]);
			e46 = new Edge(v15, v22, weight[45]);
			e47 = new Edge(v16, v8, weight[46]);
			e48 = new Edge(v16, v10, weight[47]);
			e49 = new Edge(v16, v17, weight[48]);

			e50 = new Edge(v16, v18, weight[49]);
			e51 = new Edge(v17, v10, weight[50]);
			e52 = new Edge(v17, v16, weight[51]);
			e53 = new Edge(v17, v19, weight[52]);
			e54 = new Edge(v18, v7, weight[53]);
			e55 = new Edge(v18, v16, weight[54]);
			e56 = new Edge(v18, v20, weight[55]);
			e57 = new Edge(v19, v15, weight[56]);
			e58 = new Edge(v19, v17, weight[57]);
			e59 = new Edge(v19, v20, weight[58]);

			e60 = new Edge(v20, v18, weight[59]);
			e61 = new Edge(v20, v19, weight[60]);
			e62 = new Edge(v20, v21, weight[61]);
			e63 = new Edge(v20, v22, weight[62]);
			e64 = new Edge(v21, v20, weight[63]);
			e65 = new Edge(v21, v22, weight[64]);
			e66 = new Edge(v21, v24, weight[65]);
			e67 = new Edge(v22, v15, weight[66]);
			e68 = new Edge(v22, v20, weight[67]);
			e69 = new Edge(v22, v21, weight[68]);

			e70 = new Edge(v22, v23, weight[69]);
			e71 = new Edge(v23, v14, weight[70]);
			e72 = new Edge(v23, v22, weight[71]);
			e73 = new Edge(v23, v24, weight[72]);
			e74 = new Edge(v24, v13, weight[73]);
			e75 = new Edge(v24, v21, weight[74]);
			e76 = new Edge(v24, v23, weight[75]);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * =============================================================
		 * ======================= Create graph ========================
		 * =============================================================
		 */

		Graphs g = new Graphs();
		g.setEdges(edges);
		g.setVertices(vertices);

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(5, 5);
		test.generateGraph(graph, map);

		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 14);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 40);

		ArrayList<Player> players = new ArrayList<>();
		players.add(0, player1);
		players.add(1, player2);
		graph.setPlayer(players);

		systemOptimalFlow = new SocialOptimum(graph);
		System.out.println(systemOptimalFlow);

		/*
		 * =============================================================
		 * ======================== Test MINTB =========================
		 * =============================================================
		 */

//		Mintb_FC pp = new Mintb_FC();
//		pp.run(graph);

		/*
		 * =============================================================
		 * ======================= Test GaMINTB ========================
		 * =============================================================
		 */

		GaMINTB start = new GaMINTB();

		Population population = new Population(40);

		population.generatechromosomes(graph);

		for (int i = 0; i < 40; i++) {
			population.run(start.getBestsolutions(), graph, population);
		}

		Optional<Chromosom> alpha = start.getBestsolutions().stream()
				.min(Comparator.comparingInt(Chromosom::getEfficiency));
		start.savebestsolution(graph, alpha.get());

		System.err.println("\n========== termination ==========\n");
		System.out.println("Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : "
				+ alpha.get().getEfficiency() + " || Feasibility  : " + alpha.get().isFeasible());

		TestCorrectness correct = new TestCorrectness();
		System.out.println(
				correct.test(graph, graph.getPlayers().get(0).getSource(), graph.getPlayers().get(0).getSink()));
	}
}
