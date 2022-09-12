package duke;

/**
 * Stores a pair.
 *
 * @param <U> Type of first member of pair.
 * @param <V> Type of second member of pair.
 */
public class Pair<U, V> {
    private U u;
    private V v;

    /**
     * Constructs a pair.
     *
     * @param u First member of the pair.
     * @param v Second member of the pair.
     */
    public Pair(U u, V v) {
        this.u = u;
        this.v = v;
    }

    /**
     * Gets the first member of the pair.
     *
     * @return First member of the pair.
     */
    public U getFirst() {
        return this.u;
    }

    /**
     * Gets the second member of the pair.
     *
     * @return Second member of the pair.
     */
    public V getSecond() {
        return this.v;
    }
}
