package GraphGenerators;



import java.util.*;
import java.util.function.*;



public interface Graph<V, E>
{
    

    
    E getEdge(V sourceVertex, V targetVertex);

      
  
    E addEdge(V sourceVertex, V targetVertex);

    
    boolean addEdge(V sourceVertex, V targetVertex, E e);
    
   
    boolean addVertex(V v);

   
    boolean containsEdge(V sourceVertex, V targetVertex);

    
    boolean containsEdge(E e);

    
    boolean containsVertex(V v);

   

   
    int degreeOf(V vertex);

     

  
    boolean removeEdge(E e);

    boolean removeVertex(V v);

      
    double DEFAULT_EDGE_WEIGHT = 1.0;


    double getEdgeWeight(E e);

    void setEdgeWeight(E e, double weight);


    default void setEdgeWeight(V sourceVertex, V targetVertex, double weight){
        this.setEdgeWeight(this.getEdge(sourceVertex, targetVertex), weight);
    }

}