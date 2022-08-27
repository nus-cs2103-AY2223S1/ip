package DataStruct;

public class Pair<T, S> {

    private final T head;
    private final S tail;

    public Pair(T head, S tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Getter for the head of the pair.
     *
     * @return Head of the pair
     */
    public T getHead() {
        return this.head;
    }

    /**
     * Getter for the tail of the pair.
     *
     * @return Tail of the pair
     */
    public S getTail() {
        return this.tail;
    }
}
