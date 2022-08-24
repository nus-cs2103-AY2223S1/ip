package duke;

/**
 * Represents a function that can either complete and return a value or throw a MessagefulException for the
 * caller to handle.
 *
 * @param <T> the type of the input to the function.
 * @param <R> the type of the input to the function.
 */
@FunctionalInterface
public interface FallibleFunction<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t The function argument.
     * @return The function result.
     * @throws MessagefulException when thrown by the wrapped function.
     */
    R apply(T t) throws MessagefulException;
}
