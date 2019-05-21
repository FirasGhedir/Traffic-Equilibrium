package graphGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.Vertex;

public class HeavyTail implements GraphGenerator<Vertex, Edge, Vertex> {

	int n;
	int[] array;
	Random rnd;
	double d = 0;

	public HeavyTail() {
		n = ThreadLocalRandom.current().nextInt(50, 100);
		rnd = new Random();
	}

	@Override
	public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap) {

		int size = 10;
		List<Vertex> vertices = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Vertex vertex = new Vertex(i);
			target.addVertex(vertex);
			vertices.add(vertex);
		}

		Random r = new Random();
		UFinit(size);
		while (check()) {
			int x = 0;
			int y = 0;
			do {
				x = r.nextInt(vertices.size());
				y = r.nextInt(vertices.size());
			} while (x == y);

			Vertex v = vertices.get(x);
			Vertex u = vertices.get(y);
			if (!(target.containsEdge(v, u) && target.containsEdge(u, v))) {
				target.addEdge(v, u);
				target.addEdge(u, v);
				v.setDeg(v.getDeg() + 1.0);
				u.setDeg(u.getDeg() + 1.0);
				d++;
				UFunion(x, y);

			}
		}

		for (int i = size; i < n; i++) {
			Vertex vertex = new Vertex(i);
			target.addVertex(vertex);
			vertices.add(vertex);
		}
		int tmp = 0;
		for (int i = size; i < n; i++) {
			for (int j = 0; j < size; j++) {
				do {
					double probability = rnd.nextDouble();
					double result = vertices.get(j).getDeg() / d;
					if (probability < result / d) {
						target.addEdge(vertices.get(j), vertices.get(i));
						target.addEdge(vertices.get(i), vertices.get(j));
						tmp++;
						vertices.get(i).setDeg(vertices.get(i).getDeg() + 1.0);
						vertices.get(j).setDeg(vertices.get(j).getDeg() + 1.0);

					}

				} while (vertices.get(i).getDeg() == 0);
			}

			d += tmp;
			tmp = 0;
		}

	}

	public void UFinit(int n) {
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i;
		}
	}

	public void UFunion(int a, int b) {
		int i = UFfind(a);
		int j = UFfind(b);
		double z = Math.random();
		if (z == 0) {
			array[i] = j;
		} else {
			array[j] = i;
		}
	}

	public int UFfind(int i) {
		if (i == this.array[i]) {
			return i;
		} else {
			int j = UFfind(array[i]);
			array[i] = j;
			return j;
		}

	}

	public boolean check() {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == i)
				count++;
		}

		if (count == 1)
			return false;
		else
			return true;
	}

}


