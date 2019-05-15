package graphGenerator;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.Vertex;
import player.Player;

public class GeneratorPoisson implements GraphGenerator<Vertex, Edge, Vertex> {

	int k;
	int n;
	double p;
	int Z_max;
	int Z_min;
	Random rnd = new Random();
	public static final String START_VERTEX = "Start Vertex";
	public static final String END_VERTEX = "End Vertex";
	ArrayList<Player> players;

	public GeneratorPoisson(int min, int max, double p) {
		this.Z_max = max;
		this.Z_min = min;
		this.p = p;
		this.n = ThreadLocalRandom.current().nextInt(50, 100);
		this.k = (int) (n * 0.1);
		players = new ArrayList<>();
	}

	@Override
	public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap) {
		ArrayList<Vertex> vertices = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Vertex vertex = new Vertex(i);
			vertices.add(vertex);
			target.addVertex(vertex);
		}
		ArrayList<Integer> ids = new ArrayList<>();
		for (int i = 0; i < this.k; i++) {
			int z = ThreadLocalRandom.current().nextInt(this.Z_min, this.Z_max);
			int starts = 0;
			do {
				starts = ThreadLocalRandom.current().nextInt(0, vertices.size() - 1);
			} while (ids.contains(starts));
			ids.add(starts);
			int ends = 0;

			Vertex start = vertices.get(starts);
			Vertex end = null;

			for (int j = 0; j < z; j++) {
				do {
					end = vertices.get(ThreadLocalRandom.current().nextInt(0, vertices.size() - 1));
				} while (ids.contains(end.getId()));
				target.addEdge(start, end);
				target.addEdge(end, start);
				start = end;

			}
			ends = end.getId();

			ids.add(ends);
			Player p = new Player(i, vertices.get(starts), vertices.get(ends), 10 + rnd.nextInt((100 - 10) + 1));
			players.add(p);
		}

		boolean isDirected = true;

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {

				if (i == j) {

					// no self-loops
					continue;
				}

				Vertex s = null;
				Vertex t = null;

				// s->t
				if (rnd.nextDouble() < p) {
					s = vertices.get(i);
					t = vertices.get(j);
					target.addEdge(s, t);
				}

				if (isDirected) {
					// t->s
					if (rnd.nextDouble() < p) {
						if (s == null) {
							s = vertices.get(i);
							t = vertices.get(j);
						}
						target.addEdge(t, s);
					}
				}
			}
		}

	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
}
