package graphModel;

public class Graphe implements GraphType {
	
	public Graphe() {
		
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isUndirected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMixed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAllowingMultipleEdges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAllowingSelfLoops() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAllowingCycles() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWeighted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSimple() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPseudograph() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMultigraph() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModifiable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GraphType asDirected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType asUndirected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType asMixed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType asUnweighted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType asWeighted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType asModifiable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphType asUnmodifiable() {
		// TODO Auto-generated method stub
		return null;
	}

}
