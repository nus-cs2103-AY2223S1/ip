package anthea.exception;

import java.util.function.BiFunction;

import anthea.ChatbotResponse;

/**
 * Defines an interface for functions that throw ChatbotException.
 *
 * @param <T> Input type.
 * @param <U> Input type.
 */
public interface ChatbotExceptionBiFunction<T, U> {
    /**
     * Constructs ChatbotExceptionBiFunction from BiFunction.
     *
     * @param lambda BiFunction.
     * @param <A> Input type.
     * @param <B> Input type.
     * @return Constructed ChatbotExceptionBiFunction.
     */
    public static <A, B> ChatbotExceptionBiFunction<A, B> of(BiFunction<A, B, ChatbotResponse> lambda) {
        assert lambda != null;
        return lambda::apply;
    }

    /**
     * Constructs BiFunction from ChatbotExceptionBiFunction.
     *
     * @param lambda ChatbotExceptionFunction.
     * @param <A> Input type.
     * @return Constructed Function.
     */
    public static <A, B> BiFunction<A, B, ChatbotResponse> toBiFunction(ChatbotExceptionBiFunction<A, B> lambda) {
        assert lambda != null;
        return (a, b) -> {
            try {
                return lambda.apply(a, b);
            } catch (ChatbotException exception) {
                return exception.getResponse();
            }
        };
    }

    /**
     * Applies on T, U such that it allows for throwing of ChatbotException.
     *
     * @return Single-use output ChatbotResponse.
     * @throws ChatbotException Single-use output ChatbotException.
     */
    public ChatbotResponse apply(T t, U u) throws ChatbotException;
}
