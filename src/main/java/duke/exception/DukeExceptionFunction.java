package duke.exception;

import java.util.function.Function;

import duke.DukeResponse;

/**
 * Defines an interface for functions that throw DukeException.
 *
 * @param <T> Input type.
 */
public interface DukeExceptionFunction<T> {
    /**
     * Constructs DukeExceptionFunction from Function.
     *
     * @param lambda Function.
     * @param <A> Input type.
     * @return Constructed DukeExceptionFunction.
     */
    public static <A> DukeExceptionFunction<A> of(Function<A, DukeResponse> lambda) {
        assert lambda != null;
        return a -> lambda.apply(a);
    }

    /**
     * Constructs Function from DukeExceptionFunction.
     *
     * @param lambda DukeExceptionFunction.
     * @param <A> Input type.
     * @return Constructed Function.
     */
    public static <A> Function<A, DukeResponse> toFunction(DukeExceptionFunction<A> lambda) {
        assert lambda != null;
        return a -> {
            try {
                return lambda.apply(a);
            } catch (DukeException exception) {
                return exception.getResponse();
            }
        };
    }

    /**
     * Applies on T such that it allows for throwing of DukeException.
     *
     * @return Single-use output DukeResponse.
     * @throws DukeException Single-use output DukeException.
     */
    public DukeResponse apply(T t) throws DukeException;
}
