package GraphGenerators;

public class Player {
	
	Vertex source;
	Vertex sink;
	int demand;
	int id;
	
	public Player(int id) {
		this.id=id;
		
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getSink() {
		return sink;
	}

	public void setSink(Vertex sink) {
		this.sink = sink;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
