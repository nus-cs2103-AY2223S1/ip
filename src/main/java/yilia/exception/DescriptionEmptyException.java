package yilia.exception;

import yilia.Type;

/**
 * Represents an exception to be thrown when the description of a task is empty.
 */
public class DescriptionEmptyException extends Exception {
    private final Type type;
    public DescriptionEmptyException(Type content) {
        this.type = content;
    }
    /**
     * Returns the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of " + (type == Type.EVENT ? "an " : "a ")
                + type.toString().toLowerCase() + " cannot be empty.";
    }
}
