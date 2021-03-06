package util;

import java.io.Serializable;

public class Pair<First, Second> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final First first;
	private final Second second;

	private volatile String toStringResult;

	public Pair(First first, Second second) {
		this.first = first;
		this.second = second;
	}

	public First getFirst() {
		return first;
	}

	public Second getSecond() {
		return second;
	}

	public static <A, B> Pair<A, B> of(A a, B b) {
		return new Pair<>(a, b);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		final Pair pair = (Pair) o;

		if (first != null ? !first.equals(pair.first) : pair.first != null) {
			return false;
		}

		if (second != null ? !second.equals(pair.second) : pair.second != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = first != null ? first.hashCode() : 0;

		result = 31 * result + (second != null ? second.hashCode() : 0);

		return result;
	}

	@Override
	public String toString() {
		if (toStringResult == null) {
			toStringResult = "Pair{" + "first=" + first + ", second=" + second + '}';
		}

		return toStringResult;
	}

}
