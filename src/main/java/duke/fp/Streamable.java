package duke.fp;

import java.util.stream.Stream;

/**
 * A streamable object must be able to be converted into a stream,
 * usually a collection.
 * 
 * @param <T> the type of the objects in the stream
 */
@FunctionalInterface
public interface Streamable<T> {
    /**
     * Returns a stream of the objects in this streamable.
     * 
     * @return a stream of objects of type T
     */
    Stream<T> stream();
}
