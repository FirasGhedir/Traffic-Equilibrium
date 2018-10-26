package GraphGenerators;

import java.util.*;


public interface GraphGenerator<V, E, T>
{
  
    void generateGraph(Graph<V, E> target, Map<String, T> resultMap);


    default void generateGraph(Graph<V, E> target)
    {
        generateGraph(target, null);
    }

}