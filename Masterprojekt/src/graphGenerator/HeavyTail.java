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
	int d = 0;

	public HeavyTail() {
		n = ThreadLocalRandom.current().nextInt(50, 100);
	    rnd = new Random();
	}

	@Override
	public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap) {
		// TODO Auto-generated method stub
		boolean isDirected = true;
		int size = ThreadLocalRandom.current().nextInt(0, (int) ((n * 0.55)));
		List<Vertex> nodes = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Vertex vertex = new Vertex(i);
			target.addVertex(vertex);
			nodes.add(vertex);
		}
		

		Random r = new Random();
		UFinit(size);
		while (check()) {
			int x = 0;
			int y = 0;
			do {
				x = r.nextInt(nodes.size());
				y = r.nextInt(nodes.size());
			} while (x == y);

			Vertex v = nodes.get(x);
			Vertex u = nodes.get(y);
			if (!(target.containsEdge(v, u) && target.containsEdge(u, v))) {
				target.addEdge(v, u);
				v.setDeg(v.getDeg()+1);
				u.setDeg(u.getDeg()+1);
				d++;
				UFunion(x, y);
				if (isDirected) {
					target.addEdge(u, v);
					d++;
				}
			}
		}
          int count = nodes.size()-1;
		for (int i = count; i < n ; i++) {
              Vertex vertex = new Vertex(i);
              target.addVertex(vertex);
              nodes.add(vertex);
              int tmp  = 0; 
              for(int j = 0 ; j < count ; j++) {
            	  if(rnd.nextDouble()<nodes.get(j).getDeg()/d) {
            		  target.addEdge(nodes.get(j), vertex);
            		  target.addEdge(vertex, nodes.get(j));
            		  nodes.get(j).setDeg(nodes.get(j).getDeg()+1);
            		  tmp +=2;
            	  }
              }
              d+=tmp;
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
