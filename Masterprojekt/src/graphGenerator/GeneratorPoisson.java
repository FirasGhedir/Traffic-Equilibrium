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
		int counter = 0;
		ArrayList<Vertex> vertices = new ArrayList<>(); 

		for (int i = 0; i < this.k; i++) {
			int z = ThreadLocalRandom.current().nextInt(this.Z_min, this.Z_max);
		    Vertex lastVertex = null;

			for (int j = 0; j < z; j++) {
				Vertex newVertex = new Vertex(counter);
				vertices.add(newVertex);
				target.addVertex(newVertex);
				counter++;
				  if (lastVertex == null) {
			            if (resultMap != null) {
			                resultMap.put(START_VERTEX, newVertex);
			            }
			        } else {
			            target.addEdge(lastVertex, newVertex);
			        }

			        lastVertex = newVertex;
			    }

			    if ((resultMap != null) && (lastVertex != null)) {
			        resultMap.put(END_VERTEX, lastVertex);
			    }
			 Player   p = new Player(i,resultMap.get(START_VERTEX),resultMap.get(END_VERTEX),10 + rnd.nextInt((100 - 10) + 1));
			 players.add(p);
			}
		int tmp = counter;
		for(int i = 0 ; i < n - tmp ; i++) {
		     Vertex vertex = new Vertex(counter);
		     vertices.add(vertex);
		     target.addVertex(vertex);
		     counter++;
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

	public ArrayList<Player> getPlayers(){
		return players;
	}
}
