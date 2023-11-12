package anthea.exception;

import java.util.function.Function;

import anthea.ChatbotResponse;

/**
 * Defines an interface for functions that throw ChatbotException.
 *
 * @param <T> Input type.
 */
public interface ChatbotExceptionFunction<T> {
    /**
     * Constructs ChatbotExceptionFunction from Function.
     *
     * @param lambda Function.
     * @param <A> Input type.
     * @return Constructed ChatbotExceptionFunction.
     */
    public static <A> ChatbotExceptionFunction<A> of(Function<A, ChatbotResponse> lambda) {
        assert lambda != null;
        return a -> lambda.apply(a);
    }

    /**
     * Constructs Function from ChatbotExceptionFunction.
     *
     * @param lambda ChatbotExceptionFunction.
     * @param <A> Input type.
     * @return Constructed Function.
     */
    public static <A> Function<A, ChatbotResponse> toFunction(ChatbotExceptionFunction<A> lambda) {
        assert lambda != null;
        return a -> {
            try {
                return lambda.apply(a);
            } catch (ChatbotException exception) {
                return exception.getResponse();
            }
        };
    }

    /**
     * Applies on T such that it allows for throwing of ChatbotException.
     *
     * @return Single-use output ChatbotResponse.
     * @throws ChatbotException Single-use output ChatbotException.
     */
    public ChatbotResponse apply(T t) throws ChatbotException;
}
