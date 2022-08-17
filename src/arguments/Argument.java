package arguments;

import input.Input;

/** Represents a CLI argument with type T. Implementors provide own implementation of validation
 * @param <T> Type of the argument expected from CLI
 */
public abstract class Argument<T> {
    protected T value;
    protected Input input;
    protected String argumentName;

    public Argument(Input input, String argumentName) {
        this.input = input;
        this.argumentName = argumentName;
    }

    public abstract void validate() throws IllegalArgumentException;

    // get must be abstract because how a particular Argument extracts a value may be different e.g parseInt vs
    // parsing a datetime string, etc.
    public abstract T getParameter() throws IllegalArgumentException;
}
