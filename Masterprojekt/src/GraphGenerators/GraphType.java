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

	/**
	 * 
	 * @return
	 */
	boolean isDirected();

	/**
	 * 
	 * @return
	 */
	boolean isUndirected();

	/**
	 * 
	 * @return
	 */
	boolean isMixed();

	/**
	 * 
	 * @return
	 */
	boolean isAllowingMultipleEdges();

	/**
	 * 
	 * @return
	 */
	boolean isAllowingSelfLoops();

	/**
	 * 
	 * @return
	 */
	boolean isAllowingCycles();

	/**
	 * 
	 * @return
	 */
	boolean isWeighted();

	/**
	 * 
	 * @return
	 */
	boolean isSimple();

	/**
	 * 
	 * @return
	 */
	boolean isPseudograph();

	/**
	 * 
	 * @return
	 */
	boolean isMultigraph();

	/**
	 * 
	 * @return
	 */
	boolean isModifiable();

	/**
	 * 
	 * @return
	 */
	GraphType asDirected();

	/**
	 * 
	 * @return
	 */
	GraphType asUndirected();

	/**
	 * 
	 * @return
	 */
	GraphType asMixed();

	/**
	 * 
	 * @return
	 */
	GraphType asUnweighted();

	/**
	 * 
	 * @return
	 */
	GraphType asWeighted();

	/**
	 * 
	 * @return
	 */
	GraphType asModifiable();

	/**
	 * 
	 * @return
	 */
	GraphType asUnmodifiable();
}