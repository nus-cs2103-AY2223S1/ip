package DataStruct;

public class Pair<T, S> {

    private final T head;
    private final S tail;

    public Pair(T head, S tail) {
        this.head = head;
        this.tail = tail;
    }

    public T getHead() {
        return this.head;
    }

    public S getTail() {
        return this.tail;
    }
}
