package util;

import java.util.Comparator;

public interface AddressableHeap<K, V> {

	interface Handle<K, V> {

		K getKey();

		V getValue();

		void setValue(V value);

		void decreaseKey(K newKey);

		void delete();

	}

	Comparator<? super K> comparator();

	AddressableHeap.Handle<K, V> insert(K key, V value);

	AddressableHeap.Handle<K, V> insert(K key);

	AddressableHeap.Handle<K, V> findMin();

	AddressableHeap.Handle<K, V> deleteMin();

	boolean isEmpty();

	long size();

	void clear();

}
