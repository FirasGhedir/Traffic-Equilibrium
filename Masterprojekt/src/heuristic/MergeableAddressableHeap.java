package heuristic;

public interface MergeableAddressableHeap<K, V> extends AddressableHeap<K, V> {

	/**
	 * Meld a heap into the current heap.
	 */
	void meld(MergeableAddressableHeap<K, V> other);

}