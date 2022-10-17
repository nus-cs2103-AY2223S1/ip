package duke.exception;

import java.util.function.BiFunction;

import duke.DukeResponse;

/**
 * Defines an interface for functions that throw DukeException.
 *
 * @param <T> Input type.
 */
public interface DukeExceptionBiFunction<T, U> {
    /**
     * Constructs DukeExceptionBiFunction from BiFunction.
     *
     * @param lambda BiFunction.
     * @param <A> Input type.
     * @param <B> Input type.
     * @return Constructed DukeExceptionBiFunction.
     */
    public static <A, B> DukeExceptionBiFunction<A, B> of(BiFunction<A, B, DukeResponse> lambda) {
        assert lambda != null;
        return lambda::apply;
    }

    /**
     * Constructs BiFunction from DukeExceptionBiFunction.
     *
     * @param lambda DukeExceptionFunction.
     * @param <A> Input type.
     * @return Constructed Function.
     */
    public static <A, B> BiFunction<A, B, DukeResponse> toBiFunction(DukeExceptionBiFunction<A, B> lambda) {
        assert lambda != null;
        return (a, b) -> {
            try {
                return lambda.apply(a, b);
            } catch (DukeException exception) {
                return exception.getResponse();
            }
        };
    }

    /**
     * Applies on T, U such that it allows for throwing of DukeException.
     *
     * @return Single-use output DukeResponse.
     * @throws DukeException Single-use output DukeException.
     */
    public DukeResponse apply(T t, U u) throws DukeException;
}
