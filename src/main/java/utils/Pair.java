package utils;

/**
 * Utility class to store a pair of objects.
 *
 * @param <S> Type of first object.
 * @param <T> Type of second object.
 */
public class Pair<S, T> {
    private final S first;
    private final T second;

    /**
     * Creates a pair.
     * @param first First object.
     * @param second Second object.
     */
    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
}
