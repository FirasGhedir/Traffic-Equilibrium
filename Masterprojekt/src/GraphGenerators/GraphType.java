package GraphGenerators;

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 */
public interface GraphType {

	boolean isDirected();

	boolean isUndirected();

	boolean isMixed();

	boolean isAllowingMultipleEdges();

	boolean isAllowingSelfLoops();

	boolean isAllowingCycles();

	boolean isWeighted();

	boolean isSimple();

	boolean isPseudograph();

	boolean isMultigraph();

	boolean isModifiable();

	GraphType asDirected();

	GraphType asUndirected();

	GraphType asMixed();

	GraphType asUnweighted();

	GraphType asWeighted();

	GraphType asModifiable();

	GraphType asUnmodifiable();
}