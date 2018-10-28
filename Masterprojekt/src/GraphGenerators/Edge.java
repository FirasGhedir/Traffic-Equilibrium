package GraphGenerators;

public class Edge {
   
  Vertex from;
  Vertex to;
  int weight;
  
  
  
public Edge(Vertex from, Vertex to, int weight) {
	super();
	this.from = from;
	this.to = to;
	this.weight = weight;
}
public Vertex getFrom() {
	return from;
}
public void setFrom(Vertex from) {
	this.from = from;
}
public Vertex getTo() {
	return to;
}
public void setTo(Vertex to) {
	this.to = to;
}
public int getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}
  
  
	
}
