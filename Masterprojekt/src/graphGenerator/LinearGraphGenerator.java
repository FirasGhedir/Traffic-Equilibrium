package graphGenerator;

import java.util.Map;
import java.util.Random;

import graphModel.Edge;
import graphModel.Graph;
import graphModel.Vertex;
import player.Player;

public class LinearGraphGenerator
implements
GraphGenerator<Vertex, Edge, Vertex>
{
/**
 * Role for the first vertex generated.
 */
public static final String START_VERTEX = "Start Vertex";

/**
 * Role for the last vertex generated.
 */
public static final String END_VERTEX = "End Vertex";

private int size;
Player p;
int id;
final static int d_max = 100;
final static int d_min = 10;
private Random rand = new Random();
int counter = 0;
/**
 * Construct a new LinearGraphGenerator.
 *
 * @param size number of vertices to be generated
 *
 * @throws IllegalArgumentException if the specified size is negative.
 */
public LinearGraphGenerator(int size)
{
    if (size < 0) {
        throw new IllegalArgumentException("must be non-negative");
    }

    this.size = size;
}

/**
 * {@inheritDoc}
 */
@Override
public void generateGraph(Graph<Vertex, Edge> target, Map<String, Vertex> resultMap)
{
    Vertex lastVertex = null;

    for (int i = 0; i < size; ++i) {
    	Vertex newVertex = new Vertex(counter);
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
    p = new Player(this.id,resultMap.get(START_VERTEX),resultMap.get(END_VERTEX),d_min + rand.nextInt((d_max - d_min) + 1));
}


public Player getPlayer() {
	return p;
}
}